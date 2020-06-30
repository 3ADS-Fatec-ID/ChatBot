package model;

public class University {

    public int id, addressId;
    public String name, description;

    public University() {
    }

    public University(int id, int addressId, String name, String description) {
        this.id = id;
        this.addressId = addressId;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Universidade{" + "id=" + id + ", idEndereco=" + addressId + ", nomeUniversidade=" + name + ", descricaoUniversidade=" + description + '}';
    }
}
