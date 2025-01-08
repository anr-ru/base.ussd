/*
 * Copyright 2014-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ru.anr.base.services.ussd.render;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ru.anr.base.BaseParent;
import ru.anr.base.ussd.models.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A render object to build USSD menus.
 *
 * @author Alexey Romanchuk
 * @created Jun 19, 2015
 */
public class RenderContext extends BaseParent {

    public static final String SESSION_ID = "sessionId";

    /**
     * The page
     */
    private final Page page;

    /**
     * The current {@link Navigation} object
     */
    private Navigation navi;

    /**
     * Construction of the render
     *
     * @param page The page
     */
    public RenderContext(Page page) {
        super();
        this.page = page;
    }

    /**
     * Returns the ID
     *
     * @return The id of the page
     */
    public String id() {
        return page.getId();
    }

    /**
     * Returns the title of the page
     *
     * @return The title
     */
    public String getTitle() {
        Title t = page.getTitle();
        return nullSafeOp(t.getValue()).orElse("");
    }

    /**
     * Sets the title for the page
     *
     * @param title The title
     */
    public void setTitle(String title) {
        Title t = page.getTitle();
        if (t == null) {
            page.createTitle(Protocols.ussd, title);
        } else {
            t.setValue(title);
        }
    }

    /**
     * Set the session ID
     *
     * @param session The session identifier
     */
    public void setSession(String session) {
        page.addAttr(SESSION_ID, session);
    }

    /**
     * Returns the current session ID
     *
     * @return session The session identifier
     */
    public String getSession() {
        return page.findAttribute(SESSION_ID);
    }

    /**
     * Builds the plain text div
     *
     * @param text The text
     * @return A newly created DIV (to set additional parameters)
     */
    public Div plainText(String text) {
        Div div = page.createDiv();
        div.setValue(text);
        return div;
    }

    /**
     * Creates a new link with {@link Navigation} section
     *
     * @param id     The identifier of {@link Navigation}
     * @param ref    The link url
     * @param key    The key to press
     * @param text   The title text
     * @param params Additional parameters added to the url
     * @return A new link object
     */
    public Link link(String id, String ref, String key, String text, Object... params) {
        navi = page.createNavi();
        navi.setId(id);
        return addLink(ref, key, text, params);
    }

    /**
     * Adds a link to the current (last) {@link Navigation}
     *
     * @param ref    The reference (url)
     * @param key    The key to press
     * @param text   The title text
     * @param params Additional parameters
     * @return A new link object
     */
    private Link addLink(String ref, String key, String text, Object... params) {

        String s = getSession();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        if (s != null) {
            map.put(SESSION_ID, s);
        }
        map.putAll(toMap(params));
        String url = ref;

        if (!CollectionUtils.isEmpty(map)) {
            List<NameValuePair> list = map.entrySet()
                    .stream()
                    .map(e -> new BasicNameValuePair(e.getKey(), nullSafe(e.getValue())))
                    .collect(Collectors.toList());
            url += "?" + URLEncodedUtils.format(list, "utf8");
        }

        Link l = navi.addLink(url, key);
        if (text != null) {
            l.setValue(text);
        }
        return l;
    }

    /**
     * Creates an input linked with the current {@link Navigation}
     *
     * @param name  The name of field
     * @param title The title (label)
     * @return A new object
     */
    public Input input(String name, String title) {

        Div div = page.createDiv();
        Assert.notNull(navi, "Navigation not specified");

        Input in = div.createInput(name, title, navi);
        in.setType(InputTypes.text);
        return in;
    }

    /**
     * Creates an input linked with the specified by id navigation
     *
     * @param name  The name of field
     * @param title The title (label)
     * @param navId The identifier of the navigation
     * @return A new object
     */
    public Input input(String name, String title, String navId) {
        Div div = page.createDiv();
        Input in = div.createInput(name, title, navId);
        in.setType(InputTypes.text);
        return in;
    }
}
