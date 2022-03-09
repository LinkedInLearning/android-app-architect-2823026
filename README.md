# Android-Apps entwickeln: Die Architektur einer App

Dies ist das Repository für den **LinkedIn Learning** Kurs Android-Apps entwickeln: Die Architektur einer App. Den gesamten Kurs finden Sie auf [LinkedIn Learning][lil-course-url].

![COURSENAME][lil-thumbnail-url] 

In dieser Folge ihrer Kursreihe zur Entwicklung von Android-Apps stellen David Gassner und Rudolf Histel eine komplette lauffähige App in den Mittelpunkt: Zunächst lernen Sie Activities genauer kennen und Sie sehen, wie Sie Lebenszyklus-Events mit einem LifeCycleObserver automatisch überwachen können. Im nächsten Schritt zeigen die Trainer, wie Sie auftretende GUI-Events komfortabel auswerten können und dabei die Kotlin ViewModels zum Einsatz kommen. Schließlich entsteht ein voll funktionsfähiges Würfelspiel, welches dynamisch auf die Reorientierung des Bildschirmes reagiert und die Ergebnisse mit anderen Apps teilen kann.

## Anleitung

Dieses Repository hat Branches für jedes Video im Kurs. Verwenden Sie das Ausklappmenü "Branch: ..." in GitHub um zwischen den unterschiedlichen Branches hin und her zu wechseln bzw. um bei einem spezifischen Status einzusteigen. Oder Sie fügen `/tree/BRANCH_NAME` der URL hinzu um direkt in den gewünschten Branch zu wechseln.

## Branches

Die Git Branches sind passend zu den Videos im Kurs strukturiert. Die Namenskonvention lautet `Kapitel#_Video#`. Der Branch `02_03` beinhaltet zum Beispiel die Übungen für das dritte Video im zweiten Kapitel. 
Einige Branches haben einen Anfangsstatus (`b`) für "beginning" und einen Endstatus (`e`). Der Branch mit dem `e` am Ende beinhaltet in diesem Fall stets den Code der am Ende des Videos zu sehen ist. Der `master` Branch beinhaltet den initialen Quellcode und wird nicht für die Übungen innerhalb des Kurses genutzt.

Wenn Sie von einem Branch nach Änderungen zum nächsten Branch wechseln, erhalten Sie möglicherweise die folgende Meldung:

```
error: Your local changes to the following files would be overwritten by checkout:        [files]
Please commit your changes or stash them before you switch branches.
Aborting
```

Dieses Problem lösen Sie wie folgt:
    Add changes to git using this command: git add .
    Commit changes using this command: git commit -m "some message"

## Installation

1. Um diese Übungsdateien nutzen zu können, müssen Sie folgendes installiert haben:
   - [list of requirements for course]
2. Klonen Sie das Repository in Ihre lokale Maschine unter Verwendung von terminal (Mac), CMD (Windows) oder ein anderes Werkzeug mit grafischer Bedienoberfläche wie SourceTree.

### Autor

**Rudolf Histel**

_Fachinformatiker, Dozent_

Sehen Sie sich andere Kurse des Autors auf [LinkedIn Learning](https://www.linkedin.com/in/rudolfkasper) an.

[lil-course-url]: https://www.linkedin.com/learning/android-apps-entwickeln-die-architektur-einer-app-mit-kotlin/eine-komplette-android-app-kennenlernen-und-verstehen
[lil-thumbnail-url]: https://media-exp1.licdn.com/dms/image/C560DAQFUBfUuq8AO7Q/learning-public-crop_675_1200/0/1641816410220?e=1646956800&v=beta&t=F3Wyq7WIW0tJkrHwrt19nS9Esx3cyqCbX7H3ORKMF44
