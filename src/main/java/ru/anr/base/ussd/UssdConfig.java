/**
 * 
 */
package ru.anr.base.ussd;

import org.springframework.context.annotation.Configuration;

/**
 * USSD general configuration.
 *
 *
 * @author Alexey Romanchuk
 * @created Jun 9, 2015
 *
 */
@Configuration
public class UssdConfig {

    /**
     * The login
     */
    private String login;

    /**
     * The password
     */
    private String password;

    /**
     * The name of the service
     */
    private String service;

    /**
     * The url to push
     */
    private String baseUrl;

    /**
     * The host name
     */
    private String hostName;

    /**
     * The port
     */
    private int port = 443;

    /**
     * The server's schema
     */
    private String schema = "https";

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the login
     */
    public String getLogin() {

        return login;
    }

    /**
     * @return the password
     */
    public String getPassword() {

        return password;
    }

    /**
     * @return the service
     */
    public String getService() {

        return service;
    }

    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {

        return baseUrl;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {

        return hostName;
    }

    /**
     * @return the port
     */
    public int getPort() {

        return port;
    }

    /**
     * @return the schema
     */
    public String getSchema() {

        return schema;
    }

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
     * @param hostName
     *            the hostName to set
     */
    public void setHostName(String hostName) {

        this.hostName = hostName;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(int port) {

        this.port = port;
    }

    /**
     * @param schema
     *            the schema to set
     */
    public void setSchema(String schema) {

        this.schema = schema;
    }

}
