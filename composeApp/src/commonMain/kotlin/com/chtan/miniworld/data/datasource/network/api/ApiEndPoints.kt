package com.chtan.miniworld.data.datasource.network.api

object ApiEndPoints {




    //server
//    const val BASE_URL = "http://13.48.43.197:8080"
//    const val imageSocketUrl = "ws://13.48.43.197:8080/api/ws/usercam?deviceId=6970f4a4323597dd06e16309"
//    const val driveSocketUrl = "ws://13.48.43.197:8080/api/ws/user?deviceId=6970f457323597dd06e16308"

    //local
    const val BASE_URL = "http://192.168.0.250:8080"
    const val imageSocketUrl = "ws://192.168.0.250:8080/api/ws/usercam?deviceId=693421d75a4cfba1adfaaa0e"
    const val driveSocketUrl = "ws://192.168.0.250:8080/api/ws/user?deviceId=696b642f09e25fc963cea228"


    const val SIGN_IN = "$BASE_URL/usignin"
    const val SIGN_UP = "$BASE_URL/signup"
    const val CHAT_LIST = "$BASE_URL/api/chatlist"
    const val MESSAGE_LIST = "$BASE_URL/api/getmessages"
    const val MY_PROFILE = "$BASE_URL/api/myprofile"
    const val ULALA = "$BASE_URL/api/ulala"
    const val UPLOAD_PHOTO_FOR_ULALA = "$BASE_URL/api/uploadphotoforulala"
}