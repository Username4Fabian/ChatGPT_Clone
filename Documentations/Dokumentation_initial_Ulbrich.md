## 1. Projektübersicht

Das ChatGPT Clone-Projekt ist eine einfache Chat-Anwendung, die auf der GPT-3.5-Turbo-Engine von OpenAI basiert. Die Anwendung ermöglicht es Benutzern, mit einem Chatbot zu interagieren, der mithilfe von maschinellem Lernen auf Benutzereingaben reagiert.

## 2. Klassenübersicht

### 2.1 `ChatGptCloneApplication`

Die Hauptklasse des Projekts, die die Spring Boot-Anwendung startet.

**Beispiel:**

````java
@SpringBootApplication
public class ChatGptCloneApplication {   
public static void main(String[] args) {   
SpringApplication.run(ChatGptCloneApplication.class, args);     
System.out.println("Server is running at http://localhost:8080/");     } }
````


### 2.2 `Message`

Die Entity-Klasse, die die Datenstruktur für Chatnachrichten repräsentiert. Enthält Felder wie `id`, `sessionId`, `input` und `output`.

**Beispiel:**

````java

@Entitypublic class Message {
    // ... (Felder und Methoden)
}

````

### 2.4 `MessageController`

Der RestController, der Endpunkte für die Chat-Funktionalität bereitstellt. Hier werden Benutzeranfragen verarbeitet, Nachrichten in der Datenbank gespeichert und Chatverläufe abgerufen.

**Beispiel:**

````java
@RestController
public class MessageController {
    // ... (Rest-Endpunkte und Funktionalitäten)
}

````

### 2.5 `WebConfig`

Die Konfigurationsklasse für das Web MVC-Framework, die die Cross-Origin-Ressourcenfreigabe (CORS) konfiguriert.

**Beispiel:**

````java
@Configuration
public class WebConfig {
    // ... (CORS-Konfiguration)
}

````


### 2.6 `style.css`

Die CSS-Datei für das Frontend-Design der Chat-Anwendung.

**Beispiel:**

````css
/* Allgemeine Stile */
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;600;700;800&display=swap');

/* Globale Stile für alle Elemente */
* {
    color: #fff; /* Weiße Schriftfarbe für alle Elemente */
    font-family: 'Open Sans', sans-serif; /* Verwendung der 'Open Sans'-Schriftart */
}

/* Stile für den gesamten Body-Bereich */
body {
    margin: 0; /* Keine Standardmargen */
    padding: 0; /* Keine Standardpolsterung */
    background-color: #343541; /* Dunkelgraue Hintergrundfarbe */
    display: flex; /* Verwendung von Flexbox für die Anordnung der Elemente */
}

/* Stile für Überschriften */
h1 {
    font-size: 33px; /* Größe der Überschrift */
    font-weight: 600; /* Fettstärke der Überschrift */
    padding: 10px 0 0 0; /* Innenabstand der Überschrift */
    margin-top: 10px; /* Außenabstand oben */
    margin-bottom: 3px; /* Außenabstand unten */
    text-align: center; /* Zentrierte Ausrichtung der Überschrift */
}

/* Stile für die Seitenleiste */
.side-bar {
    background-color: #202123; /* Hintergrundfarbe der Seitenleiste */
    width: 244px; /* Breite der Seitenleiste */
    height: 100vh; /* Volle Höhe der Seitenleiste */
    display: flex; /* Verwendung von Flexbox für die Anordnung der Elemente */
    flex-direction: column; /* Anordnung in Spalten */
    justify-content: space-between; /* Platz zwischen den Elementen */
    align-items: center; /* Zentrierte Ausrichtung der Elemente */
    text-align: center; /* Zentrierter Text */
}

/* Stile für den Hauptinhalt */
#main {
    display: flex; /* Verwendung von Flexbox für die Anordnung der Elemente */
    flex-direction: column; /* Anordnung in Spalten */
    justify-content: space-between; /* Platz zwischen den Elementen */
    height: 100vh; /* Volle Höhe des Hauptinhalts */
    width: 100%; /* Volle Breite des Hauptinhalts */
}

/* Stile für den unteren Abschnitt */
.bottom-section {
    width: 100%; /* Volle Breite des unteren Abschnitts */
    display: flex; /* Verwendung von Flexbox für die Anordnung der Elemente */
    flex-direction: column; /* Anordnung in Spalten */
    justify-content: center; /* Zentrierte Ausrichtung der Elemente */
    align-items: center; /* Zentrierte Ausrichtung der Elemente */
}

/* Informationstext-Stile */
.info {
    color: rgba(255, 255, 255, 0.5); /* Textfarbe mit verringerter Sichtbarkeit */
    font-size: 8px; /* Schriftgröße */
    padding-top: 10px; /* Innenabstand oben */
    padding-bottom: 3px; /* Innenabstand unten */
    text-align: center; /* Zentrierte Ausrichtung des Textes */
}

/* Stile für Eingabefelder */
input {
    border: none; /* Keine Randstilisierung */
    background-color: lightslategray; /* Hintergrundfarbe der Eingabefelder */
    width: 100%; /* Volle Breite der Eingabefelder */
    font-size: 20px; /* Schriftgröße */
    padding: 12px 15px; /* Innenabstand */
    border-radius: 5px; /* Abrundung der Ecken */
    caret-color: #343541; /* Farbe des Einfügepunkts */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Schatteneffekt */
}

/* Fokus-Stile für Eingabefelder */
input:focus {
    outline: none; /* Kein Fokusrahmen */
}

