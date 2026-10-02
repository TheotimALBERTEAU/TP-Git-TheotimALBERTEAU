# TP 03 - Opérations courantes (local)

Même dépôt que le TP 02, je continue sur `inventaire/`.

## 1. Ce qui n'entre pas dans l'historique

### Étape 2 : .gitignore

Mon `.gitignore` :

```
# Support de cours, pas un travail à versionner
*.pdf

build/
*.local
```

git status après :

```
git status
On branch main
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   .gitignore

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        tp-03-operations-locales/journal.md

no changes added to commit (use "git add" and/or "git commit -a")
```

### Étape 3 : git add -f

Pour l'indexer de force :

```
git add -f .\inventaire\build\sortie.bin
```

Pour le retirer de l'index :

```
git restore --staged .\inventaire\build\sortie.bin
```

Ce que le .gitignore empêche ou pas :

il empêche d'ajouter des fichiers lors d'un git add simple, MAIS il peut être forcé grâce au -f

### Étape 4 : dossier vide et .gitkeep

Dans `inventaire/` :

```
mkdir logs
touch logs/.gitkeep
git add logs/.gitkeep
git commit -m "Ajoute le dossier logs/ (avec .gitkeep)"
```

Pour vérifier que c'est bien dans le dépôt :

```
git ls-files inventaire/logs
inventaire/logs/.gitkeep
```

Pourquoi le .gitkeep : Git ne suit que des fichiers, pas des dossiers. Un dossier vide n'existe pas pour lui, il n'apparaît pas dans `git status` et on ne peut pas le commit. En mettant un fichier dedans, le dossier est versionné et il sera recréé quand quelqu'un clone le dépôt. Le nom `.gitkeep` n'a rien de spécial pour Git, c'est juste une convention.

### Étape 5 : .gitattributes

J'ai mis le `.gitattributes` à la racine du dépôt :

```
*.sh text eol=lf
```

Puis j'ai créé `inventaire/script.sh` (avec des fins de ligne CRLF, comme un éditeur Windows) et j'ai commit :

```
git add .gitattributes inventaire/script.sh
warning: CRLF will be replaced by LF in inventaire/script.sh.
git commit -m "Ajoute .gitattributes et script.sh"
```

```
git ls-files --eol inventaire/script.sh
i/lf    w/crlf  attr/text eol=lf      	inventaire/script.sh
```

* `i/lf` : dans l'index (et donc dans le dépôt) le fichier est en LF, Git a converti au moment du `git add`
* `w/crlf` : sur mon disque le fichier est encore en CRLF, Git n'a pas touché à ma copie

J'ai supprimé le fichier et je l'ai récupéré avec `git checkout -- inventaire/script.sh` :

```
i/lf    w/lf    attr/text eol=lf      	inventaire/script.sh
```

Maintenant il est en LF des deux côtés : quand Git réécrit le fichier, il applique `eol=lf`, même sous Windows avec `autocrlf true`. C'est utile pour les `.sh` qui ne marchent pas sous Linux avec des CRLF.

## 2. Committer et corriger un commit

Pour la suite je lance les commandes depuis `inventaire/`, donc les chemins sont relatifs à ce dossier.

### Étape 6 : trois commits sur src/App.java

J'ai ajouté un compteur d'articles dans `App.java`, en trois fois :

```
git commit -m "Ajoute un compteur d articles"
[main 2891fe0] Ajoute un compteur d articles
 1 file changed, 2 insertions(+)

git commit -m "Affiche le compteur"
[main b0b4789] Affiche le compteur
 1 file changed, 1 insertion(+), 1 deletion(-)

git commit -m "wip"
[main 4708cbc] wip
 1 file changed, 1 insertion(+)
```

### Étape 7 : git commit --amend

Le message "wip" ne veut rien dire, je le corrige.

Avant :

```
git log --oneline -4
4708cbc wip
b0b4789 Affiche le compteur
2891fe0 Ajoute un compteur d articles
4aabfea Ignore build/ et *.local, début du journal TP 03
```

```
git commit --amend -m "Commente le compteur d articles"
[main 4a826b4] Commente le compteur d articles
```

Après :

```
git log --oneline -4
4a826b4 Commente le compteur d articles
b0b4789 Affiche le compteur
2891fe0 Ajoute un compteur d articles
4aabfea Ignore build/ et *.local, début du journal TP 03
```

Empreinte avant : `4708cbc` Empreinte après : `4a826b4`

