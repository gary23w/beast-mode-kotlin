package com.gary.beastmode.activitys.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gary.beastmode.R
import com.gary.beastmode.data.loadImage
import kotlinx.android.synthetic.main.item_exercise.view.*


class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.TopicItemViewHolder>() {

    val exerciseItems = arrayListOf<Exercises>()
    fun onAddNewsItem(item: Exercises?) {
        exerciseItems.add(
            Exercises(
                "Wall sit.",
                "https://the-latest.news/wp-content/uploads/2020/07/wall_sit.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Tricep dip.",
                "https://the-latest.news/wp-content/uploads/2020/07/tricep_dip.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Squat.",
                "https://the-latest.news/wp-content/uploads/2020/07/squat.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Side plank.",
                "https://the-latest.news/wp-content/uploads/2020/07/side_plank.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Run in place.",
                "https://the-latest.news/wp-content/uploads/2020/07/run_in_place.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Push up. Rotate.",
                "https://the-latest.news/wp-content/uploads/2020/07/push_up_rotate.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Push up.",
                "https://the-latest.news/wp-content/uploads/2020/07/push_up.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Plank",
                "https://the-latest.news/wp-content/uploads/2020/07/plank.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Lunge",
                "https://the-latest.news/wp-content/uploads/2020/07/lunge.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Jumping jacks",
                "https://the-latest.news/wp-content/uploads/2020/07/jumping_jacks.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Ab crunch",
                "https://the-latest.news/wp-content/uploads/2020/07/ab_crunch.png",
                "1"
            )
        )
        exerciseItems.add(
            Exercises(
                "Step up. Step down.",
                "https://the-latest.news/wp-content/uploads/2020/07/step_uptwo.png",
                "1"
            )
        )
        notifyItemInserted(0)
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        TopicItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        )
    override fun getItemCount() = exerciseItems.size
    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        holder.bind(exerciseItems[position])
    }
    class TopicItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.title
        private val image = view.picture
        fun bind(exerciseItems: Exercises) {
            title.text = exerciseItems.name
            image.loadImage(exerciseItems.imagelink)
            itemView.setOnClickListener {
//                val context= itemView.context
//                val intent = Intent( context, WebView::class.java)
//                intent.putExtra("urlSearch", topicItem.name)
//                context.startActivity(intent)
            }
        }
    }

}

