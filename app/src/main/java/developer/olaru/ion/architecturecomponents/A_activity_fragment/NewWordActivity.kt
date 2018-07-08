package developer.olaru.ion.architecturecomponents.A_activity_fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText

import developer.olaru.ion.architecturecomponents.R

class NewWordActivity : AppCompatActivity() {

    private var mEditWordView: EditText? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        mEditWordView = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditWordView!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = mEditWordView!!.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {

        val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}