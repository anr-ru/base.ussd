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

import ru.anr.base.BaseParent;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Navigation Element
 *
 * @author Aleksey Melkov
 * @created Dec 24, 2014
 */
public class Navigation extends AbstractMarkupObject {

    private static final long serialVersionUID = 2944454040102412837L;

    /**
     * link element
     */
    private List<Link> links;

    /**
     * Add Link Element
     *
     * @param pageId    Code page for link
     * @param accesskey Code keys to perform
     * @return Object - Link
     */
    public Link addLink(String pageId, String accesskey) {

        List<Link> linkList = BaseParent.list(getLinks());
        Link link = new Link();
        link.setAccesskey(accesskey);
        link.setPageId(pageId);
        linkList.add(link);
        setLinks(linkList);
        return link;
    }

    ///////////////////////////////////////////////////////////////////////////
    ///// getters/setters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the links
     */
    @XmlElement(name = "link")
    public List<Link> getLinks() {

        return this.links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<Link> links) {

        this.links = links;
    }
}
