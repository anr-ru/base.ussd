/**
 * 
 */
package ru.anr.base.ussd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A special annotation for declarative settings for USSD commands
 *
 *
 * @author Alexey Romanchuk
 * @created Jun 19, 2015
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface USSD {

    /**
     * The code in menu to return after selection 'Back' button
     * 
     * @return The code
     */
    String previousCode() default "ussd.menu";

    /**
     * true if need return blank page (customer not receive ussd on phone)
     * 
     * @return true or false
     */
    boolean returnBlank() default false;

    /**
     * Check subscriber equal phone in token
     * 
     * @return true or false
     */
    boolean checkSubscriberInToken() default true;
}
