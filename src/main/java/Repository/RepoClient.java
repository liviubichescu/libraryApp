package Repository;

import Domain.Client;

import java.util.*;
import java.util.stream.Collectors;

public class RepoClient implements IRepoClient {

    private Map<Long , Client> entities;

    public RepoClient(){
        this.entities = new HashMap<>();
    }

    @Override
    public Client findClient(Long id){
        throw new RuntimeException("Not implemented yes!");
    }

    @Override

    public Iterable<Client> findAll(){
        Set<Client> clientSet = entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
        return clientSet;
   }

    @Override

    public Client save(Client client){
        if (client == null) {
            throw new IllegalArgumentException("Entity must not be null.");
        }

        return entities.putIfAbsent(client.getId(), client);
    }

    @Override

    public Client delete(Long id){
        throw new RuntimeException("not yet implemented");
    }

    @Override

    public Client update(Client client){
        throw new RuntimeException("not yet implemented");
    }

}
