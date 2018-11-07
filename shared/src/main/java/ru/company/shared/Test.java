package ru.company.shared;

public class Test {
    private Integer id; // ид теста
    private Question[] listQuestion; // вопросы

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question[] getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(Question[] listQuestion) {
        this.listQuestion = listQuestion;
    }
}
