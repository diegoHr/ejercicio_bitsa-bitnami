package com.diegohr.ejercicio_bitsa_bitnovo

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.diegohr.ejercicio_bitsa_bitnovo.databinding.ActivityMainBinding
import com.diegohr.ejercicio_bitsa_bitnovo.view.CastleStatusFragment
import com.diegohr.ejercicio_bitsa_bitnovo.view.GameStatusFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel by viewModels<CastleGameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setSupportActionBar(binding.toolbar)


        viewModel.errorMessage.observe(this, {
            if(it != null) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_action), {
                        viewModel.initGame()
                    }).show()
            }
        })

        viewModel.isGameFinished.observe(this, {
            if(it){
                //navController.navigate(R.id.action_LoadingFragment_to_SecondFragment)
                binding.watingView.root.visibility = View.GONE
            }else{
                binding.watingView.root.visibility = View.VISIBLE
            }
        })

        viewModel.initGame()

        binding.contentMain.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.contentMain.tablayoutNavButtonsView, binding.contentMain.viewPager) { tab, position ->
            tab.setText(getStringResIdTitleTab(position))
        }.attach()

        binding.toolbar.setTitle(R.string.app_name)

        binding.floatButtonViewRegame.setOnClickListener {
            viewModel.initGame()
        }

        binding.floatButtonViewReset.setOnClickListener {
            viewModel.reset()
        }

    }

    private fun getStringResIdTitleTab (position : Int) :  Int{
        return when(position){
            0 -> R.string.castle_status
            else -> R.string.game_status
        }
    }


    class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> CastleStatusFragment()
                else -> GameStatusFragment()
            }
        }
    }

}