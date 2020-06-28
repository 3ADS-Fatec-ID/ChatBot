package model;

public class MensagemDominio {

    public String corpoMensagemDominio;
    public int idTipoMensagemDominio, idProgesso;

    public MensagemDominio(String corpoMensagemDominio, int idTipoMensagemDominio, int idProgresso) {
        this.corpoMensagemDominio = corpoMensagemDominio;
        this.idTipoMensagemDominio = idTipoMensagemDominio;
        this.idProgesso = idProgresso;
    }

    public MensagemDominio() {
    }

    @Override
    public String toString() {
        return "MensagemDominio{" + "corpoMensagemDominio=" + corpoMensagemDominio + ", idTipoMensagemDominio=" + idTipoMensagemDominio + ", idProgesso=" + idProgesso + '}';
    }
}
