# proyectointegrado2t2024-Josseluis12
proyectointegrado2t2024-Josseluis12 created by GitHub Classroom

# Proyecto Final: "Damchat" - Desarrollo de una Aplicación de Chat en Java

## Introducción

El proyecto "Damchat" tiene como objetivo desarrollar una aplicación de chat que facilite la comunicación entre usuarios mediante una interfaz cliente-servidor. La aplicación estará desarrollada en Java y se implementará un servidor que actúe como API REST para gestionar los mensajes entre usuarios, utilizando una base de datos MySQL para almacenar la información. Además, se explorará la posibilidad de crear un cliente Android que permita a los usuarios interactuar con la misma lógica de comunicación.

## Objetivos

Los objetivos del proyecto son:

- Diseñar e implementar un servidor en Java que funcione como API REST para gestionar la comunicación entre usuarios.
- Desarrollar un cliente de escritorio en Java para que los usuarios envíen y reciban mensajes a través del servidor.
- Investigar y diseñar una interfaz de usuario para un cliente Android que permita la misma interacción con el servidor.
- Integrar una base de datos MySQL para almacenar usuarios, conversaciones y mensajes.
- Explorar funcionalidades adicionales como grupos de usuarios, envío de archivos y emojis para mejorar la experiencia del usuario.
- Implementar una API en Python con inteligencia artificial para la traducción de mensajes entre diferentes idiomas.
- Realizar pruebas exhaustivas para garantizar la estabilidad y seguridad de la aplicación.

## Metodología

El desarrollo del proyecto seguirá una metodología ágil con iteraciones cortas y entregas incrementales. Se utilizará el ciclo de vida en espiral para adaptarse a cambios y añadir nuevas funcionalidades de manera iterativa. Además, se empleará Git para el control de versiones y facilitar la colaboración entre el equipo.

## Descripción del Sistema

El sistema constará de dos componentes principales: un servidor API REST y clientes para escritorio y Android. El servidor gestionará las solicitudes de los clientes, procesará y almacenará los mensajes en la base de datos. Los clientes permitirán a los usuarios enviar y recibir mensajes a través del servidor.

## Tecnologías Utilizadas

- Java: Desarrollo del servidor y clientes.
- MySQL: Gestión de la base de datos para almacenar usuarios y mensajes.
- Python: Implementación de una API con inteligencia artificial para la traducción de mensajes.
- Android Studio: Desarrollo del cliente Android.
- Git: Control de versiones del código fuente.
- JSON: Método de transferencia de datos entre cliente y servidor.

## Funcionalidades Principales

- Registro e inicio de sesión de usuarios.
- Envío y recepción de mensajes de texto en tiempo real.
- Almacenamiento de mensajes en la base de datos para recuperación posterior.
- Creación y gestión de grupos de usuarios.
- Envío de archivos y emojis entre usuarios.
- Traducción automática de mensajes utilizando la API de inteligencia artificial.

## Planificación

1. Investigación y diseño de la arquitectura del sistema.
2. Implementación del servidor API REST en Java.
3. Desarrollo del cliente de escritorio en Java.
4. Diseño y desarrollo del cliente Android.
5. Integración de la base de datos MySQL y pruebas de integración.
6. Implementación de la API de inteligencia artificial para la traducción.
7. Implementación de funcionalidades adicionales y pruebas de aceptación.
8. Documentación y preparación para la presentación del proyecto.

## Mockup y Wireframe

- Pantalla inicial de registro e inicio de sesión

![Imagen de registro](ruta_a_tu_imagen)

![Imagen de inicio de sesión](ruta_a_tu_imagen)

- Pantalla principal del chat

![Imagen del chat](ruta_a_tu_imagen)

# Damchat - Base de Datos

## Entidades y Atributos

### 1. Usuario

| Atributo        | Tipo de Dato  | Clave |
|-----------------|---------------|-------|
| id_usuario      | INT           | PK    |
| nombre_usuario  | VARCHAR(255)  |       |
| contraseña      | VARCHAR(255)  |       |
| email           | VARCHAR(255)  |       |
| fecha_creacion  | DATETIME      |       |

### 2. Conversación

| Atributo        | Tipo de Dato  | Clave |
|-----------------|---------------|-------|
| id_conversacion | INT           | PK    |
| titulo          | VARCHAR(255)  |       |

### 3. Mensaje

| Atributo        | Tipo de Dato  | Clave | Relación           |
|-----------------|---------------|-------|--------------------|
| id_mensaje      | INT           | PK    |                    |
| id_conversacion | INT           | FK    | Pertenece a Conversación |
| id_usuario      | INT           | FK    | Envía Usuario      |
| mensaje         | TEXT          |       |                    |
| fecha_hora      | DATETIME      |       |                    |

### 4. Participante

| Atributo        | Tipo de Dato  | Clave | Relación           |
|-----------------|---------------|-------|--------------------|
| id_usuario      | INT           | FK    | Participa en Usuario |
| id_conversacion | INT           | FK    | Participa en Conversación |




https://github.com/iesfuengirola1es/proyectointegrado2t2024-Josseluis12/assets/145585591/5bc50448-1488-4701-984f-6520864ed483

