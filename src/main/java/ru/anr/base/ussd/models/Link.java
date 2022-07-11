/*
 * Copyright 2014-2022 the original author or authors.
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
 * USSD Link
 *
 * @author Aleksey Melkov
 * @created Dec 24, 2014
 */
public class Link extends AbstractMarkupObject {

    private static final long serialVersionUID = 3646923415281702034L;

    /**
     * ID of the page to navigate (service code on the side of the content
     * provider)
     */
    private String pageId;

    /**
     * The key to press (usually digital)
     */
    private String accesskey;

    /**
     * Type of link - to a page, or back
     */
    private LinkType type = LinkType.page;

    /**
     * Priority link (define the sort order)
     */
    private Integer priority;

    ///////////////////////////////////////////////////////////////////////////
    ///// getters/setters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the pageId
     */
    @XmlAttribute(name = "pageId", required = true)
    public String getPageId() {

        return pageId;
    }

    /**
     * @return the access key
     */
    @XmlAttribute(name = "accesskey")
    public String getAccesskey() {

        return accesskey;
    }

    /**
     * @return the type
     */
    @XmlAttribute(name = "type")
    public LinkType getType() {

        return type;
    }

    /**
     * @return the priority
     */
    @XmlAttribute(name = "priority")
    public Integer getPriority() {

        return priority;
    }

    /**
     * @param pageId the pageId to set
     */
    public void setPageId(String pageId) {

        this.pageId = pageId;
    }

    /**
     * @param accesskey the accesskey to set
     */
    public void setAccesskey(String accesskey) {

        this.accesskey = accesskey;
    }

    /**
     * @param type the type to set
     */
    public void setType(LinkType type) {

        this.type = type;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {

        this.priority = priority;
    }
}
