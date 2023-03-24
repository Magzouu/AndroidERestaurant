package fr.isen.pietri.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.textclassifier.TextLinks
import android.view.textclassifier.TextSelection
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.isen.pietri.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.pietri.androiderestaurant.databinding.ActivityHomeBinding
import org.json.JSONObject

class Category_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCategoryBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_category)    //pas 2 fois le setContentView avec (R.layout.activity_category) le dernier sera pris en compte
        setContentView(binding.root)


        val category =
            intent.getStringExtra("category") // Récupère le nom de la catégorie passé en argument
            supportActionBar?.title = category // Définit le titre de la page comme le nom de la catégorie

        val menuItems = when (category) { // when permet de selectionner la bonne liste en fonction de la categorie
            "Entrees" -> resources.getStringArray(R.array.starter).toList()
            "Plats" -> resources.getStringArray(R.array.dishes).toList()
            "Desserts" -> resources.getStringArray(R.array.desserts).toList()
            else -> emptyList<String>()
        }

        //val dishes = resources.getStringArray(R.array.dishes).toList() as ArrayList
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this) //les éléments vont s'ordonnancer de manière linéaire
        binding.categoryRecyclerView.adapter = CategoryAdapter(menuItems as ArrayList){
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it);
            startActivity(intent)
        }
    }

    private fun getDishFromServer() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://mescouilles"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, body,
            { response ->
                Log.d("ProduitActivity", "ça marche !")
                val data = Gson().fromJson(response.toSting(), DataResult::class.java)
                val dishes = data.data[0].items.map{it.nameFr ?: ""}.toList() as ArrayList
            },
            { error ->
                Log.e("ProduitActivity", error.toString())
            }
        )
    }
}