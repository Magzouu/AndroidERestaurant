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
            Log.d("HomeActivity", "LES ENTREES !!")
            Toast.makeText(this,"liste des entrées", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category","Entrées")
            startActivity(intent)
        }


        val plat = findViewById<TextView>(R.id.boutonPlats)
        plat.setOnClickListener {
            Log.d("HomeActivity", "LES PLATS !!")
            Toast.makeText(this,"liste des plats", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category","Plats")
            startActivity(intent)
        }


        val dessert = findViewById<TextView>(R.id.boutonDesserts)
        dessert.setOnClickListener {
            Log.d("HomeActivity", "LES DESSERTS !!")
            Toast.makeText(this,"liste des Dessert", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category","Desserts")
            startActivity(intent)
        }
    }
}