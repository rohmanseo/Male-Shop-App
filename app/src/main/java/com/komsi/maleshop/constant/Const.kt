package com.komsi.maleshop.constant

enum class ConstApi(val value: String){
    ENDPOINT("http://192.168.0.5"),
    REGISTER("${ENDPOINT.value}/api/auth/register"),
    LOGIN("${ENDPOINT.value}/api/auth/login"),
    SLIDESHOW("${ENDPOINT.value}/api/slideshow"),
    SLIDESHOW_IMAGE_URL("${ENDPOINT.value}/images/slideshow/"),
    NEWPRODUCT("${ENDPOINT.value}/api/produk/new"),
    PRODUCT_IMAGE_URL("${ENDPOINT.value}/images/produk/")
}