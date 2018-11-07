package ru.company.client;


import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.company.shared.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api")
public interface AddQuestion extends RestService {

    @POST
    @Path("/add/question") // добавить вопрос
    public void getPostHellos(Question question, MethodCallback<String> callback);

    @GET
    @Path("/add/question") // получить все вопросы
    public void getAllQuestion(MethodCallback<List<Question>> callback);

    @POST
    @Path("/question")   // отправить ответ на тест
    public void getAnswer(Answer s, MethodCallback<String> callback);

    @GET
    @Path("/results") // получить все результаты
    public void getAllResults(MethodCallback<List<Result>> callback);

    @GET
    @Path("/test") // получить тест
    public void getTest(MethodCallback<Test> callback);

    @POST
    @Path("/test") // отправить ответ на тест
    public void sendTest(AnswerTest answerTest, MethodCallback<TestResult> callback);

}
