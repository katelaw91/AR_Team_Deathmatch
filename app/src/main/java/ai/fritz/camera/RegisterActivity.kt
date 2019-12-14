package ai.fritz.camera

import ai.fritz.poseestimationdemo.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.app.ProgressDialog
import android.widget.*


class RegisterActivity : AppCompatActivity() {

    lateinit var mRegisterBtn: Button
    lateinit var mRegisterEmail: EditText
    lateinit var mRegisterName: EditText
    lateinit var mRegisterPass: EditText
    lateinit var mRegisterExisting: TextView
    lateinit var mProgressBar : ProgressDialog
    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ai.fritz.poseestimationdemo.R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        //mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        mRegisterBtn = findViewById(R.id.register_loginbtn)
        mRegisterEmail = findViewById(R.id.register_enterEmail)
        mRegisterName = findViewById(R.id.register_enterName)
        mRegisterPass = findViewById(R.id.register_enterPassword)
        mRegisterExisting = findViewById(R.id.register_existing)
        mProgressBar = ProgressDialog(this)

        mRegisterExisting.setOnClickListener {
            val returnIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(returnIntent)
            finish()
        }

        mRegisterBtn.setOnClickListener {
            //val intent = Intent(applicationContext, MainMenuActivity::class.java)

            //startActivity(intent)
            //finish()

            val name = mRegisterName.text.toString().trim()
            val email = mRegisterEmail.text.toString().trim()
            val password = mRegisterPass.text.toString().trim()

            if(TextUtils.isEmpty(name)){
                mRegisterName.error = "Enter Username"
                return@setOnClickListener
            }

             if(TextUtils.isEmpty(email)){
                 mRegisterEmail.error = "Enter Email"
                return@setOnClickListener
            }

             if(TextUtils.isEmpty(password)){
                 mRegisterPass.error = "Enter Password"
                return@setOnClickListener
             }

            createUser(name, email, password)
        }

    }

    private fun createUser(name: String, email: String, password: String) {

        mProgressBar.setMessage("Please wait...")
        mProgressBar.show()

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val currentUser = FirebaseAuth.getInstance().currentUser
                        val uid = currentUser!!.uid

                        val userMap = HashMap<String, String>()
                        userMap["name"] = name



                        mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(uid)

                        mDatabase.setValue(userMap).addOnCompleteListener( OnCompleteListener { task ->

                            if (task.isSuccessful) {
                                mProgressBar.dismiss()
                                val startIntent = Intent(applicationContext, MainMenuActivity::class.java)

                                startActivity(startIntent)
                                finish()


                            }
                        })
                    }

                    else {
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }
                    mProgressBar.dismiss()
                }
    }

}
