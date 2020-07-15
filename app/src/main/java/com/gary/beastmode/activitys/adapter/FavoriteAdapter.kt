package com.gary.beastmode.activitys.adapter


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gary.beastmode.R
import com.gary.beastmode.activitys.ExcerciseActivity
import com.gary.beastmode.data.constant
import com.gary.news.model.Favorite
import java.text.SimpleDateFormat


class FavoriteAdapter(favorite: ArrayList<Favorite>, listener: OnItemClickListener) : RecyclerView.Adapter<FavoriteAdapter.FavItemViewHolder>() {

        private var favList: List<Favorite> = favorite

        private var listenerContact: OnItemClickListener = listener

        interface OnItemClickListener {
            fun onItemClick(favorite: Favorite)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavItemViewHolder {
            return FavItemViewHolder(
                LayoutInflater.from(parent!!.context).inflate(R.layout.item_fav, parent, false)
            )
        }
        override fun getItemCount(): Int {
            return favList.size
        }

        override fun onBindViewHolder(holder: FavItemViewHolder, position: Int) {
            var currentFav: Favorite = favList[position]
            holder.date.text = getDate(currentFav)
            holder.workout.text = currentFav.workoutCLASS
            holder.rest.text = currentFav.restCLASS
            holder.intervals.text = currentFav.intervalCLASS
            holder.bind(currentFav, listenerContact)
        }
        fun addNotes(listFav: List<Favorite>) {
            this.favList = listFav
            notifyDataSetChanged()
        }
        private fun getDate(currentDate: Favorite): String {
            var date: Long = currentDate.date
            return SimpleDateFormat("yyyy-MM-dd").format(date)
        }
        class FavItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var workout = itemView.findViewById<TextView>(R.id.workout)!!
            var date = itemView.findViewById<TextView>(R.id.newsFavDate)!!
            var rest = itemView.findViewById<TextView>(R.id.rest)!!
            var intervals = itemView.findViewById<TextView>(R.id.interval)!!
            fun bind(favorite: Favorite, listener: OnItemClickListener) {
                itemView.setOnClickListener {
                    constant.WORKOUTTIME = workout.text.toString()
                    constant.RESTTIME = rest.text.toString()
                    constant.INTERVALS = intervals.text.toString()
                    val context= itemView.context
                    val intent = Intent( context, ExcerciseActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }

        }

    }