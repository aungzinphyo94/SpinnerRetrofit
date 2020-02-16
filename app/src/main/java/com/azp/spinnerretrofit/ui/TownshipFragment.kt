package com.azp.spinnerretrofit.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.azp.spinnerretrofit.R
import com.azp.spinnerretrofit.model.Township
import com.azp.spinnerretrofit.model.TownshipX
import com.azp.spinnerretrofit.viewmodel.TownshipViewmodel
import kotlinx.android.synthetic.main.fragment_township.*
import kotlinx.android.synthetic.main.fragment_township.view.*

class TownshipFragment : Fragment() {

    private var townshipArray: List<TownshipX> = ArrayList()

    lateinit var townshipViewmodel: TownshipViewmodel

    lateinit var townshipSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_township, container, false)

        townshipSpinner = view.findViewById(R.id.townshipSpinner)

//        Toast.makeText(context, townshipSpinner?.selectedItem?.toString(),Toast.LENGTH_LONG).show()

        view.btnChoose.setOnClickListener {
            Toast.makeText(context, townshipSpinner?.selectedItem?.toString(),Toast.LENGTH_LONG).show()

        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

    }

    fun observeViewModel(){
        townshipViewmodel = ViewModelProvider(this).get(TownshipViewmodel::class.java)
        townshipViewmodel.loadTownship()
        townshipViewmodel.getTownship().observe(
            this, Observer { result ->
                townshipArray = result.townships

                var data: MutableList<String> = ArrayList()

                townshipArray.forEach{
                    data.add(0, it.township_name)
                }
                townshipSpinner.adapter = context?.let { ArrayAdapter<String>(it,R.layout.support_simple_spinner_dropdown_item, data) }
            }
        )
    }

}