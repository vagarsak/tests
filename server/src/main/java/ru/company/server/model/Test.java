package ru.company.server.model;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameUser")
    private String nameUser;

    @ElementCollection(targetClass = ResultTest.class)
    @MapKeyColumn(name="key") // column name for map "key"
    @Column(name="value") // column name for map "value"
    private Map<Integer, ResultTest> listQuestionAnswer;

    @Column(name = "result")
    private Boolean result;

    public Test() {
    }

    public Test(Map<Integer, ResultTest> listQuestionAnswer) {
        this.listQuestionAnswer = listQuestionAnswer;
        this.nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
        this.result = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Map<Integer, ResultTest> getListQuestionAnswer() {
        return listQuestionAnswer;
    }

    public void setListQuestionAnswer(Map<Integer, ResultTest> listQuestionAnswer) {
        this.listQuestionAnswer = listQuestionAnswer;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
