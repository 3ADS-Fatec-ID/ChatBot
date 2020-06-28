package model;

public class Endereco {

    public String endereco, cidade, estado;

    public Endereco() {
    }

    public Endereco(String endereco, String cidade, String estado) {
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" + "endereco=" + endereco + ", cidade=" + cidade + ", estado=" + estado + '}';
    }
}
