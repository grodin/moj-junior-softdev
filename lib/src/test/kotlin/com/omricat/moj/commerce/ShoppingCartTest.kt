package com.omricat.moj.commerce

import kotlin.test.Test
import kotlin.test.assertEquals

class ShoppingCartTest {

    val testOffers = listOf(FR1BuyOneGetOneFreeOffer, SR1BulkDiscountOffer)

    @Test
    fun singleFruitTeaIsStandardPrice() {
        val products = listOf(Product.FR1)
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = Product.FR1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun singleStrawberriesIsStandardPrice() {
        val products = listOf(Product.SR1)
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = Product.SR1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun singleCoffeeIsStandardPrice() {
        val products = listOf(Product.CF1)
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = Product.CF1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun twoFruitTeasCostsSameAsSingle() {
        val products = listOf(Product.FR1, Product.FR1)
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = Product.FR1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun fourFruitTeasCostsSameAsThree() {
        val threeFruitTeas = listOf(Product.FR1, Product.FR1, Product.FR1)
        val fourFruitTeas = listOf(Product.FR1, Product.FR1, Product.FR1, Product.FR1)
        assertEquals(
            actual = checkout(threeFruitTeas, testOffers),
            expected = checkout(fourFruitTeas, testOffers)
        )
    }

    @Test
    fun twoStrawberriesCostsStandardPrice() {
        val twoStrawberries = listOf(Product.SR1, Product.SR1)
        val checkoutTotal = checkout(twoStrawberries, testOffers)
        assertEquals(expected = Product.SR1.standardPrice * 2, actual = checkoutTotal)
    }

    @Test
    fun threeStrawberriesUsesBulkDiscount() {
        val threeStrawberries = listOf(Product.SR1, Product.SR1, Product.SR1)
        assertEquals(
            expected = SR1BulkDiscountOffer.bulkPrice * 3,
            actual = checkout(threeStrawberries, testOffers)
        )
    }

    @Test
    fun checkoutOrderDoesntAffectPrice() {
        val mixedShoppingCart =
            listOf(
                Product.CF1,
                Product.SR1,
                Product.FR1,
                Product.SR1,
                Product.SR1,
                Product.CF1,
                Product.FR1,
                Product.FR1
            )
        val shuffledShoppingCart = mixedShoppingCart.shuffled()
        assertEquals(
            expected = checkout(mixedShoppingCart, testOffers),
            actual = checkout(shuffledShoppingCart, testOffers)
        )
    }
}
