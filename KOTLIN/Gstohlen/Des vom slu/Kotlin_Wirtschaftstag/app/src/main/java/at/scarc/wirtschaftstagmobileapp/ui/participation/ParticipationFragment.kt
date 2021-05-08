package at.scarc.androidApp.ui.participation

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import at.scarc.androidApp.R
import at.scarc.androidApp.controllers.CompanyController
import at.scarc.androidApp.controllers.EventController
import at.scarc.androidApp.controllers.ParticipationController
import at.scarc.androidApp.controllers.UserController
import at.scarc.androidApp.models.*
import at.scarc.androidApp.ui.participation.ParticipationFragment.OnSpinnerParticipationEventsSelected.toggleScrollViewParticipation
import at.scarc.androidApp.ui.participation.ParticipationFragment.OnSpinnerParticipationEventsSelected.toggleTxtViewNoParticipationSelected
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ParticipationFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_participation, container, false)
    }

    override fun onViewCreated(mainV: View, savedInstanceState: Bundle?) {
        super.onViewCreated(mainV, savedInstanceState)

        mainV?.findViewById<FloatingActionButton>(R.id.fab_createParticipation)?.setOnClickListener { _ -> createParticipation(mainV) }
        mainV?.findViewById<FloatingActionButton>(R.id.fab_refreshParticipations)?.setOnClickListener { _ -> refreshParticipations(mainV) }
        mainV?.findViewById<Button>(R.id.btnSaveParticipationSelection)?.setOnClickListener {
            saveParticipationSelection(mainV);
        }
        mainV?.findViewById<Button>(R.id.btnSelectAllParticipationCompanies)?.setOnClickListener {
            selectAllParticipationCompanies(mainV);
        }
        mainV?.findViewById<Button>(R.id.btnUnSelectAllParticipationCompanies)?.setOnClickListener {
            unSelectAllParticipationCompanies(mainV);
        }

        toggleScrollViewParticipation(mainV, View.INVISIBLE)
        toggleTxtViewNoParticipationSelected(mainV, View.VISIBLE)

        val listViewParticipationCompanies = mainV?.findViewById<ListView>(R.id.listViewParticipationCompanies)
        listViewParticipationCompanies.choiceMode = ListView.CHOICE_MODE_MULTIPLE;

        if (listViewParticipationCompanies != null)
            GlobalScope.launch {
                suspend {
                    CompanyController().getAll(
                            { companies ->
                                try {
                                    if (companies != null) {
                                        val adapter = activity?.let {
                                            ArrayAdapter<Company>(
                                                    it,
                                                    android.R.layout.simple_list_item_multiple_choice,
                                                    companies
                                            )
                                        }
                                        listViewParticipationCompanies.adapter = adapter
                                    } else {
                                        val adapter = activity?.let {
                                            ArrayAdapter<String>(
                                                    it,
                                                    android.R.layout.simple_list_item_multiple_choice,
                                                    listOf("no companies available")
                                            )
                                        }
                                        listViewParticipationCompanies.adapter = adapter
                                    }
                                } catch (ex: Throwable) {
                                    errorMessage(view, ex)
                                }
                            },
                            { _, t ->
                                errorMessage(view, t)
                            })
                }.invoke()
            }

        mainV.findViewById<Spinner>(R.id.spinnerParticipationEvents)?.onItemSelectedListener = OnSpinnerParticipationEventsSelected
        listViewParticipationCompanies?.onItemLongClickListener = OnItemLongClickListener {
            parentView: AdapterView<*>?,
            selectedItemView: View,
            position: Int,
            id: Long ->

            findNavController().navigate(R.id.action_nav_participation_to_nav_participation_create)

            true
        }

        refreshParticipations(mainV)
    }

    private fun refreshParticipations(view: View?) {
        val spinnerParticipationEvents = view?.findViewById<Spinner>(R.id.spinnerParticipationEvents)
        if (spinnerParticipationEvents != null) {
            GlobalScope.launch {
                EventController().getAll(
                        { events ->
                            try {
                                if (events != null) {
                                    val adapter = activity?.let {
                                        ArrayAdapter<Event>(
                                                it,
                                                android.R.layout.simple_spinner_item,
                                                events
                                        )
                                    }
                                    spinnerParticipationEvents.adapter = adapter
                                } else {
                                    val adapter = activity?.let {
                                        ArrayAdapter<String>(
                                                it,
                                                android.R.layout.simple_spinner_item,
                                                listOf("no evnets available")
                                        )
                                    }
                                    spinnerParticipationEvents.adapter = adapter
                                }
                            } catch (ex: Throwable) {
                                errorMessage(view, ex)
                            }
                        },
                        { _, t ->
                            errorMessage(view, t)
                        })
            }
        }
    }

    object OnSpinnerParticipationEventsSelected : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
        ) {
            toggleScrollViewParticipation((parentView?.parent as View), View.VISIBLE)
            toggleTxtViewNoParticipationSelected((parentView?.parent as View), View.INVISIBLE)
            fillParticipationEditFields(parentView?.parent as View, parentView?.getItemAtPosition(position) as Event)
        }

        override fun onNothingSelected(parentView: AdapterView<*>?) {
            toggleScrollViewParticipation((parentView?.parent as View), View.INVISIBLE)
            toggleTxtViewNoParticipationSelected((parentView?.parent as View), View.VISIBLE)
        }

        private fun fillParticipationEditFields(view: View, event: Event) {
            val scrollView = view.findViewById<ScrollView>(R.id.scrollViewParticipationEdit)
            val listViewParticipationCompanies = scrollView?.findViewById<ListView>(R.id.listViewParticipationCompanies)
            if (scrollView != null && listViewParticipationCompanies != null) {
                GlobalScope.launch {
                    ParticipationController().getAllByEvent(event.id!!,
                            { participations ->
                                try {
                                    if (participations != null) {
                                        val companies = participations.map { it.company }
                                        for (i in 0 until listViewParticipationCompanies.adapter.count) {
                                            if (companies.contains((listViewParticipationCompanies.getItemAtPosition(i) as Company))) {
                                                listViewParticipationCompanies.setItemChecked(i, true)
                                            } else {
                                                listViewParticipationCompanies.setItemChecked(i, false)
                                            }
                                        }
                                    }
                                } catch (ex: Throwable) {
                                    ParticipationFragment().errorMessage(view, ex)
                                }
                            },
                            { _, t ->
                                ParticipationFragment().errorMessage(view, t)
                            })
                }
            }
        }

        fun toggleTxtViewNoParticipationSelected(view: View?, visible: Int) {
            view?.findViewById<TextView>(R.id.txtViewNoParticipationSelected)?.visibility = visible
        }

        fun toggleScrollViewParticipation(view: View?, visible: Int) {
            view?.findViewById<ScrollView>(R.id.scrollViewParticipationEdit)?.visibility = visible
        }
    }

    private fun saveParticipationSelection(view: View?) {
        val listViewParticipationCompanies = view?.findViewById<ListView>(R.id.listViewParticipationCompanies)
        val newSelected : SparseBooleanArray? = listViewParticipationCompanies?.checkedItemPositions
        var currentEvent : Event? = getCurrentSelectedEvent(view);
        if (currentEvent != null && newSelected != null) {
            GlobalScope.launch {
                currentEvent.id?.let {
                    ParticipationController().getAllByEvent(it,
                            { participations ->
                                try {
                                    if (participations != null) {
                                        val currentCompanies = participations.map { participation -> participation.company }
                                        UserController().getAllByUserType(UserTypes.responsible,
                                                { users ->
                                                    try {
                                                        if (users != null) {
                                                            var responsibles : List<User> = users
                                                            var selectedCompanies : MutableList<Company> = mutableListOf()
                                                            for (i in 0 until newSelected.size()) {
                                                                if (newSelected.get(i)) {
                                                                    val selectedCompany = listViewParticipationCompanies?.getItemAtPosition(newSelected.keyAt(i)) as Company
                                                                    selectedCompanies.add(selectedCompany)
                                                                    if (!currentCompanies.contains(selectedCompany)) {
                                                                        ParticipationController().save(Participation(System.currentTimeMillis(), 100.0, 0.0, "", currentEvent, selectedCompany, responsibles.random(), null, null),
                                                                                {

                                                                                },
                                                                                { _, t ->
                                                                                    ParticipationFragment().errorMessage(view, t)
                                                                                })
                                                                    }
                                                                }
                                                            }
                                                            var notSelectedCompanies = currentCompanies.toMutableList()
                                                            notSelectedCompanies.removeAll(selectedCompanies)
                                                            notSelectedCompanies.toList()
                                                            for (part in participations) {
                                                                if (notSelectedCompanies.contains(part.company)) {
                                                                    ParticipationController().delete(part.id!!,
                                                                            {

                                                                            },
                                                                            { _, t ->
                                                                                ParticipationFragment().errorMessage(view, t)
                                                                            })
                                                                }
                                                            }
                                                            refreshParticipations(view)
                                                        }
                                                    } catch (ex: Throwable) {
                                                        errorMessage(view, ex)
                                                    }
                                                },
                                                { _, t ->
                                                    ParticipationFragment().errorMessage(view, t)
                                                })
                                    }
                                } catch (ex: Throwable) {
                                    ParticipationFragment().errorMessage(view, ex)
                                }
                            },
                            { _, t ->
                                ParticipationFragment().errorMessage(view, t)
                            })
                }
            }
        }
    }

    private fun selectAllParticipationCompanies(view: View?) {
        val listViewParticipationCompanies = view?.findViewById<ListView>(R.id.listViewParticipationCompanies)
        for (i in 0 until listViewParticipationCompanies?.adapter?.count!!) {
                listViewParticipationCompanies?.setItemChecked(i, true)
        }
    }

    private fun unSelectAllParticipationCompanies(view: View?) {
        val listViewParticipationCompanies = view?.findViewById<ListView>(R.id.listViewParticipationCompanies)
        for (i in 0 until listViewParticipationCompanies?.adapter?.count!!) {
            listViewParticipationCompanies?.setItemChecked(i, false)
        }
    }

    private fun getCurrentSelectedEvent(view: View?): Event? {
        return view?.findViewById<Spinner>(R.id.spinnerParticipationEvents)?.selectedItem as Event?
    }

    private fun createParticipation(view: View) {
        findNavController().navigate(R.id.action_nav_participation_to_nav_participation_create)
    }

    private fun errorMessage(view: View?, t: Throwable) {
        println(t.message)
    }
}