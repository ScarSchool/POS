package at.scarc.androidApp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import at.scarc.androidApp.R
import at.scarc.androidApp.controllers.UserController
import at.scarc.androidApp.models.User
import at.scarc.androidApp.models.UserTypes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserCreateFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_create, container, false)
    }

    override fun onViewCreated(mainV: View, savedInstanceState: Bundle?) {
        super.onViewCreated(mainV, savedInstanceState)

        var scrollView = view?.findViewById<ScrollView>(R.id.scrollViewUserCreate)
        scrollView?.findViewById<EditText>(R.id.plainTextCreateUserId)?.setText(System.currentTimeMillis().toString())
        mainV?.findViewById<Button>(R.id.btnCreateUser)?.setOnClickListener {
            createUser(mainV);
        }


        val adapter = activity?.let {
            ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    UserTypes.values().toList()
            )
        }
        scrollView?.findViewById<Spinner>(R.id.spinnerCreateUserTypes)?.adapter = adapter
    }

    private fun createUser(view: View?) {
        var scrollView = view?.findViewById<ScrollView>(R.id.scrollViewUserCreate)
        var id = scrollView?.findViewById<EditText>(R.id.plainTextCreateUserId)?.text.toString().toLong()
        var userType = scrollView?.findViewById<Spinner>(R.id.spinnerCreateUserTypes)?.selectedItem.toString()
        var name = scrollView?.findViewById<EditText>(R.id.plainTextCreateUserName)?.text.toString()
        var email = scrollView?.findViewById<EditText>(R.id.plainTextCreateUserEmail)?.text.toString()
        var pwdToken = scrollView?.findViewById<EditText>(R.id.plainTextCreateUserPwdToken)?.text.toString()
        GlobalScope.launch {
            UserController().save(User(id, UserTypes.valueOf(userType), name, email, pwdToken),
                    {
                        findNavController().navigate(R.id.action_nav_user_create_to_nav_user)
                    },
                    { _, t ->
                        errorMessage(view, t)
                    })
        }
    }

    private fun errorMessage(view: View?, t: Throwable) {
        println(t.message)
    }
}