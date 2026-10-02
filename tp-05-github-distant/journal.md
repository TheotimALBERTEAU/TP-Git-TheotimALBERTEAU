# TP 05 - Git distant avec GitHub

URL du dépôt :

## 1. Le dépôt distant

* Je crée sur GitHub le dépôt privé `demo-git-<nom>`, sans README
* Je relie mon dépôt du TP 04 (ou je clone)

  ```bash
  git remote add origin git@github.com:<compte>/demo-git-<nom>.git
  ```
* Premier push

  ```bash
  git push -u origin main
  Enumerating objects: 21, done.
  Counting objects: 100% (21/21), done.
  Delta compression using up to 12 threads
  Compressing objects: 100% (15/15), done.
  Writing objects: 100% (21/21), 2.00 KiB | 41.00 KiB/s, done.
  Total 21 (delta 2), reused 0 (delta 0), pack-reused 0 (from 0)
  remote: Resolving deltas: 100% (2/2), done.
  To github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git
   * [new branch]      main -> main
  branch 'main' set up to track 'origin/main'.
  ```

### git remote -v / git branch -vv

```bash
git remote -v
origin  git@github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git (fetch)
origin  git@github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git (push)
```

```bash
git branch -vv
feature-a f330b54 Deuxième commit avec ajout de ligne
  feature-b 059d97d Nouvelle ligne 2 feature-b
  feature-c 06069f2 Modif fichier.txt
* main      a4f3a95 [origin/main] Merge branch 'feature-c'
```

* Ce que signifie `origin/main` : c’est la branche main de l’origine sur le dépôt distant
* Pourquoi `-u` ne se met qu'une fois : car ca sert a définir l’origin, et elle restera inchangé

### L'autre protocole

```bash
git remote add https-origin 

git remote -v
https-origin    main (fetch)
https-origin    main (push)
origin  git@github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git (fetch)
origin  git@github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git (push)
```

* Ce que demande SSH pour s'authentifier : une clé ssh générée sur le pc puis mise dans le compte github
* Ce que demande HTTPS pour s'authentifier : Rien

## 2. Le trajet aller-retour

* Je crée le `README.md` (nom du projet + mon nom), je commit et je push

  ```bash
  git add README.md
  
  git commit -m "Add README"
  Enumerating objects: 4, done.
  Counting objects: 100% (4/4), done.
  Delta compression using up to 12 threads
  Compressing objects: 100% (2/2), done.
  Writing objects: 100% (3/3), 371 bytes | 123.00 KiB/s, done.
  Total 3 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)
  To github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git
     a4f3a95..d4a7491  main -> main
     
  git push
  ```
* Je vérifie sur GitHub que le commit est là : Le README est bien ajouté
* Je modifie le `README.md` depuis GitHub et je commit en ligne

### Prédiction avant le git pull

* Le `git pull` va écraser les fichiers, le dépôt distant est la “source fiable”

### git pull

```bash
git pull
Updating d4a7491..1bb736d
Fast-forward
 README.md | 3 ++-
 1 file changed, 2 insertions(+), 1 deletion(-)
```

* Résultat réel : Fichiers écrasés, j’ai la modification faites sur l’UI github

### git fetch vs git pull

```bash
git fetch
```

```bash
git log --oneline --all --graph
  * 1bb736d (origin/main, origin/HEAD) Update README.md
  * d4a7491 (HEAD -> main) Add README
  *   a4f3a95 Merge branch 'feature-c'
  |\  
  | * 06069f2 (feature-c) Modif fichier.txt
  * | 470d807 Modif fichier.txt main
  |/  
  *   e25e629 Merge branch 'feature-b'
  |\  
  | * 059d97d (feature-b) Nouvelle ligne 2 feature-b
  * | e920d43 Ajout fichier2
  |/  
  * f330b54 (feature-a) Deuxième commit avec ajout de ligne
  * 3530290 Premier Commit avec fichier.txt
```

* Après `git fetch`, le nouveau commit se trouve : dans mon dépôt local, mais seulement sur \`origin/main\` (la copie locale de la branche de GitHub). Ma branche \`main\` et mes fichiers ne bougent pas, elle reste un commit en arrière.

## 3. Republier sans écraser

* Je fais un commit et je le push

  ```bash
  git commit -am "Update README"
  [main 3cd47fc] Update README
   1 file changed, 3 insertions(+)
  
  git push
  Enumerating objects: 5, done.
  Counting objects: 100% (5/5), done.
  Delta compression using up to 12 threads
  Compressing objects: 100% (3/3), done.
  Writing objects: 100% (3/3), 493 bytes | 123.00 KiB/s, done.
  Total 3 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)
  To github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git
     1bb736d..3cd47fc  main -> main
  ```
* Je corrige son message avec `--amend`

  ```bash
  git commit --amend -m "Update README 2"
  [main d439bee] Update README 2
   Date: Wed Sep 30 15:02:56 2026 +0200
   1 file changed, 3 insertions(+)
  ```

### Message de rejet (verbatim)

```bash
git push
To github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git
 ! [rejected]        main -> main (non-fast-forward)
error: failed to push some refs to 'github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. If you want to integrate the remote changes,
hint: use 'git pull' before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

### git push --force-with-lease

```bash
git push --force-with-lease
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Delta compression using up to 12 threads
Compressing objects: 100% (3/3), done.
Writing objects: 100% (3/3), 497 bytes | 124.00 KiB/s, done.
Total 3 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)
To github.com:TheotimALBERTEAU/demo-git-alberteau-theotim.git
 + 3cd47fc...d439bee main -> main (forced update)
```

* Ce que `--force-with-lease` vérifie et que `--force` ne vérifie pas : `--force-with-lease` n’écrasera pas les fichiers sur le dépôt distant
* Le scénario d'équipe qu'elle protège : une modif pas pull qui se fait complètement supprimer

## 4. Issues et traçabilité

* J'ouvre une Issue sur le dépôt
  * Titre : Issue TP05 - 4.12
  * Description : Issue de test pour le TP 05 - 4.12  
                          Pour Théotim ALBERTEAU
* Commit avec `Closes #1`

  ```bash
  git commit -am "fix Issue - Closes #1"
  [main 6b564b7] fix Issue - Closes #1
   1 file changed, 2 insertions(+)
   
  git push
   Enumerating objects: 5, done.
  Counting objects: 100% (5/5), done.
  Delta compression using up to 12 threads
  ```
* L'Issue s'est fermée toute seule et affiche le commit : Oui, l’issue s’est fermée toute seule
* L'intérêt de ce lien pour une revue trois mois plus tard : Pouvoir voir quelles modifications ont été apportées

## Fin

```bash
git status
On branch main
Your branch is up to date with 'origin/main'.

nothing to commit, working tree clean
```

* Empreinte de tête en local : `6b564b7` (`HEAD -> main`)
* Empreinte de tête sur GitHub : `6b564b7` (`origin/main`)

  ```bash
  git log --oneline
  6b564b7 (HEAD -> main, origin/main, origin/HEAD) fix Issue - Closes #1
  d439bee Update README 2
  1bb736d Update README.md
  d4a7491 Add README
  a4f3a95 Merge branch 'feature-c'
  470d807 Modif fichier.txt main
  06069f2 (feature-c) Modif fichier.txt
  e25e629 Merge branch 'feature-b'
  e920d43 Ajout fichier2
  059d97d (feature-b) Nouvelle ligne 2 feature-b
  f330b54 (feature-a) Deuxième commit avec ajout de ligne
  3530290 Premier Commit avec fichier.txt
  ```