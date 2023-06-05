package com.omricat.moj.commerce

interface Offer {
    fun applyOffer(items: List<Product>): OfferResult
}

data class OfferResult(
    val price: Price,
    val processedItems: List<Product>,
    val remainingItems: List<Product>
)
