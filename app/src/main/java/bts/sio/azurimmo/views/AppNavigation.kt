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
import bts.sio.azurimmo.views.appartement.AppartementConsult
import bts.sio.azurimmo.views.appartement.AppartementDel
import bts.sio.azurimmo.views.appartement.AppartementList
import bts.sio.azurimmo.views.appartement.AppartementModify
import bts.sio.azurimmo.views.appartement.InterventionDel
import bts.sio.azurimmo.views.batiment.BatimentAdd
import bts.sio.azurimmo.views.batiment.BatimentConsult
import bts.sio.azurimmo.views.batiment.BatimentDel
import bts.sio.azurimmo.views.batiment.BatimentList
import bts.sio.azurimmo.views.batiment.BatimentModify
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
                //AppartementList(
                BatimentConsult (
                    batimentId =batimentId,
                    onAddAppartementClick = {batimentId->
                        navController.navigate("add_appartement/$batimentId")
                    },
                    onDeleteAppartementClick = {batimentId->
                        navController.navigate("del_appartement/$batimentId")
                    },
                    onModifyBatimentClick = {batimentId->
                        navController.navigate("mod_batiment/$batimentId")
                    },
                    onAppartementClick = {appartementId->
                        navController.navigate("consult_appartement/$appartementId")
                    }
                )
                Log.d("BatimentClik", "Batiment cliqué : $batimentId")
            }else{
                Text(text="Identifiant manquant", color = MaterialTheme.colorScheme.error)
            }

        }



        composable("consult_appartement/{appartementId}", listOf(navArgument("appartementId"){type= NavType.IntType})){
            backStackEntry->
            val appartementId=backStackEntry.arguments?.getInt("appartementId")
            if(appartementId!=null){
                //AppartementList(
                AppartementConsult (
                    appartementId =appartementId,
                    onModifyAppartementClick = {appartementId->
                        navController.navigate("mod_appartement/$appartementId")
                    },
                    onAddInterventionClick = {navController.navigate(route = "add_intervention") },
                    onDeleteInterventionClick = {interventionId->
                        navController.navigate("del_intervention/$interventionId")
                    }
                )
                Log.d("AppartementClik", "Appartement cliqué : $appartementId")
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
                onDeleteAppartementClick = {navController.navigate(route="del_appartement")},
                onAppartementClick =  { appartementId ->
                    navController.navigate("consult_appartement/$appartementId")
                }

            )
        }

        composable("interventions_list"){
            InterventionList(
                onAddInterventionClick = {navController.navigate("add_intervention")},
                onDeleteInterventionClick = {navController.navigate("del_intervention")}
            )
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


        composable("add_intervention"){
            InterventionAdd(
                onAddIntervention = { navController.popBackStack() },
                idAppartement = null
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


        composable("del_intervention/{idAppartement}", listOf(navArgument("idAppartement"){type=NavType.IntType})){
                backStackEntry->
            val idAppartement = backStackEntry.arguments?.getInt("idAppartement")
            InterventionDel(
                onDelIntervention = { navController.popBackStack()},
                idAppartement=idAppartement
            )
        }

        composable("del_intervention") {
            InterventionDel(
                onDelIntervention = { navController.popBackStack() }
            )
        }

        composable("mod_batiment/{idBatiment}", listOf(navArgument("idBatiment"){type=NavType.IntType})){
            backStackEntry->
            val idBatiment = backStackEntry.arguments?.getInt("idBatiment")
            if (idBatiment != null) {
                BatimentModify(
                    onModifyBatiment = { navController.popBackStack()},
                    idBatiment = idBatiment
                )
            }
        }

        composable("mod_appartement/{idAppartement}", listOf(navArgument("idAppartement"){type=NavType.IntType})){
                backStackEntry->
            val idAppartement = backStackEntry.arguments?.getInt("idAppartement")
            if (idAppartement != null) {
                AppartementModify(
                    onModifyAppartement = { navController.popBackStack()},
                    idAppartement = idAppartement
                )
            }
        }
    }
}