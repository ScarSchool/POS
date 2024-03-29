package at.scarc.androidApp.ui.department

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import at.scarc.androidApp.R
import at.scarc.androidApp.controllers.DepartmentController
import at.scarc.androidApp.models.Department
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DepartmentCreateFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_department_create, container, false)
    }

    override fun onViewCreated(mainV: View, savedInstanceState: Bundle?) {
        super.onViewCreated(mainV, savedInstanceState)

        var scrollView = view?.findViewById<ScrollView>(R.id.scrollViewDepartmentCreate)
        scrollView?.findViewById<EditText>(R.id.plainTextCreateDepartmentId)?.setText(System.currentTimeMillis().toString())
        mainV?.findViewById<Button>(R.id.btnCreateDepartment)?.setOnClickListener {
            createDepartment(mainV);
        }
    }

    private fun createDepartment(view: View?) {
        var scrollView = view?.findViewById<ScrollView>(R.id.scrollViewDepartmentCreate)
        var id = scrollView?.findViewById<EditText>(R.id.plainTextCreateDepartmentId)?.text.toString().toLong()
        var label = scrollView?.findViewById<EditText>(R.id.plainTextCreateDepartmentLabel)?.text.toString()
        GlobalScope.launch {
            DepartmentController().save(Department(id, label),
                    {
                        findNavController().navigate(R.id.action_nav_department_create_to_nav_department)
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