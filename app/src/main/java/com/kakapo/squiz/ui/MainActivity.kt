package com.kakapo.squiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.kakapo.squiz.R
import com.kakapo.squiz.adapter.CategoryAdapter
import com.kakapo.squiz.db.DBHelper
import com.kakapo.squiz.view.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    private fun setView(){
        toolbar.title = "SQuiz"
        setSupportActionBar(toolbar)

        recycler_category.setHasFixedSize(true)
        recycler_category.layoutManager = GridLayoutManager(this, 2)

        val adapter = CategoryAdapter(this, DBHelper.getInstance(this).allCategory)
        recycler_category.addItemDecoration(SpaceItemDecoration(4))
        recycler_category.adapter = adapter
    }
}