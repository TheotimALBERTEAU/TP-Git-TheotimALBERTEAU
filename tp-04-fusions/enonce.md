# TP 04 — Maîtriser les fusions

> Module CI/CD — Git, GitHub Actions & Docker · Durée estimée : 1h · Niveau : intermédiaire

## Objectifs d'apprentissage

À l'issue de ce TP, vous êtes capable de :

- provoquer délibérément une fusion en avance rapide et une fusion avec commit de fusion
  (`--no-ff`), et dire laquelle l'historique montre ;
- résoudre un conflit à la main, du marqueur `<<<<<<<` au commit de résolution ;
- lire un historique en graphe et y retrouver la forme que vous avez produite.

## Contexte

Deux personnes travaillent sur le même fichier depuis deux branches. L'une fusionne sans rien voir,
l'autre tombe sur un conflit. La différence ne tient pas à la chance : elle tient à la forme de
l'historique, et cette forme se décide.

## Prérequis

- TP 03 terminé.
- Le site `learngitbranching.js.org` accessible (étape 4, facultative si le réseau école le bloque).

## Instructions

### 1 — L'avance rapide

1. Dépôt neuf `merge-lab`, un premier commit sur `main` (`fichier.txt`, trois lignes).
2. `git switch -c feature-a`, ajoutez une ligne, commitez.
3. Revenez sur `main` et fusionnez : `git merge feature-a`. Avant de lancer la commande, écrivez
   dans `journal.md` si vous attendez un commit de fusion. Puis vérifiez avec `git lg`.
4. Expliquez en une phrase pourquoi Git n'a pas créé de commit de fusion ici.

### 2 — Forcer le commit de fusion

5. Créez `feature-b` depuis `main`, un commit dessus. Retournez sur `main`, faites-y un commit sur
   **un autre fichier** : les deux branches ont maintenant divergé.
6. Fusionnez avec `git merge --no-ff feature-b`. Regardez le graphe. Combien de parents a le
   commit de fusion ? Vérifiez : `git cat-file -p HEAD | grep parent`.
7. Reproduisez la forme visée annoncée sur la diapo du TP : `main` et `feature` qui se séparent
   puis se rejoignent. Collez votre `git lg` dans `journal.md`.

### 3 — Le conflit, provoqué puis résolu

8. Depuis `main`, créez `feature-c`. Modifiez **la même ligne** de `fichier.txt` sur les deux
   branches, avec deux contenus différents. Commitez des deux côtés.
9. Fusionnez. Avant de résoudre, copiez dans `journal.md` le contenu exact du fichier en conflit,
   marqueurs compris, et le message d'erreur exact de Git — la ligne entière, pas un résumé.
10. Résolvez à la main : gardez une version cohérente des deux, supprimez les marqueurs, `git add`,
    puis terminez la fusion. Notez la commande qui termine une fusion, et ce qui se passe si vous
    oubliez `git add`.
11. `git log --oneline --graph --all` : retrouvez le commit de fusion et ses deux parents.

### 4 — S'entraîner à la forme

12. Sur `learngitbranching.js.org`, faites les niveaux « Introduction » 1 à 4. Copiez dans
    `journal.md` la suite de commandes du niveau 4 et ce que vous en retenez sur `rebase`. Le module
    n'utilise pas `rebase` en équipe, mais vous le croiserez : sachez ce qu'il change.

<details>
<summary>Indice — l'étape 8 ne produit aucun conflit</summary>

Git fusionne ligne par ligne. Modifier la ligne 1 d'un côté et la ligne 10 de l'autre ne produit
aucun conflit : il faut toucher la **même** ligne, ou une ligne immédiatement voisine.
</details>

## Livrables

- Le dépôt `merge-lab` avec les trois branches et au moins un commit de fusion.
- `journal.md` : votre prédiction de l'étape 3, vos réponses aux étapes 4, 6, 10, le fichier en
  conflit verbatim, le message d'erreur exact, et vos deux sorties `git lg`.

**Terminé quand** : `git log --graph` montre une avance rapide et une fusion à deux parents, et que
le dépôt ne contient plus aucun marqueur de conflit (`grep -r '<<<<<<<' .` ne renvoie rien).

## Pour aller plus loin (optionnel)

- `git merge --abort` pendant un conflit : dans quel état retrouve-t-on le dépôt ?
- `git cherry-pick` : reportez un seul commit de `feature-c` sur `main`, et regardez l'empreinte du
  commit obtenu. Est-ce le même objet ?
