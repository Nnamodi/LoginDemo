package utils

actual class NumberParseException(phoneNumber: String) : Exception(
	"Failed to parse number $phoneNumber"
)