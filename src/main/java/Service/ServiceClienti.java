package Service;

import Domain.Client;
import Repository.RepoClient;

import java.util.List;

public class ServiceClienti {

    private RepoClient repoClient;

    public ServiceClienti(RepoClient repoClient) {
        this.repoClient = repoClient;
    }

    public void addClient(Client client){
        repoClient.addClient(client);
    }

    public void deleteClient(int Id){
        repoClient.deleteClient(Id);
    }

    public void updateClient(Client client) {
        repoClient.updateClient(client);
    }


    public List getAll(){
        return this.repoClient.findAll();
    }
}
