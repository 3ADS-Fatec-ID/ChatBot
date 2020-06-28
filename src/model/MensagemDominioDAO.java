package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import services.BD;

public class MensagemDominioDAO extends MensagemDominio {

    public BD bd;
    private String sql;

    public MensagemDominioDAO() {
        bd = new BD();
    }

    public MensagemDominio findMessage(String nomeMensagemDominio, int idTipoMensagemDominio) {
        sql = "select * from mensagem_dominio where nomeMensagemDominio = ? and id_Tipo_Mensagem_Dominio = ?";
        bd.getConnection();

        ArrayList<MensagemDominio> md = new ArrayList<MensagemDominio>();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, nomeMensagemDominio);
            bd.st.setInt(2, idTipoMensagemDominio);
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

    public static void main(String[] args) {
        MensagemDominioDAO mensagem_DominioDAO = new MensagemDominioDAO();
        System.out.println(mensagem_DominioDAO.findMessage("CAD_INICIO", 1).toString());
    }
}
