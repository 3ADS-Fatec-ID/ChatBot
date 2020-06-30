/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joao
 */
public class PalavraChavePesquisa {

    public int id, idPalavraChave, idCurso, idPesquisavel;

    public PalavraChavePesquisa() {
    }

    public PalavraChavePesquisa(int id, int idPalavraChave, int idCurso, int idPesquisavel) {
        this.id = id;
        this.idPalavraChave = idPalavraChave;
        this.idCurso = idCurso;
        this.idPesquisavel = idPesquisavel;
    }

    @Override
    public String toString() {
        return "PalavraChavePesquisa{" + "id=" + id + ", idPalavraChave=" + idPalavraChave + ", idCurso=" + idCurso + ", idPesquisavel=" + idPesquisavel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PalavraChavePesquisa other = (PalavraChavePesquisa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
