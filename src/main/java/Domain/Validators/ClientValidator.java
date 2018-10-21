package Domain.Validators;

import Domain.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        StringBuilder error = new StringBuilder();

        if (!entity.getName().matches("[a-zA-Z]+") && entity.getName().length() < 2) {
            error.append("The name has to be a string with atleast 2 caracters!!" + "\n");
        }
        if (!entity.getSurname().matches("[a-zA-Z]+") || entity.getSurname().length() < 2) {
            error.append("The last name has to be a string with atleast 2 caracters!!" + "\n");
        }
        if (entity.getAge() <= 0 || entity.getAge() > 200) {
            error.append("The age can not be less than 1 and and greater than 200 \n");
        }
        if (error.length() > 0) {
            throw new ValidatorException(error.toString());
        }
    }
}

