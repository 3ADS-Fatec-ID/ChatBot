package model;

public class MensagemDominio {

    public String nomeMensagemDominio, corpoMensagemDominio;
    public int idTipoMensagemDominio;

    public MensagemDominio(String nomeMensagemDominio, String corpoMensagemDominio, int idTipoMensagemDominio) {
        this.nomeMensagemDominio = nomeMensagemDominio;
        this.corpoMensagemDominio = corpoMensagemDominio;
        this.idTipoMensagemDominio = idTipoMensagemDominio;
    }

    public MensagemDominio() {
    }

    @Override
    public String toString() {
        return "MensagemDominio{" + "nomeMensagemDominio=" + nomeMensagemDominio + ", corpoMensagemDominio=" + corpoMensagemDominio + ", idTipoMensagemDominio=" + idTipoMensagemDominio + '}';
    }
}
