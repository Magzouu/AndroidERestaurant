package fr.isen.pietri.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.viewpager.widget.ViewPager
import fr.isen.pietri.androiderestaurant.model.Item

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}