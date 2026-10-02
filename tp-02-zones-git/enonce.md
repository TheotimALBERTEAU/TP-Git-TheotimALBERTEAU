# TP 02 — Comprendre les zones Git

> Module CI/CD — Git, GitHub Actions & Docker · Durée estimée : 40 min · Niveau : débutant

## Objectifs d'apprentissage

À l'issue de ce TP, vous êtes capable de :

- faire transiter un fichier du répertoire de travail vers l'index puis vers le dépôt, à la
  commande ;
- nommer l'état d'un fichier (`untracked`, `staged`, `modified`, `unmodified`) à partir d'un
  `git status`, avant de lire la réponse de Git ;
- lire `git status -s`, dont les deux colonnes ne disent pas la même chose.

## Contexte

Vous ouvrez le dépôt d'un collègue, `git status` affiche quatorze lignes, et vous ne savez pas
lesquelles partiront au prochain commit. Ce TP installe le modèle mental qui répond à cette
question : trois zones, quatre états, et des commandes qui font passer d'une zone à l'autre.

## Prérequis

- TP 01 terminé : Git installé, `user.name` et `user.email` renseignés.
- Le dossier `starter/` de ce TP : trois fichiers (`README.md`, `pom.xml`, `src/App.java`). C'est le
  dépôt `inventaire` que les diapos affichent.

## Instructions

### 1 — Un dépôt neuf

1. Copiez `starter/` dans un dossier `inventaire`, placez-vous dedans, `git init`. Lancez
   `git status` : notez la phrase exacte que Git affiche quand aucun commit n'existe encore, et ce
   qu'il dit de `src/` — un dossier, pas un fichier.
2. Listez `.git/` (`ls -a .git`). Vous n'y toucherez pas : notez simplement combien d'entrées vous
   voyez, celle qui indique la branche courante et celle qui stocke le contenu. Tout le dépôt est
   là — le nombre varie légèrement selon la version de Git.

### 2 — Le trajet d'un fichier, prédit puis vérifié

Un fichier a **deux** états à la fois : un dans la zone d'index, un dans le répertoire de travail.
Notez-les donc par paires — `untracked/untracked`, `staged/unmodified`, `staged/modified`…

Pour **chacune** des étapes 3 à 7 : écrivez d'abord dans `journal.md` la paire que vous prévoyez,
lancez ensuite `git status`, puis notez la paire réelle. Une prédiction fausse se corrige, elle ne
s'efface pas : c'est la trace de votre apprentissage, et elle est demandée en livrable.

3. État de départ, rien n'est indexé. `git status`.
4. `git add README.md`. `git status`.
5. Modifiez `README.md` **sans** refaire `git add`. `git status`. Combien de fois `README.md`
   apparaît-il, et dans quelles sections ? C'est le passage que la salle trouve le plus
   déroutant : prenez le temps de l'écrire.
6. `git commit -m "Ajoute le README"`. `git status`. Que reste-t-il ?
7. `git add README.md` puis `git commit -m "Complète le README"`. `git status`.

### 3 — La vue courte

8. Mettez le dépôt dans cet état : `pom.xml` indexé, `README.md` modifié après indexation,
   `src/App.java` jamais indexé. Lancez `git status -s`.
9. Expliquez dans `journal.md` ce que représentent la **première** et la **deuxième** colonne, et
   donnez le code exact de chacun de vos trois fichiers (`A `, ` M`, `??`, `AM`…).

<details>
<summary>Indice — si les deux colonnes se ressemblent</summary>

Faites `git add` sur un fichier, puis modifiez-le encore. Un même fichier peut avoir un contenu
dans l'index et un autre dans le répertoire de travail : c'est exactement ce que les deux colonnes
distinguent.
</details>

### 4 — Sortir de l'index

10. Indexez un fichier, puis retirez-le de l'index **sans perdre vos modifications**. Trouvez la
    commande dans la sortie de `git status` : Git l'affiche. Notez-la dans `journal.md` et dites
    dans quelle zone le fichier se trouve après.
11. Lancez `git log --oneline`. Notez le nombre de commits et les sept caractères du plus récent.

## Livrables

- `journal.md` : vos prédictions et les états réels des étapes 3 à 7, vos réponses aux étapes 9,
  10 et 11.
- La sortie de `git log --oneline` collée en fin de `journal.md`.

**Terminé quand** : `git status` est propre (« nothing to commit, working tree clean »), les trois
fichiers du projet sont versionnés, et vous savez, sans hésiter, quelle commande déplace un fichier
vers chacune des trois zones.

## Pour aller plus loin (optionnel)

- `git add -p` indexe morceau par morceau. Modifiez deux endroits d'un fichier, n'en indexez qu'un,
  et regardez `git status -s`.
- `git cat-file -p HEAD` affiche l'objet commit. Retrouvez-y l'arbre, le parent et l'auteur.
