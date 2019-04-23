package cart;

import bean.BookDetails;

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
