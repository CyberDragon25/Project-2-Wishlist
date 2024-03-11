package edu.iastate.shubham8.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WishlistAdapter
    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WishlistAdapter(wishlistItems) { item ->
            wishlistItems.remove(item)
            adapter.notifyDataSetChanged()
        }
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val itemName = findViewById<EditText>(R.id.ItemNameET).text.toString()
            val productLink = findViewById<EditText>(R.id.ProdictLinkET).text.toString()
            val price = findViewById<EditText>(R.id.PriceET).text.toString()

            wishlistItems.add(WishlistItem(itemName, productLink, price))
            adapter.notifyDataSetChanged()
        }

        // Access the EditText for the product link
        val productLinkEditText = findViewById<EditText>(R.id.ProdictLinkET)
        productLinkEditText.setOnClickListener {
            val productLink = productLinkEditText.text.toString()
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productLink))
                ContextCompat.startActivity(this, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Invalid URL for $productLink", Toast.LENGTH_LONG).show()
            }
        }
    }
}
