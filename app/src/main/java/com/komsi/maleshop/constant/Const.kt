package com.komsi.maleshop.constant

enum class ConstApi(val value: String){
    ENDPOINT("http://192.168.0.5"),
    REGISTER("${ENDPOINT.value}/api/auth/register"),
    LOGIN("${ENDPOINT.value}/api/auth/login"),
}