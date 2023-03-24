package fr.isen.pietri.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import fr.isen.pietri.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)


        val entree = findViewById<TextView>(R.id.boutonEntrees)
        entree.setOnClickListener {
            Log.d("HomeActivity", "vous avez cliqué sur entrée")
            Toast.makeText(this,"liste des entrées", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category","Entrees")
            startActivity(intent)
            val categoryName = intent.getStringExtra("entrees")
        }


        val plat = findViewById<TextView>(R.id.boutonPlats)
        plat.setOnClickListener {
            Log.d("HomeActivity", "vous avez cliqué sur plats")
            Toast.makeText(this,"liste des plats", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category","Plats")
            startActivity(intent)
            val categoryName = intent.getStringExtra("plats")
        }


        val dessert = findViewById<TextView>(R.id.boutonDesserts)
        dessert.setOnClickListener {
            Log.d("HomeActivity", "vous avez cliqué sur dessert")
            Toast.makeText(this,"liste des Dessert", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category","Desserts")
            startActivity(intent)
            val categoryName = intent.getStringExtra("Dessert")
        }
    }




    /*public fun on_button_entrees_click(v: View) {
        val toast: Toast = Toast.makeText(this, "Carrot !", Toast.LENGTH_LONG)
        toast.show()
    }

    public fun on_button_plats_click(v: View) {
        val toast: Toast = Toast.makeText(this, "Coooow !", Toast.LENGTH_LONG)
        toast.show()
    }

    public fun on_button_desserts_click(v: View) {
        val toast: Toast = Toast.makeText(this, "Cackeeee !", Toast.LENGTH_LONG)
        toast.show()
    }

    private fun openMenu(v: View) {
        val intent = Intent(v.context, Category_Activity::class.java)
        intent.putExtra("dish_type", (v as Button).text.toString())
        v.context.startActivity(intent)
    }*/
}