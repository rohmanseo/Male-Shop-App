package com.komsi.maleshop.utils

enum class ConstApi(val value: String){
    ENDPOINT("http://192.168.25.105"),

    //data
    REGISTER("${ENDPOINT.value}/api/auth/register"),
    UPDATE_PROFILE("${ENDPOINT.value}/api/auth/update"),
    LOGIN("${ENDPOINT.value}/api/auth/login"),
    SLIDESHOW("${ENDPOINT.value}/api/slideshow"),
    NEWPRODUCT("${ENDPOINT.value}/api/produk/new"),
    ORDER("${ENDPOINT.value}/api/order"),
    COAT("${ENDPOINT.value}/api/kategori/1"),
    SHIRT("${ENDPOINT.value}/api/kategori/2"),
    SEARCH("${ENDPOINT.value}/api/search/"),
    CART("${ENDPOINT.value}/api/cart"),
    WISHLIST("${ENDPOINT.value}/api/wishlist"),
    PROFILE("${ENDPOINT.value}/api/auth/me"),



    //image url
    PRODUCT_IMAGE_URL("${ENDPOINT.value}/images/produk/"),
    SLIDESHOW_IMAGE_URL("${ENDPOINT.value}/images/slideshow/"),

}