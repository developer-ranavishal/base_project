package com.example.demo_app.core

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.beYou.utils.extensions.snack
import com.example.demo_app.R
import com.example.demo_app.data.remote.BaseDataSource
import com.example.demo_app.utils.views.loader.Loader.Companion.getLoader
import java.lang.reflect.ParameterizedType


abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: VB
    lateinit var loader : Dialog

//    @Inject
//    lateinit var dbRepository: DBRepository

  //  var db = Firebase.firestore

  //  private val ioScope = CoroutineScope(Dispatchers.IO + Job())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        binding = getViewBinding()
        binding.lifecycleOwner = this
        loader = getLoader(this)
        setContentView(binding.root)
        onRendered(viewModel,binding)
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    abstract fun getViewBinding(): VB


    open fun onRendered(viewModel: VM, binding: VB) {}

    open fun <T> wsWithLoader(dataResource: BaseDataSource.Resource<T>, block: () -> Unit) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                loader.dismiss()
                block.invoke()
            }
            BaseDataSource.Resource.Status.LOADING -> {
                if (!loader.isShowing) {
                    loader.show()
                }
            }
            BaseDataSource.Resource.Status.ERROR -> {
                binding.root.snack(dataResource.message ?: getString(R.string.common_server_error)) {}
                loader.dismiss()
            }
        }
    }

    open fun <T> ws(dataResource: BaseDataSource.Resource<T>, block: () -> Unit) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                block.invoke()
            }
            BaseDataSource.Resource.Status.LOADING -> {

            }
            BaseDataSource.Resource.Status.ERROR -> {
                binding.root.snack(dataResource.message ?: getString(R.string.common_server_error)) {}
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onPause() {
        super.onPause()

    }




}