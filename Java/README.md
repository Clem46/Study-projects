

# l2s4-projet-2026

Vous devez *forker* ce projet dans votre espace de travail Gitlab (bouton `Fork`) et vidéo sur le [portail](https://www.fil.univ-lille.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)
Un unique fork doit être réalisé par équipe.

Une fois cela réalisé, supprimer ces premières lignes et remplir les noms des membres de votre équipe.
N'oubliez pas d'ajouter les autres membres de votre équipe aux membres du projet, ainsi que votre enseignant·e (statut Maintainer).

# Equipe

- sami INAN
- luc DURIEZ
- clément SORGE
- kamal SAVI

# Sujet

[Le sujet 2026](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2026.pdf)

# Livrables

Les paragraphes concernant les livrables doivent être remplis avant la date de rendu du livrable. A chaque fois on décrira l'état du projet par rapport aux objectifs du livrable. Il est attendu un texte de plusieurs lignes qui explique la modélisation choisie, et/ou les algorithmes choisis et/ou les modifications apportées à la modélisation du livrable précédent.

Un lien vers une image de l'UML doit être fourni (une photo d'un diagramme UML fait à la main est suffisant).

## Commande

### Compilation des fichiers
```bash
make 
```

### Compilation et exécution des tests
```bash
make test
```

### Suppression dossier classes
```bash
make clean
```

### Génération de la documentation
```bash
javadoc -sourcepath src -subpackages btd -d docs
```


## Livrable 1

### Atteinte des objectifs

Nous avons réussi à créer les deux types de plateaux :Board et LinearBoard. Nous avons également réalisé la méthode getPath() dans Board et dans LinearBoard. 

La méthode getPath() dans Board prend 3 paramètres : La position de départ du chemin, la position finale du chemin et une liste qui va être renvoyée à la fin qui va servir de liste entre appels récursifs. 
Dans Board, le chemin va être choisi aléatoirement mais suivant une règle :
Une cellule ne peut être ajoutée au chemin que si elle n'est connectée qu'à exactement une seule cellule déjà présente dans la liste.
Cette règle impose que le chemin construit ne soit pas collé au chemin déjà existant.

