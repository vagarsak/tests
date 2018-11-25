package ru.company.shared;

import java.util.Map;

public class TestResultView {

    private Integer id;

    private Map<Integer, Boolean> mapAnswer;

    private Boolean result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, Boolean> getMapAnswer() {
        return mapAnswer;
    }

    public void setMapAnswer(Map<Integer, Boolean> mapAnswer) {
        this.mapAnswer = mapAnswer;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
