package ai.fritz.camera

import ai.fritz.poseestimationdemo.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class LobbyActivity : AppCompatActivity() {

    lateinit var mStart : Button
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        mAuth = FirebaseAuth.getInstance()

        mStart = findViewById(R.id.lobby_start_btn)

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
           mStart.setOnClickListener {
                val newIntent = Intent(applicationContext, MainActivity::class.java)

                startActivity(newIntent)
                finish()
            }
        }
    }
}
