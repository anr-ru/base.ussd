/*
 * Copyright 2014-2025 the original author or authors.
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

import org.springframework.util.StringUtils;
import ru.anr.base.BaseParent;
import ru.anr.base.domain.api.models.RequestModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * The base markup object.
 *
 * @author Aleksey Melkov
 * @created Jan 2, 2015
 */
public abstract class AbstractMarkupObject extends RequestModel {

    private static final long serialVersionUID = 2455327797834790851L;

    private String value;

    private String id;

    /**
     * Protocol: one value, or some through gap
     */
    private String protocol;

    /**
     * Adds additional (or main) protocol
     *
     * @param p protocol
     */
    public void addProtocol(Protocols p) {
        if (StringUtils.hasLength(protocol)) {
            protocol = protocol.concat(" " + p.name());
        } else {
            setProtocol(p);
        }
    }

    /**
     * @param p the protocol to set
     */
    @XmlTransient
    public void setProtocol(Protocols p) {
        setProtocol(p.name());
    }

    ///////////////////////////////////////////////////////////////////////////
    ///// getters/setters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the value
     */
    @XmlValue
    public String getValue() {
        return value;
    }

    /**
     * @return the id
     */
    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    /**
     * @return the protocol
     */
    @XmlAttribute(name = "protocol")
    public String getProtocol() {

        return protocol;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * @param protocol the protocol to set
     */
    private void setProtocol(String protocol) {

        this.protocol = protocol;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {

        this.value = value;
    }

    /**
     * Find elements by class
     *
     * @param items - list items
     * @param clazz class
     * @return list elements
     */
    protected List<Object> findOfClass(List<Object> items, Class<?> clazz) {

        List<Object> rs = new ArrayList<>();
        List<Object> list = BaseParent.list(items);

        for (Object o : list) {

            if (clazz.isInstance(o)) {
                rs.add(o);
            }
        }
        return rs;
    }

}