/* Container-Stile für Eingabefelder */
.input-container {
    position: absolute; /* Absolute Positionierung */
    bottom: 50px; /* Abstand von unten */
    width: calc(75vw - 35px); /* Breite des Containers */
    max-width: 1500px; /* Maximale Breite des Containers */
    height: 50px}

````


### 2.7 `index.html`

Die HTML-Datei, die die Struktur der Chat-Anwendung definiert.

**Beispiel:**

````html
<!DOCTYPE html>
<html lang="de">
    <!-- ... (HTML-Struktur) -->
</html>

```
````


### 2.8 `script.js`

Die JavaScript-Datei, die die Logik für die Interaktion mit dem Chatbot enthält.

**Beispiel:**

````javascript

```// ... (JavaScript-Logik für die Chat-Interaktion)


### 2.9 `application.properties`

Die Konfigurationsdatei für Spring Boot, die Eigenschaften wie die OpenAI-API-Schlüssel und die Datenbankverbindung definiert.

**Beispiel:**

````properties

openai.api.key=YOUR_API_KEY

# H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 console (for debugging and visualizing the database)
spring.h2.console.enabled=true

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=update
````


## 3. Fehlerbehebung

### 3.1 Fehler beim Laden des API-Schlüssels

Wenn der Fehler "Could not resolve placeholder 'openai.api.key'" auftritt, überprüfen Sie die `application.properties`-Datei auf Tippfehler im API-Schlüssel und stellen Sie sicher, dass der Schlüssel korrekt eingefügt ist.

### 3.2 Fehler beim Starten der Anwendung

Falls die Anwendung nicht gestartet werden kann, aktivieren Sie das Debugging in der `application.properties`-Datei, um mehr Informationen zu erhalten. Fügen Sie dazu die Zeile `logging.level.org.springframework=DEBUG` hinzu.

## 4. Anwendungslogik

Die Anwendung ermöglicht es Benutzern, über die Benutzeroberfläche mit einem Chatbot zu interagieren. Benutzereingaben werden an die OpenAI GPT-3.5-Turbo-Engine gesendet, und die Antworten werden in der Datenbank gespeichert und im Chatverlauf angezeigt.

**Beispiel:**

````java
@PostMapping("/chatgpt")
public ResponseEntity<String> getChatGptResponse(@RequestParam String userInput) {
    // ... (Logik für die Interaktion mit der GPT-Engine und Speichern der Antwort)
}

````


## 5. Design und Styling

Die Anwendung verfügt über ein einfaches, modernes Design, das mithilfe von CSS und den Google Fonts "Open Sans" gestaltet ist. Die HTML-Struktur wird durch die `index.html`-Datei definiert, während das Styling in der `style.css`-Datei festgelegt ist.


## 6. Weitere Verbesserungen

Das Projekt könnte durch Hinzufügen von Features wie Benutzeranmeldung, personalisierten Chatverläufen und weiteren Styling-Anpassungen weiter verbessert werden.

## 7. Unterschiede Aufgabenstellung und Umsetzung

#### Frontend-Implementierung:

##### Aufgabenstellung:

Keine spezifischen Anforderungen an das Frontend-Design.

##### Umsetzung:

Das Frontend wurde ansprechend gestaltet und verwendet CSS-Styles, um eine verbesserte Benutzeroberfläche zu bieten.

#### Datenbankintegration:

##### Aufgabenstellung: Die Verwendung von JPA mit Hibernate wird empfohlen.

##### Umsetzung:

JPA mit Hibernate wurde erfolgreich implementiert, jedoch wurden spezifische Details der Datenbankintegration nicht explizit in der Aufgabenstellung definiert.

#### REST API-Entwicklung:

##### Aufgabenstellung:

Die Verwendung des Spring-Frameworks für die REST API wird gefordert.

##### Umsetzung:

Die REST API wurde unter Verwendung von Spring implementiert, jedoch wurde keine ausdrückliche Erwähnung der Verwendung von Spring Boot gemacht.

#### Dokumentation:

##### Aufgabenstellung:

Detaillierte Erklärungen und Screenshots sind erforderlich.

##### Umsetzung:

Die Dokumentation enthält detaillierte Erläuterungen und aussagekräftige Screenshots, wobei möglicherweise mehr Details als gefordert bereitgestellt wurden. Entwicklungsprozess:  Aufgabenstellung: Klare Anweisungen zur systematischen Durchführung verschiedener Entwicklungsschritte. Umsetzung: Der Entwicklungsprozess wurde befolgt, wobei möglicherweise spezifische Details wie das Spring Boot Starter Setup und die Wahl einer In-Memory-Datenbank nicht explizit erwähnt wurden.

#### Reflexion und Entwicklung:

##### Aufgabenstellung:

Wechsel der Rollen innerhalb der Gruppe für eine umfassende Erfahrung.

##### Umsetzung:

Die Reflexion und Entwicklung wurden durch den Austausch von Aufgaben gefördert, Erster Teil wurde von Ulbrich umgesetzt und von Sykes Dokumentiert, beim zweiten Teil war es umgekehrt. Trotz dieser Unterschiede wurde das Projekt erfolgreich umgesetzt und erfüllt die grundlegenden Anforderungen der Aufgabenstellung. Es zeigt auch kreative Anpassungen und Verbesserungen im Entwicklungsprozess und im Design.

## 8. Schlusswort

Das ChatGPT Clone-Projekt bietet eine einfache Möglichkeit, die OpenAI GPT-3.5-Turbo-Engine zu nutzen und in eine Chat-Anwendung zu integrieren. Die klare Struktur und das moderne Design machen die Anwendung benutzerfreundlich und erweiterbar.
