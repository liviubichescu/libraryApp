package Domain;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publishHouse;
    private int relesedYear;
    private double price;

    public Book(int id, String title, String author, String publishHouse, int relesedYear, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishHouse = publishHouse;
        this.relesedYear = relesedYear;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse;
    }

    public int getRelesedYear() {
        return relesedYear;
    }

    public void setRelesedYear(int relesedYear) {
        this.relesedYear = relesedYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                relesedYear == book.relesedYear &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publishHouse, book.publishHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publishHouse, relesedYear, price);
    }

    @Override
    public String toString() {
        return "Books in store {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishHouse='" + publishHouse + '\'' +
                ", relesedYear=" + relesedYear +
                ", price=" + price +
                '}';
    }
}
