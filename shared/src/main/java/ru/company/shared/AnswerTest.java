package ru.company.shared;

import java.util.Map;

public class AnswerTest {
    private Integer id;
    private Map<Integer,String[]> mapAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, String[]> getMapAnswer() {
        return mapAnswer;
    }

    public void setMapAnswer(Map<Integer, String[]> mapAnswer) {
        this.mapAnswer = mapAnswer;
    }
}
