package developer.olaru.ion.architecturecomponents.aActivities.mainActivity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import developer.olaru.ion.architecturecomponents.aActivities.newWordActivity.NewWordActivity
import developer.olaru.ion.architecturecomponents.bViewModel.WordViewModel
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import developer.olaru.ion.architecturecomponents.R
import developer.olaru.ion.architecturecomponents.utils.Constants.Companion.EXTRA_REPLY_REQUEST
import developer.olaru.ion.architecturecomponents.utils.Constants.Companion.NEW_WORD_ACTIVITY_REQUEST_CODE
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), Clickable {
    private var mWordViewModel: WordViewModel? = null

    private lateinit var clickable: Clickable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //get view model provider
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        clickable = this
        val adapter = WordListAdapter(clickable)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        mWordViewModel?.getAllWords()?.observe(this, Observer { words -> adapter.setWords(words) })

        val fab: FloatingActionButton = findViewById<View>(R.id.fab) as FloatingActionButton

        fab.setOnClickListener {

            startActivityForResult<NewWordActivity>(NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val word = Word(data!!.getStringExtra(EXTRA_REPLY_REQUEST))
            mWordViewModel?.insert(word)

        } else {

            longToast(R.string.empty_not_saved)
        }
    }

    override fun deleteWord(word: Word?) {

        alert("are you sure you want to delete this?", "Delete word") {
            yesButton { mWordViewModel?.deleteWord(word!!) }
            noButton { Log.i("noButton", "not deleted ") }
        }.show()

    }
}
