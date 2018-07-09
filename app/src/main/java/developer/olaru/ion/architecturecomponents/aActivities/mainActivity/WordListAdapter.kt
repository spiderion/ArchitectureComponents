package developer.olaru.ion.architecturecomponents.aActivities.mainActivity

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import developer.olaru.ion.architecturecomponents.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordListAdapter(val listener: Clickable) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mWords: List<Word>? = null

    internal fun setWords(words: List<Word>?) {

        mWords = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(v)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        holder.bind(mWords?.get(position),listener)
    }

    override fun getItemCount(): Int = mWords?.size ?: 0

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(word: Word? , listener: Clickable) {
            with(itemView) {
                wordTextView.text = word?.word ?: "No Words"

                parentLayout.setOnLongClickListener{listener.deleteWord(word)
                    Log.i("longClick","delete")
                false}
            }
        }
    }
}
