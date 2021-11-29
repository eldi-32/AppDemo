package com.ulventech.appdemo.activities


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ulventech.appdemo.R
import com.ulventech.appdemo.adapters.RepoAdapter
import com.ulventech.appdemo.models.RepoItems


import com.ulventech.appdemo.networks.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_repo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import javax.inject.Inject



@AndroidEntryPoint
class RepoActivity : AppCompatActivity() {

    lateinit var adapter: RepoAdapter

    @Inject
    lateinit var repo : Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)



        var call = repo.getRepos("l007")
        call.enqueue(object : Callback<List<RepoItems>>{
            override fun onResponse(call: Call<List<RepoItems>>, response: Response<List<RepoItems>>) {
//                Log.d("hasilnya",response.body().toString())
                var list = response.body()
                setupList(list)
            }

            override fun onFailure(call: Call<List<RepoItems>>, t: Throwable) {
                Log.e("errornya",t.message.toString())
            }

        })
    }
    fun setupList(list:List<RepoItems?>?){
        rvRepo.setHasFixedSize(true)
        adapter = RepoAdapter(list)
        rvRepo.adapter = adapter
    }
}