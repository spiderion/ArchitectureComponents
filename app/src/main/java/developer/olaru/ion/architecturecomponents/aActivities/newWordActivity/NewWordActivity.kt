package developer.olaru.ion.architecturecomponents.aActivities.newWordActivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import developer.olaru.ion.architecturecomponents.R
import developer.olaru.ion.architecturecomponents.utils.Constants.Companion.EXTRA_REPLY_REQUEST
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        saveBtn.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(editWordTextView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)

            } else {

                val word = editWordTextView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_REQUEST, word)
                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }

}