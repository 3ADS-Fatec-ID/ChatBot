package model;

public class Universidade {

    public int id, idEndereco;
    public String nomeUniversidade, descricaoUniversidade;

    public Universidade() {
    }

    public Universidade(int id, int idEndereco, String nomeUniversidade, String descricaoUniversidade) {
        this.id = id;
        this.idEndereco = idEndereco;
        this.nomeUniversidade = nomeUniversidade;
        this.descricaoUniversidade = descricaoUniversidade;
    }

    @Override
    public String toString() {
        return "Universidade{" + "id=" + id + ", idEndereco=" + idEndereco + ", nomeUniversidade=" + nomeUniversidade + ", descricaoUniversidade=" + descricaoUniversidade + '}';
    }
}
