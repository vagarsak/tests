package ru.company.shared;

import java.util.List;

public class AnswerView {
    private Integer id; // ид вопроса
    private List<String> answer; // ответ

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