Ce qui s'est passé : `--amend` ne modifie pas le commit, il en crée un nouveau qui le remplace. Le contenu est le même mais le message a changé, et comme l'empreinte est calculée à partir de tout ça (contenu, message, auteur, date, parent), on obtient un hash différent. L'ancien commit `4708cbc` n'est plus dans l'historique de la branche. C'est pour ça qu'il ne faut pas faire d'amend sur un commit déjà poussé.

### Étape 8 : graphe et diff

```
git log --oneline --graph --decorate --all
* 4a826b4 (HEAD -> main) Commente le compteur d articles
* b0b4789 Affiche le compteur
* 2891fe0 Ajoute un compteur d articles
* 4aabfea Ignore build/ et *.local, début du journal TP 03
* 29f5925 Reformule le README, poste.md et le journal du TP 02
* 69847f7 Ajoute .gitattributes et script.sh
* 8f0da1e Ajoute le dossier logs/ (avec .gitkeep)
* eadd069 TP 01 : complète poste.md
* 9b2ed22 TP 02 : journal.md
* 3300e5b Ajoute pom.xml et App.java, complète le README
* fed7744 Complète le README
* adec2e0 Ajoute le README
* a92aa20 TP 01 : poste.md
* 9459f3b Initialise le dépôt des TP : énoncés et starter
```

Une seule branche donc le graphe est une ligne droite.

```
git diff HEAD~1 HEAD
diff --git a/inventaire/src/App.java b/inventaire/src/App.java
index bc78a47..fb0ee7f 100644
--- a/inventaire/src/App.java
+++ b/inventaire/src/App.java
@@ -1,5 +1,6 @@
 public class App {
 
+  // Nombre d articles en stock
   private static int nbArticles = 0;
 
   public static void main(String[] args) {
```

On voit juste la ligne de commentaire ajoutée par le dernier commit.

## 2 bis. Supprimer, déplacer

### Étape 9 : suppression avec rm

J'ai créé et commit `doc/brouillon.md`, puis je l'ai supprimé avec `rm` :

```
git status
On branch main
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	deleted:    doc/brouillon.md
```

Ce qu'il faut faire en plus : la suppression n'est que dans le répertoire de travail, elle n'est pas indexée. Il faut faire `git rm doc/brouillon.md` (ou `git add doc/brouillon.md`) pour l'indexer, et ensuite commit.

```
git rm doc/brouillon.md
git status
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	deleted:    doc/brouillon.md

git commit -m "Supprime doc/brouillon.md"
```

### Étape 10 : git rm

J'ai créé et commit `doc/notes.md`, puis :

```
git rm doc/notes.md
rm 'inventaire/doc/notes.md'

git status
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	deleted:    doc/notes.md
```

Ce que git rm fait en une commande : il supprime le fichier du disque ET il indexe la suppression. Avec `rm` il fallait deux étapes (`rm` puis `git rm`/`git add`). Le dossier `doc/` a aussi disparu vu qu'il était vide.

### Étape 11 : ne plus suivre build/sortie.bin

Je rejoue l'erreur :

```
git add -f build/sortie.bin
git commit -m "Ajoute build/sortie.bin (par erreur)"
[main 3a20ee5] Ajoute build/sortie.bin (par erreur)
 create mode 100644 inventaire/build/sortie.bin
```

Option utilisée :

```
git rm --cached build/sortie.bin
rm 'inventaire/build/sortie.bin'

git commit -m "Arrête de suivre build/sortie.bin"
[main 3591f27] Arrête de suivre build/sortie.bin
 delete mode 100644 inventaire/build/sortie.bin
```

```
git status
On branch main
nothing to commit, working tree clean

ls build
sortie.bin

git check-ignore -v build/sortie.bin
.gitignore:4:build/	build/sortie.bin
```

Ce qu'elle laisse derrière elle : `--cached` retire le fichier de l'index seulement, il reste sur mon disque. Comme il n'est plus suivi et qu'il est dans `build/`, le `.gitignore` s'applique de nouveau et il n'apparaît plus dans `git status`. Par contre le commit `3a20ee5` est toujours dans l'historique, donc le fichier peut toujours être récupéré depuis ce commit.

### Étape 12 : git mv

```
git mv src/App.java src/Inventaire.java
git status -s
R  src/App.java -> src/Inventaire.java
```

