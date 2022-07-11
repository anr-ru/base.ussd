package ru.anr.base.ussd.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
@Disabled
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
        Assertions.assertNotNull(
                ussdRestClient.send("79122437136", "sessionId={sessionId}&pageId={id}&text={otp}",
                        "notNeed", "sendUSSDText", "7890"));
    }

    /**
     * Test USSD
     */
    @Test
    public void testSendSMSText() {

        Assertions.assertNotNull(
                ussdRestClient.send("79122437136", "sessionId={sessionId}&pageId={id}&text={otp}",
                        "notNeed", "sendSMSText", "The password: 7890"));
    }
}
