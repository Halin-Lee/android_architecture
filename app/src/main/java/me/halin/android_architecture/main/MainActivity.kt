package me.halin.android_architecture.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import me.halin.android_architecture.R
import me.halin.android_architecture.databinding.ActivityMainBinding
import me.halin.android_architecture.main.model.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = this.viewModel

        // 构造Adapter并初始化binding
        val adapter = MainAdapter(this.layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.versionModel.observe(this, adapter)
        this.viewModel.openOtherEvent.observe(this, Observer { _ ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://www.baidu.com")
            this.startActivity(intent)
        })
    }
}
