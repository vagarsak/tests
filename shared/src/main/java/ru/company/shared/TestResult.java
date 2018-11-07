package ru.company.shared;

import java.util.Map;

public class TestResult {
    private Integer id;
    private Map<Integer,Integer> mapAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, Integer> getMapAnswer() {
        return mapAnswer;
    }

    public void setMapAnswer(Map<Integer, Integer> mapAnswer) {
        this.mapAnswer = mapAnswer;
    }
}
