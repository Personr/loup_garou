COMMENT DÉPLOYER L'APPLICATION WEB : 
 
En estimant que tomcat 8.5.11 soit installé ainsi que le fichier : apache-tomcat-8.5.11/conf/tomcat_users.xml configuré (login et password)

Sinon ce référer aux étapes détaillées ici : http://chamilo2.grenet.fr/inp/courses/ENSIMAG4MMCAWE6/document/TP/Config.html


1 - Lancer netbeans
2 - Ouvrir le projet netbeans 'JeuLoupGarou'
3 - Renseigner le Master Password netbeans : 
4 - Clean & Build (Shift + F11)
5 - Run Projet (F6) -- deploie le site web en local
6 - S'inscrire sur le site
7 - Jouer.


POUR CRÉER UNE PARTIE ET LA PEUPLER : 
1 - Renseignez login et mdp dans /META-INF/context.xml
2 - Lancez le script sql : installBD.sql (a la racine du projet).

PS : Si un login est demandé pour la base de donnée déjà peuplée: 
log : levesyv
mdp : levesyv
