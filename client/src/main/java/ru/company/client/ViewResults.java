package ru.company.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.company.shared.ResultView;

import java.util.List;

public class ViewResults implements ClickHandler {
    private AddQuestion addQestion;
    private VerticalPanel verticalPanelResults;

    public ViewResults(AddQuestion addQestion, VerticalPanel verticalPanelResults) {
        this.addQestion = addQestion;
        this.verticalPanelResults = verticalPanelResults;
    }

    @Override
    public void onClick(ClickEvent clickEvent) {   // получить результаты тестов
        addQestion.getAllResults(new MethodCallback<List<ResultView>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }

            @Override
            public void onSuccess(Method method, List<ResultView> ResultList) {
                verticalPanelResults.clear();
                for (ResultView result : ResultList) {
                    String resultText = result.getId() + " " +
                            result.getQuestion() + " всего = " +
                            result.getAnswers().get("quantity") + " удачных = " +
                            result.getAnswers().get("successful");
                    final Label resulLabel = new Label(resultText);
                    verticalPanelResults.add(resulLabel);
                }
            }
        });
    }
}
