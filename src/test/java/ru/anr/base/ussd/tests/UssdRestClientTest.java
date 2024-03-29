package ru.anr.base.ussd.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import ru.anr.base.tests.JmsTests;
import ru.anr.base.ussd.UssdRestClient;

import javax.jms.Destination;

/**
 * Description ...
 *
 * @author Aleksey Melkov
 * @created Jan 2, 2015
 */
public class UssdRestClientTest extends AbstractLocalTestCase {

    /**
     * USSD client
     */
    @Autowired
    private UssdRestClient ussdRestClient;

    /**
     * {@link JmsTests}
     */
    @Autowired
    private JmsTests jms;

    /**
     * Test queue to see the results
     */
    @Autowired
    @Qualifier("testQueue")
    private Destination queue;

    /**
     * Extracts the header and body
     *
     * @return Both values as a string
     */
    private String[] extract() {
        @SuppressWarnings("unchecked")
        Message<String> m = (Message<String>) jms.receiveAndConvert(queue);
        Assertions.assertNotNull(m);
        return new String[]{nullSafe(m.getHeaders().get("URL")), m.getPayload()};
    }

    /**
     * Use case : testing a simple case
     */
    @Test
    public void testGet() {

        jms.clean(queue);

        Assertions.assertNotNull(ussdRestClient.send("79122437136", null));

        String[] values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}", values[0]);
        Assertions.assertEquals("[79122437136, service]", values[1]);

        jms.clean(queue);
        // with parameters
        Assertions.assertNotNull(ussdRestClient.send("79122437136", "sessionId={sessionId}&pageId={id}&text={otp}",
                "notNeed", "sendUSSDText", "7890"));

        values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}&sessionId={sessionId}&pageId={id}&text={otp}",
                values[0]);
        Assertions.assertEquals("[79122437136, service, notNeed, sendUSSDText, 7890]", values[1]);
    }

    /**
     * Use case : testing special sending
     */
    @Test
    public void testSendDetailed() {

        jms.clean(queue);
        Assertions.assertNotNull(
                ussdRestClient.sendDetailed("79122437136", "12345", "webx", "p1", "v1", "p2", 23, "p3",
                        d("1.23")));

        String[] values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}&sessionId={sessionId}&pageId={id}&"
                + "p1={value_p1}&p2={value_p2}&p3={value_p3}", values[0]);
        Assertions.assertEquals("[79122437136, service, 12345, webx, v1, 23, 1.23]", values[1]);
    }

    /**
     * Use case : testing special sending - boundary cases
     */
    @Test
    public void testSendDetailedBoundary() {

        // No parameters
        jms.clean(queue);
        Assertions.assertNotNull(ussdRestClient.sendDetailed("79122437136", "12345", "webx"));

        String[] values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}&sessionId={sessionId}&pageId={id}",
                values[0]);
        Assertions.assertEquals("[79122437136, service, 12345, webx]", values[1]);

        // The page is empty
        jms.clean(queue);
        Assertions.assertNotNull(ussdRestClient.sendDetailed("79122437136", "12345", null));

        values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}&sessionId={sessionId}", values[0]);
        Assertions.assertEquals("[79122437136, service, 12345]", values[1]);

        // No session, but the page exists
        jms.clean(queue);
        Assertions.assertNotNull(ussdRestClient.sendDetailed("79122437136", null, "webx"));

        values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}&pageId={id}", values[0]);
        Assertions.assertEquals("[79122437136, service, webx]", values[1]);

        // No session, no page
        jms.clean(queue);
        Assertions.assertNotNull(ussdRestClient.sendDetailed("79122437136", null, null));

        values = extract();
        Assertions.assertEquals("base?subscriber={subscriber}&service={service}", values[0]);
        Assertions.assertEquals("[79122437136, service]", values[1]);
    }
}
