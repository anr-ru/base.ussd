/**
 * 
 */
package ru.anr.base.ussd.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * Attribute element
 *
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 *
 */
public class Attribute implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -9086787513648918271L;

    /**
     * Attribute name
     */
    private String name;

    /**
     * Attribute value
     */
    private String value;

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

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
     * @param name
     *            the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {

        this.value = value;
    }
}
