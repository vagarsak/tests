package ru.company.shared;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    private Integer id = null;
    private Integer type = null;
    private String question; // сам вопрос
    private List<String> answer; // ответ на вопрос правильный
    private List<String> choicesAnswer; // все возможные ответы на вопрос
    private Result result = new Result();

    public Boolean checkAnswer(List<String> value) {
        Collections.sort(value);
        if (answer.equals(value)) {
            result.addSuccessful();
            return true;
        }
        result.addQuantity();
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        Collections.sort(answer);
        this.answer = answer;
    }

    public List<String> getChoicesAnswer() {
        return choicesAnswer;
    }

    public void setChoicesAnswer(List<String> choicesAnswer) {
        this.choicesAnswer = choicesAnswer;
    }

    public Map<String, Integer> getResult() {
        Map<String, Integer> result = new HashMap<>();
        result.put("quantity", this.result.getQuantity());
        result.put("successful", this.result.getSuccessful());
        return result;
    }
}
