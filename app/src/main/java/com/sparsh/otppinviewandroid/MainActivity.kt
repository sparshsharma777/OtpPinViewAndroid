package com.sparsh.otppinviewandroid

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sparsh.otppinviewandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnKeyListener {
    private var hasInput: Boolean=false
    private lateinit var binding:ActivityMainBinding
    private lateinit var  viewModel: OtpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(OtpViewModel::class.java)
        binding.viewModel=viewModel
        handleOTPEditTextBoxes()
        binding.executePendingBindings()

    }


    /*
    handling the next focus  nad text target
     */
    private fun handleOTPEditTextBoxes() {
        binding.et1.requestFocus()

        binding.et1.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                binding.et1.clearFocus()
                binding.et2.requestFocus()
            }
            enableDisableButton()
        }

        binding.et2.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                binding.et2.clearFocus()
                binding.et3.requestFocus()
            }
            enableDisableButton()
        }

        binding.et3.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                binding.et3.clearFocus()
                binding.et4.requestFocus()
            }
            enableDisableButton()
        }

        binding.et4.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                binding.et4.clearFocus()
                binding.et5.requestFocus()
            }
            enableDisableButton()
        }

        binding.et5.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                binding.et5.clearFocus()
                binding.et6.requestFocus()
            }
            enableDisableButton()
        }

        binding.et6.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                hideSoftKeyboard()
                // aetOtp6.clearFocus()
                hasInput = true
            }
            enableDisableButton()
        }




        binding.et2.setOnKeyListener(this)
        binding.et3.setOnKeyListener(this)
        binding.et4.setOnKeyListener(this)
        binding.et5.setOnKeyListener(this)
        binding.et6.setOnKeyListener(this)






    }
    private fun hideSoftKeyboard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }



    /**
     * @return Entered OTP
     */
    private fun getOtp(): String {
        return StringBuilder(   binding.et1.text.toString())
            .append(   binding.et2.text.toString())
            .append(   binding.et3.text.toString())
            .append(   binding.et4.text.toString())
            .append(   binding.et5.text.toString())
            .append(   binding.et6.text.toString()).toString()

    }
    private fun enableDisableButton() {
        binding.btnConfirm.isEnabled = getOtp().isNotBlank()
    }



    /*
    handling what will happen if user presses delete button on the device
     */
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (v != null && event != null && event?.action == KeyEvent.ACTION_UP&& keyCode == KeyEvent.KEYCODE_DEL) {
            when (v.id) {
                R.id.et2 -> {
                    if (binding.et2.text.toString().trim().isEmpty()) {
                        binding.et1.setText("")
                        binding.et1.requestFocus()
                        binding.et2.clearFocus()
                    }
                }
                R.id.et3 -> {
                    if (binding.et3.text.toString().trim().isEmpty()) {
                        binding.et2.setText("")
                        binding.et2.requestFocus()
                        binding.et3.clearFocus()
                    }
                }
                R.id.et4 -> {
                    if (binding.et4.text.toString().trim().isEmpty()) {
                        binding.et3.setText("")
                        binding.et3.requestFocus()
                        binding.et4.clearFocus()
                    } else {
                        hasInput = false
                    }
                }
                R.id.et5 -> {
                    if (binding.et5.text.toString().trim().isEmpty()) {
                        binding.et4.setText("")
                        binding.et4.requestFocus()
                        binding.et5.clearFocus()
                    } else {
                        hasInput = false
                    }
                }
                R.id.et6 -> {
                    if (binding.et6.text.toString().trim().isEmpty() && !hasInput) {
                        binding.et5.setText("")
                        binding.et5.requestFocus()
                        binding.et6.clearFocus()
                    } else {
                        hasInput = false
                    }
                }

            }
        }
        return false
    }

}