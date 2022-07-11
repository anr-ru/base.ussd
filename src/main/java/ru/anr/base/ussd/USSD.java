/*
 * Copyright 2014-2022 the original author or authors.
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
package ru.anr.base.ussd;

import java.lang.annotation.*;

/**
 * A special annotation for declarations of USSD commands.
 *
 * @author Alexey Romanchuk
 * @created Jun 19, 2015
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
     * true, if we need to return a blank page (the customer does not receive USSD
     * on their phone).
     *
     * @return true or false
     */
    boolean returnBlank() default false;

    /**
     * Should we check the phone number in the token with the subscriber.
     * Check subscriber equal phone in token
     *
     * @return true or false
     */
    boolean checkSubscriberInToken() default true;
}
