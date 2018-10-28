package ro.ubb.homeWorkLibrary.Validators;

import ro.ubb.homeWorkLibrary.Exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;

}
