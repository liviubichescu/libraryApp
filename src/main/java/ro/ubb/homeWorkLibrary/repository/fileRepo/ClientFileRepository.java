package ro.ubb.homeWorkLibrary.repository.fileRepo;

import ro.ubb.homeWorkLibrary.domain.Client;
import ro.ubb.homeWorkLibrary.exceptions.DatabaseException;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;
import ro.ubb.homeWorkLibrary.exceptions.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientFileRepository extends InMemoryRepository<Long, Client> {

    private String fileName;

    public ClientFileRepository(Validator<Client> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData(){
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String name = items.get(1);
                String surname = items.get((2));
                int age = Integer.parseInt(items.get(3));

                Client client = new Client(id, name, surname, age);
                client.setId(id);

                try {
                    super.save(client);
                } catch (ValidatorException | DatabaseException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Client> save(Client entity){
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Client entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getName() + "," + entity.getSurname() + "," + entity.getAge());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> delete(Long id){
        List<String> out = null;
        try {
            out = Files.lines(Paths.get(fileName))
                    .filter(line -> {
                        List<String> items = Arrays.asList(line.split(","));
                        if (!items.get(0).equals(id.toString())) {
                            return true;
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.write(Paths.get(fileName), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.delete(id);
        return Optional.empty();
    }


    @Override
    public Optional<Client> findOne(Long clientID) {

        return super.findOne(clientID);
    }

}
