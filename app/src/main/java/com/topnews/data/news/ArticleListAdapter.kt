package com.topnews.data.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.topnews.R
import javax.inject.Inject

/**
 * Adapter for [News.Article] object
 */
class ArticleListAdapter constructor(
    private val context: Context,
    private val articleClickCallback: ((News.Article) -> Unit)
) :
    ListAdapter<News.Article, ArticleListAdapter.ArticleViewHolder>(object :
        DiffUtil.ItemCallback<News.Article>() {


        override fun areItemsTheSame(oldItem: News.Article, newItem: News.Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News.Article, newItem: News.Article): Boolean {
            return oldItem == newItem
        }

    }) {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val source: TextView = itemView.findViewById(R.id.source)
        val image: ImageView = itemView.findViewById(R.id.image)
        val card: View = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.title.text = article.title?.let { NewsTitleFormatter.formatTitle(it) }
        holder.description.text = article.description
        holder.source.text = article.source?.name
        holder.card.setOnClickListener { articleClickCallback.invoke(article) }
        Glide.with(context)
            .load(article.urlToImage)
            .apply(RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE))
            .into(holder.image)
    }
}
