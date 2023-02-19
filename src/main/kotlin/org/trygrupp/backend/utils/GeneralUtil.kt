package org.trygrupp.backend.utils

import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Pattern

object GeneralUtil {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun stringIsNullOrEmpty(value: String): Boolean {
        return Objects.isNull(value) || value.isEmpty()
    }

    fun longIsNullOrZero(value: Long): Boolean {
        return Objects.isNull(value) || value == 0L
    }

    fun bigDecimalIsNull(value: BigDecimal): Boolean {
        return Objects.isNull(value) || value.compareTo(BigDecimal.ZERO) == 0
    }

    fun listIsEmpty(value: List<String?>): Boolean {
        return value.isEmpty()
    }

    fun split(toSplit: String, splitValue: String): Array<String> {
        return toSplit.split(splitValue.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    fun sha512(args: String): String? {
        try {
            // Create MessageDigest instance for MD5
            val md = MessageDigest.getInstance("SHA-512")
            //Add password bytes to digest
            md.update(args.toByteArray(StandardCharsets.UTF_8))
            //Get the hash's bytes
            val bytes = md.digest()
            //These bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            return Base64.getEncoder().encodeToString(bytes)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }

    fun generateRandomWords(numberOfWords: Int): Array<String?> {
        val randomStrings = arrayOfNulls<String>(numberOfWords)
        val random = Random()
        for (i in 0 until numberOfWords) {
            val word =
                CharArray(random.nextInt(8) + 3) // words of length 3 through 10. (1 and 2-letter words are boring.)
            for (j in word.indices) {
                word[j] = ('a'.code + random.nextInt(26)).toChar()
            }
            randomStrings[i] = String(word)
        }
        return randomStrings
    }

    fun generateRandomWord(wordLength: Int): String {
        val r = Random() // Initialize a Random Number Generator with SysTime as the seed
        val sb = StringBuilder(wordLength)
        for (i in 0 until wordLength) { // For each letter in the word
            val tmp = ('a'.code + r.nextInt('z'.code - 'a'.code)).toChar() // Generate a letter between a and z
            sb.append(tmp) // Add it to the String
        }
        return sb.toString()
    }

    /**
     * ^ represents starting character of the string.
     * (?=.*[0-9]) represents a digit must occur at least once.
     * (?=.*[a-z]) represents a lower case alphabet must occur at least once.
     * (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
     * (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
     * (?=\\S+$) white spaces don’t allowed in the entire string.
     * .{8, 20} represents at least 8 characters and at most 20 characters.
     * $ represents the end of the string.
     */
    fun isValidPassword(password: String?): Boolean {

        // Regex to check valid password.
        val regex = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$")

        // Compile the ReGex
        val p = Pattern.compile(regex)

        // If the password is empty
        // return false
        if (password == null) {
            return false
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        val m = p.matcher(password)

        // Return if the password
        // matched the ReGex
        return m.matches()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val reference = "test_20191123132233"
        log.info(LocalDateTime.parse("2022-12-13T00:00:00").toString())
    }

}