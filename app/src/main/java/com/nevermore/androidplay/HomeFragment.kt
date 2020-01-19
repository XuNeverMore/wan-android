package com.nevermore.androidplay


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.nevermore.androidplay.account.AccountViewModel
import com.nevermore.androidplay.ui.ArticleFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val userViewModel by activityViewModels<AccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.text = when (userViewModel.isLoginIn.value) {
            true -> "已登录"
            false -> "未登录"
            else -> ""
        }

        view_pager.adapter =
            PagerAdapter(this, listOf({ ArticleFragment() }, { ArticleFragment() }))
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        btn_login.setOnClickListener {
            userViewModel.isLoginIn.value?.apply {
                if (!this) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                    findNavController().navigate(action)

                }
            }
        }
    }

    class PagerAdapter(fragment: Fragment, private val fragmentCreators: List<() -> Fragment>) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return fragmentCreators.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentCreators[position].invoke()
        }


    }
}
