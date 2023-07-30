/*
 * Copyright 2014-2023 the original author or authors.
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

package ru.anr.base.services.ussd.render;

import ru.anr.base.ApplicationException;

/**
 * An exception type for processing USSD-specific errors. In some cases we need
 * to query for a pin (if authentication failed or expired).
 *
 * @author Alexey Romanchuk
 * @created Jun 22, 2015
 */

public class UssdException extends ApplicationException {

    private static final long serialVersionUID = 5619906877557039739L;

    /**
     * Query for the PIN after exception?
     */
    private final boolean pin;

    public UssdException(String msg, boolean pin) {
        super(msg);
        this.pin = pin;
    }

    public UssdException(String msg) {
        super(msg);
        this.pin = false;
    }

    public UssdException(String msg, boolean pin, Throwable cause) {
        super(msg, cause);
        this.pin = pin;
    }

    /**
     * @return the pin
     */
    public boolean isPin() {
        return pin;
    }
}
