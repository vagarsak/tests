package ru.company.shared;

import java.util.List;
import java.util.Map;

public class TestResultsView {
    private Integer id;

    private Boolean result;

    private Map<String, List<String>> questionAnswer;

    private String nameUser;

    public Map<String, List<String>> getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(Map<String, List<String>> questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
