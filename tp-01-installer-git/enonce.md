# TP 01 — Installer & configurer Git

> Module CI/CD — Git, GitHub Actions & Docker · Durée estimée : 30 min · Niveau : débutant

## Objectifs d'apprentissage

À l'issue de ce TP, vous êtes capable de :

- installer Git et vérifier son numéro de version depuis un terminal ;
- renseigner l'identité qui signera vos commits (`user.name`, `user.email`) ;
- dire quel fichier de configuration porte un réglage, parmi les trois niveaux (`--system`,
  `--global`, `--local`), et lequel l'emporte.

## Contexte

Vous rejoignez une équipe qui livre plusieurs fois par jour. Avant d'écrire la moindre ligne, votre
poste doit produire des commits correctement attribués : un historique dont les auteurs sont faux
ne se répare pas après coup, il se réécrit. C'est le seul TP du module où l'on ne code pas.

## Prérequis

- Un terminal (PowerShell, bash ou zsh) et les droits d'installation sur votre poste.
- Git 2.40 ou plus récent. Les distributions Linux d'école livrent parfois une version plus
  ancienne : vérifiez avant de conclure qu'une commande n'existe pas.

## Instructions

### 1 — Installer, puis prouver l'installation

1. Installez Git (`winget install Git.Git`, `brew install git`, `sudo apt install git` selon votre
   système).
2. **Avant** de la lancer, écrivez dans `poste.md` la version que vous pensez obtenir. Puis lancez
   `git --version` et notez la version réelle. Gardez les deux lignes, même si elles diffèrent :
   c'est le point de départ de la trace demandée en livrable.
3. Ouvrez un terminal neuf et relancez `git --version`. Si la commande n'est pas trouvée alors
   qu'elle l'était, le `PATH` n'a pas été rechargé : notez ce que vous avez fait pour le corriger.

### 2 — L'identité des commits

4. Renseignez votre identité au niveau **global** :

   ```bash
   git config --global user.name "Prénom NOM"
   git config --global user.email "prenom.nom@exemple.fr"
   ```

5. Relisez ce que Git a écrit, et où : `git config --global --list --show-origin`. Reportez le
   chemin du fichier dans `poste.md`.
6. Réglez la branche par défaut sur `main` (`init.defaultBranch`) et choisissez votre éditeur
   (`core.editor`). Un `git commit` qui ouvre `vim` sans prévenir bloque la moitié d'une salle.

### 3 — Les trois niveaux, en pratique

7. Créez un dossier de travail, `git init` dedans, puis posez une valeur **locale** différente de
   la globale :

   ```bash
   git config user.email "moi@autre-domaine.fr"
   git config --list --show-origin | grep user.email
   ```

8. Deux lignes `user.email` apparaissent. Laquelle Git utilise-t-il ? Vérifiez votre réponse avec
   `git config user.email` (sans option), puis expliquez la règle en une phrase dans `poste.md`.
9. Supprimez la valeur locale (`git config --unset user.email`) et vérifiez que la globale reprend
   la main.

<details>
<summary>Indice — si la commande de l'étape 7 ne renvoie rien</summary>

`git config` sans `--global` ni `--system` travaille sur le dépôt **courant** : la commande doit
être lancée depuis le dossier où vous avez fait `git init`, sinon Git répond qu'il n'est pas dans
un dépôt.
</details>

### 4 — Le réglage qui évite un TP de dépannage

10. Sur Windows, réglez `core.autocrlf true` ; sur Linux et macOS, `core.autocrlf input`. Notez en
    une phrase ce que ce réglage change dans le fichier écrit sur le disque. Le TP 03 y revient
    avec `.gitattributes`.

## Livrables

- `poste.md`, qui contient : votre prédiction de version et la version réelle, le chemin du fichier
  de configuration global, votre réponse à l'étape 8 et votre phrase de l'étape 10.
- La sortie complète de `git config --global --list --show-origin`, collée dans `poste.md`.

**Terminé quand** : `git config user.name` et `git config user.email` répondent tous les deux, et
que vous savez dire de quel fichier vient chaque valeur.

## Pour aller plus loin (optionnel)

- Installez `git-delta` ou activez `diff.colorMoved=zebra` et comparez l'affichage d'un `git diff`.
- Ouvrez votre `.gitconfig` dans un éditeur : tout ce que vous avez tapé est là, en texte. Aucune
  commande `git config` n'est magique.
