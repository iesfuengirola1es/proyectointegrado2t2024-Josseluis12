package com.raven.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainPrueba {

    public static void main(String[] args) throws Exception {
        // URL de la API en Flask
        String url = "http://localhost:5000/corregir_y_traducir";

        // Datos a enviar en el cuerpo JSON
        String jsonInputString = "{\"texto_original\": \"Hola mi nombre es jose y mengo muchas ganas de ir a la playa\", \"idioma_destino\": \"japones\"}";

        // Crear objeto URL y conexión HTTP
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        // Configurar la conexión
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Enviar datos JSON
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Leer la respuesta del servidor
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
