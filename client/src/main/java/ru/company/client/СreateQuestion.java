package ru.company.client;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.ui.*;

public class СreateQuestion implements MethodCallback<String> {

    private Label codeLabel;

    public СreateQuestion(Label codeLabel) {
        this.codeLabel = codeLabel;
    }

    @Override
    public void onFailure(Method method, Throwable throwable) {
        codeLabel.setText("Error");
    }

    @Override
    public void onSuccess(Method method, String s) {
        codeLabel.setText("Успешно создан вопрос");
    }
}
