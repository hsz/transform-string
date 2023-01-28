package ski.chrzanow.transformstring

import org.apache.commons.text.CaseUtils

private fun String.prepare(separator: String) =
    replace("((?<=\\p{Ll})\\p{Lu}|\\p{Lu}(?=\\p{Ll}))".toRegex(), "$separator$1")
        .replace("[\\W_]".toRegex(), separator)
        .removePrefix(separator)

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
