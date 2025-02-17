package bts.sio.azurimmo.views

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import bts.sio.azurimmo.views.appartement.AppartementList
import bts.sio.azurimmo.views.batiment.BatimentList
import bts.sio.azurimmo.views.locataire.LocataireList

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier){

    NavHost(navController=navController, startDestination = "batiment_list", modifier=modifier){
        composable("batiment_list"){
            BatimentList(
                onBatimentClick={ batimentId ->
                    navController.navigate("batiment_appartements_list/$batimentId")
                },
                onAddBatimentClick = {navController.navigate("add_batiment")}
            )
        }

        composable("batiment_appartements_list/{batimentId}", listOf(navArgument("batimentId"){type= NavType.IntType})){
                backStackEntry->
            val batimentId=backStackEntry.arguments?.getInt("batimentId")
            if(batimentId!=null){
                AppartementList(
                    batimentId =batimentId,
                    onAddAppartementClick = {navController.navigate("add_appartement")}
                )
                Log.d("BatimentClik", "Batiment cliqué : $batimentId")
            }else{
                Text(text="Identifiant manquant", color = MaterialTheme.colorScheme.error)
            }

        }


        composable("locataires_list"){
            LocataireList(onAddLocataireClick = {navController.navigate("add_locataire")})
        }

        composable("appartement_list"){
            AppartementList(onAddAppartementClick = {navController.navigate(route = "add_appartement")})
        }

    }
}