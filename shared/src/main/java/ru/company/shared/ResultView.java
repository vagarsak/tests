package ru.company.shared;

import java.util.Map;

public class ResultView {

    private Integer id;

    private String question;

    private Map<String, Integer> answers;

    public ResultView() {
    }

    public ResultView(Integer id, String question, Map<String, Integer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public Integer getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Map<String, Integer> getAnswers() {
        return answers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(Map<String, Integer> answers) {
        this.answers = answers;
    }
}
