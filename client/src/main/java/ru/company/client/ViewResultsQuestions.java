package ru.company.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.company.shared.ResultView;
import ru.company.shared.UserView;

import java.util.List;

public class ViewResultsQuestions {
    private AddQuestion addQestion;
    private VerticalPanel verticalPanelResults = new VerticalPanel();

    public ViewResultsQuestions(AddQuestion addQestion, HorizontalPanel horizontalPanelResults) {
        this.addQestion = addQestion;
        horizontalPanelResults.add(verticalPanelResults);
    }

    public void onClick() {   // получить результаты вопросов
        addQestion.getAllResults(new MethodCallback<List<ResultView>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }

            @Override
            public void onSuccess(Method method, List<ResultView> ResultList) {
                verticalPanelResults.clear();
                if(ResultList.size() == 0){
                    verticalPanelResults.add(new Label("Тесты ещё не проходили"));
                }else{
                    HorizontalPanel horizontalPanel = new HorizontalPanel();
                    horizontalPanel.add(new Label("id"));
                    horizontalPanel.add(new Label("Вопрос"));
                    horizontalPanel.add(new Label("Всего ответов"));
                    horizontalPanel.add(new Label("Правильных ответов"));
                    verticalPanelResults.add(horizontalPanel);
                }
                for (ResultView result : ResultList) {
                    HorizontalPanel horizontalPanel = new HorizontalPanel();
                    horizontalPanel.add(new Label(result.getId().toString()));
                    horizontalPanel.add(new Label(result.getQuestion()));
                    horizontalPanel.add(new Label(result.getAnswers().get("quantity").toString()));
                    horizontalPanel.add(new Label(result.getAnswers().get("successful").toString()));
                    verticalPanelResults.add(horizontalPanel);
                }
            }
        });
    }
}
