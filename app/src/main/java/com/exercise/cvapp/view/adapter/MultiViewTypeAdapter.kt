package com.exercise.cvapp.view.adapter

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.URLSpan
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exercise.cvapp.R.drawable
import com.exercise.cvapp.databinding.*
import com.exercise.cvapp.databinding.ContactCardBinding.*
import com.exercise.cvapp.models.Profile


class MultiViewTypeAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var profile: Profile? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        const val TOP_SECTION: Int = 0
        const val SUMMARY: Int = 1
        const val CONTACT: Int = 2
        const val EXPERIENCE: Int = 3
        const val SKILLS: Int = 4
        const val EDUCATION: Int = 5
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopSectionViewHolder -> profile?.let { holder.bind(it) }
            is SummaryViewHolder -> profile?.let { holder.bind(it) }
            is ContactViewHolder -> profile?.let { holder.bind(it) }
            is ExperienceViewHolder -> profile?.let { holder.bind(it) }
            is SkillsViewHolder -> profile?.let { holder.bind(it) }
            is EducationViewHolder -> profile?.let { holder.bind(it) }
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TOP_SECTION
            1 -> SUMMARY
            2 -> CONTACT
            3 -> EXPERIENCE
            4 -> SKILLS
            5 -> EDUCATION
            else -> -1

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TOP_SECTION -> TopSectionViewHolder(
                TopSectionCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            SUMMARY -> SummaryViewHolder(
                ProfileSummaryCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            CONTACT -> ContactViewHolder(
                inflate(LayoutInflater.from(parent.context), parent, false)
            )
            EXPERIENCE -> ExperienceViewHolder(
                ExperienceCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            SKILLS -> SkillsViewHolder(
                SkillsSectionCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> EducationViewHolder(
                EducationCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }


    /**
     * The TopSectionViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Profile] information.
     */
    class TopSectionViewHolder(private var binding: TopSectionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(profile: Profile) {
            binding.name.text = profile.name

            Glide.with(binding.root)
                .load(profile.profile_image)
                .apply(
                    RequestOptions()
                        .placeholder(drawable.loading_animation)
                        .error(drawable.ic_broken_image)
                )
                .into(binding.profileImage)

            binding.currentCompanyRole.text =
                profile.experienceList[0].role_name + " at " + profile.experienceList[0].company_name
            binding.currentLocation.text = profile.location
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * The SummaryViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Profile] information.
     */
    class SummaryViewHolder(private var binding: ProfileSummaryCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.summary.text = profile.summary
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * The ExperienceViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Profile] information.
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    class ExperienceViewHolder(private var binding: ExperienceCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            val context = binding.root.context
            binding.experienceList.adapter = ExperienceListAdapter(profile.experienceList)

            val itemDecor = DividerItemDecoration(context, HORIZONTAL)
            itemDecor.setOrientation(VERTICAL)
            AppCompatResources.getDrawable(context, drawable.divider)?.let { itemDecor.setDrawable(it) }
            binding.experienceList.addItemDecoration(itemDecor)

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * The EducationViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Profile] information.
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    class EducationViewHolder(private var binding: EducationCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            val context = binding.root.context
            binding.educationList.adapter = EducationListAdapter(profile.educationList)

            // Add list view divider
            val itemDecor = DividerItemDecoration(context, HORIZONTAL)
            itemDecor.setOrientation(VERTICAL)
            AppCompatResources.getDrawable(context, drawable.divider)
                ?.let { itemDecor.setDrawable(it) }
            binding.educationList.addItemDecoration(itemDecor)
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * The SkillsViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Profile] information.
     */
    class SkillsViewHolder(private var binding: SkillsSectionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.environment.text = profile.skills?.environment
            binding.methodology.text = profile.skills?.methodologies
            binding.technologies.text = profile.skills?.technologies
            binding.languages.text = profile.skills?.programming_languages
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * The ContactViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Profile] information.
     */
    class ContactViewHolder(private var binding: ContactCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            val profileLink: TextView = binding.profile
            val emailLink: TextView = binding.email
            val phoneLink: TextView = binding.phone

            profileLink.text = profile.contact?.profile_link
            Linkify.addLinks(profileLink, Linkify.WEB_URLS)
            stripUnderlines(profileLink)

            emailLink.text = profile.contact?.email
            Linkify.addLinks(emailLink, Linkify.EMAIL_ADDRESSES)
            stripUnderlines(emailLink)

            phoneLink.text = profile.contact?.phone
            Linkify.addLinks(phoneLink, Linkify.PHONE_NUMBERS)
            stripUnderlines(phoneLink)
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        private fun stripUnderlines(textView: TextView) {
            val s = SpannableString(textView.text)
            val spans = s.getSpans(0, s.length, URLSpan::class.java)
            for (span in spans) {
                val start = s.getSpanStart(span)
                val end = s.getSpanEnd(span)
                s.removeSpan(span)
                s.setSpan(URLSpanNoUnderline(span.url), start, end, 0)
            }
            textView.text = s
        }

        private inner class URLSpanNoUnderline(url: String) : URLSpan(url) {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
    }
}
