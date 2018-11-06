package ru.company.server;


import org.springframework.web.bind.annotation.*;
import ru.company.shared.Answer;
import ru.company.shared.Message;
import ru.company.shared.Question;
import ru.company.shared.Result;

import java.util.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class HelloResource {

    List<Question> database;
    List<Result> results;
    public HelloResource() {
        database = new ArrayList<>();
        results = new ArrayList<>();
    }

    @RequestMapping(value = "/add/question", method = RequestMethod.POST)
    public String getPostHello(@RequestBody Question question) { // добавить вопрос
        question.setId(database.size());
        database.add(question);
        results.add(new Result(question.getId()));
        return "1";
    }

    @RequestMapping(value = "/add/question", method = RequestMethod.GET)
    public List<Question> getAllQuestion() { // получить все вопросы
        return database;
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public String getPostHello(@RequestBody Answer answer) { // принять ответ на тест
        for(int i =0; i < database.size();i++){
             if (database.get(i).getId() ==  answer.getId() ){ //нашли тест
                 String[] answerServer = database.get(i).getAnswer();
                 String[] answerClient = answer.getAnswer();
                 Arrays.sort(answerServer);
                 Arrays.sort(answerClient);
                 if(Arrays.equals(answerServer,answerClient)){
                    results.get(answer.getId()).addSuccessful();
                    return "1";
                 }
                 results.get(answer.getId()).addQuantity();
                 break;
             }
        }
        return "0";
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public List<Result> getAllResults() { // получить все результаты
        return results;
    }

}