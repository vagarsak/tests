package ru.company.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import ru.company.shared.*;

public class RestyGwtApp implements EntryPoint {

    private final Label codeLabel = new Label(); // убрать отсюда позже
    private final AddQuestion addQestion = GWT.create(AddQuestion.class);

    private final HorizontalPanel horizontalPanel = new HorizontalPanel();
    private final HorizontalPanel horizontalPanelGetTest = new HorizontalPanel();
    private final HorizontalPanel horizontalPanelGetResult = new HorizontalPanel();
    private final HorizontalPanel horizontalPanelGetResultTests = new HorizontalPanel();
    private final HorizontalPanel horizontalPanelAuth = new HorizontalPanel();
    private final TabPanel tabPanel = new TabPanel();

    public void onModuleLoad() {
        final UserView user_autentification = new UserView();
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        RootPanel.get("center").add(codeLabel); // вспомогательная инфа

        final ViewResultsQuestions viewResults = new ViewResultsQuestions(addQestion, horizontalPanelGetResult);
        final ViewResultsTests viewResultsTests = new ViewResultsTests(addQestion, horizontalPanelGetResultTests);

        final String nameTabBar1 = "Создать вопрос";
        final String nameTabBar2 = "Пройти тест";
        final String nameTabBar3 = "Просмотреть результаты вопросов";
        final String nameTabBar4 = "Просмотреть результаты тестов";

        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> selectionEvent) {
                String nameTabBar = tabPanel.getTabBar().getTabHTML(selectionEvent.getSelectedItem());
                if(nameTabBar.equals(nameTabBar3)){
                    viewResults.onClick();  // обновляем результаты вопросов
                }
                if(nameTabBar.equals(nameTabBar4)){
                    viewResultsTests.getAllResultsTests(); // обновляем результаты тестов
                    if(user_autentification.getAuthorities().get(0).equals("ROLE_ADMIN") ){
                        viewResultsTests.resultUser(); // запрос всех пользователей
                    }
                }
            }
        });

        addQestion.getUser(new MethodCallback<UserView>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                codeLabel.setText("Пользователь: ошибка");
            }
            @Override
            public void onSuccess(Method method, UserView user) {
                user_autentification.setAuthorities(user.getAuthorities());
                for(int i =0; i< user.getAuthorities().size(); i++){
                    switch (user.getAuthorities().get(i)){
                        case "ROLE_USER":
                            tabPanel.add(horizontalPanelGetTest, nameTabBar2);
                            tabPanel.add(horizontalPanelGetResultTests, nameTabBar4);
                            break;
                        case "ROLE_ADMIN":
                            tabPanel.add(horizontalPanel, nameTabBar1);
                            tabPanel.add(horizontalPanelGetTest, nameTabBar2);
                            tabPanel.add(horizontalPanelGetResult, nameTabBar3);
                            tabPanel.add(horizontalPanelGetResultTests, nameTabBar4);
                    }
                }
                tabPanel.selectTab(0);
                authorized(user);
            }
        });
    }

    private  void authorized(UserView user){
        RootPanel.get("center").add(horizontalPanelAuth); // информация о пользователе
        RootPanel.get("center").add(tabPanel);
        Label authName = new Label("Пользователь: " + user.getName());
        horizontalPanelAuth.add(authName);
        final Button logout = new Button("Выйти");
        logout.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Window.open("/logout", "_self", "");
            }
        });
        horizontalPanelAuth.add(logout);

        final VerticalPanel verticalPanel2 = new VerticalPanel();
        final ListBox lb = new ListBox();
        final Label LabelListBox = new Label("Выберите тип вопроса");
        lb.addItem("Текстовый ответ"); // 0
        lb.addItem("1 ответ на выбор"); // 1
        lb.addItem("Несколько ответов"); // 2
        lb.addChangeHandler(new ChangeHandler() {
            ViewСreateQuestion viewСreateQuestion = new ViewСreateQuestion(codeLabel,addQestion);
            @Override
            public void onChange(ChangeEvent event) {
                verticalPanel2.clear();
                switch (lb.getSelectedIndex()) {
                    case 1:
                        viewСreateQuestion.answerMultiple(verticalPanel2, "RadioButton");
                        break;
                    case 2:
                        viewСreateQuestion.answerMultiple(verticalPanel2, "CheckBox");;
                        break;
                    default:
                        viewСreateQuestion.answerText(verticalPanel2);
                }
            }
        });
        horizontalPanel.add(LabelListBox);
        horizontalPanel.add(lb);
        horizontalPanel.add(verticalPanel2);

        final VerticalPanel verticalPanel = new VerticalPanel(); // вопросы отображаются здесь
        final ViewTests viewTests = new ViewTests(codeLabel,addQestion);
        final Button getQuestion = new Button("Получить все вопросы");
        getQuestion.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                viewTests.getQuestions(verticalPanel);
            }
        });
        final Button getTest = new Button("Пройти тест");
        getTest.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                viewTests.getTest(verticalPanel);
            }
        });
        switch (user.getAuthorities().get(0)){
            case "ROLE_USER":
                horizontalPanelGetTest.add(getTest);
                break;
            case "ROLE_ADMIN":
                horizontalPanelGetTest.add(getQuestion);
                horizontalPanelGetTest.add(getTest);
        }
        horizontalPanelGetTest.add(verticalPanel);
    }
}


