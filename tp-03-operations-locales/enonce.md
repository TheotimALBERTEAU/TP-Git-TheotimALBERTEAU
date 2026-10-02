# TP 03 — Opérations courantes (local)

> Module CI/CD — Git, GitHub Actions & Docker · Durée estimée : 1h · Niveau : débutant

## Objectifs d'apprentissage

À l'issue de ce TP, vous êtes capable de :

- exclure de l'historique ce qui n'a rien à y faire (`.gitignore`), et forcer la fin de ligne d'un
  fichier (`.gitattributes`) ;
- enchaîner `add`, `commit`, `commit --amend`, supprimer et déplacer un fichier suivi
  (`git rm`, `git mv`) ;
- lire l'historique et les différences (`log`, `diff`, `diff --staged`) ;
- annuler proprement selon la zone concernée (`restore`, `restore --staged`) ;
- mettre un travail de côté (`stash`) et poser des alias.

## Contexte

Vous reprenez un projet de la semaine dernière : le dossier de build a été commité par erreur, un
message de commit est faux, et vous devez basculer d'urgence sur autre chose sans perdre ce que
vous avez commencé. Rien d'exotique : c'est le quotidien, et chacun de ces gestes a sa commande.

## Prérequis

- TP 02 terminé : les trois zones et les quatre états sont acquis. Reprenez **le même dépôt
  `inventaire`** : ce TP le fait évoluer.
- Un éditeur de texte configuré comme `core.editor` (TP 01).

## Instructions

### 1 — Ce qui n'entre pas dans l'historique

1. Dans votre dépôt `inventaire`, créez `build/sortie.bin` et `config.local`.
2. Écrivez un `.gitignore` qui exclut `build/` et `*.local`. Vérifiez avec `git status` : les deux
   doivent disparaître de la liste.
3. Indexez `build/sortie.bin` **de force** (`git add -f`), constatez qu'il passe, puis retirez-le de
   l'index. Écrivez en une phrase, dans `journal.md`, ce que `.gitignore` empêche exactement — et
   ce qu'il n'empêche pas.
4. Créez un dossier `logs/` vide et faites-le apparaître dans l'historique. Git ne versionne pas
   les dossiers vides : trouvez la convention (un fichier `.gitkeep`) et dites pourquoi elle est
   nécessaire.
5. Ajoutez un `.gitattributes` qui force `*.sh` en `text eol=lf`. Créez un `script.sh`, commitez,
   puis vérifiez avec `git ls-files --eol script.sh`. Notez ce qu'affiche la colonne `i/` (index) et
   la colonne `w/` (working tree).

### 2 — Committer, et corriger un commit

6. Faites trois commits successifs sur `src/App.java`, avec des messages courts.
7. Le dernier message est mauvais : corrigez-le avec `git commit --amend`. Comparez
   `git log --oneline` avant et après : notez les deux empreintes du dernier commit. Que s'est-il
   passé ? Répondez dans `journal.md` — c'est une question d'examen.
8. Affichez l'historique en graphe : `git log --oneline --graph --decorate --all`. Puis la
   différence entre les deux derniers commits : `git diff HEAD~1 HEAD`.

### 2 bis — Supprimer, déplacer, et voir la différence au bon endroit

9. Créez `doc/brouillon.md`, commitez-le. Supprimez-le ensuite **avec `rm`** (ou l'explorateur) et
   lancez `git status`. Que faut-il faire de plus pour que la suppression entre dans le prochain
   commit ?
10. Créez `doc/notes.md`, commitez-le, puis supprimez-le cette fois avec `git rm doc/notes.md`.
    Comparez avec l'étape 9 : qu'est-ce que `git rm` fait en une seule commande ?
11. Vous découvrez que `build/sortie.bin` avait été commité avant que le `.gitignore` n'existe
    (rejouez le cas : `git add -f build/sortie.bin`, commitez). Vous voulez qu'il **cesse d'être
    suivi** tout en restant sur votre disque. Trouvez l'option de `git rm` qui fait cela,
    appliquez-la, commitez, et vérifiez avec `git status` qu'il est redevenu non suivi — donc
    ignoré. Notez l'option et ce qu'elle laisse derrière elle.
12. Renommez `src/App.java` en `src/Inventaire.java` avec `git mv`, et regardez `git status -s` :
    Git parle-t-il d'un renommage, ou d'une suppression plus d'un ajout ?
13. Modifiez `src/Inventaire.java` sans l'indexer et lancez `git diff` : vous voyez la
    modification. Indexez-la, relancez `git diff` — que constatez-vous ? Lancez alors
    `git diff --staged`. Écrivez dans `journal.md` ce que compare chacune des deux commandes.

### 3 — Annuler, selon la zone

14. Modifiez `src/Inventaire.java` sans l'indexer, puis annulez la modification. Quelle commande ?
15. Modifiez-le puis indexez-le, et annulez **l'indexation seulement**, en conservant la
    modification. Quelle commande ?
16. Remplissez ce tableau dans `journal.md` — il vous servira tout le semestre :

    | Je veux annuler… | Commande | Ce que je perds |
    | --- | --- | --- |
    | une modification non indexée | | |
    | une indexation | | |
    | le message du dernier commit | | |

### 4 — Mettre de côté, et s'outiller

17. Commencez une modification, puis `git stash`. Vérifiez que le répertoire de travail est propre,
    listez la pile (`git stash list`), puis restaurez (`git stash pop`).
18. Posez deux alias utiles, par exemple :

    ```bash
    git config --global alias.st "status -s"
    git config --global alias.lg "log --oneline --graph --decorate --all"
    ```

19. Lancez `git lg` et collez la sortie dans `journal.md`.

<details>
<summary>Indice — l'étape 14 ou 15 ne fait pas ce que vous attendez</summary>

`git restore <fichier>` travaille sur le répertoire de travail, `git restore --staged <fichier>`
sur l'index. La sortie de `git status` propose la bonne commande dans chaque section : lisez-la
avant de chercher ailleurs.
</details>

## Livrables

- Le dépôt `inventaire` avec son `.gitignore`, son `.gitattributes`, son `logs/.gitkeep` et au
  moins six commits.
- `journal.md` : réponses aux étapes 3, 4, 5, 7, 9 à 13, le tableau de l'étape 16, et la sortie de
  `git lg`.

**Terminé quand** : `git status` est propre, `build/sortie.bin` n'est plus **suivi** (il reste sur
votre disque et n'apparaît plus dans `git status`), et le tableau des annulations est rempli de
mémoire, sans relire le support. L'historique, lui, garde la trace du commit fautif de l'étape 11 :
c'est précisément ce que l'étape vous fait constater.

## Pour aller plus loin (optionnel)

- `git stash` avec plusieurs entrées : `git stash push -m "essai A"`, puis `git stash apply
  stash@{1}`. Quelle différence entre `apply` et `pop` ?
- Cherchez ce que fait `git restore --source=HEAD~2 src/app.txt`, et vérifiez sur votre dépôt.
