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
import bts.sio.azurimmo.views.appartement.AppartementAdd
import bts.sio.azurimmo.views.appartement.AppartementDel
import bts.sio.azurimmo.views.appartement.AppartementList
import bts.sio.azurimmo.views.batiment.BatimentAdd
import bts.sio.azurimmo.views.batiment.BatimentDel
import bts.sio.azurimmo.views.batiment.BatimentList
import bts.sio.azurimmo.views.locataire.LocataireDel
import bts.sio.azurimmo.views.contrat.InterventionList
import bts.sio.azurimmo.views.intervention.InterventionAdd
import bts.sio.azurimmo.views.locataire.LocataireAdd
import bts.sio.azurimmo.views.locataire.LocataireList

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier){

    NavHost(navController=navController, startDestination = "batiment_list", modifier=modifier){
        composable("batiment_list"){
            BatimentList(
                onBatimentClick={ batimentId ->
                    navController.navigate("batiment_appartements_list/$batimentId")
                },
                onAddBatimentClick = {navController.navigate("add_batiment")},
                onDeleteBatimentClick = {navController.navigate("del_batiment")}
            )
        }

        composable("batiment_appartements_list/{batimentId}", listOf(navArgument("batimentId"){type= NavType.IntType})){
                backStackEntry->
            val batimentId=backStackEntry.arguments?.getInt("batimentId")
            if(batimentId!=null){
                AppartementList(
                    batimentId =batimentId,
                    onAddAppartementClick = {batimentId->
                        navController.navigate("add_appartement/$batimentId")
                    },
                    onDeleteAppartementClick = {batimentId->
                        navController.navigate("del_appartement/$batimentId")
                    }
                )
                Log.d("BatimentClik", "Batiment cliqué : $batimentId")
            }else{
                Text(text="Identifiant manquant", color = MaterialTheme.colorScheme.error)
            }

        }


        composable("locataires_list"){
            LocataireList(
                onAddLocataireClick = {navController.navigate("add_locataire")},
                onDeleteLocataireClick = {navController.navigate("del_locataire")}
            )
        }


        composable("appartement_list"){
            AppartementList(
                onAddAppartementClick = {navController.navigate(route = "add_appartement") },
                onDeleteAppartementClick = {navController.navigate(route="del_appartement")}
            )
        }

        composable("interventions_list"){
            InterventionList(onAddInterventionClick = {navController.navigate("add_intervention")})
        }

        composable("add_intervention"){
            InterventionAdd(onAddIntervention = {
                navController.popBackStack()
            })
        }

        composable("add_batiment"){
            BatimentAdd(onBatimentAdd = {
                navController.popBackStack()
            })
        }

        composable("add_locataire"){
            LocataireAdd(onAddLocataire = {
                navController.popBackStack()
            })
        }
        composable("add_appartement/{idBatiment}", listOf(navArgument("idBatiment"){type=NavType.IntType})){
            backStackEntry->
            val idBatiment = backStackEntry.arguments?.getInt("idBatiment")
            if(idBatiment != null){
                AppartementAdd(
                    onAddAppartement = {
                        navController.popBackStack()
                    },
                    idBatiment = idBatiment
                )
            }

        }

        composable("add_appartement"){
            AppartementAdd(
                onAddAppartement = { navController.popBackStack() },
                idBatiment = null
            )
        }


        composable("del_batiment"){
            BatimentDel(
                onDelBatiment = { navController.popBackStack()}
            )
        }

        composable("del_locataire"){
            LocataireDel(
                onDelLocataire = { navController.popBackStack()}
            )
        }

        composable("del_appartement/{idBatiment}", listOf(navArgument("idBatiment"){type=NavType.IntType})){
            backStackEntry->
            val idBatiment = backStackEntry.arguments?.getInt("idBatiment")
            AppartementDel(
                onDelAppartement = { navController.popBackStack()},
                idBatiment=idBatiment
            )
        }

        composable("del_appartement"){
            AppartementDel(
                onDelAppartement = { navController.popBackStack()}
            )
        }
    }
}