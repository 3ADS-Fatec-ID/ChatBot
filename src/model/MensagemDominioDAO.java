package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Mensagem_Dominio;
import services.BD;

public class MensagemDominioDAO extends Mensagem_Dominio{

	public BD bd;
	private String sql;	

	public MensagemDominioDAO() {
		bd = new BD();
	}

	public Mensagem_Dominio findMessage(String nomeMensagemDominio,int id_Tipo_Mensagem_Dominio) {
		Mensagem_Dominio mensagemDominioFinal = new Mensagem_Dominio();
		sql = "select * from mensagem_dominio where nomeMensagemDominio = ? and id_Tipo_Mensagem_Dominio = ?";
		bd.getConnection();
		ArrayList<Mensagem_Dominio> md = new ArrayList<Mensagem_Dominio>();

		try {

			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1,nomeMensagemDominio);
			bd.st.setInt(2, id_Tipo_Mensagem_Dominio);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) {
				Mensagem_Dominio mensagemDominio = new Mensagem_Dominio();
				mensagemDominio.corpoMensagemDominio = bd.rs.getString("corpoMensagemDominio");
				mensagemDominio.nomeMensagemDominio = bd.rs.getString("nomeMensagemDominio");
				mensagemDominio.id_Tipo_Mensagem_Dominio = bd.rs.getInt("id_Tipo_Mensagem_Dominio");
				md.add(mensagemDominio);
			}
			for (Mensagem_Dominio mensagem_Dominio : md) {
				System.out.println(mensagem_Dominio.nomeMensagemDominio);
				System.out.println(mensagem_Dominio.corpoMensagemDominio);
				System.out.println(mensagem_Dominio.id_Tipo_Mensagem_Dominio);
			}

			return mensagemDominioFinal;
		}
		catch(SQLException erro) {
			return null;
		}
		finally {
			bd.close();
		}
	}
	
	public static void main(String[] args) {
		MensagemDominioDAO mensagem_DominioDAO = new MensagemDominioDAO();
		System.out.println(mensagem_DominioDAO.findMessage("CAD_INICIO",1));
	}
}
