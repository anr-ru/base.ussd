/**
 * 
 */
package ru.anr.base.ussd.models;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * Input Element
 *
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 *
 */
public class Input extends AbstractMarkupObject {

    /**
     * Serial ID
     */
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

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

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
     * @param name
     *            the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {

        this.title = title;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(InputTypes type) {

        this.type = type;
    }

    /**
     * @param navigationId
     *            the navigationId to set
     */
    public void setNavigationId(String navigationId) {

        this.navigationId = navigationId;
    }
}