Git affiche un renommage (`R`). En vrai Git ne stocke pas les renommages, il voit une suppression + un ajout et il déduit que c'est un renommage parce que le contenu est quasi identique. Même après avoir changé `class App` en `class Inventaire`, le commit indique un renommage à 87% :

```
git commit -m "Renomme App en Inventaire"
 rename inventaire/src/{App.java => Inventaire.java} (87%)
```

### Étape 13 : git diff / git diff --staged

J'ai ajouté une méthode `getNbArticles()` sans l'indexer :

```
git diff
@@ -3,6 +3,10 @@ public class Inventaire {
   // Nombre d articles en stock
   private static int nbArticles = 0;
 
+  public static int getNbArticles() {
+    return nbArticles;
+  }
+
```

Après `git add src/Inventaire.java`, `git diff` n'affiche plus rien. La modif n'a pas disparu, elle est juste passée dans l'index, et `git diff --staged` la montre :

```
git diff --staged
@@ -3,6 +3,10 @@ public class Inventaire {
   // Nombre d articles en stock
   private static int nbArticles = 0;
 
+  public static int getNbArticles() {
+    return nbArticles;
+  }
+
```

* `git diff` compare le répertoire de travail avec l'index (ce qui n'est pas encore indexé)
* `git diff --staged` compare l'index avec le dernier commit (ce qui partira au prochain commit)

## 3. Annuler selon la zone

### Étape 14 : annuler une modif non indexée

```
git status -s
 M src/Inventaire.java

git restore src/Inventaire.java

git status -s
```

(plus rien, la modif est perdue)

### Étape 15 : annuler seulement l'indexation

```
git status -s
M  src/Inventaire.java

git restore --staged src/Inventaire.java

git status -s
 M src/Inventaire.java
```

Le `M` passe de la 1re à la 2e colonne : ce n'est plus indexé mais la modif est toujours dans le fichier.

### Étape 16

| Je veux annuler… | Commande | Ce que je perds |
|------------------|----------|-----------------|
| une modification non indexée | `git restore <fichier>` | la modification, définitivement |
| une indexation   | `git restore --staged <fichier>` | rien, la modif reste dans le fichier |
| le message du dernier commit | `git commit --amend -m "nouveau message"` | l'ancien commit (nouvelle empreinte) |

## 4. Stash et alias

### Étape 17 : git stash

J'ai repris la modif de l'étape 15 (pas indexée) :

```
git stash
Saved working directory and index state WIP on main: ef85c7c Ajoute getNbArticles()

git status
On branch main
nothing to commit, working tree clean

git stash list
stash@{0}: WIP on main: ef85c7c Ajoute getNbArticles()

git stash pop
Changes not staged for commit:
	modified:   src/Inventaire.java
Dropped refs/stash@{0} (3190cfa17308eb1051a1cf2fc12e9eaa2776bf64)
```

Après le stash le répertoire est propre, et le pop remet la modif et vide la pile. Comme c'était juste une ligne de test, je l'ai annulée ensuite avec `git restore`.

### Étape 18 : alias

```
git config --global alias.st "status -s"
git config --global alias.lg "log --oneline --graph --decorate --all"
```

### Étape 19 : git lg

```
git lg
* ef85c7c (HEAD -> main) Ajoute getNbArticles()
* f0cc368 Renomme App en Inventaire
* 3591f27 Arrête de suivre build/sortie.bin
* 3a20ee5 Ajoute build/sortie.bin (par erreur)
* 2f113bf Supprime doc/notes.md
* 35e07cb Ajoute doc/notes.md
* 5a531a2 Supprime doc/brouillon.md
* 9a17a2c Ajoute doc/brouillon.md
* 4a826b4 Commente le compteur d articles
* b0b4789 Affiche le compteur
* 2891fe0 Ajoute un compteur d articles
* 4aabfea Ignore build/ et *.local, début du journal TP 03
* 29f5925 Reformule le README, poste.md et le journal du TP 02
* 69847f7 Ajoute .gitattributes et script.sh
* 8f0da1e Ajoute le dossier logs/ (avec .gitkeep)
* eadd069 TP 01 : complète poste.md
* 9b2ed22 TP 02 : journal.md
* 3300e5b Ajoute pom.xml et App.java, complète le README
* fed7744 Complète le README
* adec2e0 Ajoute le README
* a92aa20 TP 01 : poste.md
* 9459f3b Initialise le dépôt des TP : énoncés et starter
```