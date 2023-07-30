/*
 * Copyright 2014-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ru.anr.base.ussd;

import org.springframework.context.annotation.Configuration;

/**
 * The USSD general configuration.
 *
 * @author Alexey Romanchuk
 * @created Jun 9, 2015
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

    ///////////////////////////////////////////////////////////////////////////
    ///// getters/setters
    ///////////////////////////////////////////////////////////////////////////

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
     * @param login the login to set
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {

        this.service = service;
    }

    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {

        this.baseUrl = baseUrl;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {

        this.hostName = hostName;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {

        this.port = port;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(String schema) {

        this.schema = schema;
    }

}
