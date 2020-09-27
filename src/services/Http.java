package services;

import static spark.Spark.post;

import com.google.gson.Gson;

import intent.main.MainIntent;
import spark.Request;
import spark.Response;
import spark.Route;

public class Http {
    public static Route messages = (Request req, Response res) -> {
        StandardRequest request = new Gson().fromJson(req.body(), StandardRequest.class);
        intent.IntentDTO result = (new MainIntent()).run(request.id, "WEB USER", request.message);
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
