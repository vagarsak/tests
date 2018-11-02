package ru.company.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import ru.company.shared.Message;

public class RestyGwtApp implements EntryPoint {

    public void onModuleLoad() {

        Defaults.setServiceRoot(GWT.getHostPageBaseURL());

        final Button sendButton = new Button("Отправить");
        final TextBox nameField = new TextBox();
        nameField.setText("Ввведите что нибудь на отправку на сервер");
        final Label errorLabel = new Label();
        final Label codeLabel = new Label();
        sendButton.addStyleName("sendButton");

        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);


        final HelloClient client = GWT.create(HelloClient.class);

        class MyHandler implements ClickHandler {

            public void onClick(ClickEvent event) {
                sendNameToServer();
            }

            private void sendNameToServer() {
                Message message = new Message();
                message.setMessage(nameField.getText());

                client.getPostHellos(message,new MethodCallback<Message>() {

                    public void onSuccess(Method method, Message response) {
                        codeLabel.setText(response.getMessage());
                    }

                    public void onFailure(Method method, Throwable exception) {
                        Label label = new Label("Error");
                    }
                });
            }
        }

        RootPanel.get("code").add(codeLabel);

        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
    }
}