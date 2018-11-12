package ru.company.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import ru.company.shared.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestyGwtApp implements EntryPoint {

    final Label codeLabel = new Label(); // убрать отсюда позже
    final AddQuestion addQestion = GWT.create(AddQuestion.class);

    private void answerText(VerticalPanel verticalPanel) {
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

    private void answerMultiple(final VerticalPanel verticalPanel, final String type) {
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

    public void onModuleLoad() {

        Defaults.setServiceRoot(GWT.getHostPageBaseURL());

        RootPanel.get("center").add(codeLabel); // вспомогательная инфа
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        final VerticalPanel verticalPanel2 = new VerticalPanel();

        final ListBox lb = new ListBox();
        lb.addItem("Текстовый ответ"); // 0
        lb.addItem("1 ответ на выбор"); // 1
        lb.addItem("Несколько ответов"); // 2
        lb.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                verticalPanel2.clear();
                switch (lb.getSelectedIndex()) {
                    case 1:
                        answerMultiple(verticalPanel2, "RadioButton");
                        break;
                    case 2:
                        answerMultiple(verticalPanel2, "CheckBox");
                        break;
                    default:
                        answerText(verticalPanel2);
                }
            }
        });
        final Label LabelListBox = new Label("Выберите тип вопроса");
        horizontalPanel.add(LabelListBox);
        horizontalPanel.add(lb);
        RootPanel.get("left").add(horizontalPanel); // выбор типа вопроса
        RootPanel.get("left").add(verticalPanel2);  // создание вопроса


        final Button getQuestion = new Button("Получить все вопросы");
        RootPanel.get("center").add(getQuestion);
        final VerticalPanel verticalPanel = new VerticalPanel(); // вопросы отображаются здесь

        final Button getTest = new Button("Пройти тест");
        RootPanel.get("center").add(getTest);
        RootPanel.get("center").add(verticalPanel);

        getTest.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addQestion.getTest(new MethodCallback<TestView>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        codeLabel.setText("Ошибка getResults.addClickHandler");
                    }

                    @Override
                    public void onSuccess(Method method, final TestView test) {
                        verticalPanel.clear();
                        final Map<Integer, List<String>> mapAnswer = new HashMap<>();
                        for (QuestionView vopro : test.getListQuestion()) {
                            final Integer id = vopro.getId(); // id
                            final Label questionId = new Label("id вопроса - " + id.toString());  // id
                            Label questionText = new Label("Вопрос: " + vopro.getQuestion()); // вопрос

                            final List<String> listAnswer = new ArrayList(); // Выбранные ответы
                            HorizontalPanel horizontalPanel1Answer = new HorizontalPanel();
                            if (vopro.getType() == 1 || vopro.getType() == 2) {
                                for (String s : vopro.getChoicesAnswer()) {  // варианты ответа
                                    final Label answerLabel = new Label(s); // ответ
                                    HorizontalPanel horizontalPanel12 = new HorizontalPanel();
                                    if (vopro.getType() == 1) {
                                        final RadioButton rb = new RadioButton("myRadioGroup" + id);
                                        horizontalPanel12.add(rb);
                                        rb.addClickHandler(new ClickHandler() {
                                            @Override
                                            public void onClick(ClickEvent clickEvent) {
                                                if (rb.getValue()) {
                                                    listAnswer.clear();
                                                    listAnswer.add(answerLabel.getText());
                                                    mapAnswer.put(id, listAnswer);
                                                }
                                            }
                                        });
                                    } else {
                                        final CheckBox rb = new CheckBox();
                                        horizontalPanel12.add(rb);
                                        rb.addClickHandler(new ClickHandler() {
                                            @Override
                                            public void onClick(ClickEvent clickEvent) {
                                                String text = answerLabel.getText();
                                                Integer index = listAnswer.indexOf(text);
                                                if (rb.getValue()) {
                                                    if (index == -1) {
                                                        listAnswer.add(answerLabel.getText());
                                                        mapAnswer.put(id, listAnswer);
                                                    }
                                                } else {
                                                    listAnswer.remove(text);
                                                    mapAnswer.put(id, listAnswer);
                                                }
                                            }
                                        });
                                    }
                                    horizontalPanel12.add(answerLabel);
                                    horizontalPanel1Answer.add(horizontalPanel12);
                                }
                            }

                            if (vopro.getType() == 0) {
                                final TextBox answerText = new TextBox(); // сам ответ
                                horizontalPanel1Answer.add(answerText);
                                answerText.addValueChangeHandler(new ValueChangeHandler<String>() {
                                    @Override
                                    public void onValueChange(ValueChangeEvent<String> valueChangeEvent) {
                                        listAnswer.clear();
                                        listAnswer.add(answerText.getText());
                                        mapAnswer.put(id, listAnswer);
                                    }
                                });
                            }

                            verticalPanel.add(questionId);
                            verticalPanel.add(questionText);
                            verticalPanel.add(horizontalPanel1Answer);
                        }

                        Button getAnswer = new Button("Ответить"); // отправить ответ
                        verticalPanel.add(getAnswer);

                        getAnswer.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent clickEvent) {
                                AnswerTestView answerTestView = new AnswerTestView();
                                answerTestView.setId(test.getId());
                                answerTestView.setMapAnswer(mapAnswer);
                                addQestion.sendTest(answerTestView, new MethodCallback<TestResultView>() {
                                    @Override
                                    public void onFailure(Method method, Throwable throwable) {
                                        codeLabel.setText("что то пошло не так getAnswer.addClickHandler");
                                    }

                                    @Override
                                    public void onSuccess(Method method, TestResultView testResult) {
                                        if (testResult.getResult()) {
                                            codeLabel.setText("Тест пройден ");
                                        } else {
                                            codeLabel.setText("Тест провален ");
                                        }
                                        for (Map.Entry<Integer, Boolean> entry : testResult.getMapAnswer().entrySet()) {
                                            codeLabel.setText(codeLabel.getText() + entry.getValue() + " ");
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        final Button getResults = new Button("Получить результаты");
        RootPanel.get("right").add(getResults);
        final VerticalPanel verticalPanelResults = new VerticalPanel();
        RootPanel.get("right").add(verticalPanelResults);
        getResults.addClickHandler(new ViewResults(addQestion, verticalPanelResults));  // получить все результаты

        class MyHandlerGetQuestion implements ClickHandler { // кнопка получить все вопросы
            public void onClick(ClickEvent event) {
                addQestion.getAllQuestion(new MethodCallback<List<QuestionView>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        codeLabel.setText("Error MyHandlerGetQuestion");
                    }

                    @Override
                    public void onSuccess(Method method, List<QuestionView> question) {
                        viewQuestion(question);
                    }
                });
            }

            private void viewQuestion(List<QuestionView> question) {
                verticalPanel.clear();
                for (QuestionView vopro : question) {
                    final Integer id = vopro.getId(); // id
                    final Label questionId = new Label("id вопроса - " + id.toString());  // id
                    Label questionText = new Label("Вопрос: " + vopro.getQuestion()); // вопрос

                    final List<String> listAnswer = new ArrayList(); // Выбранные ответы
                    HorizontalPanel horizontalPanel1Answer = new HorizontalPanel();
                    if (vopro.getType() == 1 || vopro.getType() == 2) {
                        for (String s : vopro.getChoicesAnswer()) {  // варианты ответа
                            final Label answerLabel = new Label(s); // ответ
                            HorizontalPanel horizontalPanel12 = new HorizontalPanel();
                            if (vopro.getType() == 1) {
                                final RadioButton rb = new RadioButton("myRadioGroup" + id);
                                horizontalPanel12.add(rb);
                                rb.addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent clickEvent) {
                                        if (rb.getValue()) {
                                            listAnswer.clear();
                                            listAnswer.add(answerLabel.getText());
                                        }
                                    }
                                });
                            } else {
                                final CheckBox rb = new CheckBox();
                                horizontalPanel12.add(rb);
                                rb.addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent clickEvent) {
                                        String text = answerLabel.getText();
                                        Integer index = listAnswer.indexOf(text);
                                        if (rb.getValue()) {
                                            if (index == -1) {
                                                listAnswer.add(text);
                                            }
                                        } else {
                                            listAnswer.remove(text);
                                        }
                                    }
                                });
                            }
                            horizontalPanel12.add(answerLabel);
                            horizontalPanel1Answer.add(horizontalPanel12);
                        }
                    }

                    if (vopro.getType() == 0) {
                        final TextBox answerText = new TextBox(); // сам ответ
                        horizontalPanel1Answer.add(answerText);
                        answerText.addValueChangeHandler(new ValueChangeHandler<String>() {
                            @Override
                            public void onValueChange(ValueChangeEvent<String> valueChangeEvent) {
                                listAnswer.clear();
                                listAnswer.add(answerText.getText());
                            }
                        });
                    }

                    Button getAnswer = new Button("Ответить"); // отправить ответ
                    verticalPanel.add(questionId);
                    verticalPanel.add(questionText);
                    verticalPanel.add(horizontalPanel1Answer);
                    verticalPanel.add(getAnswer);

                    getAnswer.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            AnswerView answerView = new AnswerView();
                            answerView.setId(id);
                            answerView.setAnswer(listAnswer);
                            addQestion.sendAnswer(answerView, new MethodCallback<Boolean>() {
                                @Override
                                public void onFailure(Method method, Throwable throwable) {
                                    codeLabel.setText("что то пошло не так getAnswer.addClickHandler");
                                }

                                @Override
                                public void onSuccess(Method method, Boolean s) {
                                    if (s) {
                                        codeLabel.setText("Ответ успешно принят");
                                    } else {
                                        codeLabel.setText("Ответ неверный");
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }
        MyHandlerGetQuestion handler2 = new MyHandlerGetQuestion();
        getQuestion.addClickHandler(handler2);
    }
}


