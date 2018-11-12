package ru.company.server.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private Integer type = null;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    @ElementCollection(targetClass = String.class)
    private List<String> answer;

    @Column(name = "choicesAnswer")
    @ElementCollection(targetClass = String.class)
    private List<String> choicesAnswer;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="result_id")
    private Result result;

    public Boolean checkAnswer(List<String> value) {
        Collections.sort(value);
        if(value.size() == answer.size()){
            for(int i = 0; i<answer.size(); i++){
                if (!answer.get(i).equals(value.get(i))) {
                    result.addQuantity();
                    return false;
                }
            }
            result.addSuccessful();
            return true;
        }
        result.addQuantity();
        return false;
    }

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
        Collections.sort(answer);
        this.answer = answer;
    }

    public List<String> getChoicesAnswer() {
        return choicesAnswer;
    }

    public void setChoicesAnswer(List<String> choicesAnswer) {
        this.choicesAnswer = choicesAnswer;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public Map<String, Integer> getResultMap() {
        Map<String, Integer> result = new HashMap<>();
        result.put("quantity", this.result.getQuantity());
        result.put("successful", this.result.getSuccessful());
        return result;
    }

}
