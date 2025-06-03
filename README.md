# bomber7

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
## Javadoc: 
https://stephane-lpt.github.io/bomber7/

## Projet long
=======
A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).
>>>>>>> 8fbcd1a (update methods)

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

=======
A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).
=======
## Javadoc: 
https://stephane-lpt.github.io/bomber7/
>>>>>>> 189e37e (Update README.md)

## Projet long

Nous vous proposons de définir vous-mêmes le sujet du projet long sur lequel vous allez travailler. Vous trouverez ici les principales consignes et les jalons de ce projet.

Rappel : Ce projet sert de support à la matière Gestion de projet / Méthodes agiles (SCRUM)et sera évalué aussi bien par CPO (Projet Long) que par Gestion de projet. Vous aurez donc 2 notes.
Constitution des équipes

Il y aura 6 équipes. Ceci fera donc des équipes de 5 à 6 personnes.

Les équipes devront être équilibrées en fonction des origines (RT, Info, etc.).

L’enseignant référent de votre équipe est :

<<<<<<< HEAD
>>>>>>> 23b2787 (update methods)
Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
=======
    E1 : ?
    E2 : ?
    E3 : ?
    E4 : ?
    E5 : ?
    E6 : ? 

Notation du projet

    Chaque étape du projet (décrite ci-après) sera prise en compte dans la note du projet.
    À la fin du projet, une démonstration, une présentation orale et un compte-rendu concernant l’organisation du projet seront faits. Trois personnes seront tirées au sort, chacune pour présenter l’un des aspects. Les autres membres de l’équipe répondront aux questions.
    Une partie de la note sera définie par l’équipe elle-même.

    Chaque membre de l’équipe répartira un nombre de points égal à 1 de moins que la taille de son équipe entre ses coéquipiers. Chaque coéquipier aura un nombre de points compris entre 0 et 3. Par exemple, pour une équipe de 6 personnes, chaque équipier pourra répartir 5 points sur ses coéquipiers en donnant à chacun un nombre de points entre 0 et 3.

    La note d’un étudiant sera donc : (note de l’enseignant) + (moyenne des points attribuées par ses coéquipiers - 1).

Dépôts Git

Dans chaque dépôt, il faudra créer trois dossiers :

    ​livrables : ne doit contenir que les livrables explicitement demandés en respectant les noms (tout en minuscules) donnés ci-après,
    ​src : le code source de votre projet,
    ​doc : les fichiers sources des documents fournis dans les livrables, etc.

Vous pouvez bien sûr créer d’autres dossiers pour organiser votre dépôt.
2025/03/07§: Jalon « Équipes constituées » (étudiants).

Une activité pour choisir son équipe est disponible sur Moodle. Si les règles de constitution des équipes ne sont pas respectées nous adapterons les équipes.
2025/03/10 : Jalon « Création dépôts Git » (Xavier Crégut).

Création des dépôts Git qui seront utilisés pour le projet. Les dépôts ne peuvent être créés qu’une fois les équipes constituées.
2025/03/14 : Jalon « Liste des sujets envisagés » (équipes).

Chaque équipe déposera sur Gitlab, dans le dossier ​livrables, un document ​sujets​.​pdf qui contiendra au moins 4 sujets de projet envisagés, classés par ordre de préférence décroissante de l’équipe (le premier sujet est le sujet préféré de l’équipe).

Les sujets proposés doivent être décrits dans le document sujets.pdf (tout en minuscules) qui doit être déposé dans le dossier « livrables »

Chaque sujet doit être décrit sur une page environ dans le but de convaincre de la pertinence et de l’intérêt de votre projet. Vous devez considérer que ce document s’adresse à des clients potentiels, à des business angels, à votre supérieur hiérarchique...

Il doit y avoir suffisamment d’information sur le projet pour que votre enseignant puisse le comprendre et évaluer le travail envisagé. Ce document sera le point de départ pour discuter de votre projet avec votre enseignant. C’est lui qui validera le sujet de votre projet au final.

En aucun cas il ne faut parler des solutions informatiques qui seront apportées pour réaliser ce projet. Il ne s’agit pas de décrire comment ce sujet sera traité d’un point de vue programmation mais ce qu’il apportera à ses utilisateurs.

Quelques recommandations :

    Le projet doit être suffisamment ambitieux puisque les équipes sont de grande taille ! Vous ne devez pas pouvoir terminer le projet dans le temps imparti. C’est le but.
    L’application développée devra être pourvue d’une interface graphique.
    Vous ne ferez ni une application concurrente, ni une application distribuée, ni une application client/serveur (ces notions seront vues en 2A).
    Il serait souhaitable de choisir un sujet original.

Attention, vous ne pouvez pas prendre un sujet qui serait du type « boîte à jeux » où chaque membre de l’équipe s’occuperait d’un des jeux !

