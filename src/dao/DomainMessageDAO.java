package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import model.DomainMessage;

public class DomainMessageDAO extends DAO {

    private DomainMessage domainMessage;

    public DomainMessageDAO(DomainMessage domainMessage) {
        this.domainMessage = domainMessage;
    }

    public DomainMessageDAO() {
    }

    public DomainMessage find(String name) {
        String sql = "select * from Mensagem_Dominio md, Progresso p where md.id_Progresso = p.id and p.nomeProgresso = ?";
        bd.getConnection();

        ArrayList<DomainMessage> messages = new ArrayList<>();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, name);
            bd.rs = bd.st.executeQuery();

            while (bd.rs.next()) {
                messages.add(new DomainMessage(
                        bd.rs.getString("corpoMensagemDominio"),
                        bd.rs.getInt("id_Progresso")));
            }

            int index = ThreadLocalRandom.current().nextInt(0, messages.size());

            return messages.get(index);
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }

    public ArrayList<DomainMessage> list(String name) {
        String sql = "select * from Mensagem_Dominio md, Progresso p where md.id_Progresso = p.id and p.nomeProgresso = ?";
        bd.getConnection();

        ArrayList<DomainMessage> messages = new ArrayList<>();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, name);
            bd.rs = bd.st.executeQuery();

            while (bd.rs.next()) {
                messages.add(new DomainMessage(
                        bd.rs.getString("corpoMensagemDominio"),
                        bd.rs.getInt("id_Progresso")));
            }

            return messages;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
