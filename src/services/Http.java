package services;

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
    	Student student = new Student();
    	student.telegramId = Long.parseLong(request.id);
        StudentDAO studentDAO = new StudentDAO(student);
      	intent.IntentDTO result = (new MainIntent()).run(request.id, studentDAO.find().name, request.message);
        return new Gson().toJson(new StandardReponse(result.getMessage()));
    };

    public static Route start = (Request req, Response res) -> {
        IStartRequest request = new Gson().fromJson(req.body(), IStartRequest.class);
        Student student = new Student();
        student.email = request.email;
        student.name = request.name;
        StudentDAO studentDAO = new StudentDAO(student);
        if (!studentDAO.existsByEmail()) {
            studentDAO.addWithEmail();
        }
        Long telegramId = studentDAO.findByEmail(student.email).telegramId;
    	return new Gson().toJson(new StandardReponse(
            Long.toString(telegramId)
        ));
    };

    public static Route audios = (Request req, Response res) -> {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        Part uploadedFile = request.raw().getPart("audio");
        String messageText = "";
        try (final InputStream in = uploadedFile.getInputStream()) {
            Watson watson = new Watson();
            messageText = watson.convert(audio);
        }
    	Student student = new Student();
    	student.telegramId = Long.parseLong(request.raw().getPart("id"));
        StudentDAO studentDAO = new StudentDAO(student);
      	intent.IntentDTO result = (new MainIntent()).run(request.raw().getPart("id"), studentDAO.find().name, messageText);
        return new Gson().toJson(new StandardReponse(result.getMessage()));
    }
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

class IAudiosRequest {
    public String message;
    public String id;
}

class IStartRequest {
    public String email;
    public String name;
}
