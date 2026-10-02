# TP 05 — Git distant avec GitHub

> Module CI/CD — Git, GitHub Actions & Docker · Durée estimée : 1h · Niveau : intermédiaire

## Objectifs d'apprentissage

À l'issue de ce TP, vous êtes capable de :

- créer un dépôt GitHub, le cloner en SSH ou en HTTPS, et dire ce que chaque protocole exige ;
- enchaîner `commit`, `push`, `pull`, et lire ce que `git remote -v` et `git branch -vv`
  affichent ;
- republier un historique modifié sans écraser le travail d'un autre (`--force-with-lease`) ;
- ouvrir une Issue et la fermer depuis un message de commit.

## Contexte

Votre travail ne vaut que s'il est ailleurs que sur votre disque. À partir d'ici, le dépôt local
n'est qu'une copie parmi d'autres, et les commandes qui traversent le réseau ont leurs propres
pièges : rejet de publication, divergence, écrasement.

## Prérequis

- TP 04 terminé.
- Un compte GitHub. Pour SSH : une clé générée (`ssh-keygen -t ed25519`) et ajoutée au compte.

## Instructions

### 1 — Le dépôt distant

1. Créez sur GitHub un dépôt **privé** `demo-git-<votre-nom>`, sans README.
2. Clonez-le, ou reliez votre dépôt du TP 04 :

   ```bash
   git remote add origin git@github.com:<compte>/demo-git-<nom>.git
   git push -u origin main
   ```

3. Lancez `git remote -v` et `git branch -vv`. Notez dans `journal.md` ce que signifie
   `origin/main` dans la seconde sortie, et pourquoi `-u` ne se met qu'une fois.
4. Essayez l'autre protocole : si vous avez cloné en SSH, ajoutez un second remote en HTTPS
   (`git remote add https-origin …`). Notez ce que chacun demande pour s'authentifier.

### 2 — Le trajet aller-retour

5. Créez un `README.md` (deux lignes : le nom du projet, votre nom), commitez, poussez. Vérifiez
   sur GitHub que le commit y est.
6. Depuis l'interface GitHub, modifiez ce `README.md` et commitez **en ligne**. Votre dépôt local est
   maintenant en retard.
7. **Avant** de lancer `git pull`, écrivez dans `journal.md` ce que vous prévoyez : avance rapide,
   commit de fusion, ou conflit ? Puis lancez-le et notez le résultat réel. S'ils diffèrent,
   expliquez pourquoi en une phrase.
8. Comparez `git fetch` seul et `git pull` : après `git fetch`, où se trouve le nouveau commit ?
   Répondez avec `git log --oneline --all --graph` à l'appui.

### 3 — Republier sans écraser

9. Faites un commit, poussez-le, puis corrigez son message avec `git commit --amend`.
10. Tentez `git push`. Copiez dans `journal.md` le message de rejet **verbatim** : c'est celui que
    vous reverrez toute votre carrière.
11. Republiez avec `git push --force-with-lease`. Expliquez en deux phrases ce que cette option
    vérifie que `--force` ne vérifie pas, et le scénario d'équipe qu'elle protège.

### 4 — Issues et traçabilité

12. Ouvrez une Issue sur votre dépôt : un titre clair, deux lignes de description.
13. Faites un commit dont le message contient `Closes #1`, poussez. Vérifiez que l'Issue s'est
    fermée seule et qu'elle affiche le commit.
14. Notez dans `journal.md` l'intérêt de ce lien pour une revue trois mois plus tard.

<details>
<summary>Indice — le push de l'étape 2 est refusé avec « Permission denied (publickey) »</summary>

La clé n'est pas connue de GitHub, ou l'agent SSH ne la charge pas. `ssh -T git@github.com`
affiche à qui GitHub vous reconnaît : commencez par là plutôt que par recréer une clé.
</details>

## Livrables

- L'URL du dépôt GitHub, avec au moins cinq commits et une Issue fermée par un commit.
- `journal.md` : réponses aux étapes 3, 4, 8, 11, 14, votre prédiction et le résultat réel de
  l'étape 7, le message de rejet verbatim de l'étape 10.

**Terminé quand** : `git status` indique `Your branch is up to date with 'origin/main'`, et que
l'historique en ligne et l'historique local affichent la même empreinte de tête.

## Pour aller plus loin (optionnel)

- Ajoutez un collègue en lecture seule et demandez-lui de cloner : que voit-il de votre historique ?
- `git push --dry-run` : lisez ce qu'il annonce avant de publier pour de bon.
