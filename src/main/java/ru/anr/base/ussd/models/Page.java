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

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ru.anr.base.BaseParent;

/**
 * 
 * Main page element
 *
 *
 * @author Aleksey Melkov
 * @created Dec 20, 2014
 *
 */
@XmlRootElement(name = "page")
@XmlType(propOrder = { "title", "divs", "navigations", "attributes" })
public class Page extends AbstractMarkupObject {

    /**
     * Serial
     */
    private static final long serialVersionUID = -3357264832992775158L;

    /**
     * Protocol version
     */
    private String version = "2.0";

    /**
     * Page title
     */
    private Title title;

    /**
     * Navigation elements
     */
    private List<Navigation> navigations;

    /**
     * Div elements
     */
    private List<Div> divs;

    /**
     * Attributes
     */
    private List<Attribute> attributes;

    /**
     * Add Navigation element
     * 
     * @return Added navigation element
     */
    public Navigation createNavi() {

        List<Navigation> naviList = BaseParent.list(getNavigations());
        Navigation navi = new Navigation();
        naviList.add(navi);
        setNavigations(naviList);
        return navi;
    }

    /**
     * Create embedded element {@link Div}
     * 
     * @return Created element
     */
    public Div createDiv() {

        List<Div> divList = BaseParent.list(getDivs());
        Div div = new Div();
        divList.add(div);
        setDivs(divList);
        return div;
    }

    /**
     * Add attribute
     *
     * @param name
     *            name
     * @param value
     *            value
     * @return Added attribute
     */
    public Attribute addAttr(String name, String value) {

        List<Attribute> attrsList = BaseParent.list(getAttributes());
        Attribute attr = findAttribute(name, attrsList);
        if (attr == null) {
            attr = new Attribute();
            attr.setName(name);
            attrsList.add(attr);
            setAttributes(attrsList);
        }
        attr.setValue(value);

        return attr;
    }

    /**
     * Create title element
     *
     * @param p
     *            protocol
     * @param value
     *            title text
     * @return Object title
     */
    public Title createTitle(Protocols p, String value) {

        this.title = new Title();
        this.title.setProtocol(p);
        this.title.setValue(value);

        return title;
    }

    /**
     * Find attribute by name
     *
     * @param name
     *            name
     * @return attribute value
     */
    public String findAttribute(String name) {

        List<Attribute> listAttrs = getAttributes();
        Attribute attr = findAttribute(name, listAttrs);

        return attr == null ? null : attr.getValue();
    }

    /**
     * Find attribute by name
     *
     * @param name
     *            name
     * @param listAttrs
     *            list of attributes
     * @return attribute value
     */
    private Attribute findAttribute(String name, List<Attribute> listAttrs) {

        Attribute attr = null;

        if (listAttrs != null && !listAttrs.isEmpty()) {

            for (Object o : listAttrs) {

                Attribute a = (Attribute) o;

                if (name.equals(a.getName())) {
                    attr = a;
                    break;
                }
            }
        }
        return attr;
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the version
     */
    @XmlAttribute(name = "version")
    public String getVersion() {

        return version;
    }

    /**
     * @return the title
     */
    @XmlElement(name = "title")
    public Title getTitle() {

        return title;
    }

    /**
     * @return navigations
     */
    @XmlElement(name = "navigation")
    public List<Navigation> getNavigations() {

        return this.navigations;
    }

    /**
     * @return the divs
     */
    @XmlElement(name = "div")
    public List<Div> getDivs() {

        return divs;
    }

    /**
     * @return the attributes
     */
    @XmlElement(name = "attribute")
    @XmlElementWrapper(name = "attributes")
    public List<Attribute> getAttributes() {

        return attributes;
    }

    /**
     * @param navigations
     *            the navigations to set
     */
    public void setNavigations(List<Navigation> navigations) {

        this.navigations = navigations;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {

        this.version = version;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(Title title) {

        this.title = title;
    }

    /**
     * @param divs
     *            the divs to set
     */
    public void setDivs(List<Div> divs) {

        this.divs = divs;
    }

    /**
     * @param attributes
     *            the attributes to set
     */
    public void setAttributes(List<Attribute> attributes) {

        this.attributes = attributes;
    }
}
