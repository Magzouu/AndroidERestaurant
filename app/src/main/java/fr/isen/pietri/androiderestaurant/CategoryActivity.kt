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
                Log.d("CategoryActivity", "ça fonctionneeeeee")
                val data = Gson().fromJson(response.toString(), DataResult::class.java)
                val dishes = data.data[0].items.map{it.nameFr ?: ""}.toList() as ArrayList
                handleAPIData(response.toString())
            },
            { error ->
                Log.e("CategoryActivity", error.toString())
            }
        )

        queue.add(request)
    }

    private fun handleAPIData(data: String) {
        val platRes = Gson().fromJson(data, DataResult::class.java)
        val dishList = arrayListOf<Item>()

        for (platCategory in platRes.data) {
            for (dish in platCategory.items) {
                if (platCategory.nameFr == categoryA) {
                    dishList.add(dish)
                }
            }
        }

        adapter = CategoryAdapter(dishList) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoryRecyclerView.adapter = adapter
    }
}