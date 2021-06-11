package com.sparsh.otppinviewandroid

import android.os.CountDownTimer
import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit

class OtpViewModel :ViewModel() {

    private  var countDownTimer  : CountDownTimer?= null
    private val timer = MutableLiveData<String>()
    val otpTimingText= ObservableField<String>()
    var otpEnabled= ObservableField<Boolean>(false)

    val otp1= ObservableField<String>()
    val otp2= ObservableField<String>()
    val otp3= ObservableField<String>()
    val otp4= ObservableField<String>()
    val otp5= ObservableField<String>()
    val otp6= ObservableField<String>()
    var alpha= ObservableField<Float>(0.4f)
    var enabled= ObservableField<Boolean>(false)


    init {

        otp1.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAndUpdate()
            }

        })
        otp2.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAndUpdate()
            }

        })
        otp3.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAndUpdate()
            }

        })
        otp4.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAndUpdate()
            }

        })
        otp5.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAndUpdate()
            }

        })
        otp6.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAndUpdate()
            }

        })
    }

    private fun checkAndUpdate(){
        if(otp1.get().toString().trim().length==1&&
            otp2.get().toString().trim().length==1&&
            otp3.get().toString().trim().length==1&&
            otp4.get().toString().trim().length==1&&
            otp5.get().toString().trim().length==1&&
            otp6.get().toString().trim().length==1){
            enabled.set(true)
            alpha.set(1.0f)

        }
        else{
            enabled.set(false)
            alpha.set(0.4f)
        }


    }







    fun startTimer() {
        val time = 120000L

        if(countDownTimer!=null)
            countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(time, 1000L) {
            override fun onFinish() {
                timer.value = null
                otpEnabled.set(true)
            }

            override fun onTick(millisUntilFinished: Long) {
                otpEnabled.set(false)

                val minutes = (millisUntilFinished / 1000) / 60;
                val  seconds = (millisUntilFinished / 1000) % 60;

                Log.d("time-", millisUntilFinished.toString())
                timer.value = String.format("%02d:%02d",minutes,seconds)
                otpTimingText.set(String.format(MyApplication.instance.getString(R.string.resend_otp_in, TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt())))
                Log.d("time", String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)))
            }

        }
        countDownTimer?.start()
    }
}