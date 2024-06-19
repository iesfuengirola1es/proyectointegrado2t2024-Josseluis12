package com.raven.model;

import com.raven.app.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Send_Message {

    private MessageType messageType;
    private int fromUserID;
    private int toUserID;
    private String originalText; // Cambio de 'text' a 'originalText'
    private Model_File_Sender file;
    private String translatedText; // Nuevo campo para el mensaje traducido

    public Model_Send_Message(MessageType messageType, int fromUserID, int toUserID, String originalText) {
        this.messageType = messageType;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.originalText = originalText; // Inicialización del mensaje original
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public Model_File_Sender getFile() {
        return file;
    }

    public void setFile(Model_File_Sender file) {
        this.file = file;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageType", messageType.getValue());
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            json.put("originalText", originalText); // Incluir el mensaje original
            json.put("translatedText", translatedText); // Incluir el mensaje traducido
            if (messageType == MessageType.FILE || messageType == MessageType.IMAGE) {
                json.put("text", file.getFileExtensions());
            } else {
                json.put("text", originalText); // Enviar el texto original al frontend
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
