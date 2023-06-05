package com.omricat.moj.commerce

@JvmInline
value class Price(val priceInPence: UInt)

operator fun Price.plus(other: Price): Price = Price(this.priceInPence + other.priceInPence)

operator fun Price.times(multiple: Int): Price = Price(this.priceInPence * multiple.toUInt())
