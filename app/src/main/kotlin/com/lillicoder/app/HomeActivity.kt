/*
 * Copyright 2020 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lillicoder.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

/**
 * Home activity for SWWM.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Cannot use findNavController extension function until after onCreate finishes,
        // find the navigation controller manually
        val navController = navController()
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val configuration = AppBarConfiguration(topLevelDestinations(), drawerLayout)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setupWithNavController(navController, configuration)

        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setupWithNavController(navController)
    }

    /**
     * Gets the [NavController] for this activity.
     * @return Nav controller or null if no controller found.
     */
    private fun navController() = navHostFragment().navController

    /**
     * Gets the [NavHostFragment] for this activity.
     * @return Nav host fragment or null if no fragment found.
     */
    private fun navHostFragment() = supportFragmentManager.findFragmentById(
        R.id.container
    ) as NavHostFragment

    /**
     * Gets the set of top-level destinations for this activity. Top-level destinations
     * act as siblings in a navigation hierarchy and will show the drawer icon (instead of an
     * up arrow) when displayed.
     * @return Top-level destination IDs.
     */
    private fun topLevelDestinations(): Set<Int> {
        return setOf(
            R.id.homeFragment,
            R.id.dialogsFragment,
            R.id.gridsFragment,
        )
    }
}
