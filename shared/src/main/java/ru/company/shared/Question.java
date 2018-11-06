package ru.company.shared;

public class Question {
    private Integer id = null;
    private Integer type = null;
    private String question; // сам вопрос
    private String[] answer; // ответ на вопрос правильный
    private String[] choicesAnswer; // всевозможные ответы на вопрос

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

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String[] getChoicesAnswer() {
        return choicesAnswer;
    }

    public void setChoicesAnswer(String[] choicesAnswer) {
        this.choicesAnswer = choicesAnswer;
    }
}
