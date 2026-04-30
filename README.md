# LabThreadsAsyncTask 

> Travail Pratique Android — Programmation Asynchrone (Thread & AsyncTask)

---

## 📱 Aperçu

Application Android démontrant la programmation asynchrone sans bloquer l'interface utilisateur (UI Thread).

<img width="564" height="696" alt="image" src="https://github.com/user-attachments/assets/13bc7620-a92a-46a5-8f19-9d73e8d4ed75" />

<img width="631" height="881" alt="image" src="https://github.com/user-attachments/assets/e2dabb30-01d2-4355-9b54-73b2a239b9b1" />


---

## 🎯 Objectifs du TP

- Comprendre la différence entre **UI Thread** et **Worker Thread**
- Créer un `Thread` avec `Runnable`
- Mettre à jour l'interface avec `Handler` et `View.post(...)`
- Utiliser `AsyncTask` avec barre de progression
- Éviter les erreurs classiques : UI bloquée, `CalledFromWrongThreadException`

---
## 🎬 Démonstration vidéo

> https://github.com/user-attachments/assets/d1392d65-61f8-4e6d-b447-30fc5756ffab

##  Fonctionnalités

| Bouton | Action | Technologie |
|---|---|---|
| **Charger image (Thread)** | Charge une image avec progression 0→100% | `Thread` + `Handler` |
| **Calcul lourd (AsyncTask)** | Effectue un calcul intensif avec ProgressBar verte | `AsyncTask` |
| **Afficher Toast** | Affiche un Toast immédiat même pendant un traitement | UI Thread |

---

##  Architecture du projet

```
LabThreadsAsyncTask/
├── app/src/main/
│   ├── java/com/example/labthreadsasynctask/
│   │   └── MainActivity.java          ← Logique Thread + AsyncTask
│   └── res/
│       ├── layout/
│       │   └── activity_main.xml      ← Interface utilisateur
│       └── drawable/
│           ├── photo.jpg              ← Image réelle chargée
│           ├── card_background.xml    ← Style carte blanche
│           ├── progress_green.xml     ← Barre de progression verte
│           ├── image_border.xml       ← Bordure image arrondie
│           └── placeholder.xml       ← Image par défaut
└── 
```

---

##  Concepts clés

### 1. UI Thread (Main Thread)
- Affiche l'écran et gère les clics
- **Ne jamais** y faire un traitement long → risque d'ANR

### 2. Worker Thread
- Exécute les tâches longues en arrière-plan
- **Ne peut pas** modifier directement une View

### 3. Retour au UI Thread
```java
// Méthode utilisée dans ce projet
mainHandler.post(() -> {
    img.setImageBitmap(bitmap);  // ✅ Sécurisé
});
```

### 4. AsyncTask
```
onPreExecute()      → UI thread   (avant)
doInBackground()    → Worker thread (pendant)
onProgressUpdate()  → UI thread   (progression)
onPostExecute()     → UI thread   (après)
```

---

##  Technologies utilisées

- **Langage** : Java
- **IDE** : Android Studio
- **Min SDK** : API 21 (Android 5.0)
- **APIs** : `Thread`, `Handler`, `AsyncTask`, `BitmapFactory`

---

## ✅ Résultats de validation

- [x] Toast s'affiche immédiatement → UI non bloquée
- [x] Image chargée avec progression visible (0% → 100%)
- [x] ProgressBar verte animée pendant le calcul
- [x] Résultat AsyncTask affiché : `51599823`
- [x] Design moderne et responsive




---

## 👨‍💻 Auteur

- **Nom** : ASSEKNOUR Sana
- **Filière** :Cybersecurity - ENSA Marrakech
- **Année** : 2025-2026

---

## 📚 Références

- [Android Developers — Processes and Threads](https://developer.android.com/guide/components/processes-and-threads)
- [Android Developers — AsyncTask](https://developer.android.com/reference/android/os/AsyncTask)
- [Android Developers — Handler](https://developer.android.com/reference/android/os/Handler)
