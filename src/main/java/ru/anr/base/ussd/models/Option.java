/*
 * Copyright 2014 the original author or authors.
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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * Option Element
 *
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 *
 */
public class Option implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -1918201442128395665L;

    /**
     * Param value
     */
    private String value;

    /**
     * true/false - determines whether the value is the default value
     */
    private String selected;

    /**
     * Numeric buttons to select
     */
    private String accesskey;

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the value
     */
    @XmlAttribute(name = "value", required = true)
    public String getValue() {

        return value;
    }

    /**
     * @return the selected
     */
    @XmlAttribute(name = "selected")
    public String getSelected() {

        return selected;
    }

    /**
     * @return the accesskey
     */
    @XmlAttribute(name = "accesskey", required = true)
    public String getAccesskey() {

        return accesskey;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {

        this.value = value;
    }

    /**
     * @param selected
     *            the selected to set
     */
    public void setSelected(String selected) {

        this.selected = selected;
    }

    /**
     * @param accesskey
     *            the access key to set
     */
    public void setAccesskey(String accesskey) {

        this.accesskey = accesskey;
    }
}
