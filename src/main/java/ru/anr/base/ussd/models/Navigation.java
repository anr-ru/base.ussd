/**
 * 
 */
package ru.anr.base.ussd.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import ru.anr.base.BaseParent;

/**
 * 
 * Navigation element
 *
 *
 * @author Aleksey Melkov
 * @created Dec 24, 2014
 *
 */
public class Navigation extends AbstractMarkupObject {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2944454040102412837L;

    /**
     * link element
     */
    private List<Link> links;

    /**
     * Add Link Element
     * 
     * @param pageId
     *            Code page for link
     * @param accesskey
     *            Code keys to perform
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

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the links
     */
    @XmlElement(name = "link")
    public List<Link> getLinks() {

        return this.links;
    }

    /**
     * @param links
     *            the links to set
     */
    public void setLinks(List<Link> links) {

        this.links = links;
    }

}
