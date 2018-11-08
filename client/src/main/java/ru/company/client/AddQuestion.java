package ru.company.client;


import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.company.shared.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api")
public interface AddQuestion extends RestService {

    @POST
    @Path("/add/question") // добавить вопрос
    public void getPostHellos(QuestionView question, MethodCallback<Boolean> callback);

    @GET
    @Path("/all/question") // получить все вопросы
    public void getAllQuestion(MethodCallback<List<QuestionView>> callback);

    @POST
    @Path("/question")   // отправить ответ на вопрос
    public void sendAnswer(Answer s, MethodCallback<Boolean> callback);

    @GET
    @Path("/results") // получить все результаты
    public void getAllResults(MethodCallback<List<ResultView>> callback);

    @GET
    @Path("/test") // получить тест
    public void getTest(MethodCallback<TestView> callback);

    @POST
    @Path("/test") // отправить ответ на тест
    public void sendTest(AnswerTest answerTest, MethodCallback<TestResultView> callback);

}
