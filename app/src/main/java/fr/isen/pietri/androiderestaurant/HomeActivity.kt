package fr.isen.pietri.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import fr.isen.pietri.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)
    }

    val entree = findViewById<Button>(R.id.boutonEntrees)
    binding.starters.setOnClickListener {
        Log.d("HomeActivity", "carrotttt !")
        Toast.makeText()
        val intent
    }




    public fun on_button_entrees_click(v: View) {
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
}