# Projet JEE - Quiz Paulitique

L'objectif de cet outil est de fournir au membres de l'association Paulitique
un moyen de générer des quiz auxquels les participants à un évènement répondront
pendant celui-ci.

Le projet utilise Java 11.

Le jar contenu dans le projet utilise la base de donnée mysql PaulitiqueQuiz locale avec pour identifiants root:root
Pour modifier ces paramètres il faut cloner le répo: `git clone https://github.com/Paulitique/quiz/` modifier les paramètres du fichier `src/main/resources/application.properties`.

Puis compiler le projet:

`./mvnw clean install` (Cette commande lance les tests, assurez vous qu'ils passent. Si les tests échouent, vous avez probablement mal configuré la base de données.)

Et enfin lancer le jar:

`java -jar target/quiz-0.0.1-SNAPSHOT.jar`
.


Une fois le jar lancé, vous pouvez vous connecter sur le front du site en vous connectant à `http://localhost:8080/`.
Vous pouvez acceder au swagger de l'API en vous connectant à `http://localhost:8080/swagger-ui/`
