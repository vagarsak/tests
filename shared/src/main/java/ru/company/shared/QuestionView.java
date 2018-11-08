package ru.company.shared;

import java.util.List;

public class QuestionView {
    private Integer id = null;
    private Integer type = null;
    private String question; // сам вопрос
    private List<String> answer; // ответ на вопрос правильный
    private List<String> choicesAnswer; // все возможные ответы на вопрос

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
        this.answer = answer;
    }

    public List<String> getChoicesAnswer() {
        return choicesAnswer;
    }

    public void setChoicesAnswer(List<String> choicesAnswer) {
        this.choicesAnswer = choicesAnswer;
    }
}
