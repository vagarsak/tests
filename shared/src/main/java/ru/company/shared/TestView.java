package ru.company.shared;

import java.util.List;

public class TestView {

    private Integer id;

    private List<QuestionView> listQuestion;

    public TestView() {
    }

    public TestView(Integer id, List<QuestionView> listQuestion) {
        this.id = id;
        this.listQuestion = listQuestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<QuestionView> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<QuestionView> listQuestion) {
        this.listQuestion = listQuestion;
    }
}
