package com.omricat.moj.commerce

/**
 * Abstract class representing a product we sell
 */
sealed class Product(val productName: String, val standardPrice: Price) {
    object FR1 : Product("Fruit tea", standardPrice = Price(311u))
    object SR1 : Product("Strawberries", standardPrice = Price(500u))
    object CF1 : Product("Coffee", standardPrice = Price(1123u))
}
