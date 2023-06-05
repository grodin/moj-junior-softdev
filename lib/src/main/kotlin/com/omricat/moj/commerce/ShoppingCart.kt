package com.omricat.moj.commerce

sealed class Product(val productName: String, val standardPrice: Price) {
    object FR1 : Product("Fruit tea", standardPrice = Price(311u))
    object SR1 : Product("Strawberries", standardPrice = Price(500u))
    object CF1 : Product("Coffee", standardPrice = Price(1123u))
}

fun Collection<Product>.sum(): Price = Price(sumOf { it.standardPrice.priceInPence })

fun checkout(shoppingCart: List<Product>, offers: List<Offer>): Price {
    var remainingItems = shoppingCart.toList()
    var totalPrice = Price(0u)
    offers.forEach { offer ->
        val result = offer.applyOffer(remainingItems)
        totalPrice += result.price
        remainingItems = result.remainingItems
    }
    return totalPrice + remainingItems.sum()
}

val currentOffers = listOf(FR1BuyOneGetOneFreeOffer, SR1BulkDiscountOffer)

