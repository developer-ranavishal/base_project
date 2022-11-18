package com.example.demo_app.ui.screen.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo_app.core.BaseFragment
import com.example.demo_app.data.local.UserProfile
import com.example.demo_app.data.paging.NewsPagingAdapter
import com.example.demo_app.data.remote.data.News
import com.example.demo_app.databinding.FragmentHomeScreenBinding
import com.example.demo_app.logD
import com.example.demo_app.ui.screen.auth.VMAuth
import com.example.demo_app.utils.extensions.showToast
import kotlinx.coroutines.launch

class HomeScreen : BaseFragment<VMHome, FragmentHomeScreenBinding>() {


    private val newsAdapter by lazy {
        NewsPagingAdapter{position,index,news ->
            if (position==0){
                onItemClick(index,news)
            }
        }
    }



    override fun getViewBinding(): FragmentHomeScreenBinding =
        FragmentHomeScreenBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        getUserProfileFromDb()
        setNewsRv()
    }




    /** reading data from the cloud fireStore **/
    private fun getUserProfileFromDb() {
        vm.db.collection("users").document((vm.prefManger().getString("f-auth-token"))).get()
            .addOnSuccessListener { result ->
                val userProfile = result.toObject(UserProfile::class.java)
                binding.user = userProfile
                logD("db_read", "$userProfile")
            }
            .addOnFailureListener { exception ->
                logD("db_read", "$exception")

            }
    }




    private fun setNewsRv(){
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.setHasFixedSize(true)
        loader.show()
        lifecycleScope.launchWhenStarted{
            vm.news.observe(viewLifecycleOwner){
                loader.hide()
                newsAdapter.submitData(lifecycle,it)
            }
        }

    }

    private fun onItemClick(index : Int, news: News.Result) {
        showToast("${news.creator} news selected at $index")
    }
}