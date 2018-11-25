package ru.company.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.company.shared.QuestionView;

import java.util.ArrayList;
import java.util.List;

public class ViewСreateQuestion {
     private final Label codeLabel; // убрать отсюда позже
     private final AddQuestion addQestion;

    public ViewСreateQuestion(Label codeLabel, AddQuestion addQestion) {
        this.codeLabel = codeLabel;
        this.addQestion = addQestion;
    }

    public void answerText(VerticalPanel verticalPanel) {
        final Button creatQuestion = new Button("Создать вопрос");
        final TextBox questionText = new TextBox(); // сам вопрос
        final Label questionLabel = new Label("Вопрос");
        final TextBox answer = new TextBox(); // сам ответ
        final Label answerLabel = new Label("Ответ");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(questionLabel);
        horizontalPanel.add(questionText);
        HorizontalPanel horizontalPanel2 = new HorizontalPanel();
        horizontalPanel2.add(answerLabel);
        horizontalPanel2.add(answer);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(horizontalPanel2);
        verticalPanel.add(new Label());
        verticalPanel.add(creatQuestion);

        creatQuestion.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                QuestionView question = new QuestionView();
                List<String> answerList = new ArrayList<>();
                answerList.add(answer.getText());
                question.setAnswer(answerList);
                question.setQuestion(questionText.getText());
                question.setType(0);
                addQestion.getPostHellos(question, new СreateQuestion(codeLabel));// отправляем на сервер
            }
        });
    }

    public void answerMultiple(final VerticalPanel verticalPanel, final String type) {
        final Button creatQuestion = new Button("Создать вопрос");
        final TextBox questionText = new TextBox(); // сам вопрос
        final Label questionLabel = new Label("Вопрос");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(questionLabel);
        horizontalPanel.add(questionText);
        verticalPanel.add(horizontalPanel);
        final VerticalPanel verticalPanel1ListAnswer = new VerticalPanel();
        verticalPanel.add(verticalPanel1ListAnswer);

        final Button addAnswer = new Button("Добавить ответ");
        final List<HorizontalPanel> hh = new ArrayList<>();
        addAnswer.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                TextBox rbText = new TextBox(); // ответ
                Button deleteRb = new Button("Удалить ответ");
                final HorizontalPanel horizontalPanel = new HorizontalPanel();
                if (type.equals("RadioButton")) {
                    RadioButton rb = new RadioButton("myRadioGroup");
                    rb.setChecked(true);
                    horizontalPanel.add(rb);
                } else {
                    CheckBox rb = new CheckBox();
                    horizontalPanel.add(rb);
                }
                horizontalPanel.add(rbText);
                horizontalPanel.add(deleteRb);
                verticalPanel1ListAnswer.add(horizontalPanel);
                hh.add(horizontalPanel);
                deleteRb.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        hh.remove(horizontalPanel);
                        horizontalPanel.removeFromParent();
                    }
                });
            }
        });

        HorizontalPanel horizontalPanel1 = new HorizontalPanel();
        horizontalPanel1.add(addAnswer);
        horizontalPanel1.add(creatQuestion);
        verticalPanel.add(horizontalPanel1);  // кнопка отправки и добавление ответа
        creatQuestion.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                List choicesAnswer = new ArrayList();
                List answerList = new ArrayList();
                for (HorizontalPanel horizontalPanel : hh) {
                    TextBox textBox = (TextBox) horizontalPanel.getWidget(1);
                    if (type.equals("RadioButton")) {
                        RadioButton lb = (RadioButton) horizontalPanel.getWidget(0);
                        if (lb.getValue()) { // правильный ответ
                            answerList.add(textBox.getText());
                        }
                    } else {
                        CheckBox lb = (CheckBox) horizontalPanel.getWidget(0);
                        if (lb.getValue()) { // правильный ответ
                            answerList.add(textBox.getText());
                        }
                    }
                    choicesAnswer.add(textBox.getText());// все ответы
                }
                QuestionView question = new QuestionView();
                question.setAnswer(answerList);
                question.setChoicesAnswer(choicesAnswer);
                question.setQuestion(questionText.getText());
                if (type.equals("RadioButton")) {
                    question.setType(1);
                } else {
                    question.setType(2);
                }
                addQestion.getPostHellos(question, new СreateQuestion(codeLabel));// отправляем на сервер
                for (HorizontalPanel horizontalPanel : hh) {
                    horizontalPanel.removeFromParent();
                }
                hh.clear();
            }
        });
    }

    private class СreateQuestion implements MethodCallback<Boolean> {

        private Label codeLabel;

        public СreateQuestion(Label codeLabel) {
            this.codeLabel = codeLabel;
        }

        @Override
        public void onFailure(Method method, Throwable throwable) {
            codeLabel.setText("Error");
        }

        @Override
        public void onSuccess(Method method, Boolean s) {
            codeLabel.setText("Успешно создан вопрос");
        }
    }

}
