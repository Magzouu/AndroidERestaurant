package fr.isen.pietri.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.pietri.androiderestaurant.model.Item

class CategoryAdapter(private var dishes: ArrayList<Item>, val onDishClickListener: (Item) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var list = arrayListOf<Item>()

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val dishName = view.findViewById<TextView>(R.id.dishName)
        val dishImage = view.findViewById<ImageView>(R.id.dishPicture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_cell, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val dish = dishes[position]

        holder.dishName.text = dish.nameFr

        if (dish.images[0].isNotEmpty())
        {
            Picasso.get().load(dishes[position].images[0])
                .placeholder(R.drawable.crumate)    // mettre le crumate si l'image n'est pas trouvée
                .into(holder.dishImage)
        }
        holder.itemView.setOnClickListener {
            onDishClickListener(dish)
        }
    }

    fun updateDishes(dishesFromAPI:ArrayList<Item>){
        list = dishesFromAPI
        dishes = dishesFromAPI
        notifyDataSetChanged()
    }

}
