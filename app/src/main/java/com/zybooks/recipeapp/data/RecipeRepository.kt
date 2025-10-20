package com.zybooks.recipeapp.data

import com.zybooks.recipeapp.R

class RecipeRepository {

    private val recipes = listOf(
        // 1. Spaghetti Bolognese
        Recipe(
            id = 1,
            title = "Spaghetti Bolognese",
            category = "Pasta",
            ingredients = listOf(
                "8 oz (225 g) spaghetti",
                "1 lb (450 g) ground beef",
                "1 small onion, diced",
                "2 cloves garlic, minced",
                "1 can (15 oz) tomato sauce",
                "2 tbsp tomato paste",
                "1 tsp dried oregano",
                "1 tsp dried basil",
                "Salt and pepper, to taste",
                "2 tbsp olive oil"
            ),
            instructions = """
                1. Bring a large pot of salted water to a boil and cook the spaghetti according to package instructions. Drain and set aside.
                2. In a large skillet, heat olive oil over medium heat. Add diced onion and cook until translucent, about 3-4 minutes.
                3. Add minced garlic and cook for another 1 minute until fragrant.
                4. Add ground beef and cook until browned, breaking it apart with a spoon. Drain excess fat if necessary.
                5. Stir in tomato sauce, tomato paste, oregano, basil, salt, and pepper. Simmer for 10-15 minutes, stirring occasionally.
                6. Serve sauce over cooked spaghetti. Garnish with grated Parmesan cheese if desired.
            """.trimIndent(),
            imageResId = R.drawable.spaghetti
        ),

        // 2. Chicken Stir Fry
        Recipe(
            id = 2,
            title = "Chicken Stir Fry",
            category = "Asian",
            ingredients = listOf(
                "1 lb (450 g) chicken breast, thinly sliced",
                "1 red bell pepper, sliced",
                "1 yellow bell pepper, sliced",
                "1 cup broccoli florets",
                "2 cloves garlic, minced",
                "2 tbsp soy sauce",
                "1 tbsp oyster sauce (optional)",
                "1 tsp sesame oil",
                "2 tbsp vegetable oil",
                "Salt and pepper, to taste"
            ),
            instructions = """
                1. Heat 1 tbsp vegetable oil in a large skillet or wok over medium-high heat.
                2. Add sliced chicken and cook until browned and cooked through, about 5-7 minutes. Remove chicken from the pan and set aside.
                3. Add the remaining 1 tbsp vegetable oil. Stir-fry garlic for 30 seconds until fragrant.
                4. Add bell peppers and broccoli. Cook for 3-5 minutes until vegetables are tender-crisp.
                5. Return the chicken to the pan. Add soy sauce, oyster sauce, and sesame oil. Stir to combine and cook for another 2 minutes.
                6. Season with salt and pepper to taste. Serve hot over steamed rice or noodles.
            """.trimIndent(),
            imageResId = R.drawable.stir_fry
        ),

        // 3. Caprese Salad
        Recipe(
            id = 3,
            title = "Caprese Salad",
            category = "Salad",
            ingredients = listOf(
                "3 ripe tomatoes",
                "8 oz (225 g) fresh mozzarella cheese",
                "Fresh basil leaves",
                "2 tbsp extra-virgin olive oil",
                "1 tbsp balsamic glaze (optional)",
                "Salt and freshly ground black pepper, to taste"
            ),
            instructions = """
                1. Slice tomatoes and mozzarella into 1/4-inch thick slices.
                2. Arrange tomato and mozzarella slices alternately on a serving plate. Tuck basil leaves in between.
                3. Drizzle with olive oil and balsamic glaze (if using).
                4. Season with salt and pepper to taste.
                5. Serve immediately as a fresh appetizer or side dish.
            """.trimIndent(),
            imageResId = R.drawable.caprese
        ),

        // 4. Pancakes
        Recipe(
            id = 4,
            title = "Fluffy Pancakes",
            category = "Breakfast",
            ingredients = listOf(
                "1 1/2 cups all-purpose flour",
                "3 1/2 tsp baking powder",
                "1 tsp salt",
                "1 tbsp sugar",
                "1 1/4 cups milk",
                "1 egg",
                "3 tbsp melted butter",
                "Butter or oil for cooking"
            ),
            instructions = """
                1. In a large bowl, sift together flour, baking powder, salt, and sugar.
                2. Make a well in the center and pour in milk, egg, and melted butter. Mix until smooth.
                3. Heat a lightly oiled griddle or skillet over medium-high heat.
                4. Pour 1/4 cup of batter for each pancake. Cook until bubbles appear on the surface, then flip and cook until golden brown.
                5. Serve hot with syrup, fresh fruit, or toppings of your choice.
            """.trimIndent(),
            imageResId = R.drawable.pancakes
        ),

        // 5. Beef Tacos
        Recipe(
            id = 5,
            title = "Beef Tacos",
            category = "Mexican",
            ingredients = listOf(
                "1 lb (450 g) ground beef",
                "1 packet taco seasoning",
                "8 taco shells",
                "1 cup shredded lettuce",
                "1 cup diced tomatoes",
                "1 cup shredded cheese",
                "Sour cream and salsa, for serving"
            ),
            instructions = """
                1. In a skillet, cook ground beef over medium heat until browned. Drain excess fat.
                2. Add taco seasoning and water as directed on the packet. Simmer for 5 minutes.
                3. Warm taco shells according to package instructions.
                4. Fill each shell with seasoned beef, lettuce, tomatoes, and cheese.
                5. Top with sour cream and salsa. Serve immediately.
            """.trimIndent(),
            imageResId = R.drawable.beef_tacos
        ),

        // 6. Vegetable Soup
        Recipe(
            id = 6,
            title = "Hearty Vegetable Soup",
            category = "Soup",
            ingredients = listOf(
                "2 tbsp olive oil",
                "1 onion, diced",
                "2 cloves garlic, minced",
                "2 carrots, sliced",
                "2 celery stalks, sliced",
                "1 zucchini, chopped",
                "1 can (14 oz) diced tomatoes",
                "4 cups vegetable broth",
                "1 tsp dried thyme",
                "Salt and pepper, to taste"
            ),
            instructions = """
                1. In a large pot, heat olive oil over medium heat. Add onion and garlic, cook until softened.
                2. Add carrots and celery, cook for 5 minutes.
                3. Add zucchini, diced tomatoes, vegetable broth, and thyme. Bring to a boil.
                4. Reduce heat and simmer for 20 minutes until vegetables are tender.
                5. Season with salt and pepper. Serve hot with crusty bread.
            """.trimIndent(),
            imageResId = R.drawable.vegetable_soup
        ),

        // 7. Grilled Salmon
        Recipe(
            id = 7,
            title = "Grilled Lemon Garlic Salmon",
            category = "Seafood",
            ingredients = listOf(
                "4 salmon fillets",
                "2 tbsp olive oil",
                "2 cloves garlic, minced",
                "1 lemon, juiced",
                "Salt and pepper, to taste",
                "Fresh parsley for garnish"
            ),
            instructions = """
                1. Preheat grill to medium-high heat.
                2. In a small bowl, mix olive oil, garlic, lemon juice, salt, and pepper.
                3. Brush salmon fillets with the mixture.
                4. Grill salmon for 4-5 minutes per side, until cooked through.
                5. Garnish with fresh parsley and serve with lemon wedges.
            """.trimIndent(),
            imageResId = R.drawable.salmon
        ),

        // 8. Margherita Pizza
        Recipe(
            id = 8,
            title = "Margherita Pizza",
            category = "Pizza",
            ingredients = listOf(
                "1 pizza dough (store-bought or homemade)",
                "1/2 cup tomato sauce",
                "8 oz (225 g) fresh mozzarella, sliced",
                "Fresh basil leaves",
                "2 tbsp olive oil",
                "Salt, to taste"
            ),
            instructions = """
                1. Preheat oven to 475°F (245°C).
                2. Roll out pizza dough on a floured surface. Transfer to a baking sheet or pizza stone.
                3. Spread tomato sauce over dough, leaving a 1-inch border.
                4. Arrange mozzarella slices and basil leaves on top. Drizzle with olive oil and sprinkle with salt.
                5. Bake for 12-15 minutes until crust is golden and cheese is melted.
                6. Slice and serve hot.
            """.trimIndent(),
            imageResId = R.drawable.margherita_pizza
        ),

        // 9. Chocolate Chip Cookies
        Recipe(
            id = 9,
            title = "Chocolate Chip Cookies",
            category = "Dessert",
            ingredients = listOf(
                "2 1/4 cups all-purpose flour",
                "1 tsp baking soda",
                "1 tsp salt",
                "1 cup unsalted butter, softened",
                "3/4 cup granulated sugar",
                "3/4 cup brown sugar",
                "1 tsp vanilla extract",
                "2 large eggs",
                "2 cups chocolate chips"
            ),
            instructions = """
                1. Preheat oven to 375°F (190°C).
                2. In a small bowl, combine flour, baking soda, and salt.
                3. In a large bowl, beat butter, granulated sugar, brown sugar, and vanilla extract until creamy.
                4. Add eggs one at a time, beating well after each.
                5. Gradually add flour mixture and mix until combined. Stir in chocolate chips.
                6. Drop by rounded tablespoon onto ungreased baking sheets.
                7. Bake 9-11 minutes or until golden brown. Cool on wire racks.
            """.trimIndent(),
            imageResId = R.drawable.cookies
        ),

        // 10. Greek Yogurt Parfait
        Recipe(
            id = 10,
            title = "Greek Yogurt Parfait",
            category = "Breakfast",
            ingredients = listOf(
                "2 cups Greek yogurt",
                "1 cup granola",
                "1 cup mixed berries (strawberries, blueberries, raspberries)",
                "2 tbsp honey"
            ),
            instructions = """
                1. In serving glasses, layer 1/4 cup Greek yogurt at the bottom.
                2. Add a layer of granola, then a layer of berries.
                3. Repeat layers until glasses are full.
                4. Drizzle honey on top and serve immediately.
            """.trimIndent(),
            imageResId = R.drawable.yogurt_parfait
        )
    )

    // Function to return all recipes
    fun getAllRecipes(): List<Recipe> = recipes
}
