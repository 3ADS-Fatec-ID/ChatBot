package services;

import static spark.Spark.post;

import com.google.gson.Gson;

import intent.main.MainIntent;
import model.Student;
import model.StudentWeb;
import dao.StudentWebDAO;
import spark.Request;
import spark.Response;
import spark.Route;

public class Http {
    public static Route messages = (Request req, Response res) -> {
       
    	StandardRequest request = new Gson().fromJson(req.body(), StandardRequest.class);
    	
    	/*Student student = new Student();
    	StudentWeb studentWeb = new StudentWeb();
    	studentWeb.webId = Integer.getInteger(request.id);

        StudentWebDAO studentWebDAO = new StudentWebDAO(studentWeb);
            
        
        if (studentWebDAO.exists()) {
        	student = studentWebDAO.find();
        }else {
        	
        	//pedir usuario e e-mail no chat
        	String nomeUsuario = "web";    	
        	String email = "web@web.com";            	
        	studentWebDAO.addUsuarioWeb(nomeUsuario, email);
        	studentWebDAO.addRelation();
        	student = studentWebDAO.find();
        }
        
        intent.IntentDTO result = (new MainIntent()).run(Integer.toString(student.id), student.name, request.message);*/
    	intent.IntentDTO result = (new MainIntent()).run(request.id, "WEB-USER", request.message);
        return new Gson().toJson(new StandardReponse(result.getMessage()));
    };
}

class StandardReponse {
    public String response;

    StandardReponse(String response) {
        this.response = response;
    }
}

class StandardRequest {
    public String message;
    public String id;
}
