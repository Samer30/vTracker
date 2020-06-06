package samer.app.vtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * #Features:
 * search
 * timestamp for data
 * fav countries
 * refresh
 * #One activity:
 * -Refresh button on top
 * -Search bar on top
 * Show favorites on top, favorite by long clicking on country
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.java";
    private RecyclerView recyclerView;
    private AdapterForRecyclerView adapterRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    //Data:
    protected static List<Country> list_countries = new ArrayList<>(); //stores all countries
    protected static List<Country> list_countries_search = new ArrayList<>(); //stores search results

    private DatabaseHelper databaseHelper; //create instance of database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView(); //sets up recycler view for current activity

        databaseHelper = new DatabaseHelper(this); //intialize instance of database created earlier


    }

    private void setupRecyclerView() {
        // Lookup the recyclerView in activity layout
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_countries);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // Create adapter passing in the sample user data
        adapterRecyclerView = new AdapterForRecyclerView(this);
        // Attach the adapter to the recyclerView to populate items
        recyclerView.setAdapter(adapterRecyclerView);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    //BUTTONS:

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed Called");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(intent);
    }

    public void button_refresh(View view) {
        Log.i(TAG, "menu_refresh clicked");
        //Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        onPause();
        //startActivity(intent);
    }

    public void button_settings(View view) {
        Log.i(TAG, "menu_settings clicked");
        //Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        onPause();
        //startActivity(intent);
    }
}