package com.ranzan.retrofit_iikotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var responseList: List<ResponseModel> = listOf<ResponseModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPost.setOnClickListener {
            callApi()
        }
    }

    private fun callApi() {
        val apiClient = Network.getRetrofitInstance().create(ApiClient::class.java)
        apiClient.getData(etPostId.text.toString().toInt())
            .enqueue(object : Callback<List<ResponseModel>> {
                override fun onResponse(
                    call: Call<List<ResponseModel>>,
                    response: Response<List<ResponseModel>>
                ) {
                    responseList = response.body()!!
                    setRecyclerView()
                }

                override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
                    Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT)
                }

            })
    }

    private fun setRecyclerView() {
        val postAdapter = PostViewAdapter(responseList)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = linearLayoutManager
    }
}