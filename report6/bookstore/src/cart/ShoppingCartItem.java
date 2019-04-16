/*
 * 
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc. Use is
 * subject to license terms.
 *  
 */

package cart;

import database.BookDetails;

public class ShoppingCartItem {
	BookDetails item;

    int quantity;

    public ShoppingCartItem(BookDetails anItem) {
        item = anItem;
        quantity = 1;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public BookDetails getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
