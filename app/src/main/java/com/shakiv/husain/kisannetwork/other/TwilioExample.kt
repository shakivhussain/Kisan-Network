package com.shakiv.husain.kisannetwork.other

import android.util.Log
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber


class TwilioExample {
    fun sendMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
        val message: Message = Message.creator(
            PhoneNumber("+919084747646"),  // Replace with your Twilio phone number
            PhoneNumber("+919457596811"),  // Replace with the recipient's phone number
            "Where's Wallace?"
        )
            .create()
        Log.d("TAG", "sendMessage: ${message.getSid()}")
        System.out.println(message.getSid())
    }

    companion object {
        // Find your Account SID and Auth Token at twilio.com/console
        // Replace these with your actual Account SID and Auth Token
        const val ACCOUNT_SID = "ACb11605ea63f04343fd50fe92515af4a7"
        const val AUTH_TOKEN = "ece552a98290f848b7684ddaf6957233"
    }
}