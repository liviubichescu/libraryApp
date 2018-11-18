package ro.ubb.homeWorkLibrary.repository.databaseRepo;

import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SalesDatabaseRepository extends InMemoryRepository<String, Sales> {

    private static final String URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USERNAME = System.getProperty("user");
    private static final String PASSWORD = System.getProperty("password");

    public SalesDatabaseRepository(Validator<Sales> validator) {
        super(validator);
    }


    public List<Sales> findAll() {
        List<Sales> sales = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from sales");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long bookID = resultSet.getLong("booksID");
                Long clientID = resultSet.getLong("clientID");

                Sales sale= new Sales(bookID,clientID);
                sale.setId(bookID.toString()+clientID.toString());

                sales.add(sale);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }


    @Override
    public Optional<Sales> save(Sales entity){
        Optional<Sales> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToDatabase(entity);
        return Optional.empty();
    }

    public void saveToDatabase(Sales sales) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into sales(booksID, clientID) values (?,?)");
        ) {
            statement.setLong(1, sales.getBookIdSale());
            statement.setLong(2, sales.getClientIdSale());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Sales> update(Sales client) {
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
//                PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(
//                     "update clients set name=?, surname=?, age=? where id=?")
//        ) {
//            statement.setString(1, client.getName());
//            statement.setString(2, client.getSurname());
//            statement.setInt(3, client.getAge());
//            statement.setLong(4, client.getId());
//
//            statement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return Optional.empty();
    }

    @Override
    public Optional<Sales> delete(String id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("delete from sales where CONCAT(booksid || '' || clientid) LIKE cast(? as varchar)")
        ) {
            statement.setString(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
