package Domain.Validators;

import Domain.Book;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book entity) throws ValidatorException {
        StringBuilder error = new StringBuilder();

        if (!entity.getTitle().matches("[a-zA-Z]+") && entity.getTitle().length() < 2){
            error.append("The name has to be a string with atleast 2 caracters!!" + "\n");
        }
        if (!entity.getAuthor().matches("[a-zA-Z]+") && entity.getAuthor().length() < 2){
            error.append("The name has to be a string with atleast 2 caracters!!" + "\n");
        }
        if (!entity.getPublishHouse().matches("[a-zA-Z]+") && entity.getPublishHouse().length() < 2){
            error.append("The Publisher has to be a string with atleast 2 caracters!!"+ "\n");
        }
        if (entity.getRelesedYear()> 2018){
            error.append("The appearance year can not be greater 2018 \n");
        }
        if (entity.getPrice() <= 0){
            error.append("The price can not be less than 0 \n");
        }
        if (error.length() > 0 ){
            throw new ValidatorException(error.toString());
        }
    }
}

