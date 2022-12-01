package com.example.realmdatabase.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realmdatabase.MainActivity
import com.example.realmdatabase.Presenter
import com.example.realmdatabase.data.model.Contact
import com.example.realmdatabase.databinding.ActivityAddContactBinding
import com.example.realmdatabase.presenter.MainAction
import org.koin.android.ext.android.inject

class AddContactActivity : AppCompatActivity(), MainAction {

    private lateinit var binding: ActivityAddContactBinding

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.initAction(this)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etName.requestFocus()
        binding.btnSave.setOnClickListener {
            with(binding) {
                presenter.addContact(
                    name = etName.text.toString(),
                    surname = etSurname.text.toString(),
                    number = etNumber.text.toString()
                )

                startActivity(Intent(this@AddContactActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onAddContact(contacts: List<Contact>) {
        Toast.makeText(this, contacts.last().name, Toast.LENGTH_SHORT).show()
    }
}