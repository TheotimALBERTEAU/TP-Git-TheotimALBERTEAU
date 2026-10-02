# TP 06 - Travail collaboratif (GitHub Flow)

URL du dépôt : https://github.com/TheotimALBERTEAU/Flow-Lab-Theotim-Landry

Membres du groupe : Théotim ALBERTEAU, Landry (Yndral-c)

## 1. Le dépôt d'équipe

* Création du dépôt public `Flow-Lab-Theotim-Landry` avec `README.md` et `Salutation.java`
* Issues ouvertes (une par membre)
  
  | Issue | Membre | Salutation demandée |
  |-------|--------|---------------------|
  | \#1   | Théotim | Langue              |
  | \#2   | Théotim | Anonyme             |
  | \#3   | Landry | Version tutoyée     |
  | \#4   | Landry | Selon l’heure       |

### Protection de main

* Règle activée : Settings → Rules → Rulesets → « Require a pull request before merging », 1 approbation
* Ce que la règle interdit exactement : La règle interdit de push directement sur le main.
* Test avec un push direct sur `main`

  ```bash
  git push
  Enumerating objects: 5, done.
  Counting objects: 100% (5/5), done.
  Delta compression using up to 12 threads
  Compressing objects: 100% (3/3), done.
  Writing objects: 100% (3/3), 386 bytes | 32.00 KiB/s, done.
  Total 3 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)
  remote: error: GH013: Repository rule violations found for refs/heads/main.
  remote: Review all repository rules at https://github.com/TheotimALBERTEAU/Flow-Lab-Theotim-Landry/rules?ref=refs%2Fheads%2Fmain
  remote: 
  remote: - Changes must be made through a pull request.
  remote: 
  To github.com:TheotimALBERTEAU/Flow-Lab-Theotim-Landry.git
   ! [remote rejected] main -> main (push declined due to repository rule violations)
  error: failed to push some refs to 'github.com:TheotimALBERTEAU/Flow-Lab-Theotim-Landry.git'
  ```

## 2. Ma branche

* Clone et création de ma branche

  ```bash
  git clone 
  git switch -c  feat/salutation-langue
  Switched to a new branch 'feat/salutation-langue'
  ```
* Mes deux commits (Conventional Commits) et le push

  ```bash
  git add starter/
  warning: in the working copy of 'starter/Salutation.java', LF will be replaced by CRLF the next time Git touches it
  
  git commit -m "feat: Add salutation by language"
  [feat/salutation-langue d10a4f7] feat: Add salutation by language
   1 file changed, 2 insertions(+), 1 deletion(-)
   
  git add README.md
    
  git commit -m "docs: README"
  [feat/salutation-langue 4b342c8] docs: README
   1 file changed, 1 insertion(+), 1 deletion(-)
   
  git push --set-upstream origin feat/salutation-langue
  Enumerating objects: 14, done.
  Counting objects: 100% (14/14), done.
  Delta compression using up to 12 threads
  Compressing objects: 100% (9/9), done.
  Writing objects: 100% (10/10), 1.09 KiB | 123.00 KiB/s, done.
  Total 10 (delta 1), reused 0 (delta 0), pack-reused 0 (from 0)
  remote: Resolving deltas: 100% (1/1), completed with 1 local object.
  remote: 
  remote: Create a pull request for 'feat/salutation-langue' on GitHub by visiting:
  remote:      https://github.com/TheotimALBERTEAU/Flow-Lab-Theotim-Landry/pull/new/feat/salutation-langue
  remote: 
  To github.com:TheotimALBERTEAU/Flow-Lab-Theotim-Landry.git
   * [new branch]      feat/salutation-langue -> feat/salutation-langue
  branch 'feat/salutation-langue' set up to track 'origin/feat/salutation-langue'.
  ```

### Ma Pull Request

* Lien : https://github.com/TheotimALBERTEAU/Flow-Lab-Theotim-Landry/pull/5
* Description :
  * Ce que ça change : ajoute une salutation selon la langue dans `Salutation.java`
  * Pourquoi : c'est ce que demande l'Issue #1
  * Comment le vérifier : `cd starter`, puis `javac Salutation.java && java Salutation`, la salutation s'affiche dans la langue choisie
  * Closes #1

