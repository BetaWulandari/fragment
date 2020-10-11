package com.bwrsks.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.fragment_option_dialog.*

class OptionDialogFragment : Fragment(), View.OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbBeta: RadioButton
    private lateinit var rbReza: RadioButton
    private lateinit var rbYosi: RadioButton
    private lateinit var rbAwan: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_options)
        rbBeta = view.findViewById(R.id.rb_beta)
        rbReza = view.findViewById(R.id.rb_reza)
        rbYosi = view.findViewById(R.id.rb_yosi)
        rbAwan = view.findViewById(R.id.rb_awan)

    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose -> {
                val checkedRadioButtonId = rg_options.checkedRadioButtonId
                if (checkedRadioButtonId != -1){
                    var coach: String? = null
                    when (checkedRadioButtonId){
                        R.id.rb_beta -> coach = rbBeta.text.toString().trim()
                        R.id.rb_reza -> coach = rbReza.text.toString().trim()
                        R.id.rb_yosi -> coach = rbYosi.text.toString().trim()
                        R.id.rb_awan -> coach = rbAwan.text.toString().trim()
                    }

                    if (optionDialogListener != null){
                        optionDialogListener?.onOptionChosen(coach)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }
    interface OnOptionDialogListener{
        fun onOptionChosen(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

}