package com.omricat.moj.commerce

/**
 * Functional interface representing an offer
 */
interface Offer {

    /**
     * Applies this offer to a list of [Product], returning the price and list of processed items
     */
    fun applyOffer(items: List<Product>): Result

    /**
     * Convenience class to represent the result of applying an offer
     */
    class Result(val price: Price, val processedItems: List<Product>)
}

/**
 * Buy-one-get-one-free offer on Fruit Tea items
 */
object FR1BuyOneGetOneFreeOffer : Offer {
    override fun applyOffer(items: List<Product>): Offer.Result {
        val onlyFr1Items = items.filterIsInstance<FR1>()

        // Each pair is priced as a single item (Buy-one-get-one-free)
        val numberOfPairs = onlyFr1Items.size / 2

        // Any leftovers after removing the pairs (can only be zero or one)
        val numberOfLeftovers = onlyFr1Items.size % 2
        return Offer.Result(
            price = FR1.standardPrice * (numberOfPairs + numberOfLeftovers),
            processedItems = onlyFr1Items
        )
    }
}

/**
 * Bulk discount on Strawberries
 */
object SR1BulkDiscountOffer : Offer {

    val bulkPrice = Price(450u)

    override fun applyOffer(items: List<Product>): Offer.Result {
        val onlySr1Items = items.filterIsInstance<SR1>()
        val itemPrice = if (onlySr1Items.size >= 3) bulkPrice else SR1.standardPrice
        return Offer.Result(
            price = itemPrice * onlySr1Items.size,
            processedItems = onlySr1Items
        )
    }
}
