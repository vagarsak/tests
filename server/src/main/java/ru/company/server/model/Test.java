package ru.company.server.model;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameUser")
    private String nameUser;

    @Column(name = "listQuestionId")
    @ElementCollection
    private List<Integer> listQuestionId;

    @Column(name = "result")
    private Boolean result;

    public Test() {
    }

    public Test(List<Integer> listQuestionId) {
        this.listQuestionId = listQuestionId;
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

    public List<Integer> getListQuestionId() {
        return listQuestionId;
    }

    public void setListQuestionId(List<Integer> listQuestionId) {
        this.listQuestionId = listQuestionId;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
