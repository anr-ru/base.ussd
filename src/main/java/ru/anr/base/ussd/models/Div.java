/*
 * Copyright 2014 the original author or authors.
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

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.util.Assert;

import ru.anr.base.BaseParent;

/**
 * 
 * Div Element
 *
 *
 * @author Aleksey Melkov
 * @created Dec 25, 2014
 *
 */
public class Div extends AbstractMarkupObject {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2642560050178183113L;

    /**
     * Value: either empty or sms (you need to override the delivery method, if
     * it can be done the way specified in the protocol)
     */
    private String type;

    /**
     * Div elements
     */
    private List<Div> divs;

    /**
     * Input elements
     */
    private List<Input> inputs;

    /**
     * Select elements
     */
    private List<Select> selects;

    /**
     * Setting param - delivery by SMS
     */
    public void setSMS() {

        setType("sms");
    }

    /**
     * Setting param - Delivery on the selected protocol
     */
    public void setNoSMS() {

        setType(null);
    }

    /**
     * Create a nested div
     * 
     * @return Created element {@link Div}
     */
    public Div createDiv() {

        List<Div> divList = BaseParent.list(getDivs());
        Div div = new Div();
        divList.add(div);
        setDivs(divList);
        return div;
    }

    /**
     * Create a nested div
     * 
     * @param text
     *            text value
     * 
     * @return Created element {@link Div}
     */
    public Div createDiv(String text) {

        List<Div> divList = BaseParent.list(getDivs());
        Div div = new Div();
        div.setValue(text);
        divList.add(div);
        setDivs(divList);
        return div;
    }

    /**
     * Create input
     * 
     * @param name
     *            Input control name
     * @param title
     *            title - prompt for input
     * @param navigation
     *            The ID of the navigation section, the link of which will be
     *            transferred to the entered value.
     * 
     * @return Created element {@link Div}
     */
    public Input createInput(String name, String title, Navigation navigation) {

        List<Input> inputList = BaseParent.list(getInputs());
        Input input = new Input();
        input.setName(name);
        input.setTitle(title);

        assertNavigation(navigation);

        input.setNavigationId(navigation.getId());
        inputList.add(input);
        setInputs(inputList);
        return input;
    }

    /**
     * Create input
     * 
     * @param name
     *            Input control name
     * @param title
     *            title - prompt for input
     * @param navigationId
     *            The ID of the navigation section, the link of which will be
     *            transferred to the entered value.
     * 
     * @return Created element {@link Div}
     */
    public Input createInput(String name, String title, String navigationId) {

        List<Input> inputList = BaseParent.list(getInputs());
        Input input = new Input();
        input.setName(name);
        input.setTitle(title);

        input.setNavigationId(navigationId);
        inputList.add(input);
        setInputs(inputList);
        return input;
    }

    /**
     * Create Select
     * 
     * @param name
     *            Select control name
     * @param title
     *            title - prompt for input
     * @param navigation
     *            The ID of the navigation section, the link of which will be
     *            transferred to the entered value.
     * 
     * @return Created element {@link Div}
     */
    public Select createSelect(String name, String title, Navigation navigation) {

        List<Select> selectList = BaseParent.list(getSelects());
        Select select = new Select();
        select.setName(name);
        select.setTitle(title);

        assertNavigation(navigation);

        select.setNavigationId(navigation.getId());
        selectList.add(select);
        setSelects(selectList);
        return select;
    }

    /**
     * Checking that the navigation unit identifier
     * 
     * @param navigation
     *            Block with navigation
     */
    private void assertNavigation(Navigation navigation) {

        Assert.notNull(navigation.getId(), "Navigation should have an ID");
    }

    // /////////////////////////////////////////////////////////////////////////
    // // getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the type
     */
    @XmlAttribute(name = "type")
    public String getType() {

        return type;
    }

    /**
     * @return the divs
     */
    @XmlElement(name = "div")
    public List<Div> getDivs() {

        return divs;
    }

    /**
     * @return the selects
     */
    @XmlElement(name = "select")
    public List<Select> getSelects() {

        return selects;
    }

    /**
     * @return the inputs
     */
    @XmlElement(name = "input")
    public List<Input> getInputs() {

        return inputs;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {

        this.type = type;
    }

    /**
     * @param divs
     *            the divs to set
     */
    public void setDivs(List<Div> divs) {

        this.divs = divs;
    }

    /**
     * @param inputs
     *            the inputs to set
     */
    public void setInputs(List<Input> inputs) {

        this.inputs = inputs;
    }

    /**
     * @param selects
     *            the selects to set
     */
    public void setSelects(List<Select> selects) {

        this.selects = selects;
    }

}
