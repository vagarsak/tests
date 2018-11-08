package ru.company.shared;

import java.util.List;
import java.util.Map;

public class AnswerTest {
    private Integer id;
    private Map<Integer, List<String>> mapAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, List<String>> getMapAnswer() {
        return mapAnswer;
    }

    public void setMapAnswer(Map<Integer, List<String>> mapAnswer) {
        this.mapAnswer = mapAnswer;
    }
}
