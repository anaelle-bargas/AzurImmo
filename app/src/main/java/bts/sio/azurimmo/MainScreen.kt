package bts.sio.azurimmo

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bts.sio.azurimmo.views.appartement.AppartementList
import bts.sio.azurimmo.views.batiment.BatimentList


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController=navController, startDestination = "batiment_list"){
        composable("batiment_list"){
            BatimentList(onBatimentClick={ batimentId ->
                navController.navigate("batiment_appartements_list/$batimentId")
            })
        }

        composable("batiment_appartements_list/{batimentId}", listOf(navArgument("batimentId"){type= NavType.IntType})){
            backStackEntry->
            val batimentId=backStackEntry.arguments?.getInt("batimentId")
            if(batimentId!=null){
                AppartementList(batimentId=batimentId)
                Log.d("BatimentClik", "Batiment cliqué : $batimentId")
            }else{
                Text(text="Identifiant manquant", color = MaterialTheme.colorScheme.error)
            }

        }
    }
}