/**
 * 
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
