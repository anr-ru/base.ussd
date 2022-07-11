package ru.anr.base.ussd.tests;

import org.junit.jupiter.api.Disabled;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.anr.base.tests.BaseTestCase;

/**
 * The base testcase.
 *
 * @author Alexey Romanchuk
 * @created Jun 17, 2015
 */
@ActiveProfiles("test")
@ContextConfiguration(locations = {"classpath:/tests-local-context.xml"}, inheritLocations = false)
@Disabled
public class AbstractLocalTestCase extends BaseTestCase {

}
