package ai.fritz.camera

import ai.fritz.poseestimationdemo.R
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password






class LoginActivity : AppCompatActivity() {

    lateinit var mLoginButton: Button
    lateinit var mCreateUser : TextView
    lateinit var mForgotPass : TextView
    lateinit var mLoginEmail : EditText
    //lateinit var mLoginUser : EditText
    lateinit var mLoginPass : EditText
    lateinit var mProgressbar : ProgressDialog
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance();

        mLoginButton = findViewById(R.id.login_loginbtn)
        mCreateUser = findViewById(R.id.login_createnew)
        mForgotPass = findViewById(R.id.login_forgotpassword)
        mLoginEmail = findViewById(R.id.login_enterEmail)
        //mLoginUser = findViewById(R.id.login_enterName)
        mLoginPass = findViewById(R.id.login_enterPassword)
        mProgressbar = ProgressDialog(this)
        mCreateUser.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)


            startActivity(intent)
            finish()
        }

        mLoginButton.setOnClickListener {
            val email = mLoginEmail.text.toString().trim()
            val password = mLoginPass.text.toString().trim()

            /*if(TextUtils.isEmpty(email)){
                mLoginUser.error = "Enter Username"
                return@setOnClickListener
            }*/

            if(TextUtils.isEmpty(password)){
                mLoginPass.error = "Enter Password"
                return@setOnClickListener
            }

            loginUser(email, password)
        }

    }

    private fun loginUser(email: String, password: String) {

        mProgressbar.setMessage("Please wait...")
        mProgressbar.show()



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        mProgressbar.dismiss()

                        val startIntent = Intent(applicationContext, MainMenuActivity::class.java)


                        startActivity(startIntent)
                        finish()

                    } else {
                        Toast.makeText(this,"Authentication failed.", Toast.LENGTH_SHORT).show()

                    }

                    mProgressbar.dismiss()
                }
    }

}
