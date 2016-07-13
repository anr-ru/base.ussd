/**
 * 
 */
package ru.anr.base.services.ussd.render;

import ru.anr.base.ApplicationException;

/**
 * An exception type for processing USSD-specific errors. In some cases we need
 * to query for a pin (if authentication failed or expired).
 *
 *
 * @author Alexey Romanchuk
 * @created Jun 22, 2015
 *
 */

public class UssdException extends ApplicationException {

    /**
     * 
     */
    private static final long serialVersionUID = 5619906877557039739L;

    /**
     * Query for a pin after exception?
     */
    private final boolean pin;

    /**
     * Constructor
     * 
     * @param msg
     *            A message for a user
     * @param pin
     *            true, if query for a pin
     */
    public UssdException(String msg, boolean pin) {

        super(msg);
        this.pin = pin;
    }

    /**
     * Constructor
     * 
     * @param msg
     *            A message for a user
     * @param pin
     *            true, if query for a pin
     */
    public UssdException(String msg) {

        super(msg);
        this.pin = false;
    }

    /**
     * Constructor in traces of exception
     * 
     * @param msg
     *            A message to show for a user
     * @param pin
     *            true, if a pin code is required to enter again
     * @param cause
     *            The original cause of the exception
     */
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
