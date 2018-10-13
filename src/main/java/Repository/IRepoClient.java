package Repository;

import Domain.Client;

import java.util.List;

public interface IRepoClient {

    void addClient(Client client);

    void deleteClient(int id);

    Client findClient(int id);

    List<Client> findAll();

    Client updateClient(Client client);
}
