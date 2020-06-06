package samer.app.vtracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static samer.app.vtracker.MainActivity.list_countries;
import static samer.app.vtracker.MainActivity.list_countries_search;

public class AdapterForRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "AdapterForRecyclerView";
    private Context context;

    //Default constructor:
    AdapterForRecyclerView(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Gets note data based on position
        Country country = list_countries.get(position);
        ((ListViewHolder) holder).bind(country);
    }


    //Returns size of list_countries
    @Override
    public int getItemCount() {
        if (list_countries.isEmpty()) {
            return 0;
        } else {
            return list_countries.size();
        }
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    static class ListViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private TextView textView_country_name;
        private TextView textView_number_infected;
        private TextView textView_number_recovered;
        private TextView textView_number_deceased;



        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        ListViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            textView_country_name = itemView.findViewById(R.id.country_name);
            textView_number_infected = itemView.findViewById(R.id.number_infected);
            textView_number_recovered = itemView.findViewById(R.id.number_recovered);
            textView_number_deceased = itemView.findViewById(R.id.number_deceased);
        }

        void bind(Country country) {
            textView_country_name.setText(country.getCountry_name());
            textView_number_infected.setText(country.getNumber_infected());
            textView_number_recovered.setText(country.getNumber_recovered());
            textView_number_deceased.setText(country.getNumber_deceased());
        }
    }

    /**
     * @author Samer M
     * @param searchFor
     */
    void searchFor(String searchFor) {
        if(list_countries.size() > list_countries_search.size()){
            list_countries_search.clear();
            list_countries_search.addAll(list_countries);
        }
        list_countries.clear();
        Log.i(TAG, "LIST COUNTRIES: " + list_countries.toString());
        searchFor = searchFor.trim();
        if (searchFor.isEmpty()) {
            list_countries.addAll(list_countries_search);
            Log.e(TAG, "search term is null.");
        } else {
            searchFor = searchFor.toLowerCase(); //change searchFor to lowercase
            for (Country i : list_countries_search) {
                //Search notes:
                if (i.getCountry_name().toLowerCase().contains((searchFor))) {
                    list_countries.add(i);
                    Log.i(TAG, i.getCountry_name());
                }
            }//For
        }//Else
        notifyDataSetChanged();
    }//searchNotes
}
