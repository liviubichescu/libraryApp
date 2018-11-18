package ro.ubb.homeWorkLibrary.repository.fileRepo;

import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.exceptions.DatabaseException;
import ro.ubb.homeWorkLibrary.exceptions.ValidatorException;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;
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

public class SalesFileRepository extends InMemoryRepository<String, Sales> {

    private String fileName;

    public SalesFileRepository(Validator<Sales> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData(){
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                String id = items.get(0);
                Long bookID = Long.parseLong(items.get(1));
                Long clientID = Long.parseLong(items.get(2));

                Sales sale = new Sales(bookID,clientID);
                sale.setId(id);


                try {
                    super.save(sale);
                } catch (ValidatorException | DatabaseException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Sales> save(Sales entity){
        Optional<Sales> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Sales entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getBookIdSale() + "," + entity.getClientIdSale());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Sales> delete(String id){
        List<String> out = null;
        try {
            out = Files.lines(Paths.get(fileName))
                    .filter(line -> {
                        List<String> items = Arrays.asList(line.split(","));
                        if (!items.get(0).equals(id)) {
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
    public Optional<Sales> findOne(String clientID) {

        return super.findOne(clientID);
    }

}
