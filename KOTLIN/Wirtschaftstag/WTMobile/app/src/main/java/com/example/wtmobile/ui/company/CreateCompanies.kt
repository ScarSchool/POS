package com.example.wtmobile.ui.company
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wtmobile.R
import com.example.wtmobile.data.API
import com.example.wtmobile.data.models.Company
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCompanies : AppCompatActivity()  {

    lateinit var etComments : EditText
    lateinit var etName : EditText
    lateinit var etZipTown : EditText
    lateinit var etStreet : EditText
    lateinit var etPhone : EditText
    lateinit var etReplyTo : EditText
    lateinit var etEmail : EditText
    lateinit var btnAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_create)
    }

    override fun onStart() {
        super.onStart()
        etComments = findViewById(R.id.etComments)
        etName = findViewById(R.id.etName)
        etZipTown = findViewById(R.id.etZipTown)
        etStreet = findViewById(R.id.etAddress)
        etPhone = findViewById(R.id.etPhone)
        etReplyTo = findViewById(R.id.etReply)
        etEmail = findViewById(R.id.etEmail)
        btnAdd = findViewById(R.id.button_add_event)

        btnAdd.setOnClickListener {
            var company : Company = Company(
                -1,
                etName.text.toString(),
                etZipTown.text.toString(),
                etStreet.text.toString(),
                etPhone.text.toString(),
                etReplyTo.text.toString(),
                etComments.text.toString(),
                etEmail.text.toString(),
            )

            GlobalScope.launch {
                try {
                    var result : Company = API.getAPI().createCompany(company)

                    Snackbar.make(etComments.rootView, "Successfully created a new company with id " + result.id, Snackbar.LENGTH_LONG)
                        .show()
                } catch(ex : Exception) {
                    Snackbar.make(etComments.rootView, "Could not create new company: " + ex.message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}