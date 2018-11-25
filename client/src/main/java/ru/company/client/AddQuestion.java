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
    @Path("/admin/add/question") // добавить вопрос
    public void getPostHellos(QuestionView question, MethodCallback<Boolean> callback);

    @GET
    @Path("/admin/all/question") // получить все вопросы
    public void getAllQuestion(MethodCallback<List<QuestionView>> callback);

    @POST
    @Path("/admin/question")   // отправить ответ на вопрос
    public void sendAnswer(AnswerView s, MethodCallback<Boolean> callback);

    @GET
    @Path("/admin/question/results") // получить все результаты
    public void getAllResults(MethodCallback<List<ResultView>> callback);

    @GET
    @Path("/test") // получить тест
    public void getTest(MethodCallback<TestView> callback);

    @POST
    @Path("/test") // отправить ответ на тест
    public void sendTest(AnswerTestView answerTest, MethodCallback<TestResultView> callback);

    @GET
    @Path("/user") // получить user
    public void getUser(MethodCallback<UserView> callback);

    @GET
    @Path("/results/tests") // получить результат тестов
    public void geResultsTest (MethodCallback<List<TestResultsView>> callback);

}
