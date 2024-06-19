package com.raven.model;

import com.raven.app.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Receive_Message {

    private MessageType messageType;
    private int fromUserID;
    private String text;
    private Model_Receive_Image dataImage;
    private Model_File_Sender file; // Añadido para los mensajes de archivo

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Receive_Image getDataImage() {
        return dataImage;
    }

    public void setDataImage(Model_Receive_Image dataImage) {
        this.dataImage = dataImage;
    }

    public Model_File_Sender getFile() { // Añadido para obtener el archivo
        return file;
    }

    public void setFile(Model_File_Sender file) { // Añadido para establecer el archivo
        this.file = file;
    }

    public Model_Receive_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            messageType = MessageType.toMessageType(obj.getInt("messageType"));
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            if (!obj.isNull("dataImage")) {
                dataImage = new Model_Receive_Image(obj.getJSONObject("dataImage"));
            }
            if (!obj.isNull("file")) { // Añadido para los mensajes de archivo
                file = new Model_File_Sender(obj.getJSONObject("file"));
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageType", messageType.getValue());
            json.put("fromUserID", fromUserID);
            json.put("text", text);
            if (dataImage != null) {
                json.put("dataImage", dataImage.toJsonObject());
            }
            if (file != null) { // Añadido para los mensajes de archivo
                json.put("file", file.toJsonObject());
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
