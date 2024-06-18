package com.raven.service;

import okhttp3.*;

import java.io.IOException;

public class TranslationService {

    public interface TranslationCallback {
        void onSuccess(String translatedMessage);
        void onFailure(Exception e);
    }

    private final OkHttpClient client;
    private static final String API_URL = "http://127.0.0.1:5000/corregir_y_traducir";

    public TranslationService() {
        client = new OkHttpClient();
    }

    public void translateMessage(String message, String targetLanguage, TranslationCallback callback) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = "{\"texto_original\":\"" + message + "\", \"idioma_destino\":\"" + targetLanguage + "\"}";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        System.out.println("Sending request to translation API: " + json); // Debug message

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Translation API call failed: " + e.getMessage()); // Debug message
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    System.out.println("Translation API call unsuccessful: " + response); // Debug message
                    callback.onFailure(new IOException("Unexpected code " + response));
                    return;
                }

                String responseData = response.body().string();
                System.out.println("Received response from translation API: " + responseData); // Debug message
                String translatedMessage = extractTranslatedText(responseData);
                callback.onSuccess(translatedMessage);
            }
        });
    }

    private String extractTranslatedText(String responseData) {
        // Parsear el JSON manualmente o usar una librería como Jackson o Gson
        int startIndex = responseData.indexOf("\"texto_traducido\":\"") + 19;
        int endIndex = responseData.indexOf("\"", startIndex);
        return responseData.substring(startIndex, endIndex);
    }
}
