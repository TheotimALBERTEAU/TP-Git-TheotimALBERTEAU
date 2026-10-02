# TP 01 - Installer et configurer Git

## 1. Installation

Version que j’avais avant update : 

```bash
git --version
git version 2.49.0.windows.5
```

Version après `git update` :

```bash
git --version
git version 2.55.0.windows.5
```

Dans un nouveau terminal, `git --version` marche toujours, pas de souci de PATH.

## 2. Identité des commits

```bash
# Commandes exécutées à l'installation de git
git config --global user.name "Théotim ALBERTEAU"
git config --global user.email "sunshouwu@outlook.com"
```

Git écrit ça dans mon fichier de config global : `C:/Users/Théotim/.gitconfig` (dans la sortie de Git le é s'affiche `\303\251`, c'est juste l'encodage).

Branche par défaut et éditeur :

```bash
git config --global init.defaultBranch main
git config --global core.editor "code --wait"
```

Du coup les nouveaux dépôts partent sur `main` au lieu de `master`, et les messages de commit s'ouvrent dans VS Code.

## 3. Les trois niveaux

Dans un dépôt de test, j'ai mis un email local différent :

```bash
git config user.email "moi@autre-domaine.fr"
git config --list --show-origin | grep user.email
file:"C:/Users/Th\303\251otim/.gitconfig"       user.email=sunshouwu@outlook.com
file:.git/config        user.email=moi@autre-domaine.fr
```

`git config user.email` renvoie `moi@autre-domaine.fr`, donc c'est le local qui gagne.

La règle : c'est le niveau le plus proche du dépôt qui l'emporte, local > global > system.

Après `git config --unset user.email`, il ne reste que la valeur globale :

```bash
file:"C:/Users/Th\303\251otim/.gitconfig"       user.email=sunshouwu@outlook.com
```

## 4. Fins de ligne

```bash
git config --global core.autocrlf true
```

Sur Windows, avec `autocrlf true`, les fichiers sont écrits sur le disque avec des fins de ligne CRLF, mais Git les stocke en LF dans le dépôt.

## Sortie de git config --global --list --show-origin

```bash
file:"C:/Users/Th\303\251otim/.gitconfig"       filter.lfs.clean=git-lfs clean -- %f
file:"C:/Users/Th\303\251otim/.gitconfig"       filter.lfs.smudge=git-lfs smudge -- %f
file:"C:/Users/Th\303\251otim/.gitconfig"       filter.lfs.process=git-lfs filter-process
file:"C:/Users/Th\303\251otim/.gitconfig"       filter.lfs.required=true
file:"C:/Users/Th\303\251otim/.gitconfig"       user.name=Théotim ALBERTEAU
file:"C:/Users/Th\303\251otim/.gitconfig"       user.email=sunshouwu@outlook.com
file:"C:/Users/Th\303\251otim/.gitconfig"       init.defaultbranch=main
file:"C:/Users/Th\303\251otim/.gitconfig"       core.editor=code --wait
file:"C:/Users/Th\303\251otim/.gitconfig"       core.autocrlf=true
```

Tout vient du même fichier, `C:/Users/Théotim/.gitconfig`.