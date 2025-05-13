package bts.sio.azurimmo.views.navBar

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun AppNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Batiments", Icons.Default.Home, "batiment_list"),
        BottomNavItem("Apparts", Icons.Default.CheckCircle, "appartement_list"),
        BottomNavItem("Inters", Icons.Default.Build, "interventions_list"),
        BottomNavItem("Locataires", Icons.Default.AccountCircle, "locataires_list"),
        BottomNavItem("Contrats", Icons.Default.ShoppingCart, "contrats_list")
    )


    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 8.dp
    ) {
        val currentRoot = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach{item->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label={ Text(item.label) },
                selected = currentRoot==item.route,
                onClick = { navController.navigate(item.route)}
            )
        }
    }
}