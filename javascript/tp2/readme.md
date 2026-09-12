# TP 2 : balles rebondissantes

  Le [sujet du TP](https://www.fil.univ-lille.fr/~routier/enseignement/licence/js-s4/tdtp/exercices-javascript-balles.html).

## Mise en place du TP

On utilise ici le [template de TP](https://www.fil.univ-lille.fr/~routier/enseignement/licence/js-s4/html/template-app.html) mis à disposition.

  1. Si vous ne l'avez pas encore fait, installez <i>Node.js</i> comme indiqué dans ce [document](https://intranet.fil.univ-lille.fr/2020/04/09/nodejs-et-npm/).  

  2. Dans le dossier `tp2/`, exécutez :
```bash  	  
tp2$  npm install
```  
  3. Exécutez la commande `npm run build` pour créer le dossier `./dist/` et construire un premier *bundle*
  4. Vous pouvez ouvrir le fichier `dist/index.html`, mais pour le moment le script n'est pas actif, vous allez devoir le compléter. Vous pouvez cependant vérifier que tout s'est bien déroulé en consultant la console (<kbd>Ctrl Shift K</kbd>) dans laquelle vous devez lire le message `le bundle a été généré`. Si c'est le cas, c'est que tout s'est bien déroulé.
  5.	Pendant le TP vous devrez compléter ou créer les modules JavaScript demandés.  
    Après chaque modification, il faut générer le <q>nouveau</q> <i>bundle</i>, toujours à l'aide de la commande <code>npm run build</code> et c'est le fichier `dist/index.html` qu'il faut consulter pour avoir le résultat

  >  Attention, le résultat <strong>ne se consulte pas</strong> avec le fichier `src/index.html` : vous devez faire vos modifications et votre travail dans le dossier `src/` **mais le résultat du travail est observé dans le dossier `dist/`**,  et donc à l'aide du fichier **`dist/index.html`**.

  6. Cependant, comme indiqué dans <a href="https://www.fil.univ-lille.fr/~routier/enseignement/licence/js-s4/html/template-app.html#heading2" target="new">cette section</a>, vous pouvez profiter des facilités offertes par  <i>Webpack</i> pendant la phase de développement pour reconstruire automatiquement le bundle en utilisant <code>npm run watch</code>, <strong>ou</strong> pour plus de facilités encore et visualiser les résultats <q>en direct</q>  en démarrant le serveur de développement&nbsp;:</p>
```bash
tp2$  npm run dev-server
```
  L'exécution de cette commande, active un serveur qui affiche dans votre navigateur le fichier `index.html` du TP. Ce chargeur actualise la page à chaque modification et sauvegarde de l'un des fichiers de script utilisés.

  **C'est la solution que l'on vous conseille d'adopter.** Et on supposera par la suite que c'est ce que vous faites .

  7. N'oubliez pas d'exécuter la commande <code>npm run build</code> après l'arrêt du serveur de développement pour mettre à jour le dossier `dist/`.

> NB : le dossier `dist/` ne sera pas mis sur le dépôt car il peut être regénéré à partir des sources.

## Votre travail

Expliquez ici comment exécuter votre projet. Vous pouvez également ajouter toute précision utile sur votre travail.
