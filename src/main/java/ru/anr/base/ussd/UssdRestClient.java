/**
 * 
 */
package ru.anr.base.ussd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsOperations;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import ru.anr.base.BaseSpringParent;
import ru.anr.base.facade.web.api.RestClient;

/**
 * USSD Client implementation.
 *
 *
 * @author Aleksey Melkov
 * @created Jan 2, 2015
 *
 */

public class UssdRestClient extends BaseSpringParent {

    /**
     * The logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UssdRestClient.class);

    /**
     * Login
     */
    private String login;

    /**
     * password
     */
    private String password;

    /**
     * Name of service
     */
    private String service;

    /**
     * BaseUrl part
     */
    private String baseUrl;

    /**
     * UssdConfig
     */
    private UssdConfig ussdConfig;

    /**
     * Reference to client implementation
     */
    private final UssdRest client;

    /**
     * Request data stored for test purpose (in 'test' mode we actually don't
     * send a request).
     */
    @Autowired(required = false)
    @Qualifier("testQueue")
    private Destination testQueue;

    /**
     * Reference to {@link JmsOperations}
     */
    @Autowired(required = false)
    private JmsOperations jms;

    /**
     * Inner class - a rest client with reconfigured headers
     */
    public class UssdRest extends RestClient {

        /**
         * Default constructor
         * 
         * @param schema
         *            The schema
         * 
         */
        protected UssdRest(String schema) {

            super(schema);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected HttpHeaders applyHeaders() {

            HttpHeaders hh = super.applyHeaders();
            hh.add("X-Authentication-Name", login);
            hh.add("X-Authentication-Password", password);
            return hh;
        }

    }

    /**
     * Default constructor
     * 
     * @param ussdConfig
     *            Ussd Config
     */
    public UssdRestClient(UssdConfig ussdConfig) {

        super();

        this.setUssdConfig(ussdConfig);

        client = new UssdRest(this.ussdConfig.getSchema());

        client.setHost(this.ussdConfig.getHostName());
        client.setPort(this.ussdConfig.getPort());
        client.setAccept(MediaType.APPLICATION_XML);
        client.setContentType(MediaType.APPLICATION_XML);

        setLogin(this.ussdConfig.getLogin());
        setPassword(this.ussdConfig.getPassword());
        setBaseUrl(this.ussdConfig.getBaseUrl());
        setService(this.ussdConfig.getService());
    }

    /**
     * Sends a USSD command with extracted sessionId and pageId (common used
     * parameters)
     * 
     * @param subscriber
     *            The phone number
     * @param sessionId
     *            The sessionId
     * @param pageId
     *            The pageId
     * @param paramPairs
     *            A list of additional parameters
     * @return true, if sending has been successful
     */
    public String sendDetailed(String subscriber, Object sessionId, String pageId, Object... paramPairs) {

        final StringBuilder url = new StringBuilder(41);
        List<Object> objects = list();

        if (sessionId != null) {
            url.append("sessionId={sessionId}");
            objects.add(sessionId);
        }
        if (pageId != null) {
            ends(url);
            url.append("pageId={id}");
            objects.add(pageId);
        }

        Map<String, Object> params = toMap(paramPairs);
        params.forEach((k, v) -> {
            ends(url);
            url.append(k + "={value_" + k + "}");
            objects.add(v);
        });
        return send(subscriber, url.toString(), objects.toArray(new Object[objects.size()]));
    }

    /**
     * Adds the '&' symbol to the specified StringBuilder
     * 
     * @param s
     *            The String Builder
     * @return The String builder with the added value
     */
    private StringBuilder ends(StringBuilder s) {

        return (s.length() == 0) ? s : s.append('&');
    }

    /**
     * Sends USSD menu initiated by our server
     * 
     * @param subscriber
     *            A user's mobile number
     * @param url
     *            Additional parameters as url part
     * @param params
     *            Parameters values
     * @return true, if sending has been successful
     */
    public String send(String subscriber, String url, Object... params) {

        List<Object> all = list(subscriber, service);

        StringBuilder s = new StringBuilder(baseUrl);
        s.append("?subscriber={subscriber}&service={service}");

        if (url != null && !url.contains("sessionId")) {
            Authentication token = SecurityContextHolder.getContext().getAuthentication();
            if (token instanceof OAuth2Authentication) {
                OAuth2AuthenticationDetails details =
                        (OAuth2AuthenticationDetails) ((OAuth2Authentication) token).getDetails();
                all.add(details.getTokenValue());
                s.append("&sessionId={sessionId}");
            }
        }

        if (params != null && params.length > 0) {
            all.addAll(list(params));
        }

        if (StringUtils.hasLength(url)) {
            s.append('&');
            s.append(url);
        }
        return internalSend(subscriber, s.toString(), all);
    }

    /**
     * Performs sending in the case of production configuration. If it's not the
     * production mode, it just writes a message to a log.
     * 
     * @param subscriber
     *            The phone
     * @param url
     *            the url in the query
     * @param params
     *            Parameters of the query
     * @return Resulted state (Queued in case of success)
     */
    private String internalSend(String subscriber, String url, List<Object> params) {

        Map<String, String> map = new HashMap<String, String>();
        String result = null;
        logger.info("USSD sending details: to {}, url: {}, params: {}", subscriber, url, params);

        if (isProdMode()) {
            if (!waitCondition(30, 2000, true, args -> {
                String res = waitSend(url, params);
                map.put("result", res);
                return !HttpStatus.BAD_REQUEST.name().equals(res);
            })) {
                result = map.get("result");
            }

        } else {
            result = "Queued";
            if (testQueue != null) {

                Map<String, Object> hh = toMap("PHONE", subscriber, "URL", url);
                Message<String> msg = new GenericMessage<String>(params.toString(), hh);

                jms.convertAndSend(testQueue, msg);

                logger.debug("Sent a message: {}", msg);
            }
        }
        return result;
    }

    /**
     * Performs for wait sending.
     * 
     * @param url
     *            the url in the query
     * @param params
     *            Parameters of the query
     * @return Resulted state (Queued in case of success)
     */
    private String waitSend(String url, List<Object> params) {

        String result = null;
        try {
            ResponseEntity<String> r = client.get(url, params.toArray(new Object[params.size()]));
            result = r.getBody();
        } catch (HttpClientErrorException ex) {
            if (HttpStatus.BAD_REQUEST.equals(ex.getStatusCode())) {
                result = HttpStatus.BAD_REQUEST.name();
            }
            logger.error("USSD client error: {}({}) - {}", ex.getStatusCode(), ex.getStatusText(),
                    ex.getResponseBodyAsString());
        } catch (HttpServerErrorException ex) {
            logger.error("USSD server error: {}({}) - {}", ex.getStatusCode(), ex.getStatusText(),
                    ex.getResponseBodyAsString());
        }
        return result;
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(String service) {

        this.service = service;
    }

    /**
     * @param baseUrl
     *            the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {

        this.baseUrl = baseUrl;
    }

    /**
     * @return the client
     */
    public UssdRest getClient() {

        return client;
    }

    /**
     * @param ussdConfig
     *            the ussdConfig to set
     */
    public void setUssdConfig(UssdConfig ussdConfig) {

        this.ussdConfig = ussdConfig;
    }
}
