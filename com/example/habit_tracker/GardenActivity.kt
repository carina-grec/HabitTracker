package com.example.habit_tracker

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.TreeAdapter

class GardenActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var treeAdapter: TreeAdapter
    private val treeList = mutableListOf<Tree>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garden)
        try {
            recyclerView = findViewById(R.id.tree_recycler_view)
            recyclerView.layoutManager = GridLayoutManager(this, 3) // 3 columns in the grid
            treeAdapter = TreeAdapter(treeList)
            recyclerView.adapter = treeAdapter

            loadTrees()
        } catch (e: Exception) {
            Log.e("GardenActivity", "Error initializing GardenActivity", e)
            Toast.makeText(this, "Error initializing GardenActivity", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadTrees() {
        val sharedPreferences = getSharedPreferences("TreeGardenPrefs", MODE_PRIVATE)
        val treeCount = sharedPreferences.getInt("treeCount", 0)

        if (treeCount == 0) {
            Toast.makeText(this, "No trees yet! Complete tasks to earn trees.", Toast.LENGTH_SHORT).show()
            return
        }

        for (i in 1..treeCount) {
            treeList.add(Tree("Tree $i"))
        }
        treeAdapter.notifyDataSetChanged()
    }
}
