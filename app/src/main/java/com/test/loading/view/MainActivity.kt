package com.test.loading.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.loading.R
import com.test.loading.model.Memes
import com.test.loading.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel :ListViewModel
     //val memesAdapater :MemsAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //assign viewmodel to observe
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
//        memesList.apply {
//            layoutManager = LinearLayoutManager(context)
//             adapter=memesAdapater
//        }

        observeModel()

    }

    fun observeModel(){
        viewModel.memesList.observe(this, Observer { memesListData ->
            memesList.visibility = View.VISIBLE
            var memesarrayData = ArrayList<Memes>()
            memesarrayData.addAll(memesListData)

            val adapter = MemsAdapter(memesarrayData, baseContext)
            memesList.layoutManager =
                    LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
            memesList.addItemDecoration(SimpleDividerItemDecoration(baseContext))
            memesList.adapter = adapter;

        })
        viewModel.loading.observe(this, Observer { loadingFinish ->
            if (loadingFinish) {
                loadingBar.visibility = View.VISIBLE
            } else {
                loadingBar.visibility = View.GONE
            }
        })
        viewModel.apiError.observe(this, Observer { isError ->
            Log.e("isError", isError.toString())
            if (isError) {
                errorText.visibility = View.VISIBLE
            } else {
                loadingBar.visibility = View.GONE
                errorText.visibility = View.GONE
            }

        })


    }
}