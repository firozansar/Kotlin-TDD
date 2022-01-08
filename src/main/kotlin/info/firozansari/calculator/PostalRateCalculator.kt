package info.firozansari.calculator

import kotlin.jvm.JvmStatic
import java.util.Locale
import java.lang.NumberFormatException
import java.util.regex.Pattern

object PostalRateCalculator {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size != 7) {
            print("Usage: FromPostalCode, ToPostalCode, Length (cm), Width (cm), Height (cm), Weight (kg), PostType")
            return
        } else {
            var fromPC = args[0].replace(" ", "")
            var toPC = args[1].replace(" ", "")
            val postType = args[6]
            val sLength = args[2]
            val sWidth = args[3]
            val sHeight = args[4]
            val sWeight = args[5]
            var length = 0f
            var width = 0f
            var height = 0f
            var weight = 0f
            val obj = Pattern.compile("([A-Z])(\\d)([A-Z])(\\d)([A-Z])(\\d)")
            val match1 = obj.matcher(fromPC)
            val match2 = obj.matcher(toPC)
            toPC = toPC.uppercase(Locale.getDefault())
            fromPC = fromPC.uppercase(Locale.getDefault())
            try {
                length = sLength.toFloat()
                width = sWidth.toFloat()
                height = sHeight.toFloat()
                weight = sWeight.toFloat()
            } catch (e: NumberFormatException) {
                print("Measurements must be numbers!")
                return
            }
            val girth = (width + height) * 2
            if (height < 0.0 || weight < 0.0 || width < 0.0 || length < 0.0) {
                print("Measurements must be positive!")
                return  //change the order
            } else if (length < width || length < height) {
                print("Length must be longest side of parcel!")
                return
            } else if (length > 200.0) {
                print("Length must not exceed 200.0 cm!")
                return
            } else if (length < 0.10 || width < 0.10 || height < 0.10) {
                print("Measurements must be at least 0.10 cm!")
                return
            } else if (weight > 30.0) {
                print("Weight must not exceed 30.0 kg!")
                return
            } else if (postType != "Regular" && postType != "Xpress" && postType != "Priority") {
                print("Post Type must be either: Regular, Xpress or Priority")
                return
            } else if (fromPC.length != 6) {
                print("From postal code must have a length of 6")
                return
            } else if (length + girth > 300.0) {
                print("Length plus girth must not exceed 300.0 cm!")
                return
            } else if (toPC.length != 6) {
                print("To postal code must have a length of 6")
                return
            } else if (!match1.find()) {
                print("From postal code should have this format: letter number letter number letter number")
                return
            } else if (!match2.find()) {
                print("To postal code should have this format: letter number letter number letter number")
                return
            }
            //IF we had an error then we should ve returned above
            //so now we calculate
            val distance =
                (Math.abs(toPC[0] - fromPC[0]) / 20).toFloat() //dividing by 20 just to give us a "realistic" ratio
            var volumeRatio = (length * width * height - 6000) / 6000 //with that we ll know the interval of the volume
            if (volumeRatio < 0) volumeRatio = 0f
            var Regprice = 9.59.toFloat()
            var Xprsprice = 11.64.toFloat()
            var Prioprice = 19.30.toFloat()
            if (postType == "Regular") {
                Regprice += (volumeRatio * 0.7).toFloat() //adding the 'Regular 'price for the volume
                Regprice += (weight * 0.08).toFloat() //adding the price for the weight
                Regprice *= (1 + distance + 0.15).toFloat() //calculating the price for the distance and the taxes
                System.out.format("Regular Price is:$%.2f", Regprice)
            } else if (postType == "Xpress") {
                Xprsprice += (volumeRatio * 0.87).toFloat() //adding the 'Xpress 'price for the volume
                Xprsprice += (weight * 0.24).toFloat() //adding the price for the weight
                Xprsprice *= (1 + distance + 0.15).toFloat() //calculating the price for the distance and the taxes
                System.out.format("Xpress Price is:$%.2f", Xprsprice)
            } else if (postType == "Priority") {
                Prioprice += (volumeRatio * 1.09).toFloat() //adding the 'Priority 'price for the volume
                Prioprice += (weight * 0.48).toFloat() //adding the price for the weight
                Prioprice *= (1 + distance + 0.15).toFloat() //calculating the price for the distance and the taxes
                System.out.format("Priority Price is:$%.2f", Prioprice)
            }
        }
    }
}