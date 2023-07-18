# ticket-generator
Progettazione &amp; Algoritmi, University of Bergamo

il progetto consiste nell'implementazione di un sistema IOT di generazione dei biglietti presenti negli uffici pubblici come poste, agenzia delle entrate, ospedali...

Composto tra 4 sotto progetti per i vari device utilizzati per l'esecuzione delle funzionalità
* ticketgeneratorClient: webApp Angular visualizzata sullo schermo del totem dell'ufficio pubblico per la generazione del biglietto
* ticketgeneratorClientOperator: webApp Angular utilizzata sulle postazioni operatore per la segnalazione dell'evento di avanzamento
* ticketgeneratorServer: applicazione Java/Spring contenente tutte le business logic e la comunicazione con i dati persistenti (su MongoDB)
* ticketgeneratorVisualizer: webApp Angular visualizzata sullo schermo avente funzione di bacheca all'interno dell'ufficio pubblico
* Generatoredibiglietti: app Java/Android per l'utilizzo del servizio slegato dal luogo dell'ufficio (generazione del biglietto non in sede)

Main features del ticket-generator
* Generazione varie tipologie di biglietto in sede con totem
* Visualizzazione dashboard in tempo reale
* Traccia della coda salvata con persistenza su MongoDB Atlas
* Sistema di stima del tempo di servizio sulla base di una history
* Profilazione per utilizzo on-line
* Generazione del biglietto on-line tramite app Android

Il ticketgeneratorServer non ha previsto attualmente una dashboard di impostazioni/configurazione

### Generatoredibiglietti


![immagine](https://github.com/atusghen/ticket-generator/assets/39970186/0ee68bed-d2bc-4a8d-bcb6-a6cfe93354e1)

Nota: attualmente le webApp sono dei "dummy" in Java/Spring oppure Html/Javascript e pertanto non implementano le funzionalità previste. L'utilizzo consigliato della versione 1.0 è solamente per la generazione online del biglietto con app Android e il servizio messo a disposizione localmente oppure pubblicato su cloud


Changelog Versione 1.0
TODO


