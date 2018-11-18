package ro.ubb.homeWorkLibrary.validators;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.exceptions.ValidatorException;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book entity) throws ValidatorException {

        StringBuilder error = new StringBuilder();

        if (!entity.getTitle().matches("[a-zA-Z0-9 ]+") || entity.getTitle().length() < 2){
            error.append("The book title has to be a string with at least 2 caracters and format A-Z!!!" + "\n");
        }
        if (!entity.getAuthor().matches("[a-zA-Z0-9 ]+") || entity.getAuthor().length() < 2){
            error.append("The author name has to be a string with at least 2 caracters!!" + "\n");
        }
        if (!entity.getPublishHouse().matches("[a-zA-Z0-9 ]+") || entity.getPublishHouse().length() < 2){
            error.append("The Publisher has to be a string with atleast 2 caracters!!"+ "\n");
        }
        if (entity.getRelesedYear()> 2018){
            error.append("The book year can not be greater 2018!!! \n");
        }
        if (entity.getPrice() < 0){
            error.append("The price can not be less than 0!!! \n");
        }
        if (error.length() > 0 ){
            throw new ValidatorException(error.toString());
        }
    }
}

