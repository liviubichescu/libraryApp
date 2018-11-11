package ro.ubb.homeWorkLibrary.domain;

public class Sales extends BaseEntity<String> {

    private Long bookIdSale;
    private Long clientIdSale;


    public Sales(Long bookIdSale, Long clientIdSale) {
        super(bookIdSale.toString() + clientIdSale.toString());
        this.bookIdSale = bookIdSale;
        this.clientIdSale = clientIdSale;
    }

    public Long getBookIdSale() {
        return bookIdSale;
    }

    public void setBookIdSale(Long bookIdSale) {
        this.bookIdSale = bookIdSale;
    }

    public Long getClientIdSale() {
        return clientIdSale;
    }

    public void setClientIdSale(Long clientIdSale) {
        this.clientIdSale = clientIdSale;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesID=" + getId() +
                ", bookIdSale=" + bookIdSale +
                ", clientIdSale=" + clientIdSale +
                '}';
    }

}
