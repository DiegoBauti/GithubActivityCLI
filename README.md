# GitHubActivityCLI

Una interfaz de línea de comandos (CLI) sencilla para obtener y mostrar la actividad reciente de un usuario de GitHub usando la API de GitHub. Construido en Java con Gson para el manejo de JSON.

## Funcionalidades

- Obtener los eventos recientes de cualquier usuario de GitHub.
- Soporta tipos de eventos como `PushEvent`, `CreateEvent`, `ForkEvent`, `IssuesEvent`, `PullRequestEvent`, entre otros.
- Muestra la salida en un formato legible con tipo de evento, repositorio, acción y fecha/hora.

## Requisitos

- Java 21 o superior
- Maven (para construir el proyecto)

## Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tuusuario/githubactivitycli.git
   cd githubactivitycli


2. Construir el proyecto con Maven:

   ```bash
   mvn clean package
   ```

   Esto generará un archivo `jar` con todas las dependencias dentro de `target/`.

## Uso

Ejecutar la CLI desde la línea de comandos:

```bash
java -jar target/APIGithub-1.0-SNAPSHOT-jar-with-dependencies.jar <nombre-de-usuario>
```

Ejemplo:

```bash
java -jar target/APIGithub-1.0-SNAPSHOT-jar-with-dependencies.jar kamranahmedse
```

Salida:

```
[PushEvent] Pushed commit to kamranahmedse/developer-roadmap (2025-10-22 05:39:20)
[CreateEvent] Create branch in kamranahmedse/developer-roadmap (2025-10-28 07:24:57)
[WatchEvent] Watch in Obra-Studio/obra-icons-mr (2025-10-23 02:10:00)

