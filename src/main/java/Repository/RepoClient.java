package Repository;

import Domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoClient implements IRepoClient {

    private ArrayList<Client> clients;

    public RepoClient() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void addClient(Client client) throws IllegalArgumentException {

        if(findClient(client.getId())==null){
            clients.add(client);
        }
        else
            throw new IllegalArgumentException("This id is used by another client");
    }

    @Override
    public void deleteClient(int id) throws IllegalArgumentException {
        Client client = findClient(id);
        clients.remove(client);
    }

    @Override
    public Client findClient(int id) {

        Client client = null;
        for (int i=0; i<clients.size();i++) {
            if (clients.get(i).getId() == id) {
                client=clients.get(i);
            }
    }

    return client;

    }

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public Client updateClient(Client client) {
        Client client1=findClient(client.getId());

        for (int i=0; i<clients.size();i++) {
            if (clients.get(i)==client1) {
                clients.set(i,client);
            }
        }
        return client1;
    }
}
