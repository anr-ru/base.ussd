/**
 * 
 */
package ru.anr.base.ussd.models;

import javax.xml.bind.annotation.XmlEnum;

/**
 * 
 * Link Type
 *
 *
 * @author Aleksey Melkov
 * @created Dec 24, 2014
 *
 */
@XmlEnum(String.class)
public enum LinkType {

    /**
     * Return Link Type
     */
    back,
    /**
     * Page Link Type
     */
    page;
}
