/*
 * Copyright 2014-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ru.anr.base.ussd.models;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * The attribute element
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 */
public class Attribute implements Serializable {

    private static final long serialVersionUID = -9086787513648918271L;

    /**
     * Attribute name
     */
    private String name;

    /**
     * Attribute value
     */
    private String value;

    ///////////////////////////////////////////////////////////////////////////
    ///// getters/setters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the name
     */
    @XmlAttribute(name = "name")
    public String getName() {

        return name;
    }

    /**
     * @return the value
     */
    @XmlAttribute(name = "value")
    public String getValue() {

        return value;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {

        this.value = value;
    }
}