## 3. La revue

### Mes commentaires sur la PR de Landry (#6)

* `question:` sur le `System.out.println(saluerParHeure("user"));` : pourquoi remplacer l'appel à `saluer` ? Du coup on n'affiche plus que la salutation par heure, et `saluerVersionTutoyee` n'est jamais appelée.
* `suggestion:` sur `saluerParHeure` : l'heure est écrite en dur ("il est 13h25"). On pourrait prendre l'heure réelle avec `LocalTime.now()` et afficher "Bonjour" ou "Bonsoir".
* `issue:` sur `saluerVersionTutoyee` : "Hello user version tutoyée !!" n'est pas vraiment un tutoiement, plutôt quelque chose comme "Salut user, comment vas-tu ?".

### Commentaires reçus sur ma PR

* `issue:` j'ai remplacé "Hello" par "Hallo" au lieu d'ajouter une salutation : l'anglais a disparu.
* `suggestion:` faire une méthode `saluerParLangue(nom, langue)` à part, et laisser `saluer` comme avant.
* `question:` pourquoi le commit "Test Ruleset Bloquage Main" est dans la PR ? (j'avais créé ma branche depuis mon `main` local, qui contenait mon commit de test)
* Ce que j'ai corrigé : j'ai remis "Hello" dans `saluer` et j'ai mis la salutation allemande dans une méthode à part, `saluerAllemand`.

  ```bash
  git add starter/Salutation.java
  git commit -m "fix : Reviews"
  git push
  ```
* La PR s'est mise à jour sans la rouvrir : oui, le nouveau commit est apparu tout seul dans la PR #5 et les commentaires sont passés en "Outdated".

### Fusion en squash

* Nombre de commits ajoutés sur `main` par cette fusion : 1 seul. La PR #6 de Landry avait 6 commits, le squash les regroupe en un seul (`982f62f`).
* Ce qu'il advient de l'Issue liée : elle se ferme toute seule au moment de la fusion, grâce au `Closes #` de la description, et elle affiche un lien vers la PR.

## 4. Le conflit d'équipe

* Première PR fusionnée : #8 (Landry, version tutoyée, branche `feat/saluerVersionTutoyee`)
* Seconde PR en conflit : #7 (la mienne, salutation anonyme, branche `feature/saluer-anonyme`)

### Prédiction avant d'ouvrir le fichier

* Je pense voir les marqueurs `<<<<<<< HEAD`, `=======` et `>>>>>>> origin/main` dans la méthode `saluer()`, sur la ligne du `return` : on l'a modifiée tous les deux. Ma version sera en haut (HEAD) et celle de Landry en dessous.

### Résolution en local

```bash
git switch feature/saluer-anonyme
git fetch origin
git merge origin/main
```

### Salutation.java en conflit (avant résolution)

```java
// Starting point for TP 06. One method, one line per member: the conflicts are real because
// everyone edits the same place, which is exactly what happens on a shared codebase.
public class Salutation {

  public static void main(String[] args) {
    System.out.println(saluer("user"));
    System.out.println(saluerParHeureFrancaise("user"));
  }

  static String saluer(String nom) {
    // TODO: chaque membre du groupe ajoute ICI sa salutation, dans sa propre branche.
<<<<<<< HEAD
    return "Hello, il fallait une heure donc il est 13h44 !";
=======
    return "Hello tutoyée, " + nom + " il fallait une heure donc il est 13h44 !";
>>>>>>> origin/main
  }

  static String saluerParHeureFrancaise(String nom) {
    return "Hello " + nom + " il est 13h25 !!";
  }

  static String saluerAllemand(String nom) {
    // TODO: chaque membre du groupe ajoute ICI sa salutation, dans sa propre branche.
    // Salutation en Allemand
    return "Hallo soutenu, " + nom + "!";
  }
}
```

```bash
git add starter/Salutation.java
git commit -m "fix : resolve conflicts"
git push
```

* Ce que j'ai gardé : une seule ligne qui reprend les deux versions, le "tutoyée" de Landry et ma version sans le nom (anonyme).

### Après résolution

```bash
git log --oneline --graph --all
*   367d4ae (HEAD -> feature/saluer-anonyme, origin/feature/saluer-anonyme) fix : resolve conflicts
|\  
| * f25b30c (origin/main, origin/HEAD) feat: changement du message dans le return (#8)
* | 38f96da feat : saluer anonyme
* | 8135e5a (main) Merge branch 'main' of github.com:TheotimALBERTEAU/Flow-Lab-Theotim-Landry
|\| 
| | * 82df9e3 (origin/feat/saluerVersionTutoyee) feat: changement du message dans le return
| |/  
| * c9e18f5 Feat/salutation langue Closes #1 (#5)
| * 982f62f Feat/saluer par heure Closes #3 (#6)
| | * 8860ff7 (origin/feat/salutation-langue, feat/salutation-langue) fix : miss }
| | *   ca4ecd7 Merge branch 'feat/salutation-langue' of github.com:TheotimALBERTEAU/Flow-Lab-Theotim-Landry into feat/salutation-langue
| | |\  
| | | * 4b342c8 docs: README
| | | * d10a4f7 feat: Add salutation by language
| |_|/  
|/| |   
* | | 9dc7a97 Test Ruleset Bloquage Main
|/ /  
| * e5462b7 fix : Reviews
| * 14f8415 docs: README
| * 883d54f feat: Add salutation by language
|/  
| * 6c8499e (origin/feat/saluerParHeure) fix: suppression de la methode saluerVersionTutoyee
| * c237155 fix: nom de méthode plus précis
| * be7d3fe fix: appel des 3 methodes
| * 54be5a8 fix: ce que return la methode saluer
| * f2a14ba feat: Ajout méthode saluer version tutoyée
| * a42cb89 feat: Ajout méthode saluer par heure
|/  
* 680eb29 First Commit
```

Le programme compile toujours après la résolution :

```bash
javac Salutation.java && java Salutation
Hello tutoyée, il fallait une heure donc il est 13h44 !
Hello user il est 13h25 !!
```

* Pourquoi le conflit est apparu ici et pas au TP 04 : au TP 04 j'étais seul et j'avais fait exprès de modifier la même ligne sur deux branches locales. Ici personne ne l'a cherché : `main` a avancé sur GitHub avec la PR de Landry pendant que ma branche était ouverte, et on avait touché à la même ligne de `saluer()`.

## 5. Relecture du flux (réponses collectives)

* Combien de temps une branche est restée ouverte : `feat/salutation-langue` environ une journée (créée le 1er octobre, fusionnée le 2). Les autres moins d'une heure : `feat/saluerParHeure`, `feat/saluerVersionTutoyee` et `feature/saluer-anonyme`.
* Qui a relu quoi : j'ai relu et approuvé les PR #6 et #8 de Landry, Landry a relu et approuvé mes PR #5 et #7.
* Ce qui aurait été différent si `main` n'avait pas été protégée : on aurait pu push directement sur `main`, sans relecture. Le premier à push aurait gagné, l'autre aurait eu son push refusé et un conflit à régler seul, et du code qui ne compile pas aurait pu arriver sur `main` sans que personne ne le voie.

## Salutation.java final

```java
// Starting point for TP 06. One method, one line per member: the conflicts are real because
// everyone edits the same place, which is exactly what happens on a shared codebase.
public class Salutation {

  public static void main(String[] args) {
    System.out.println(saluer("user"));
    System.out.println(saluerParHeureFrancaise("user"));
  }

  static String saluer(String nom) {
    // TODO: chaque membre du groupe ajoute ICI sa salutation, dans sa propre branche.
    return "Hello tutoyée, il fallait une heure donc il est 13h44 !";
  }

  static String saluerParHeureFrancaise(String nom) {
    return "Hello " + nom + " il est 13h25 !!";
  }

  static String saluerAllemand(String nom) {
    // TODO: chaque membre du groupe ajoute ICI sa salutation, dans sa propre branche.
    // Salutation en Allemand
    return "Hallo soutenu, " + nom + "!";
  }
}
```

```bash
javac Salutation.java && java Salutation
Hello tutoyée, il fallait une heure donc il est 13h44 !
Hello user il est 13h25 !!
```
