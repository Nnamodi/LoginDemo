package utils

import cocoapods.libPhoneNumber_iOS.NBAsYouTypeFormatter
import cocoapods.libPhoneNumber_iOS.NBPhoneNumber
import cocoapods.libPhoneNumber_iOS.NBPhoneNumberUtil
import platform.Foundation.NSNumber

actual class PhoneNumberUtil {
	private val phoneUtil = NBPhoneNumberUtil.sharedInstance()!!

	actual fun parse(phoneNumber: String, defaultRegion: String): PhoneNumber {
		return phoneUtil.parse(phoneNumber, defaultRegion, null)?.asKotlin()
			?: throw NumberParseException(phoneNumber)
	}

	actual fun isValidNumber(phoneNumber: PhoneNumber): Boolean = phoneUtil.isValidNumber(phoneNumber.asNative())

	actual fun format(phoneNumber: PhoneNumber, format: PhoneNumberFormat): String {
		return phoneUtil.format(phoneNumber.asNative(), format.ordinal.toLong(), null)!!
	}

	actual fun getAsYouTypeFormatter(regionCode: String) = AsYouTypeFormatter(NBAsYouTypeFormatter(regionCode))

	actual companion object {
		actual fun getInstance() = PhoneNumberUtil()
	}
}

private fun NBPhoneNumber.asKotlin() = PhoneNumber(
	countryCode = countryCode?.intValue,
	extension = extension,
	hasItalianLeadingZero = italianLeadingZero,
	nationalNumber = nationalNumber?.longValue,
	numberOfLeadingZeros = numberOfLeadingZeros?.intValue,
	preferredDomesticCarrierCode = preferredDomesticCarrierCode,
	rawInput = rawInput
)

@Suppress("CAST_NEVER_SUCCEEDS")
private fun PhoneNumber.asNative() = NBPhoneNumber().also { phoneNumber ->
	phoneNumber.countryCode = countryCode?.let { it as NSNumber }
	phoneNumber.extension = extension
	phoneNumber.italianLeadingZero = hasItalianLeadingZero
	phoneNumber.nationalNumber = nationalNumber?.let { it as NSNumber }
	phoneNumber.numberOfLeadingZeros = numberOfLeadingZeros?.let { it as NSNumber }
	phoneNumber.preferredDomesticCarrierCode = preferredDomesticCarrierCode
	phoneNumber.rawInput = rawInput
}