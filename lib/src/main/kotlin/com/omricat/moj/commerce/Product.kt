package com.omricat.moj.commerce

/** Interface for products we sell */
interface Product {
    val productName: String
    val standardPrice: Price
}

private class CompanionProduct(
    override val productName: String,
    override val standardPrice: Price
) : Product

class FR1 : Product {
    override val productName: String
        get() = Companion.productName

    override val standardPrice: Price
        get() = Companion.standardPrice

    companion object {
        val productName = "Fruit tea"
        val standardPrice = Price(311u)
    }
}

class SR1 : Product {
    override val productName: String
        get() = Companion.productName

    override val standardPrice: Price
        get() = Companion.standardPrice

    companion object {
        val productName = "Strawberries"
        val standardPrice = Price(500u)
    }
}

class CF1 : Product {
    override val productName: String
        get() = Companion.productName

    override val standardPrice: Price
        get() = Companion.standardPrice
    
    companion object {
        val productName = "Coffee"
        val standardPrice = Price(1123u)
    }
}
