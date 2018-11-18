package ro.ubb.homeWorkLibrary.validators;

import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.exceptions.ValidatorException;

public class SalesValidator implements Validator<Sales> {
    @Override
    public void validate(Sales sales) throws ValidatorException {

        StringBuilder error = new StringBuilder();

        if (!sales.getId().matches("[0-9]+")){
            error.append("The id has to be a numerical number!!!" + "\n");
        }
        if (error.length() > 0) {
            throw new ValidatorException(error.toString());
        }
    }
}
