package ro.ubb.homeWorkLibrary.service;

/** parcurgem lista de vanzari
 * punem id-ul fiecarei carti vandute intr-o lista si facem un count pe numarul de carti vandute
 *
 * calculam profitul pentru fiecare tip de carte din lista noastra inmultind numarul de carti cu pretul aferent al cartii respective
 * adaugam intr-o noua lista titlul cartii si profitul
 * ordonam lista
 */


public class BestProfit{

    private String bookTitle;
    private double profit;

    public BestProfit() {
    }

    public BestProfit(String bookTitle, double profit) {
        this.bookTitle = bookTitle;
        this.profit = profit;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return  "bookTitle= " + bookTitle +
                ", profit= " + profit;
    }
}
