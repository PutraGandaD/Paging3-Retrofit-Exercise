package com.putragandad.pagingretrofit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.pagingretrofit.domain.model.Repo
import com.putragandad.pagingretrofit.presentation.databinding.ListViewholderBinding

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ListViewholderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.apply {
                binding.tvRepoName.text = repo.username
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ListViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.username == newItem.username

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }
}