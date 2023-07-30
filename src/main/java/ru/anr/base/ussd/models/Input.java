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
package ru.anr.base.ussd.models;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * The Input Element
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 */
public class Input extends AbstractMarkupObject {

    private static final long serialVersionUID = -8682715505156789152L;

    /**
     * Control Name
     */
    private String name;

    /**
     * Title for field
     */
    private String title;

    /**
     * Control Type
     */
    private InputTypes type = InputTypes.text;

    /**
     * The ID of the navigation section, the link of which will be transferred
     * to the entered value.
     */
    private String navigationId;

    ///////////////////////////////////////////////////////////////////////////
    ///// getters/setters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the name
     */
    @XmlAttribute(name = "name", required = true)
    public String getName() {

        return name;
    }

    /**
     * @return the title
     */
    @XmlAttribute(name = "title", required = true)
    public String getTitle() {

        return title;
    }

    /**
     * @return the type
     */
    @XmlAttribute(name = "type")
    public InputTypes getType() {

        return type;
    }

    /**
     * @return the navigationId
     */
    @XmlAttribute(name = "navigationId", required = true)
    public String getNavigationId() {

        return navigationId;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {

        this.title = title;
    }

    /**
     * @param type the type to set
     */
    public void setType(InputTypes type) {

        this.type = type;
    }

    /**
     * @param navigationId the navigationId to set
     */
    public void setNavigationId(String navigationId) {

        this.navigationId = navigationId;
    }
}
