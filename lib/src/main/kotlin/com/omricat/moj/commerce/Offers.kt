package com.omricat.moj.commerce

object FR1BuyOneGetOneFreeOffer : Offer {
    override fun applyOffer(items: List<Product>): OfferResult {
        val (onlyFr1Items, remainingItems) = items.partition { product -> product is Product.FR1 }

        // Each pair is priced as a single item (Buy-one-get-one-free)
        val numberOfPairs = onlyFr1Items.size / 2

        // Any leftovers after removing the pairs (can only be zero or one)
        val numberOfLeftovers = onlyFr1Items.size % 2
        return OfferResult(
            price = Product.FR1.standardPrice * (numberOfPairs + numberOfLeftovers),
            processedItems = onlyFr1Items,
            remainingItems
        )
    }
}

object SR1BulkDiscountOffer : Offer {

    val bulkPrice = Price(450u)

    override fun applyOffer(items: List<Product>): OfferResult {
        val (onlySr1Items, remainingItems) = items.partition { it is Product.SR1 }
        val itemPrice = if (onlySr1Items.size >= 3) bulkPrice else Product.SR1.standardPrice
        return OfferResult(
            price = itemPrice * onlySr1Items.size,
            processedItems = onlySr1Items,
            remainingItems
        )
    }
}
