package Repository;

import Domain.Client;


public interface IRepoClient {

    Client findClient(Long id);

    Iterable<Client> findAll();

    Client save(Client entity);

    Client delete(Long id);

    Client update(Client entity);

}