package com.omricat.moj.commerce

import kotlin.test.Test
import kotlin.test.assertEquals

class ShoppingCartTest {

    private val testOffers = listOf(FR1BuyOneGetOneFreeOffer, SR1BulkDiscountOffer)

    private fun checkout(items: List<Product>, offers: List<Offer>): Price =
        ShoppingCart(offers).apply { scanAllItems(items) }.checkout()

    @Test
    fun singleFruitTeaIsStandardPrice() {
        val products = listOf(FR1())
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = FR1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun singleStrawberriesIsStandardPrice() {
        val products = listOf(SR1())
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = SR1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun singleCoffeeIsStandardPrice() {
        val products = listOf(CF1())
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = CF1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun twoFruitTeasCostsSameAsSingle() {
        val products = listOf(FR1(), FR1())
        val checkoutTotal = checkout(products, testOffers)
        assertEquals(expected = FR1.standardPrice, actual = checkoutTotal)
    }

    @Test
    fun fourFruitTeasCostsSameAsThree() {
        val threeFruitTeas = listOf(FR1(), FR1(), FR1())
        val fourFruitTeas = listOf(FR1(), FR1(), FR1(), FR1())
        assertEquals(
            actual = checkout(threeFruitTeas, testOffers),
            expected = checkout(fourFruitTeas, testOffers)
        )
    }

    @Test
    fun twoStrawberriesCostsStandardPrice() {
        val twoStrawberries = listOf(SR1(), SR1())
        val checkoutTotal = checkout(twoStrawberries, testOffers)
        assertEquals(expected = SR1.standardPrice * 2, actual = checkoutTotal)
    }

    @Test
    fun threeStrawberriesUsesBulkDiscount() {
        val threeStrawberries = listOf(SR1(), SR1(), SR1())
        assertEquals(
            expected = SR1BulkDiscountOffer.bulkPrice * threeStrawberries.size,
            actual = checkout(threeStrawberries, testOffers)
        )
    }

    @Test
    fun checkoutOrderDoesntAffectPrice() {
        val mixedShoppingCart =
            listOf(
                CF1(),
                SR1(),
                FR1(),
                SR1(),
                SR1(),
                CF1(),
                FR1(),
                FR1()
            )
        val shuffledShoppingCart = mixedShoppingCart.shuffled()
        assertEquals(
            expected = checkout(mixedShoppingCart, testOffers),
            actual = checkout(shuffledShoppingCart, testOffers)
        )
    }
}
