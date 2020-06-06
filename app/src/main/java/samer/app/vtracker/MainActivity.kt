package samer.app.vtracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

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
class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapterRecyclerView: AdapterForRecyclerView? = null
    private val layoutManager: RecyclerView.LayoutManager? = null
    private var databaseHelper //create instance of database
            : DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView() //sets up recycler view for current activity
        databaseHelper = DatabaseHelper(this) //intialize instance of database created earlier
    }

    private fun setupRecyclerView() {
        // Lookup the recyclerView in activity layout
        recyclerView = findViewById<View>(R.id.recyclerView_countries) as RecyclerView
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView!!.setHasFixedSize(true)
        // Create adapter passing in the sample user data
        adapterRecyclerView = AdapterForRecyclerView(this)
        // Attach the adapter to the recyclerView to populate items
        recyclerView!!.adapter = adapterRecyclerView
        // Set layout manager to position the items
        recyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    //BUTTONS:
    override fun onBackPressed() {
        Log.i(TAG, "onBackPressed Called")
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        finish()
        startActivity(intent)
    }

    fun button_refresh(view: View?) {
        Log.i(TAG, "menu_refresh clicked")
        //Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        onPause()
        //startActivity(intent);
    }

    fun button_settings(view: View?) {
        Log.i(TAG, "menu_settings clicked")
        //Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        onPause()
        //startActivity(intent);
    }

    companion object {
        private const val TAG = "MainActivity.java"

        //Data:
        @JvmField
        var list_countries: MutableList<Country> = ArrayList() //stores all countries
        @JvmField
        var list_countries_search: MutableList<Country> = ArrayList() //stores search results
    }
}