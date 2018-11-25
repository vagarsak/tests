package ru.company.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.company.shared.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewTests {
    private final Label codeLabel; // убрать отсюда позже
    private final AddQuestion addQestion;

    public ViewTests(Label codeLabel, AddQuestion addQestion) {
        this.codeLabel = codeLabel;
        this.addQestion = addQestion;
    }

    public void getTest(final VerticalPanel verticalPanel){
        addQestion.getTest(new MethodCallback<TestView>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                codeLabel.setText("Ошибка getResults.addClickHandler");
            }

            @Override
            public void onSuccess(Method method, final TestView test) {
                verticalPanel.clear();
                final Map<Integer, List<String>> mapAnswer = new HashMap<>();
                Label testId = new Label("Тест: " + test.getId()); // тест номер
                verticalPanel.add(testId);
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
                                verticalPanel.clear();
                                String result = "";
                                if (testResult.getResult()) {
                                    result = "Тест пройден ";
                                } else {
                                    result = "Тест провален ";
                                    for (Map.Entry<Integer, Boolean> entry : testResult.getMapAnswer().entrySet()) {
                                        result += entry.getValue() + " ";
                                    }
                                }
                                verticalPanel.add(new Label(result));
                            }
                        });
                    }
                });
            }
        });
    }

    public void getQuestions(final VerticalPanel verticalPanel){
        addQestion.getAllQuestion(new MethodCallback<List<QuestionView>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                codeLabel.setText("Error MyHandlerGetQuestion");
            }

            @Override
            public void onSuccess(Method method, List<QuestionView> question) {
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
        });
    }
}
