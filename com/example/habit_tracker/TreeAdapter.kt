package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_tracker.R
import com.example.habit_tracker.Tree

class TreeAdapter(private val treeList: List<Tree>) : RecyclerView.Adapter<TreeAdapter.TreeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tree, parent, false)
        return TreeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        holder.bind(treeList[position])
    }

    override fun getItemCount(): Int {
        return treeList.size
    }

    class TreeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val treeImageView: ImageView = itemView.findViewById(R.id.tree_image_view)

        fun bind(tree: Tree) {
            // Set a tree icon for each tree
            treeImageView.setImageResource(R.drawable.tree_icon) // Replace with your tree icon
        }
    }
}
