package ai.fritz.camera

import ai.fritz.poseestimationdemo.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class NewMatchActivity : AppCompatActivity() {

    lateinit var mCreateLobby : Button
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)

        mAuth = FirebaseAuth.getInstance()

        mCreateLobby = findViewById(R.id.newmatch_btn)


    }

    override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser == null){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        else{
            //remain in activity
            mCreateLobby.setOnClickListener {
                val newIntent = Intent(applicationContext, LobbyActivity::class.java)

                startActivity(newIntent)
                finish()
            }

        }
    }
}

