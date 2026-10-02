# TP 02 - Les zones Git

J'ai copié `starter/` dans `inventaire/`, à la racine de mon dépôt de TP. Il n'y a qu'un seul `.git` pour tous les TP, donc les chemins dans les sorties commencent par `inventaire/`.

Pour les états je note index / répertoire de travail (ex : `staged/modified`).

## 1. Un dépôt neuf

`git init` puis `git status` :

```bash
On branch main

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
	"CI_CD_\342\200\224_Git_&_GitHub_(Fondation)_\342\200\224_Sup_de_Vinci_B3_Dev.pdf"
	_ancien/
	inventaire/
	tp-01-installer-git/
	tp-02-zones-git/
	tp-03-operations-locales/

nothing added to commit but untracked files present (use "git add" to track)
```

* La phrase quand il n'y a pas encore de commit : "No commits yet".
* Les dossiers apparaissent en un seul bloc (`inventaire/`), Git ne liste pas les fichiers dedans tant qu'aucun n'est suivi. Pareil pour `src/`.
* La branche est `main` grâce au `init.defaultBranch` du TP 01.

Ensuite j'ai mis le PDF et `_ancien/` dans le `.gitignore`.

```bash
ls -a .git
.  ..  HEAD  branches  config  description  hooks  info  objects  refs
```

8 entrées (sans `.` et `..`). `HEAD` indique la branche courante (`ref: refs/heads/main`) et `objects/` contient le contenu.

## 2. Le trajet de README.md

| Étape | Prévu | Réel |
|-------|-------|------|
| 3\. départ | untracked/untracked | untracked/untracked |
| 4\. git add | staged/unmodified | staged/unmodified |
| 5\. modif sans add | staged/modified | staged/modified |
| 6\. commit | unmodified/modified | unmodified/modified |
| 7\. add + commit | unmodified/unmodified | unmodified/unmodified |

### Étape 3

```bash
On branch main
Untracked files:
  (use "git add <file>..." to include in what will be committed)
	inventaire/

nothing added to commit but untracked files present (use "git add" to track)
```

Le README est dans `inventaire/` qui n'est pas suivi : untracked/untracked.

### Étape 4 : git add inventaire/README.md

```bash
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	new file:   inventaire/README.md

Untracked files:
	inventaire/pom.xml
	inventaire/src/
```

staged/unmodified : le fichier est copié dans l'index et il n'a pas bougé sur le disque.

Maintenant qu'un fichier du dossier est suivi, Git affiche `pom.xml` et `src/` séparément.

Git propose `git restore --staged` et pas `git rm --cached` parce que mon dépôt avait déjà des commits (ceux du TP 01). Sur un dépôt sans commit c'est `git rm --cached` qui est proposé.

### Étape 5 : modif sans refaire git add

```bash
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	new file:   inventaire/README.md

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	modified:   inventaire/README.md
```

staged/modified. Le README apparaît deux fois :

* dans "Changes to be committed" : c'est la version qui a été copiée dans l'index au moment du `git add`, c'est celle-là qui partira au commit
* dans "Changes not staged for commit" : c'est ma modif faite après le `git add`, elle est seulement dans le répertoire de travail

En fait `git add` prend une copie du fichier à ce moment-là, ce n'est pas un lien vers le fichier. En `git status -s` ça donne `AM`.

La première fois, `git status` n'avait rien changé : je n'avais pas enregistré le fichier dans l'éditeur. Git ne voit que ce qui est enregistré sur le disque.

### Étape 6 : git commit -m "Ajoute le README"

```
[main adec2e0] Ajoute le README
 1 file changed, 11 insertions(+)
 create mode 100644 inventaire/README.md
```

```
Changes not staged for commit:
	modified:   inventaire/README.md

Untracked files:
	inventaire/pom.xml
	inventaire/src/
```

Il reste ma modif de l'étape 5 : le commit a pris la version de l'index (11 lignes), pas celle du disque. Et `pom.xml` / `src/` sont toujours untracked.

unmodified/modified

### Étape 7 : git add puis git commit -m "Complète le README"

```
[main fed7744] Complète le README
 1 file changed, 2 insertions(+)
```

```
On branch main
Untracked files:
	inventaire/pom.xml
	inventaire/src/

nothing added to commit but untracked files present (use "git add" to track)
```

unmodified/unmodified, le README n'apparaît plus, tout est à jour.

Au premier essai j'avais fait le commit sans `git add` : Git a mis "nothing added to commit" et aucun commit n'a été créé.

## 3. La vue courte

### Étape 8

`pom.xml` indexé, `README.md` modifié + indexé puis re-modifié, `src/App.java` jamais indexé :

```
git status -s
MM inventaire/README.md
A  inventaire/pom.xml
?? inventaire/src/
```

### Étape 9

* 1re colonne : l'index, donc ce qui a changé entre le dernier commit et l'index (ce qui partira au prochain commit)
* 2e colonne : le répertoire de travail, ce qui a changé entre l'index et le fichier sur le disque (modifié mais pas indexé)

Mes trois fichiers :

* `MM` README.md : une modif est indexée (1re colonne) et le fichier a encore été modifié après (2e colonne)
* `A ` pom.xml : nouveau fichier ajouté à l'index, rien de plus
* `??` src/ : pas suivi du tout

J'ai aussi vu `AM` à l'étape 5 (nouveau fichier indexé puis modifié) et ` M` à l'étape 10 (modifié mais rien d'indexé).

## 4. Sortir de l'index

### Étape 10

Au départ le README est indexé (état de l'étape 8) :

```
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	modified:   inventaire/README.md
	new file:   inventaire/pom.xml
```

La commande que Git donne :

```
git restore --staged inventaire/README.md
```

Après :

```
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	new file:   inventaire/pom.xml

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	modified:   inventaire/README.md
```

```
git status -s
 M inventaire/README.md
A  inventaire/pom.xml
?? inventaire/src/
```

Le README est revenu dans le répertoire de travail (modified). On passe de `MM` à ` M`, la colonne de l'index est vide. Mes modifs sont toujours là, `git diff --stat` affiche encore `inventaire/README.md | 6 ++++++`.

Attention : `git restore <fichier>` sans `--staged` supprime les modifs du fichier, on ne peut pas les récupérer.

### Étape 11

Après avoir ajouté le reste (`git add inventaire` + commit), `git log --oneline` donne 5 commits (2 pour le TP 01, 3 pour ce TP). Le dernier est `3300e5b`.

## Récap des commandes

* répertoire de travail -> index : `git add <fichier>`
* index -> dépôt : `git commit -m "..."`
* index -> répertoire de travail (en gardant les modifs) : `git restore --staged <fichier>`
* annuler les modifs du répertoire de travail (on les perd) : `git restore <fichier>`

## Fin

```
git status
On branch main
nothing to commit, working tree clean
```

Les 3 fichiers du projet sont versionnés.

git log --oneline (avant de commit ce journal) :

```
3300e5b Ajoute pom.xml et App.java, complète le README
fed7744 Complète le README
adec2e0 Ajoute le README
a92aa20 TP 01 : poste.md
9459f3b Initialise le dépôt des TP : énoncés et starter
```