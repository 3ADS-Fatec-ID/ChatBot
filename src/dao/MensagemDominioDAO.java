package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import model.MensagemDominio;

import services.BD;

public class MensagemDominioDAO {

    private final BD bd;
    private final MensagemDominio mensagemDominio;

    public MensagemDominioDAO(MensagemDominio mensagemDominio) {
        this.bd = new BD();
        this.mensagemDominio = mensagemDominio;
    }

    public MensagemDominio findMessage() {
        String sql = "select * from mensagem_dominio where nomeMensagemDominio = ? and id_Tipo_Mensagem_Dominio = ?";
        bd.getConnection();

        ArrayList<MensagemDominio> md = new ArrayList<>();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, mensagemDominio.nomeMensagemDominio);
            bd.st.setInt(2, mensagemDominio.idTipoMensagemDominio);
            bd.rs = bd.st.executeQuery();

            while (bd.rs.next()) {
                md.add(new MensagemDominio(bd.rs.getString("nomeMensagemDominio"),
                        bd.rs.getString("corpoMensagemDominio"),
                        bd.rs.getInt("id_Tipo_Mensagem_Dominio")));
            }

            int index = ThreadLocalRandom.current().nextInt(0, md.size());

            return md.get(index);
        } catch (SQLException e) {
            return null;
        } finally {
            bd.close();
        }
    }
}
