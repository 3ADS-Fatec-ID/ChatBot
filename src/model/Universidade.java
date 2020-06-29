package model;

public class Universidade {

    public int id, idEndereco;
    public String nomeUniversidade, descricaoUniversidade;
    public Endereco endereco;

    public Universidade() {
    }

    public Universidade(int id, String nomeUniversidade, String descricaoUniversidade, Endereco endereco) {
        this.id = id;
        this.nomeUniversidade = nomeUniversidade;
        this.descricaoUniversidade = descricaoUniversidade;
        this.endereco = endereco;
    }

    public Universidade(int id, int idEndereco, String nomeUniversidade, String descricaoUniversidade) {
        this.id = id;
        this.idEndereco = idEndereco;
        this.nomeUniversidade = nomeUniversidade;
        this.descricaoUniversidade = descricaoUniversidade;
    }

    @Override
    public String toString() {
        return "Universidade{" + "id=" + id + ", nomeUniversidade=" + nomeUniversidade + ", descricaoUniversidade=" + descricaoUniversidade + ", endereco=" + endereco + '}';
    }
}