L’enseignant évaluera les sujets et donnera le feu vert à l’équipe pour traiter l’un des sujets (ou vous aidera à construire un sujet adapté aux attentes).
2025/03/18 : Jalon « Sélection du sujet de projet » (enseignant).

L’enseignant communiquera le sujet retenu.
2025/03/28 : Jalon « Fonctionnalités du sujet » (équipe).

Il s’agit de décrire les fonctionnalités de l’application que vous allez développer dans un document appelé fonctionnalites.pdf (nom à respecter impérativement, nom tout en minuscules, sans accents), à déposer dans le dossier « livrables ». Il doit contenir :

    une page de garde qui rappelle le numéro de l’équipe, ses membres, le titre du projet, l’objet du document...
    l’objectif général du projet
    une description des fonctionnalités (attendues) de l’application qui sera développée. Il s’agit de décrire le service rendu (besoin) et non la manière dont ce service sera rendu (comment).
    les interfaces utilisateur envisagées (esquisses réalisées à main levée ou avec un outil de dessin).
    plusieurs cas d’usage (scénarios) pour comprendre les fonctionnalités (et, plus tard, valider l’application développée).
    (facultatif) En annexe, lister les points durs, les points qui vous paraissent difficiles dans le projet proposé.

Il ne faut pas hésiter à mettre beaucoup de fonctionnalités. Les étapes suivantes consisteront à les organiser, définir des priorités, etc. Tout ne sera pas à développer lors du projet long !

Attention : Ici, on ne doit pas parler de programmation objet, d’UML, de Java, etc. On ne veut pas savoir comment sera réalisée l’application mais bien qu’elles sont les les possibilités qu’elle offrira à ses utilisateurs.

Ce document pourrait donc servir de trame pour le futur manuel utilisateur. Il restera à ajouter quelques captures d’écrans pour remplacer les esquisses et les modes opératoires tels qu’ils ont été finalement réalisés.
2022/06/20, 10 h : Points d’équipe.

Chaque membre d’une équipe notera ses coéquipiers en utilisant le fichier notes-equipiers.txt qui est la racine du projet Git utilisé pour les TP.

Lorsque ce fichier sera traité, un fichier points-equipiers-resultats.txt indiquera ce qui aura été pris en compte.
2022/06/20, 10 h : Livrables.

Sur votre projet Git, un dossier livrables devra contenir le rapport (rapport.txt), le manuel utilisateur (utilisateur.pdf) ainsi que les sources de votre projet (dossier src/). Il est aussi possible de mettre une archive sources.zip dans le dossier livrables.

Merci de respecter les noms indiqués, y compris la casse.
2024/06/21, 8h : Remise de la présentation.

La présentation (presentation.pdf) doit être déposée dans le dossier livrables/.
2024/06/21 : Présentation orale.

Pour la présentation orale, le déroulement sera le suivant :

    15 minutes (maximum à ne pas dépasser !) à utiliser pour :
        une démonstration de l’application réalisée
        une présentation technique du travail réalisé (architecture, choix de conception et réalisation, etc.). UML est certainement un outil utile pour cette présentation...
        une présentation de l’organisation de l’équipe et la mise en œuvre des méthodes agiles.
    Vous devriez passer de 3 à 6 minutes sur chacune de ces trois parties.

    2023-2024 : La partie gestion de projet aura été traitée la semaine précédente.

    Attention : Les trois orateurs seront tirés au sort. Chacun doit donc être prêt à présenter chacune des trois parties.

    Vous êtes libres d’organiser ces trois présentations dans l’ordre que vous souhaitez (même s’il paraît logique de commencer par la démonstration car tous les membres du jury ne connaitront pas votre sujet).
    10-15 minutes de questions/discussions.

Il peut être intéressant de commencer par la démonstration car c’est l’occasion de rappeler le périmètre du projet et comment il a été traité du point de vue des utilisateurs. La présentation orale se concentrera sur les aspects techniques et organisationnels du projet.

La présentation technique doit être technique ! Il n’est pas utile de nous détailler le sujet (la démonstration l’a fait). Ce qu’il faut faire, c’est nous expliquer comment vous l’avez traité : quels sont les choix faits ? Quels sont les problèmes rencontrés et les solutions apportées. Nous vous rappelons qu’UML est un bon outil pour communiquer !

La gestion de projet doit expliquer comment l’équipe s’est organisée, les outils utilisés en accord avec ce qui a été présenté en gestion de projet.

Horaires de passage pour l’oral

Toutes les équipes assistent à toutes les présentations de leur groupe de TD.

L’ordre de passage des équipes sera choisi au fur et à mesure. 
>>>>>>> 189e37e (Update README.md)
