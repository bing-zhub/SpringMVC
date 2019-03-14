/*
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package exception;

/**
 * This application exception indicates that an order cannot be completed.
 */

public class OrderException extends Exception {

    public OrderException() {
    }

    public OrderException(String msg) {
        super(msg);
    }
}
