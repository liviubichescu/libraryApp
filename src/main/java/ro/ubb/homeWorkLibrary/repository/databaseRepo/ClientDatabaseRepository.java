package ro.ubb.homeWorkLibrary.repository.databaseRepo;

import ro.ubb.homeWorkLibrary.domain.Client;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDatabaseRepository extends InMemoryRepository<Long, Client> {

    private static final String URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USERNAME = System.getProperty("user");
    private static final String PASSWORD = System.getProperty("password");

    public ClientDatabaseRepository(Validator<Client> validator) {
        super(validator);
    }


    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from clients");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                Client client = new Client(id, name, surname, age);

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    @Override
    public Optional<Client> save(Client entity){
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToDatabase(entity);
        return Optional.empty();
    }

    public void saveToDatabase(Client client) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into clients(id, name, surname, age) values (?,?,?,?)");
        ) {
            statement.setLong(1, client.getId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getSurname());
            statement.setInt(4, client.getAge());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> update(Client client) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "update clients set name=?, surname=?, age=? where id=?")
        ) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setLong(4, client.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("delete from clients where id = ?")
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<Client> findOne(Long id) {
        Client client = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from clients where id = ?");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                client = new Client(id, name, surname, age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(client);
    }
}
