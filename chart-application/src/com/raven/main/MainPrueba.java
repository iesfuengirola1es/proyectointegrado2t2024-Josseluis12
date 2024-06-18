package com.raven.main;

import com.raven.service.TranslationService;

public class MainPrueba {

    public static void main(String[] args) {
        String textoOriginal = "Hello, how are you?";
        String idiomaDestino = "español"; // Idioma destino deseado para la traducción

        try {
            String[] resultados = TranslationService.corregirYTraducir(textoOriginal, idiomaDestino);
            String textoCorregido = resultados[0];
            String textoTraducido = resultados[1];

            // Mostrar resultados
            System.out.println("Texto original: " + textoOriginal);
            System.out.println("Texto corregido: " + textoCorregido);
            System.out.println("Texto traducido: " + textoTraducido);

        } catch (Exception ex) {
            ex.printStackTrace();
            // Manejo de errores
        }
    }
}
