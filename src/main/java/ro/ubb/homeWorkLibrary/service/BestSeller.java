package ro.ubb.homeWorkLibrary.service;

public class BestSeller implements Comparable<BestSeller>{

    private Long bookId;
    private int numberOfSales;

    public BestSeller(Long bookId, int numberOfSales) {
        this.bookId = bookId;
        this.numberOfSales = numberOfSales;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    @Override
    public String toString() {
        return "Best Seller book has id= " + bookId +
                " and a number of sales= " + numberOfSales +
                ".";
    }

    @Override
    public int compareTo(BestSeller o) {
        if (this.getNumberOfSales() > o.getNumberOfSales())
            return 1;
        else if (this.getNumberOfSales() < o.getNumberOfSales())
            return -1;
        else
            return 0;
    }
}