La méthode getPath() dans LinearBoard prend les 3 mêmes paramètre que Board.
Dans cette méthode le chemin renvoyé est une ligne droite entre les deux points.
La principale difficulté était dans le calcul des nouveaux points.
Si on prend deux points alignés verticalement ou horizontalement, nous devons savoir le sens (pour savoir si on doit ajouter i ou soustraire i de X ou Y)
Par exemple, si on prend deux points alignés verticalement et que le début du chemin est en haut (donc l'arriver est en bas).
On souhaite savoir le sens. Pour le calculer nous regardons le résultat de la soustraction des Y des deux points: 
si le Y de la position finale - le Y de la position d'arrivée est supérieur à 0 le sens est du haut vers le bas. Sinon l'inverse.
On a donc a incrémenter un i en commencant à 1 et de le multiplier au résultat précèdent. (si sens haut vers bas = 1 sinon -1)

### Difficultés restant à résoudre

Les difficultés à résondre vont être pour la classe Baloon et la gestion de plusieurs entités sur une même case. 

### UML Livrable 1
[UML Livrable 1](https://gitlab-etu.fil.univ-lille.fr/luc.duriez.etu/l-2-s-4-projet-sorge-inan-savi-duriez/-/blob/main/imageUML/Livrable1.png?ref_type=heads)

## Livrable 2

### Commande du Livrable

#### Compilation

```bash
make jarClassic jarLinear
```

#### Execution

```bash
w# Pour un Classicboard

java -jar Livrable/Livrable2b.jar  # Pour un LinearBoard
```

### Atteinte des objectifs

Nous avons réussi à simuler un round uniquement avec les ballons (pas de tour) avec les deux types de Board.

On a également questionné et choisi de mettre Board en abstract. On a donc LinearBoard et ClassicBoard qui sont enfant de Board (pour plus de logique).
On a créé une classe Game pour pouvoir déléguer le code de Main dans Game, notamment pour la boucle de jeu. Dans le futur Main.java on va donc devoir appeler game.playRound() qui jouera le tour selon l'attribut round dans game.

On a également dans Ballon.java, ajouter un attribut index qui va représenter son **index** de création. Cet attribut est exclusivement pour l'affichage en console.

On a créé également StateCell qui représente l'affichage en console d'une Cellule (Une cellule peut-être vide(EMPTY) / un chemin(PATH) /ou une tower(TOWER)). 


#### Choix de modélisation

Nous avons choisi de faire apparaître les ballons un à un. Pour un plateau linéaire, les ballons apparaissent aléatoirement sur les chemins.

Pour une meilleure visibilité, nous avons fait le choix que le nombre maximum de chemin pouvant être mis en paramètre du constructeur de linearBoard doit être inférieur ou égal a : (largeur + hauteur) / 2 

Pour le LinearBoard, nous avons choisi : si le joueur place une tour sur un chemin alors son état est uniquement TOWER. (Cette règle est uniquement pour LinearBoard car dans ClassicBoard, il est impossible d'ajouter une tour sur un chemin (ou empiler les tours)).

### Difficultés restant à résoudre

Une des difficuté va être de gérer le cas où dans un LinearBoard, si une cellule est un chemin et que on pose une tour et qu'ensuite on vend la tour, on dois replacer son état initial. Il nous faut donc retenir "l'etat de base" de la case. 
Nous allons également devoir créer la classe abstraite Tower et les enfants.

### UML Livrable 2
[UML Livrable 2](https://gitlab-etu.fil.univ-lille.fr/luc.duriez.etu/l-2-s-4-projet-sorge-inan-savi-duriez/-/blob/main/imageUML/Livrable2.png?ref_type=heads)

## Livrable 3

### Commande du Livrable

#### Compilation

```bash
make jar
```

#### Execution

```bash
java -jar Livrable/Livrable3/Livrable3a.jar  # Pour un Classicboard

java -jar Livrable/Livrable3/Livrable3b.jar   # Pour un LinearBoard
```

### Atteinte des objectifs

Nous avons réussi lors de ce livrable à créer l'intégralité des 7 tours. L'ensemble est fonctionnel. 
On a également créé les enfants DamageTower.java et UtilityTower.java qui hérite de Tower tout deux. UtilityTower va servir au tour utilitaire qui ne réalise pas de dégât au ballon, mais leur applique un effet. Au contraire donc de DamageTower qui lui a un projectile qui a un certain dégât cumulé avec les upgrades (prochain livrable).

Nous avons créé beaucoup de méthode qui servent à l'affichage.

### Difficultés restant à résoudre

En difficulté, nous avons eu beaucoup de mal avec BombTower, TackTower et les Slow/Ice Tower. 
L'affichage est à retravailler pour plus de lisibilité. 

### Choix de modélisation

On a choisi de faire deux sous classe, DamageTower et UtilityTower pour plus de claireté dans le code.  

### UML Livrable 3
[UML Livrable 3](https://gitlab-etu.fil.univ-lille.fr/luc.duriez.etu/l-2-s-4-projet-sorge-inan-savi-duriez/-/blob/main/imageUML/Livrable3.png?ref_type=heads)

## Livrable 4

### Commande du Livrable

#### Compilation

```bash
make jar
```

#### Execution

```bash
java -jar Livrable/Livrable4/Livrable4a.jar  # Pour un Classicboard

java -jar Livrable/Livrable4/Livrable4b.jar   # Pour un LinearBoard
```

### Atteinte des objectifs

Pour ce quatrième livrable, nous avons implémenté avec succès le système d'améliorations (Upgrade) pour les tours. Nous avons créé une interface Upgrade qui est implémentée par 4 classes distinctes modifiant les statistiques des tours : DamageUpgrade, RangeUpgrade, AttackSpeedUpgrade et ProjectileUpgrade.

L'achat et la revente de ces améliorations sont gérés dynamiquement et impactent directement les crédits du joueur. Seules les classes héritant de DamageTower peuvent recevoir ces améliorations. Les UtilityTower (comme IceTower ou SlowTower) lancent explicitement une exception (NoSuchElementException) si l'on tente de les améliorer ou de revendre une amélioration.

Nous avons mis à jour nos classes MainClassic et MainLinear pour simuler une partie de 10 manches. Dans cette simulation, 2 tours de chaque type sont placées et reçoivent une amélioration dès le début du jeu. Juste avant la manche 6, le programme parcourt les tours et revend (revert) les améliorations appliquées aux DamageTower.

Enfin, nous avons mis en place une gestion d'exceptions pour empêcher l'application d'une amélioration qui n'est pas dans la liste des améliorations disponibles de la tour, ou la revente d'une amélioration non possédée.


### Choix de modélisation

Nous avons fait le choix d'utiliser une interface Upgrade en créant des méthodes apply() et remove(). Cela nous permet d'avoir la logique de modification des statistiques (range, damage, projectile et attackSpeed) directement dans la classe Upgrade, rendant le code des tours beaucoup plus propre et flexible.

Avoir créé deux sous-classes DamageTower et UtilityTower lors du livrable précédent nous a permis de restreindre très facilement le système d'amélioration aux tours infligeant des dégâts via la signature des méthodes de l'interface Upgrade.
Nous avons également modifié les packages de tower. (tower.tower, tower.upgrade, tower.projectile)

### Difficultés restant à résoudre

Nous avons rencontré quelques difficultés comme l'amélioration de damageTower et non utilityTower mais elles ont toutes été résolues. Un point d'amélioration est de vérifier au maximum les cas extrêmes qui peuvent arrêter le programme et générer une erreur.

## Livrable 5

### Atteinte des objectifs

Pour ce cinquième livrable, nous avons implémenté la gestion des actions du joueur. Le joueur peut désormais interagir avec le jeu par la console entre chaque round. On lui propose plusieurs choix : acheter et placer de nouvelles tours, améliorer des tours existantes, ou encore revendre ses tours et upgrades.
 
Le joueur gagne des crédits à chaque fois qu'un ballon est détruit. À l'inverse, l'achat de tours ou d'upgrades consomme ces crédits. Nous avons créé une exception NotEnoughCreditsException qui empêche le joueur de réaliser une action s'il ne possède pas les crédits nécessaires.

Enfin, nous avons ajouté une musique de fond et amélioré l'affichage dans la console en ajoutant des émojis pour différencier les différentes tours.

Néanmoins, il nous a été demandé de modifié le Main afin de jouer 10 round avec la créations de chaque tour en deux exemplaires au début du jeu, l'achat d'une tour chaque round pour les 5 premiers rounds et l'upgrade d'une tour chaque round pour les rounds de 6 à 10. Nous avons donc modifié notre Main afin de suivre ces contraintes.

### Difficultés restant à résoudre

Nous n'avons aucune difficultés restantes à résoudre.

## Livrable 6

### Atteinte des objectifs

Le projet est terminé dans son intégralité.
Kamal a ajouté une musique au jeu (jar "-music")

### Difficultés restant à résoudre

/

# Journal de bord

Le journal de bord doit être rempli à la fin de chaque séance encadrée, et **avant** de quitter la salle. 

Pour chaque semaine on y trouvera :
- ce qui a été réalisé, les difficultés rencontrées et comment elles ont été surmontées (on attend du contenu, pas uniquement une phrase du type "tous les objectifs ont été atteints")
- la liste des objectifs à réaliser d'ici à la prochaine séance encadrée

## Semaine 1

### Ce qui a été réalisé

UML :
    environ 50-60% de l'UML a été réalisé. Il reste à ajouter tous les attributs et toutes les méthodes. 

Calcul du chemin :
    l'algorithme du calcul du chemin a été réalisé en pseudo-code pour le premier type de plateau. Le calcul du chemin n'est pas complêtement terminé. Environ 80% de cette étape a été fait.

### Difficultés rencontrées

Pas de difficultés rencontrées pour l'instant au niveau de l'UML. Au niveau du calcul du chemin, quelques difficultés ont été rencontrées sur la validité du chemin.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 2

### Ce qui a été réalisé

L'UML a été à peu pris fini avec quelques modifications (environ 95% de l'UML est réalisé). Les classes java sont en cours de création.

### Difficultés rencontrées

On a eu quelques difficultées au niveau de certains concepts comme la gestion du parcours des ballons avec une taille de cases donnée...

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 3

### Ce qui a été réalisé

Pour cette troisième semaine, on a réfléchi à la manière dont on va gérer les déplacements des ballons sur la grille et également la boucle principale du jeu. On a aussi travaillé sur la gestion du temps pour chaque manche du jeu.


### Difficultés rencontrées

On a rencontré quelques difficultés sur la gestion des déplacements des ballons pour le calcul de la recherche du chemin par les ballons.


### Objectifs pour la semaine et répartition du travail par membre

## Semaine 4

### Ce qui a été réalisé

On a réalisé plusieurs choses :
-   On a changé les packages des différentes classes.
-   On a créé une classe Game dans laquelle on a déplacé des méthodes de la classes Board
-   On a modifié l'UML pour y ajouter StateCell et les liens entre les classes

### Difficultés rencontrées

La gestion des mouvements des ballons a pris plus de temps que les autres tâches.

### Objectifs pour la semaine et répartition du travail par membre

Objectif général : Gestion d'un round dans sa totalité.

## Semaine 5

On a réfléchis a l'implémentation des towers. On a fais un choix d'implémentation pour la range des tours et on a finalement choisis de considérer les cases au lieu d'une range circulaire.

### Ce qui a été réalisé

On a modifi" l'UMl.
### Difficultés rencontrées

Implémentation de la range et la gestion du temps et de la portée de chaque tower.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 6

### Ce qui a été réalisé

On a fini la gestion de chacune des classes des tours. On s'est occupé des différents projectiles sauf pour la tour à bombes qui est encore en cours de réalisation, et par la même occasion, on a ajouté deux classes pour différencier les tours utilitaires et celles qui font des dégats.

### Difficultés rencontrées

La principale difficulté rencontrée est la gestion des tirs et de la portée des différentes tours par exemple faire en sorte que la tour tape le dernier ballon ou encore que la tour à bombe tape en zone sur plusieurs ballons en plus du dernier. La reflexion a neanmoins été faite pour ce problème.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 7

### Ce qui a été réalisé

On a créé une interface Upgrade et des classes de tous les types d'upgrades (dmg, range,...) qui l'implémentent l'interface.
On a fait en sorte que seule les sous-classes de DamageTower peuvent avoir des upgrades.
Les upgrades fonctionnent sur les tours, on peut les retirer, si on demande d'upgrade une tower avec une upgrade qui n'existe pas dans ses upgrades, une exception se lance.

### Difficultés rencontrées

Gérer la revente d'une upgrade d'une tour en prennant en compte les multipicateurs des différentes statistiques d'une tour ou d'un changement de projectile pour la bombTower.

### Objectifs pour la semaine et répartition du travail par membre

Commencer le choix joueur.

## Semaine 8

### Ce qui a été réalisé

Pendant la séance on s'est occupé d'ajouter la gestion des 10 manches du jeu ainsi que d'ajouter 2 tours de chaque type, une upgrade pour chaque tour au début du jeu et l'inversion de ces upgrades juste avant le round 6.

On a aussi réalisé le choix de l'action par le joueur pour le livrable 5.

### Difficultés rencontrées

Nous n'avons pas rencontré de réelles difficultés à part l'inversion des upgrades car il fallait inverser uniquement les DamageUpgrade et non les UtilityTower.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 9

### Ce qui a été réalisé

Pendant cette semaine 8, on s'est occupé de finir les deux classes main classic et linear afin de finir le livrable 4 pour les deux types de plateau. On a aussi ajouté la gestion de l'exception si on essaye d'appliquer une upgrade qui n'existe pas. Les méthodes d'amélioration, d'inverson d'une amélioration et d'autres en rapport avec cela ont été déplacées dans la class Tower.

### Difficultés rencontrées

Aucune difficulté n'a été rencontré pour cette semaine.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 10

### Ce qui a été réalisé

Durant cette semaine 10, nous avons continué dans l'affichage et la logique des choix proposés au joueur. Nous avons également réglé certains bugs dans cette gestion de choix afin d'éviter les différents arrêts du programme. Nous avons donc fini l'amélioration d'une tour et aussi la vente de cette même amélioration.

### Difficultés rencontrées

La gestion du choix pour le joueur a posé un problème au niveau des améliorations qui ne fonctionnait pas mais nous avons pu résoudre ce soucis. Aucune autre difficulté n'a été rencontrée pendant cette semaine.

### Objectifs pour la semaine et répartition du travail par membre

Les objectifs de cette semaine étaient d'améliorer l'affichage des choix et de résoudre les différents bugs liés à cela.

## Semaine 11

### Ce qui a été réalisé

Corrections des derniers bugs, ajout de l'exception pour les crédits du joueur, ajout des crédits gagnés lorsqu'un balloon est détruit.
Commencement d'un affichage différent pour les tours (avec des émojis pour les différentier) + musique du jeu.

### Difficultés rencontrées

Essayé de faire une interface graphique pour le chemin avec javafx mais abandon.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 12

### Ce qui a été réalisé

Pas de cours, mais nous avons continué et terminé le projet.
Nous avons améliorer l'affichage des chemins sur les boards.
Une optimisation de createPath pour le classicBoard a été effectuée pour pouvoir créer des boards de taille supérieur.

### Difficultés rencontrées

affichage des nouveaux chemins (LinearBoard) -> gestion des "virages"
Mise en place des listes chooser.


### Objectifs pour finaliser le projet et répartition du travail par membre

/
