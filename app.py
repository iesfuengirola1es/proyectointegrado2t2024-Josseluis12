from flask import Flask, request, jsonify
import openai

app = Flask(__name__)

# Configura las credenciales de la API de OpenAI
openai.api_key = 'sk-4mgIzrcdBVpS7fw52M7VU3BlbkF2G4C5qJORSeSlDGzZP'

@app.route("/corregir_y_traducir", methods=["POST"])
def corregir_y_traducir():
    # Obtener datos del texto y el idioma destino desde la solicitud JSON
    data = request.json
    texto_original = data.get("texto_original")
    idioma_destino = data.get("idioma_destino", "español")

    # Verificar si se proporcionó un texto original
    if not texto_original:
        return jsonify({"error": "El campo 'texto_original' es requerido."}), 400

    try:
        # Corregir y traducir el texto
        texto_corregido, texto_traducido = corregir_y_traducir_funcion(texto_original, idioma_destino)
        
        # Devolver el texto corregido y traducido como JSON
        return jsonify({
            "texto_corregido": texto_corregido,
            "texto_traducido": texto_traducido
        })

    except Exception as e:
        return jsonify({"error": str(e)}), 500  # Devolver un mensaje de error 500 si ocurre una excepción

@app.route("/consulta", methods=["POST"])
def consulta():
    # Obtener datos de la consulta y el contexto desde la solicitud JSON
    data = request.json
    consulta = data.get("consulta")
    contexto = data.get("contexto")

    # Verificar si se proporcionó una consulta y un contexto
    if not consulta:
        return jsonify({"error": "El campo 'consulta' es requerido."}), 400
    if not contexto:
        return jsonify({"error": "El campo 'contexto' es requerido."}), 400

    try:
        # Generar una respuesta utilizando la API de OpenAI
        respuesta = generar_respuesta(consulta, contexto)
        
        # Devolver la respuesta como JSON
        return jsonify({"respuesta": respuesta})

    except Exception as e:
        return jsonify({"error": str(e)}), 500  # Devolver un mensaje de error 500 si ocurre una excepción

def corregir_y_traducir_funcion(texto, idioma_destino):
    # Corregir el texto en su idioma original
    prompt_correccion = f"Corrige el siguiente texto en su idioma original:\n\n{texto}"
    respuesta_correccion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "Eres un asistente que corrige y traduce textos."},
            {"role": "user", "content": prompt_correccion}
        ],
        max_tokens=100
    )
    texto_corregido = respuesta_correccion['choices'][0]['message']['content'].strip()
    
    # Traducir el texto corregido al idioma destino
    prompt_traduccion = f"Traduce el siguiente texto al {idioma_destino}:\n\n{texto_corregido}"
    respuesta_traduccion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "Eres un asistente que corrige y traduce textos."},
            {"role": "user", "content": prompt_traduccion}
        ],
        max_tokens=100
    )
    texto_traducido = respuesta_traduccion['choices'][0]['message']['content'].strip()
    
    return texto_corregido, texto_traducido

def generar_respuesta(consulta, contexto):
    # Generar una respuesta utilizando la API de OpenAI
    response = openai.Completion.create(
        engine="davinci",
        prompt=f"{consulta}\nContexto: {contexto}",
        max_tokens=150,
    )
    respuesta = response.choices[0].text.strip()
    return respuesta

if __name__ == "__main__":
    app.run(debug=True)
