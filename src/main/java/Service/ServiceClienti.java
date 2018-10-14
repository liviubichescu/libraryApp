package Service;

import Domain.Client;
import Repository.IRepoClient;
import Repository.RepoClient;

import java.util.List;

public class ServiceClienti {

    private IRepoClient repoClient;

    public ServiceClienti(RepoClient repoClient) {
        this.repoClient = repoClient;
    }

    public void addClient(Client client){
        repoClient.save(client);
    }

    public void deleteClient(Long Id){
        repoClient.delete(Id);
    }

    public void updateClient(Client client) {
        repoClient.update(client);
    }


    public Iterable<Client> getAll(){
        return this.repoClient.findAll();
    }
}
