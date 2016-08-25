/**
 * 
 */
package ru.anr.base.services.ussd.render;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import ru.anr.base.BaseParent;
import ru.anr.base.ussd.models.Div;
import ru.anr.base.ussd.models.Input;
import ru.anr.base.ussd.models.InputTypes;
import ru.anr.base.ussd.models.Link;
import ru.anr.base.ussd.models.Navigation;
import ru.anr.base.ussd.models.Page;
import ru.anr.base.ussd.models.Protocols;
import ru.anr.base.ussd.models.Title;

/**
 * The render context object.
 *
 *
 * @author Alexey Romanchuk
 * @created Jun 19, 2015
 *
 */

public class RenderContext extends BaseParent {

    /**
     * sessionId
     */
    public static final String SESSION_ID = "sessionId";

    /**
     * sessionId
     */
    public static final String SESSION_ID_FOR_USSD_SEND = String.format("%s={%s}", SESSION_ID, SESSION_ID);

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
     * @param page
     *            The page
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
        return (t == null) ? "" : t.getValue();
    }

    /**
     * Sets the title for the page
     * 
     * @param title
     *            The title
     */
    public void setTitle(String title) {

        Title t = page.getTitle();
        if (t == null) {
            t = page.createTitle(Protocols.ussd, title);
        } else {
            t.setValue(title);
        }
    }

    /**
     * Applies for a new session
     *
     * @param session
     *            The session identifier
     */
    public void setSession(String session) {

        page.addAttr(SESSION_ID, session);
    }

    /**
     * get a session
     *
     * @return session The session identifier
     */
    public String getSession() {

        return page.findAttribute(SESSION_ID);
    }

    /**
     * Builds the plain text div
     *
     * @param text
     *            The text
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
     * @param id
     *            The identifier of {@link Navigation}
     * @param ref
     *            The link url
     * @param key
     *            The key to press
     * @param text
     *            The title text
     * @param params
     *            Additional parameters added to the url
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
     * @param ref
     *            The reference (url)
     * @param key
     *            The key to press
     * @param text
     *            The title text
     * @param params
     *            Additional parameters
     * @return A new link object
     */
    public Link addLink(String ref, String key, String text, Object... params) {

        String s = getSession();
        Map<String, Object> map = toMap();

        if (s != null) {
            map.put(SESSION_ID, s);
        }
        map.putAll(toMap(params));
        String url = ref;

        if (!CollectionUtils.isEmpty(map)) {

            List<NameValuePair> list = list(map.entrySet().stream().map(//
                    e -> new BasicNameValuePair(e.getKey(), nullSafe(e.getValue()))));

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
     * @param name
     *            The name of field
     * @param title
     *            The title (label)
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
     * @param name
     *            The name of field
     * @param title
     *            The title (label)
     * @param navId
     *            The identifier of the navigation
     * @return A new object
     */
    public Input input(String name, String title, String navId) {

        Div div = page.createDiv();

        Input in = div.createInput(name, title, navId);
        in.setType(InputTypes.text);
        return in;
    }

}
