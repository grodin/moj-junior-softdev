package com.omricat.moj.commerce

/**
 * Class representing a shopping cart. Is initialized empty.
 *
 * @param[offers] the currently active offers
 */
class ShoppingCart(val offers: List<Offer>) {
    private val cart: MutableList<Product> = mutableListOf()

    /** Returns the current content of the shopping cart */
    // The implementation of toList will produce a new List containing the original elements
    // but this is an implementation detail and should be replaced by a proper defensive copy method
    fun contentsOfCart(): List<Product> = cart.toList()

    /**
     * Scans an item, adding it to the shopping cart
     *
     * @param[item] the item to add
     */
    fun scanItem(item: Product) {
        cart.add(item)
    }

    /**
     * Scans all items, adding them to the shopping cart
     *
     * @param[items] the items to add
     */
    fun scanAllItems(items: Collection<Product>) {
        cart.addAll(items)
    }

    /**
     * Calculate price of all items currently in shopping cart, applying all appropriate offers
     */
    fun checkout(): Price {
        val unprocessedItems = cart.toMutableList()
        var totalPrice = Price(0u)
        offers.forEach { offer ->
            val result = offer.applyOffer(cart)
            totalPrice += result.price
            unprocessedItems.removeAll(result.processedItems)
        }
        return totalPrice + unprocessedItems.sumWithStandardPrices()
    }

    private fun Collection<Product>.sumWithStandardPrices(): Price = Price(sumOf { it.standardPrice.priceInPence })
}

