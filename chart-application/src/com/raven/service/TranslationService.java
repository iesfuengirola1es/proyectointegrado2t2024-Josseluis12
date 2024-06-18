package com.raven.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TranslationService {

    private static final String SERVER_URL = "http://localhost:5000/corregir_y_traducir"; // URL de tu API local

    public static String[] corregirYTraducir(String textoOriginal, String idiomaDestino) throws IOException {
        URL url = new URL(SERVER_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configuración de la conexión
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Creación del cuerpo JSON de la solicitud
        String jsonInputString = "{\"texto_original\": \"" + textoOriginal + "\", \"idioma_destino\": \"" + idiomaDestino + "\"}";

        // Envío de la solicitud
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(jsonInputString);
            outputStream.flush();
        }

        // Lectura de la respuesta
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Parseo de la respuesta JSON
        String textoCorregido = null;
        String textoTraducido = null;
        if (response.length() > 0) {
            String jsonResponse = response.toString();
            textoCorregido = jsonResponse.split("\"texto_corregido\":")[1].split(",")[0].trim();
            textoTraducido = jsonResponse.split("\"texto_traducido\":")[1].split("}")[0].trim();
            textoCorregido = textoCorregido.substring(1, textoCorregido.length() - 1); // Eliminar comillas dobles
            textoTraducido = textoTraducido.substring(1, textoTraducido.length() - 1); // Eliminar comillas dobles
        }

        return new String[]{textoCorregido, textoTraducido};
    }
}
