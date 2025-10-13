# Recipe Finder App

A simple Android app built with **Jetpack Compose** to browse, view, and save recipes. This project was created as a final project for an Android development course and demonstrates the use of Compose UI, ViewModels, local JSON data, navigation, and optional persistent storage with Room.

---

## 📌 Features

- Browse a list of recipes in a **lazy scrollable list**.
- View detailed recipe information, including ingredients and instructions.
- Save favorite recipes (using Room database for persistence).
- Navigation between screens implemented with **Jetpack Compose Navigation**.
- Local JSON used as initial data source, easily extendable to a web API.
- Clean and responsive UI with placeholder images for recipes.

---

## 🛠️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/recipe-finder.git

2. **Open in Android Studio**
   - Select “Open an existing project” and navigate to the project folder.

3. **Build and Run**
   - Ensure you have an emulator or connected Android device.
  
4. **Test Functionality**
   - Browse recipes, click for details, and (optionally) save favorites.
   
## 📝 Notes
- The app currently uses local JSON (assets/recipes.json) for recipe data.
- imageUrl fields are empty placeholders for now; placeholder images are used in the UI.
- Favorites functionality requires Room database setup.
- Optional features for future updates:
   - Integration with a web API (e.g., TheMealDB or Edamam).
   - Camera or sensor features.
   - Dark mode preferences using DataStore.

 ## ⚡ Technologies Used
 - Kotlin
 - Jetpack Compose (UI, LazyColumn, Card)
 - ViewModel + StateFlow/LiveData
 - Navigation Compose
 - JSON for local data storage
 - Room Database (optional for favorites)

## 📄 License
- This project is for educational purposes and course submission.
