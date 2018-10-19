package Repository;
import Domain.Book;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;



/*
 * @author Liviu
 */
public class BookFileRepository extends InMemoryRepository<Long, Book> {

    private String fileName;

    public BookFileRepository(Validator<Book> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String title = items.get(1);
                String author = items.get((2));
                String publishHouse = items.get((3));
                int relesedYear = Integer.parseInt(items.get(4));
                double price = Double.parseDouble(items.get(5));

                Book book = new Book(id,title,author,publishHouse,relesedYear,price);
                book.setId(id);

                try {
                    super.save(book);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Optional<Book> save(Book entity) throws ValidatorException {
        Optional<Book> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Book entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getTitle() + "," + entity.getAuthor() + "," + entity.getPublishHouse()+ "," + entity.getRelesedYear()+ "," + entity.getPrice());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Optional<Book> delete(Long id) {
//        Path path = Paths.get(fileName);
//        File temp = new File("./data/tempBooks.txt");
//        PrintWriter out = null;
//        try {
//            out = new PrintWriter(new FileWriter(temp));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            Files.lines(path)
//                    .filter(p -> p.contains(id));
//                    .forEach(out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.flush();
//        out.close();
//        temp.renameTo(path.toFile());
//
//
////
////        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
////            stream
////                    .filter(line -> id.equals(line.charAt(0)))
////                    .forEach(System.out::println);
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//        return Optional.empty();
//    }


    @Override
    public Optional<Book> findOne(Long aLong) {

        return super.findOne(aLong);
    }

}
