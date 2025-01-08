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

import ru.anr.base.BaseParent;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Select Element
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 */
public class Select extends AbstractMarkupObject {

    private static final long serialVersionUID = -8682715505156789152L;

    /**
     * Control name
     */
    private String name;

    /**
     * Control title
     */
    private String title;

    /**
     * The ID of the navigation section, the link of which will be transferred
     * to the entered value.
     */
    private String navigationId;

    /**
     *
     */
    private List<Option> options;

    /**
     * Add value to select
     *
     * @param value        value
     * @param defaultValue true, If a default value (java, wap - version)
     * @param key          Button to select the (digital)
     * @return Created Object (Option)
     */
    public Option addOption(String value, boolean defaultValue, String key) {

        List<Option> optionList = BaseParent.list(getOptions());
        Option option = new Option();
        option.setAccesskey(key);
        option.setSelected(String.valueOf(defaultValue));
        option.setValue(value);
        optionList.add(option);
        setOptions(optionList);
        return option;
    }

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
     * @return the navigationId
     */
    @XmlAttribute(name = "navigationId", required = true)
    public String getNavigationId() {

        return navigationId;
    }

    /**
     * @return the options
     */
    @XmlElement(name = "option")
    public List<Option> getOptions() {

        return this.options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(List<Option> options) {

        this.options = options;
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
     * @param navigationId the navigationId to set
     */
    public void setNavigationId(String navigationId) {

        this.navigationId = navigationId;
    }
}
