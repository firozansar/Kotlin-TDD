package info.firozansari.parcel_delivery

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ParcelInfoTest {

    @Test
    fun testRightPriceOutput() {
        val parcelInfo = ParcelInfo("", "", DeliveryType.SameDay, PackageType.Document)
        val price = parcelInfo.calculatePrice()
        assertNotNull(price)
        assertEquals(price, 400)
    }

    @Test
    fun calculateTwoDaysLargeParcelPrice() {
        val parcelInfo = ParcelInfo("", "", DeliveryType.TwoDays, PackageType.LargeParcel)
        val price = parcelInfo.calculatePrice()
        assertNotNull(price)
        assertEquals(price, 300)
    }
}
