package ru.anr.base.services.ussd.render;

import org.junit.Assert;
import org.junit.Test;
import ru.anr.base.services.serializer.XMLSerializerImpl;
import ru.anr.base.ussd.models.Page;
import ru.anr.base.ussd.tests.AbstractLocalTestCase;

/**
 * Tests for the Ussd Render
 */
public class RenderContextTest extends AbstractLocalTestCase {

    @Test
    public void link() {

        Page root = new Page();

        RenderContext ctx = new RenderContext(root);
        ctx.setSession("session");

        ctx.link("id", "ref", "key", "text", "p1", "v1", "p2", "v2");
        ctx.setTitle("Title");
        ctx.input("in", "Enter it", "id");

        XMLSerializerImpl xml = new XMLSerializerImpl();

        String strXml = xml.toStr(root);
        Assert.assertEquals("<?xml version='1.0' encoding='UTF-8'?>" +
                "<page version=\"2.0\">" +
                "<title protocol=\"ussd\">Title</title><div><input name=\"in\" title=\"Enter it\" type=\"text\" navigationId=\"id\"/></div>" +
                "<navigation id=\"id\">" +
                "<link pageId=\"ref?sessionId=session&amp;p1=v1&amp;p2=v2\" accesskey=\"key\" " +
                "type=\"page\">text</link>" +
                "</navigation><attributes><attribute name=\"sessionId\" value=\"session\"/></attributes></page>", strXml);
    }
}
