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
        if (entities.containsKey(id))
            return entities.get(id);

        throw new RuntimeException("The specified client does not exist!");
    }

    @Override

    public Iterable<Client> findAll(){
        Set<Client> clientSet = entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
        return clientSet;
   }

    @Override

    public Client save(Client client){
        if (client == null) {
            throw new IllegalArgumentException("Entity - client - must not be null.");
        }

        return entities.putIfAbsent(client.getId(), client);
    }

    @Override

    public Client delete(Long id){
        if (entities.containsKey(id))
            return entities.remove(id);

        throw new RuntimeException("The specified client does not exists in database!!!");
    }

    @Override

    public Client update(Client client){
        if (entities.containsKey(client.getId())){
            entities.remove(client.getId());
            return entities.put(client.getId(),client);
        }
        throw new RuntimeException("Update error: The specified client does not exists in database!!!");
    }

}
