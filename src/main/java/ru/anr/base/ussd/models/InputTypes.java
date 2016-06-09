/**
 * 
 */
package ru.anr.base.ussd.models;

import javax.xml.bind.annotation.XmlEnum;

/**
 * 
 * Input Types
 *
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 *
 */
@XmlEnum(String.class)
public enum InputTypes {

    /**
     * Text entry field (string)
     */
    text,
    /**
     * Hidden field
     */
    hidden,
    /**
     * Input field for numbers
     */
    number;
}
