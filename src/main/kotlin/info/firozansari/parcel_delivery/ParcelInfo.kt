package info.firozansari.parcel_delivery

class ParcelInfo(
    var originLocation: String,
    var destLocation: String,
    var deliveryType: DeliveryType,
    var packageType: PackageType
) {
    fun calculatePrice(): Long {
        if (deliveryType == DeliveryType.SameDay) {
            if (packageType == PackageType.Document) {
                return 400
            } else if (packageType == PackageType.SmallParcel) {
                return 700
            } else if (packageType == PackageType.LargeParcel) {
                return 900
            }
        } else if (deliveryType == DeliveryType.TwoDays) {
            if (packageType == PackageType.Document) {
                return 100
            } else if (packageType == PackageType.SmallParcel) {
                return 250
            } else if (packageType == PackageType.LargeParcel) {
                return 300
            }
        }
        return 0
    }
}