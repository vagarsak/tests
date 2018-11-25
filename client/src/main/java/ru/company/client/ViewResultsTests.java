package ru.company.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.company.shared.ResultView;
import ru.company.shared.TestResultsView;

import java.util.List;

public class ViewResultsTests {
    private AddQuestion addQestion;
    private HorizontalPanel horizontalPanelResults;
    private VerticalPanel verticalPanelResultsTests = new VerticalPanel();

    public ViewResultsTests(AddQuestion addQestion, HorizontalPanel horizontalPanelResults) {
        this.addQestion = addQestion;
        this.horizontalPanelResults = horizontalPanelResults;
        horizontalPanelResults.add(verticalPanelResultsTests);
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
                    horizontalPanel.add(new Label("Результат"));
                    verticalPanelResultsTests.add(horizontalPanel);
                }
                for(TestResultsView testResultsView : ResultList){
                    HorizontalPanel horizontalPanelTest = new HorizontalPanel();
                    horizontalPanelTest.add(new Label(testResultsView.getId().toString()));
                    horizontalPanelTest.add(new Label(testResultsView.getNameUser()));
                    VerticalPanel verticalPanel = new VerticalPanel();
                    horizontalPanelTest.add(verticalPanel);
                    for(String question :testResultsView.getQuestion()){
                        Label questionText = new Label("Вопрос: " + question);
                        verticalPanel.add(questionText);
                    }
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
}
