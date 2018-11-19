package ro.ubb.homeWorkLibrary.service;

public class ActiveClients {

    private String numeClient;
    private int booksBought;

    public ActiveClients() {
    }

    public ActiveClients(String numeClient, int booksBought) {
        this.numeClient = numeClient;
        this.booksBought = booksBought;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public int getBooksBought() {
        return booksBought;
    }

    public void setBooksBought(int booksBought) {
        this.booksBought = booksBought;
    }

    @Override
    public String toString() {
        return "ActiveClients{" +
                "numeClient='" + numeClient + '\'' +
                ", booksBought=" + booksBought +
                '}';
    }
}
