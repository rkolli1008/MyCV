package com.exercise.cvapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exercise.cvapp.R
import com.exercise.cvapp.databinding.EducationListItemBinding
import com.exercise.cvapp.models.Education

class EducationListAdapter(private val dataSource: List<Education>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return EducationViewHolder(
            EducationListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is EducationViewHolder) {
            holder.bind(dataSource[position])
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class EducationViewHolder(private var binding: EducationListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(education: Education) {
            Glide.with(binding.root)
                .load(education.logo_url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(binding.logoUrl)
            binding.universityName.text = education.university_name
            binding.courseTitle.text = education.course_title
            binding.period.text = education.period
        }
    }
}