package Domain;

import java.util.Objects;

public class Client extends BaseEntity<Long> {
    private String Name;
    private String Surname;
    private int age;

    public Client(Long id, String name, String surname, int age) {
        super(id);
        this.Name = name;
        this.Surname = surname;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age &&
                Objects.equals(Name, client.Name) &&
                Objects.equals(Surname, client.Surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Surname, age);
    }

    @Override
    public String toString() {
        return "Client{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", age=" + age +
                '}';
    }
}
