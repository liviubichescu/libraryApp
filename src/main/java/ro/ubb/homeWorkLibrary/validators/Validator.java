package ro.ubb.homeWorkLibrary.validators;

import ro.ubb.homeWorkLibrary.exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

}
