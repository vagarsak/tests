package ru.company.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "listQuestion")
    @ElementCollection(targetClass = Question.class, fetch = FetchType.LAZY)
    private List<Question> listQuestion;

    public Test() {
    }

    public Test(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }
}
