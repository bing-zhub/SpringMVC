package bean;

public class BookDetails implements Comparable {
    private String bookId = null;

    private String title = null;

    private String firstName = null;

    private String surname = null;

    private float price = 0.0F;

    private int year = 0;

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    private String description = null;

    private int inventory = 0;

    public BookDetails(){}

    public BookDetails(String bookId, String surname, String firstName,
            String title, float price, int year, String description,
            int inventory) {
        this.bookId = bookId;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.price = price;
        this.year = year;
        this.description = description;
        this.inventory = inventory;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getBookId() {
        return this.bookId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getInventory() {
        return this.inventory;
    }

    public int compareTo(Object o) {
        BookDetails n = (BookDetails) o;
        int lastCmp = title.compareTo(n.title);
        return (lastCmp);
    }
}
