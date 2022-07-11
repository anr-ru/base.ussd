package ru.anr.base.ussd.tests;

import org.springframework.messaging.Message;
import org.springframework.util.Assert;
import org.springframework.web.util.UriTemplate;
import ru.anr.base.BaseParent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple wrapper around a USSD message.
 *
 * @author Alexey Romanchuk
 * @created May 24, 2016
 */
public class USSDMessage extends BaseParent {

    /**
     * Parameters of the message
     */
    private final List<String> params;

    /**
     * Representation as a map
     */
    private final Map<String, String> map = toMap();

    /**
     * The url where to send
     */
    private final String url;

    /**
     * @param m A JMS Message read from the test queue
     */
    public USSDMessage(Message<String> m) {
        super();
        this.params = list(m.getPayload().substring(1, m.getPayload().length() - 1).split(","))
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());

        this.url = nullSafeOp((String) m.getHeaders().get("URL")).orElse("");

        UriTemplate t = new UriTemplate(url);
        t.expand(params.toArray(new Object[]{})); // to check matching

        List<String> names = t.getVariableNames();
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i), params.get(i));
        }
    }

    /**
     * @return the params
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param name The name of a parameter
     * @return the map
     */
    public String getParam(String name) {
        Assert.isTrue(map.containsKey(name), "The parameter '" + name + "' not found");
        return map.get(name);
    }
}
