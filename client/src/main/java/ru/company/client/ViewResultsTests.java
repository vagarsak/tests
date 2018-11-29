package ru.company.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.company.shared.*;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewResultsTests {
    private AddQuestion addQestion;
    private VerticalPanel verticalPanelResultsTests = new VerticalPanel();
    private VerticalPanel verticalPanelResultsUser = new VerticalPanel();

    public ViewResultsTests(AddQuestion addQestion, HorizontalPanel horizontalPanelResults) {
        this.addQestion = addQestion;
        horizontalPanelResults.add(verticalPanelResultsTests);
        horizontalPanelResults.add(verticalPanelResultsUser);
    }


    public void getAllResultsTests() {   // получить результаты тестов
        addQestion.geResultsTest(new MethodCallback<List<TestResultsView>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }

            @Override
            public void onSuccess(Method method, List<TestResultsView> ResultList) {
                verticalPanelResultsTests.clear();
                if(ResultList.size() == 0){
                    verticalPanelResultsTests.add(new Label("Тесты ещё не проходили"));
                }else{
                    HorizontalPanel horizontalPanel = new HorizontalPanel();
                    horizontalPanel.add(new Label("id"));
                    horizontalPanel.add(new Label("Пользователь"));
                    horizontalPanel.add(new Label("Вопросы"));
                    horizontalPanel.add(new Label("Ответы"));
                    horizontalPanel.add(new Label("Результат"));
                    verticalPanelResultsTests.add(horizontalPanel);
                }
                for(TestResultsView testResultsView : ResultList){
                    HorizontalPanel horizontalPanelTest = new HorizontalPanel();
                    horizontalPanelTest.add(new Label(testResultsView.getId().toString()));
                    horizontalPanelTest.add(new Label(testResultsView.getNameUser()));

                    VerticalPanel verticalPanel = new VerticalPanel();
                    for(Map.Entry<String,List<String>> questionAnswer :testResultsView.getQuestionAnswer().entrySet()){
                        HorizontalPanel horizontalQuestionAnswer = new HorizontalPanel();
                        VerticalPanel verticalPanelQuestion = new VerticalPanel();
                        VerticalPanel verticalPanelAnswer = new VerticalPanel();
                        horizontalQuestionAnswer.add(verticalPanelQuestion);
                        horizontalQuestionAnswer.add(verticalPanelAnswer);

                        String question = questionAnswer.getKey();
                        Label questionText = new Label("Вопрос: " + question);
                        verticalPanelQuestion.add(questionText);
                        for(String str : questionAnswer.getValue()){
                            Label answerText = new Label("Ответ: " + str);
                            verticalPanelAnswer.add(answerText);
                        }
                        verticalPanel.add(horizontalQuestionAnswer);
                    }
                    horizontalPanelTest.add(verticalPanel);
                    String result = "Не пройден";
                    if(testResultsView.getResult() != null){
                        result =  testResultsView.getResult() ? "Пройден успешно" : "Завален";
                    }
                    horizontalPanelTest.add(new Label(result));
                    verticalPanelResultsTests.add(horizontalPanelTest);
                }
            }
        });
    }

    public void resultUser() {   // получить результаты правильных вопросов
        verticalPanelResultsUser.clear();
        final Label LabelListBox = new Label("Выберите пользователя");
        final ListBox lb = new ListBox();
        lb.clear();

        verticalPanelResultsUser.add(LabelListBox);
        verticalPanelResultsUser.add(lb);
        final VerticalPanel verticalPanelQuestionUser = new VerticalPanel();
        verticalPanelResultsUser.add(verticalPanelQuestionUser);

        addQestion.getAllUser(new MethodCallback<List<UserView>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }

            @Override
            public void onSuccess(Method method, List<UserView> ResultList) {
                for(UserView userView : ResultList){
                    lb.addItem(userView.getName());
                }
                lb.addChangeHandler(new ChangeHandler() {

                    @Override
                    public void onChange(ChangeEvent event) {
                        final String username = lb.getItemText(lb.getSelectedIndex());
                        addQestion.getAllQuestionSuccess(username, new MethodCallback<List<String>>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                verticalPanelQuestionUser.clear();
                            }

                            @Override
                            public void onSuccess(Method method, List<String> questions) {
                                verticalPanelQuestionUser.clear();
                                for (String question : questions) {
                                    Label questionText = new Label("Вопрос: " + question); // вопрос
                                    HorizontalPanel horizontalPanelQuestion = new HorizontalPanel();
                                    horizontalPanelQuestion.add(questionText);
                                    verticalPanelQuestionUser.add(horizontalPanelQuestion);
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}
