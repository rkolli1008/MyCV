package com.exercise.cvapp.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exercise.cvapp.R
import com.exercise.cvapp.databinding.ExperienceListItemBinding
import com.exercise.cvapp.models.Experience

class ExperienceListAdapter(private val dataSource: List<Experience>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExperienceViewHolder(ExperienceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ExperienceViewHolder) {
            holder.bind(dataSource[position])
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ExperienceViewHolder(private var binding: ExperienceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(experience: Experience) {
            Glide.with(binding.root)
                .load(experience.logo_url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(binding.logoUrl)
            binding.companyName.text = experience.company_name
            binding.role.text = experience.role_name
            binding.duration.text = experience.date_from_to
        }
    }
}