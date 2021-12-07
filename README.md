# API-2021-SMTP
## Rapport
### 1. Objectif

Le but de ce laboratoire est d'initier les étudiants au protocole derrière un **SMTP**, ainsi que de mettre en place le 
coté client de ce dernier, ensuite d'apprendre à se servir de **serveur Mock** pour testé notre application et vérifier 
son bon fonctionnement, et pour finir utiliser **Docker** afin de générer des images et des containers.

### 2. Instruction d'installation

Pour pouvoir utiliser les fonctionnalités de ce repo, dénoter tout d'abord qu'un environnement de dévellopement 
java est nécéssaire pour lancer l'application, si c'est déjà le cas il suffit de clone ce repo pour commencer votre 
aventure avec le protocole SMTP. Ensuite installer Docker et pour finir il faut utiliser un Mock server pour tester 
ce que l'on fait ( MockMock, mailtrap, etc..). Ici on utilise MockMock qui vous est fourni dans comme ``.jar`` dans 
le repo, pour plus d'information aller sur[MockMock](https://github.com/HEIGVD-Course-API/MockMock).

### 3. Instruction d'utilisation

Premièrement, il vous faudra ouvrir le repo sur votre IDE grâce au pom.xml (pour cela, il vous faudra avoir installer 
maven auparavant). Notre repo est diviser en 2 directory différent: **config** et **src**. config est le directory qui a 
tous les fichiers liés aux propriétés de notre projet et 
src contient tout le code de notre application.

Dans le directory **config**, vous trouverez 3 fichiers différents:**Configuration** qui est celui qui a les propriétés 
de notre application (adresse serveur, port, nombre de personnes par groupe, etc.), **Messages** qui est ou vous écrivez 
ce que vous désirez envoyer aux victimes (il faut a tout prix finir celui-ci avec ``MESSAGE_END`` pour qu'il soit 
envoyé sans soucis ou séparer les différents messages que vous voulez écrire). Pour finir,**Victims** qui contient les 
adresses mail des victimes de votre blague. Vous pouvez en ajoutez autant que vous le désirez, alors ne vous génez pas.

#### 3.1 Mise en place de MockMock
Commencer par ouvrir une invite de commande sur votre machine et aller sur l'emplacement ou se trouve ce repo. 
Lorsque c'est fait taper la commande suivante sur l'invite :``java -jar MockMock.jar`` pour le run avec les paramètres 
par défaut (port : 25 et webInterface : 8282), par contre si vous désirez le lancer avec les paramètres que vous désirez 
faite la commande suivante :``java -jar MockMock.jar -p 2021 -h 8080``, dans ce cas 2021 est le port et 8080 la 
webInterface définir dans le fichier des propriétés du repo. Si vous souhaitez changer le port, il vous suffit d'ouvrir 
le fichier configuration dans config et changer la variable smtpServerPort avec ce que vous désirez. Mais n'oublier de 
faire de même lorsque vous lancer MockMock.

#### 3.2 Utilisation de Docker

#### 3.3 Lancement de l'application

Quand vous avez effectué les étapes précèdentes, il ne reste plus qu'à lancer notre application de prank. Pour cela, il 
faut que vous modifier les fichiers dans le directory config. Ajoutez les addresses mails des victimes, combien de 
groupes il faut former, etc.

Lorsque c'est fait, il vous suffit de simplement run l'application sur votre IDE et le tour est joué. 








