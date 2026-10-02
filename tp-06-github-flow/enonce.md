# TP 06 — Travail collaboratif (GitHub Flow)

> Module CI/CD — Git, GitHub Actions & Docker · Durée estimée : 1h30 · Niveau : intermédiaire

## Objectifs d'apprentissage

À l'issue de ce TP, vous êtes capable de :

- organiser un dépôt d'équipe : collaborateurs, Issues, branche protégée ;
- dérouler le GitHub Flow de bout en bout — branche, commits, Pull Request, revue, fusion ;
- écrire une revue utile au format Conventional Comments ;
- résoudre un conflit survenu entre deux Pull Requests, et non plus entre deux branches locales.

## Contexte

Vous travaillez à trois ou quatre sur le même dépôt, le même après-midi, sur des fichiers voisins.
C'est le premier TP où le problème n'est plus Git mais la coordination : qui relit quoi, dans quel
ordre on fusionne, et ce qu'on fait quand `main` a bougé sous la branche.

## Prérequis

- TP 05 terminé.
- Groupes de trois ou quatre. Un dépôt **public**, et non privé comme au TP 05 : les règles de
  protection de branche et les rulesets ne sont gratuits que sur un dépôt public ou dans une
  organisation. Ne poussez donc rien de personnel. Tous les TP suivants gardent cette contrainte.
- Un membre du groupe crée le dépôt et ajoute les autres en collaborateurs (Settings → Collaborators).

## Instructions

### 1 — Le dépôt d'équipe (ensemble, 15 min)

1. Créez le dépôt public `flow-lab-<groupe>`, avec un `README.md` et le fichier
   `starter/Salutation.java` fourni, placé à la racine.
2. Ouvrez **une Issue par membre**. Chacune demande une salutation supplémentaire dans la méthode
   `saluer` : selon l'heure, selon la langue, une version tutoyée, une version pour un client
   anonyme. Toutes touchent la **même méthode** — c'est voulu.
3. Activez la protection de `main` : Settings → Rules → Rulesets → « Require a pull request before
   merging », au moins **1 approbation**. Notez dans `journal.md` ce que la règle interdit
   exactement — testez-la en tentant un `git push` direct sur `main`, et collez le message de refus
   verbatim.

### 2 — Chacun sa branche (20 min)

4. Clonez le dépôt, créez votre branche depuis `main` : `git switch -c feat/<votre-section>`.
5. Implémentez votre salutation dans `Salutation.java`, vérifiez que le programme compile
   (`javac Salutation.java && java Salutation`), et faites deux commits en Conventional Commits
   (`feat: …`, `docs: …`). Poussez la branche.
6. Ouvrez une Pull Request qui référence votre Issue (`Closes #N`), avec une description en trois
   lignes : ce que ça change, pourquoi, comment le vérifier.

### 3 — La revue (25 min)

7. Relisez la PR d'un camarade et laissez **trois commentaires** au format Conventional Comments,
   dont au moins un `suggestion:` et un `question:`. Un commentaire = un endroit précis du diff.
8. Recevez les commentaires sur votre PR, répondez-y, corrigez, repoussez. Constatez que la PR se
   met à jour sans qu'on la rouvre.
9. Approuvez la PR que vous avez relue, puis fusionnez-la en **squash**. Dans `journal.md`, notez
   le nombre de commits ajoutés sur `main` par cette fusion, et ce qu'il advient de l'Issue liée.

### 4 — Le conflit d'équipe (20 min)

10. Deux membres ont modifié la même méthode dans deux PR ouvertes en parallèle. Fusionnez la
    première.
11. Sur la seconde, GitHub annonce un conflit. Avant d'ouvrir le fichier, écrivez dans `journal.md`
    ce que vous prévoyez de voir, puis résolvez le conflit **en local** :

    ```bash
    git switch feat/<branche>
    git fetch origin
    git merge origin/main        # ou : git pull origin main
    # résoudre, git add, git commit
    git push
    ```

12. Collez dans `journal.md` le contenu de `Salutation.java` en conflit avant résolution et la
    sortie de `git log --oneline --graph --all` après. Vérifiez que le programme compile encore
    une fois le conflit résolu : une résolution qui casse la compilation est le cas le plus
    fréquent en entreprise. Dites en une phrase pourquoi le conflit est apparu ici et pas au
    TP 04.

### 5 — Relecture du flux (10 min)

13. À trois, répondez par écrit : combien de temps une branche est-elle restée ouverte ? Qui a
    relu quoi ? Qu'est-ce qui aurait été différent si `main` n'avait pas été protégée ?

<details>
<summary>Indice — le ruleset n'apparaît pas dans un dépôt privé</summary>

Les rulesets et la protection de branche sont facturés sur un dépôt privé hors organisation. Le
dépôt de ce TP doit être public, ou vivre dans l'organisation de l'école.
</details>

## Livrables

- L'URL du dépôt : une Issue par membre, toutes fermées par leur PR, `main` protégée, au moins une
  fusion en squash et une PR passée par un conflit.
- `journal.md` par membre : message de refus verbatim de l'étape 3, réponses aux étapes 9 et 12,
  votre prédiction de l'étape 11, et les réponses collectives de l'étape 13.
- `Salutation.java` final, qui compile et affiche la salutation de chacun.

**Terminé quand** : `main` ne contient que des commits arrivés par Pull Request, chaque PR a au
moins une revue approuvée, et `Salutation.java` compile en portant la salutation de chaque membre.

## Pour aller plus loin (optionnel)

- Ajoutez un modèle de PR (`.github/pull_request_template.md`) et observez son effet sur la PR
  suivante.
- Comparez les trois modes de fusion proposés par GitHub (merge, squash, rebase) sur l'historique
  obtenu. Lequel rend `main` lisible ? Lequel perd le détail des commits ?
