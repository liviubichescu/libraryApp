package ro.ubb.homeWorkLibrary.Domain;

import java.util.Objects;

public class Book extends BaseEntity<Long>{
    private String title;
    private String author;
    private String publishHouse;
    private int relesedYear;
    private double price;

    public Book() {
    }
    public Book(Long id, String title, String author, String publishHouse, int relesedYear, double price) {
        super(id);
        this.title = title;
        this.author = author;
        this.publishHouse = publishHouse;
        this.relesedYear = relesedYear;
        this.price = price;
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
    public String toString() {
        return "Book{" +
                " id =' " + super.getId()+ '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishHouse='" + publishHouse + '\'' +
                ", relesedYear=" + relesedYear +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return relesedYear == book.relesedYear &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publishHouse, book.publishHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),title, author, publishHouse, relesedYear, price);
    }
}
