package com.nevermore.androidplay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nevermore.androidplay.R
import com.nevermore.androidplay.data.Article
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * 首页文章
 */
class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter: MyAdapter = MyAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.articleList.observe(this, Observer {
            it?.let { adapter.submitList(it) }
        })

        viewModel.getArticleList(0)

    }

    class MyAdapter(diffCallback: DiffUtil.ItemCallback<Article> = MyCallback()) :
        ListAdapter<Article, ViewHolder>(diffCallback) {

        class MyCallback :
            DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.apkLink == newItem.apkLink
            }

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_article, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            item?.apply {
                holder.tvTitle?.text = title
            }
            holder.itemView.setOnClickListener {
                item?.apply {
                    Navigation.findNavController(it)
                        .navigate(
                            R.id.webViewFragment, bundleOf("title" to title, "link" to link)
                        )
                }
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
        }

    }
}
