# Recipe Finder App Roadmap

This document outlines the development timeline and steps for the **Recipe Finder** Android app. It is designed to guide the building process from initial setup to a fully functional app.

---

## Phase 1: Project Setup
- [x] Create a new Android Studio project with **Jetpack Compose** support.
- [x] Decide on an app name (e.g., RecipeVault, HomePlate).
- [x] Delete unnecessary test folders (`androidTest/` and `test/`) if not used.
- [x] Ensure `assets/` folder exists under `src/main/`.
- [x] Create Kotlin packages for organization:
  - `data`
  - `viewmodel`
  - `ui.screens`
  - `ui.components`
  - `navigation`
  - `ui.theme`

---

## Phase 2: Add Local Data
- [x] Create `recipes.json` in `assets/` with sample recipes.
- [x] Create `Recipe.kt` data class to define recipe structure.
- [x] Create `RecipeRepository.kt` to load recipes from the JSON file.

---

## Phase 3: ViewModel Setup
- [ ] Create `RecipeViewModel.kt` to:
  - Load recipes from the repository.
  - Expose recipes to the UI via `StateFlow` or `LiveData`.
  - Handle selected recipe state.

---

## Phase 4: Build UI Screens
- [x] **RecipeListScreen**
  - Display recipes in a `LazyColumn`.
  - Use `RecipeCard` component for each recipe.
  - Clicking a card navigates to `RecipeDetailScreen`.
- [x] **RecipeDetailScreen**
  - Display recipe details: image, ingredients, instructions.
  - Optional: add “Save to Favorites” button.
- [x] **FavoritesScreen** (optional for Phase 1)
  - Display saved recipes.
- [x] **UI Components**
  - Create reusable `RecipeCard.kt` composable for recipe previews.

---

## Phase 5: Navigation
- [x] Create `NavGraph.kt` to define app navigation.
- [x] Define routes: `list` → `detail` → `favorites`.
- [x] Pass selected recipe ID from list to detail screen.
- [x] Set up `NavHost` in `MainActivity.kt`.

---

## Phase 6: Testing Local Recipe Repository
- [x] Run the app and verify:
  - Recipes display correctly in the list.
  - Clicking a recipe opens the detail screen.
  - Images and data display properly (placeholder if `imageResId` is empty).

---

## Phase 7: Persistent Storage (Room)
- [ ] Add Room database for favorites.
  - Create `RecipeDao.kt` for database queries.
  - Create `AppDatabase.kt` to set up Room.
- [ ] Update `RecipeViewModel.kt` to handle favorites.
- [ ] Update `FavoritesScreen` to display saved recipes.
- [ ] Add dialogs/buttons to add/remove favorites.

---

## Phase 8: Optional Extras
- [ ] Add **camera or sensor integration** (Chapter 12) — e.g., photo of cooked dish.
- [ ] Use **DataStore** to save user preferences (dark mode, etc.).
- [ ] Replace JRecipe Repositoy with a **web API** (TheMealDB, Edamam, etc.).
- [ ] Polish UI:
  - Colors, typography, spacing.
  - Smooth navigation and responsive performance.

---

## Phase 9: Final Touches
- [ ] Test the app thoroughly (list, detail, favorites, navigation).
- [ ] Ensure the app runs without crashes.
- [ ] Add comments at the top of each Kotlin file describing its purpose.
- [ ] Prepare a short demo video or screenshots for submission.

---

**Note:** Check off each task as you complete it. This roadmap is designed to guide development and ensure all course requirements are met.
