package Domain;

import java.util.Objects;

public class Client {
    private int id;
    private String Name;
    private String Surname;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Client(int id, String name, String surname, int age){
        this.id = id;
        this.Name = name;
        this.Surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id &&
                age == client.age &&
                Objects.equals(Name, client.Name) &&
                Objects.equals(Surname, client.Surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, Surname, age);
    }
}
