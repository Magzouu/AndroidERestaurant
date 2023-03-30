package fr.isen.pietri.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pietri.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.pietri.androiderestaurant.model.DataResult
import fr.isen.pietri.androiderestaurant.model.Item
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var adapter: CategoryAdapter
    private lateinit var categoryA: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_category)    //pas 2 fois le setContentView avec (R.layout.activity_category) le dernier sera pris en compte
        setContentView(binding.root)


        /*val category =
            intent.getStringExtra("category") // Récupère le nom de la catégorie passé en argument
            supportActionBar?.title = category // Définit le titre de la page comme le nom de la catégorie

        val menuItems = when (category) { // selectionne la bonne liste en fonction de la categorie
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
        }*/

        categoryA = intent.getStringExtra("category") ?: ""
        binding.categoryTitle.text = categoryA

        getDishFromServer()
    }

    private fun getDishFromServer() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val body = JSONObject().apply { put("id_shop", "1") }
        val request = JsonObjectRequest(
            Request.Method.POST, url, body,
            { response ->
                Log.d("CategoryActivity", "ça marche")
                val data = Gson().fromJson(response.toString(), DataResult::class.java)
                //val dishes = data.data[0].items.map{it.nameFr ?: ""}.toList() as ArrayList
                handleAPIData(response.toString())
            },
            { error ->
                Log.e("CategoryActivity", error.toString())
            }
        )

        queue.add(request)
    }

    private fun handleAPIData(data: String) {
        val dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == categoryA }
        val platList = arrayListOf<Item>()

        for (dishCat in dishesResult.data) {
            for (dish in dishCat.items) {
                Log.d("KONAR", dishCat.nameFr ?: "la bite")
                Log.d("KONASS", categoryA)
                if (dishCat.nameFr == categoryA) {
                    platList.add(dish)
                }
            }
        }

        adapter = CategoryAdapter(platList) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoryRecyclerView.adapter = adapter
    }
}