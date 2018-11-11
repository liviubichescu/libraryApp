package ro.ubb.homeWorkLibrary.repository.databaseRepo;

import ro.ubb.homeWorkLibrary.domain.Sales;

import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;


public class DBSalesRepository extends InMemoryRepository<String, Sales> {

    private static final String URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USERNAME = System.getProperty("user");
    private static final String PASSWORD = System.getProperty("password");

    public DBSalesRepository(Validator<Sales> validator) {
        super(validator);
    }

//    public List<Client> findAll() {
//
//        List<Client> clients = new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
//
//             PreparedStatement statement = connection.prepareStatement("select * from clients");
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Long id = resultSet.getLong("id ");
//                String name = resultSet.getString("name");
//                String surname = resultSet.getString("surname");
//                int age = resultSet.getInt("age");
//
//                Client client = new Client(id, name, surname, age);
//
//                clients.add(client);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return clients;
//    }

}
