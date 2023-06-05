package com.omricat.moj.commerce

sealed class Product(val productName: String) {
    object FR1 : Product("Fruit tea")
    object SR1 : Product("Strawberries")
    object CF1 : Product("Coffee")
}

@JvmInline value class Price(val priceInPence: Int)

operator fun Price.plus(other: Price): Price = Price(this.priceInPence + other.priceInPence)

operator fun Price.times(multiple: Int): Price = Price(this.priceInPence * multiple)

fun Product.standardPrice(): Price =
    when (this) {
        is Product.FR1 -> Price(311)
        is Product.SR1 -> Price(500)
        is Product.CF1 -> Price(1123)
    }

fun Product.SR1.bulkPrice(): Price = Price(450)

fun checkout(shoppingCart: List<Product>): Price =
    with(shoppingCart) {
        filterIsInstance<Product.FR1>().totalPrice() +
            filterIsInstance<Product.SR1>().totalPrice() +
            filterIsInstance<Product.CF1>().totalPrice()
    }

@JvmName("totalFR1Price")
private fun List<Product.FR1>.totalPrice(): Price {
    // Each pair is priced as a single item (Buy-one-get-one-free)
    val numberOfPairs = size / 2

    // Any leftovers after removing the pairs (can only be zero or one)
    val numberOfLeftovers = size % 2
    return Product.FR1.standardPrice() * (numberOfPairs + numberOfLeftovers)
}

@JvmName("totalSR1Price")
private fun List<Product.SR1>.totalPrice(): Price {
    val discountedPrice = if (size >= 3) Product.SR1.bulkPrice() else Product.SR1.standardPrice()
    return discountedPrice * size
}

@JvmName("totalCF1Price")
private fun List<Product.CF1>.totalPrice(): Price = Product.CF1.standardPrice() * size
