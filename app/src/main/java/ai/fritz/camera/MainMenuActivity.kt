package ai.fritz.camera

import ai.fritz.poseestimationdemo.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainMenuActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    lateinit var mNew : Button
    lateinit var mFind : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        mAuth = FirebaseAuth.getInstance()

        mNew = findViewById(R.id.mainmenu_newmatch_btn)
        mFind = findViewById(R.id.mainmenu_lobby_btn)


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
            mNew.setOnClickListener {
                val newIntent = Intent(applicationContext, NewMatchActivity::class.java)

                startActivity(newIntent)
                finish()
            }
            mFind.setOnClickListener {
                val findIntent = Intent(applicationContext, FindMatchActivity::class.java)

                startActivity(findIntent)
                finish()
            }
        }
    }
}
