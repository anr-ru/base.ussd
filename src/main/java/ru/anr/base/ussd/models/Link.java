/**
 * 
 */
package ru.anr.base.ussd.models;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * Ussd Link
 *
 *
 * @author Aleksey Melkov
 * @created Dec 24, 2014
 *
 */
public class Link extends AbstractMarkupObject {

    /**
     * Serial ID
     */
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

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

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
     * @param pageId
     *            the pageId to set
     */
    public void setPageId(String pageId) {

        this.pageId = pageId;
    }

    /**
     * @param accesskey
     *            the accesskey to set
     */
    public void setAccesskey(String accesskey) {

        this.accesskey = accesskey;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(LinkType type) {

        this.type = type;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(Integer priority) {

        this.priority = priority;
    }
}
