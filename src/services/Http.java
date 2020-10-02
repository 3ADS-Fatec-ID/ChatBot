package services;

import static spark.Spark.post;

import com.google.gson.Gson;

import intent.main.MainIntent;
import spark.Request;
import spark.Response;
import spark.Route;
import dao.StudentDAO;
import model.Student;

public class Http {
    public static Route messages = (Request req, Response res) -> {
    	IMessagesRequest request = new Gson().fromJson(req.body(), IMessagesRequest.class);
    	intent.IntentDTO result = (new MainIntent()).run(request.id, "WEB-USER", request.message);
        return new Gson().toJson(new StandardReponse(result.getMessage()));
    };

    public static Route start = (Request req, Response res) -> {
        IStartRequest request = new Gson().fromJson(req.body(), IStartRequest.class);
        student = new Student();
        student.email = request.email;
        student.name = request.name;
        studentDAO = new StudentDAO(student);
        if (!studentDao.existsByEmail()) {
            studentDAO.addWithEmail();
        }
    	return new Gson().toJson(new StandardReponse(
            studentDAO.findByEmail(student.email).telegramId
        ));
    };
}

class StandardReponse {
    public String response;

    StandardReponse(String response) {
        this.response = response;
    }
}

class IMessagesRequest {
    public String message;
    public String id;
}

class IStartRequest {
    public String email;
    public String name;
}
