package com.raven.component;

import com.raven.app.MessageType;
import com.raven.event.PublicEvent;
import com.raven.model.Model_Send_Message;
import com.raven.model.Model_User_Account;
import com.raven.service.Service;
import com.raven.service.TranslationService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat_Bottom extends javax.swing.JPanel {

    private Model_User_Account user;
    private TranslationService translationService;

    public Chat_Bottom() {
        initComponents();
        init();
    }

    private void init() {
        translationService = new TranslationService();
        cmdSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                send();
            }
        });
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    private void send() {
        String text = txtMessage.getText().trim();
        if (!text.equals("")) {
            System.out.println("Message to send: " + text); // Debug message
            translateAndSendMessage(text, "italiano");
        }
    }

    private void translateAndSendMessage(String message, String targetLanguage) {
        System.out.println("Translating message to " + targetLanguage + ": " + message); // Debug message
        translationService.translateMessage(message, targetLanguage, new TranslationService.TranslationCallback() {
            @Override
            public void onSuccess(String translatedMessage) {
                System.out.println("Translation successful: " + translatedMessage); // Debug message
                Model_Send_Message sendMessage = new Model_Send_Message(MessageType.TEXT, Service.getInstance().getUser().getUserID(), user.getUserID(), translatedMessage);
                PublicEvent.getInstance().getEventChat().sendMessage(sendMessage);
                txtMessage.setText("");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                System.out.println("Error translating message: " + e.getMessage()); // Debug message
                // Optional: Display an error message in the UI
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMessage = new javax.swing.JTextField();
        cmdSend = new javax.swing.JButton();

        txtMessage.setToolTipText("Escribe tu mensaje aquí");

        cmdSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/send.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdSend)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMessage)
                                        .addComponent(cmdSend, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSend;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
