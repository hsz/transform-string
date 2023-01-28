package ski.chrzanow.transformstring

import org.apache.commons.text.CaseUtils

/**
 * Replaces matched patterns of uppercase letters that are preceded or followed by lowercase letters with the separator
 * followed by the matched letter. It also replaces any non-word or underscore characters with the separator. Finally,
 * it removes any separators that may have been added to the beginning of the string.
 *
 *  @param separator the separator to be inserted between apparent word boundaries.
 *  @return a string retaining its original case with each apparent word separated by the provided separator.
 *
 **/
internal fun String.prepare(separator: String) =
    replace("((?<=\\p{Ll})\\p{Lu}|\\p{Lu}(?=\\p{Ll}))".toRegex(), "$separator$1")
        .replace("[\\W_]+".toRegex(), separator)
        .removePrefix(separator)
        .removeSuffix(separator)


fun String.transformstring() = prepare("").lowercase()

fun String.TRANSFORMSTRING() = prepare("").uppercase()

fun String.transformString() = CaseUtils.toCamelCase(prepare(" "), false, ' ')

fun String.TransformString() = CaseUtils.toCamelCase(prepare(" "), true, ' ')

fun String.transform_string() = prepare("_").lowercase()

fun String.TRANSFORM_STRING() = prepare("_").uppercase()

fun String.`transform-string`() = prepare("-").lowercase()

fun String.`TRANSFORM-STRING`() = prepare("-").uppercase()

fun String.tRaNsFoRmStRiNg(): String {
    var newStr = ""
    var shouldBeUpperCase = false
    forEach { str ->
        if(str.isLetter()) {
            newStr += if(shouldBeUpperCase) str.uppercase() else str.lowercase()
            shouldBeUpperCase = !shouldBeUpperCase
        } else newStr += str
    }
    return newStr
}
