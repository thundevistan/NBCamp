package bootcamp.sparta.disneym.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.databinding.ActivityMainBinding
import bootcamp.sparta.disneym.repository.MainRepository

class MainActivity : AppCompatActivity() {


    companion object {
        fun newIntent(context: Context) : Intent = Intent(context, MainActivity::class.java)
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        initView()
    }

    private fun initView()=with(binding) {


    }

}