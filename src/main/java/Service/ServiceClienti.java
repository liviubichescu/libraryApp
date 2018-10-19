package Service;

import Domain.Client;
import Domain.Validators.ValidatorException;
import Repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceClienti {

    private Repository<Long, Client> repository;

    public ServiceClienti(Repository<Long, Client> repository) {
        this.repository = repository;
    }

    public void addClient(Client client)throws ValidatorException {
        repository.save(client);
    }

    public Set<Client> getAllClients() {
        Iterable<Client> clients = repository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Client> filterClientsByName(String s) {
        Iterable<Client> clients = repository.findAll();
        Set<Client> filteredClients = new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(client -> !client.getName().contains(s));

        return filteredClients;
    }

    public void removeClient(Long clientId){
        repository.delete(clientId);
    }
}
