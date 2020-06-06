package samer.app.vtracker

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterForRecyclerView
//Default constructor:
internal constructor(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.country, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Gets note data based on position
        val country = MainActivity.list_countries[position]
        (holder as ListViewHolder).bind(country)
    }

    //Returns size of list_countries
    override fun getItemCount(): Int {
        return if (MainActivity.list_countries.isEmpty()) {
            0
        } else {
            MainActivity.list_countries.size
        }
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    internal class ListViewHolder(itemView: View) : ViewHolder(itemView) {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private val textView_country_name: TextView
        private val textView_number_infected: TextView
        private val textView_number_recovered: TextView
        private val textView_number_deceased: TextView
        fun bind(country: Country) {
            textView_country_name.text = country.country_name
            textView_number_infected.text = country.number_infected
            textView_number_recovered.text = country.number_recovered
            textView_number_deceased.text = country.number_deceased
        }

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            textView_country_name = itemView.findViewById(R.id.country_name)
            textView_number_infected = itemView.findViewById(R.id.number_infected)
            textView_number_recovered = itemView.findViewById(R.id.number_recovered)
            textView_number_deceased = itemView.findViewById(R.id.number_deceased)
        }
    }

    /**
     * @author Samer M
     * @param searchFor
     */
    fun searchFor(searchFor: String) {
        var searchFor = searchFor
        if (MainActivity.list_countries.size > MainActivity.list_countries_search.size) {
            MainActivity.list_countries_search.clear()
            MainActivity.list_countries_search.addAll(MainActivity.list_countries)
        }
        MainActivity.list_countries.clear()
        Log.i(TAG, "LIST COUNTRIES: " + MainActivity.list_countries.toString())
        searchFor = searchFor.trim { it <= ' ' }
        if (searchFor.isEmpty()) {
            MainActivity.list_countries.addAll(MainActivity.list_countries_search)
            Log.e(TAG, "search term is null.")
        } else {
            searchFor = searchFor.toLowerCase() //change searchFor to lowercase
            for (i in MainActivity.list_countries_search) {
                //Search notes:
                if (i.country_name.toLowerCase().contains(searchFor)) {
                    MainActivity.list_countries.add(i)
                    Log.i(TAG, i.country_name)
                }
            } //For
        } //Else
        notifyDataSetChanged()
    } //searchNotes

    companion object {
        private const val TAG = "AdapterForRecyclerView"
    }

}


