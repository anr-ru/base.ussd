/**
 * 
 */
package ru.anr.base.ussd.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import org.springframework.util.StringUtils;

import ru.anr.base.BaseParent;
import ru.anr.base.domain.api.models.RequestModel;

/**
 * 
 * AbstractMarkupObject class
 * <p>
 * Base object for ussd
 *
 * @author Aleksey Melkov
 * @created Jan 2, 2015
 */
public abstract class AbstractMarkupObject extends RequestModel {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2455327797834790851L;

    /**
     * Value
     */
    private String value;

    /**
     * Id
     */
    private String id;

    /**
     * Protocol: one value, or some through gap
     */
    private String protocol;

    /**
     * 
     * Add additional (or main) protocol
     * 
     * @param p
     *            protocol
     */
    public void addProtocol(Protocols p) {

        if (StringUtils.hasLength(protocol)) {
            protocol = protocol.concat(" " + p.name());
        } else {
            setProtocol(p);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCode() {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCode(Integer code) {

        // Do nothing
    }

    /**
     * 
     * @param p
     *            the protocol to set
     */
    @XmlTransient
    public void setProtocol(Protocols p) {

        setProtocol(p.name());
    }

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the value
     */
    @XmlValue
    public String getValue() {

        return value;
    }

    /**
     * @return the id
     */
    @XmlAttribute(name = "id")
    public String getId() {

        return id;
    }

    /**
     * @return the protocol
     */
    @XmlAttribute(name = "protocol")
    public String getProtocol() {

        return protocol;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * @param protocol
     *            the protocol to set
     */
    private void setProtocol(String protocol) {

        this.protocol = protocol;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {

        this.value = value;
    }

    /**
     * Find elements by class
     * 
     * @param items
     *            - list items
     * @param clazz
     *            class
     * @return list elements
     */
    protected List<Object> findOfClass(List<Object> items, Class<?> clazz) {

        List<Object> rs = new ArrayList<Object>();
        List<Object> list = BaseParent.list(items);

        for (Object o : list) {

            if (clazz.isInstance(o)) {
                rs.add(o);
            }
        }
        return rs;
    }

}
