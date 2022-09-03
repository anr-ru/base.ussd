package ru.anr.base.ussd.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.anr.base.ussd.UssdRestClient;

/**
 * Description ...
 *
 * @author Aleksey Melkov
 * @created Jan 2, 2015
 */
@ActiveProfiles("production")
@Ignore
public class UssdRealTest extends AbstractLocalTestCase {

    /**
     * USSD client
     */
    @Autowired
    private UssdRestClient ussdRestClient;

    /**
     * Test USSD
     */
    @Test
    public void testSendUSSDText() {
        Assert.assertNotNull(
                ussdRestClient.send("79122437136", "sessionId={sessionId}&pageId={id}&text={otp}",
                        "notNeed", "sendUSSDText", "7890"));
    }

    /**
     * Test USSD
     */
    @Test
    public void testSendSMSText() {
        Assert.assertNotNull(
                ussdRestClient.send("79122437136", "sessionId={sessionId}&pageId={id}&text={otp}",
                        "notNeed", "sendSMSText", "The password: 7890"));
    }
}
