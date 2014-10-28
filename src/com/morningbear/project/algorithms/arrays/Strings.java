package com.morningbear.project.algorithms.arrays;

import org.junit.Test;
import org.junit.Assert;

import java.util.Hashtable;

/**
 * ASCII strings: 256 elements
 * Unicode strings: 65,000 elements
 * @since 9/29/14
 * @author Victor
 */
public class Strings {

    @Test
    public void testIsUniqueChars() {
        String testString1 = "Halo!";
        String testString2 = "Hello";
        String testString3 = new String(new char[65]).replace("\0", "Halo");

        Assert.assertTrue(isUniqueChars(testString1));
        Assert.assertFalse(isUniqueChars(testString2));
        Assert.assertFalse(isUniqueChars(testString3));
    }

    @Test
    public void testPermutation() {
        String testString1 = "Hello";
        String testString2 = "ellHo";
        String testString3 = "ellH3";
        String testString4 = "ellHooo";

        Assert.assertTrue("These two strings are not permutation", permutation(testString1, testString2));
        Assert.assertFalse("These two strings are permutation", permutation(testString1, testString3));
        Assert.assertFalse("These two strings are permutation", permutation(testString1, testString4));
    }

    @Test
    public void testNonRepeatedChar() {
        String testString1 = "total";
        String testString2 = "teeter";

        Assert.assertEquals("nonRepeatedChar function is not working", nonRepeatedChar(testString1).toString(), "o");
        Assert.assertEquals("nonRepeatedChar function is not working", nonRepeatedChar(testString2).toString(), "r");
    }

    @Test
    public void testRemoveSpecifiedChars() {
        String str = "Hello world";
        String remove1 = "aeiou";
        String remove2 = "d";
        String remove3 = "!";

        Assert.assertEquals(removeSpecifiedChars(str, remove1), "Hll wrld");
        Assert.assertEquals(removeSpecifiedChars(str, remove2), "Hello worl");
        Assert.assertEquals(removeSpecifiedChars(str, remove3), "Hello world");
    }

    @Test
    public void testReverseString() {
        String str = "Hello world !";

        Assert.assertEquals("! world Hello", reverseString(str));
    }

    @Test
    public void testSumUpString() {
        char[] testArray = new char[3];
        testArray[0] = 1;
        testArray[1] = 64;
        testArray[2] = 'C';

        Assert.assertTrue(sumUpString(testArray, 'A'));
        Assert.assertFalse(sumUpString(testArray, 'B'));
    }

    /**
     * Given an array, determine if there exists 2 elements that sums up
     * to a specific character, such as 's'
     * Discuss the efficiency of the algorithm
     *      O(n)
     * @param array: Input array
     * @param x: Input Character
     * @return boolean
     */
    public boolean sumUpString(char[] array, char x) {
        Hashtable<Character, Integer> hash = new Hashtable<Character, Integer>();
        for (int i = 0; i < array.length; i++) {
            hash.put(array[i], i);
        }

        for (int i = 0; i < array.length; i++) {
            if (hash.get((char)(x - array[i])) != null && hash.get((char)(x - array[i])) != i) {
                return true;
            }
        }

        return false;
    }

    /**
     * Write a function that reverses the order of the words in a string.
     * Example:
     *      str     = "Do or do not, there is no try."
     *      new_str = "try. no is there not, do or Do".
     * Assume that all words are space delimited and treat punctuation
     * the same as letters.
     * @param str: Input String
     * @return String
     */
    public String reverseString(String str) {
        String new_str = "";

        Hashtable<Integer, String> wordsHash = new Hashtable<Integer, String>();

        String word = "";
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c.toString().equals(" ")) {
                wordsHash.put(j++, word);
                word = "";
            } else if (i == str.length() - 1) {
                word += c;
                wordsHash.put(j, word);
            } else {
                word += c;
            }
        }

        for(int i = j; i >= 0; i--) {
            if (i == 0) {
                new_str += wordsHash.get(i);
            } else {
                new_str += wordsHash.get(i) + " ";
            }
        }

        return new_str;
    }

    /**
     * Write an efficient function in C that deletes
     * characters from a string. Use the prototype.
     * void removeChars(char[] str, char[] remove)
     * where any character existing in remove must be deleted
     * from str.
     * Example:
     *      str = "Battle of the vowels"
     *      remove = "aeiou"
     *      result = "Bttl f th vwls"
     * Discuss the efficiency of the algorithm
     *      O(m+n)
     * @param str: Input String
     * @param remove: Input remove String
     * @return String
     */
    public String removeSpecifiedChars(String str, String remove) {
        boolean[] removeCharSet = new boolean[256];
        String new_str = "";

        for (int i = 0; i < remove.length(); i++) {
            removeCharSet[remove.charAt(i)] = true;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!removeCharSet[str.charAt(i)]) {
                new_str += str.charAt(i);
            }
        }

        return new_str;
    }

    /**
     * Write an efficient function to find the first non-repeated
     * character in a string.
     * Example:
     *      1. "total"
     *         "o" is the first non-repeated character
     *      2. "teeter"
     *         "r" is the first non-repeated character
     * Discuss the efficiency of the algorithm
     *      O(n)
     * @param str: Input String
     * @return Character
     */
    public Character nonRepeatedChar(String str) {
        Hashtable<Character, Integer> charHash = new Hashtable<Character, Integer>();
        Integer charHashValue;

        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            charHashValue = charHash.get(c);

            if (charHashValue == null) {
                charHash.put(c, 1);
            } else {
                charHash.put(c, charHashValue + 1);
            }
        }

        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (charHash.get(c) == 1) return c;
        }

        return null;
    }

    /**
     * Implement an algorithm to determine
     * if a string has all unique characters,
     * cannot use additional data structures.
     * @param str: Input String
     * @return boolean
     */
    public boolean isUniqueChars(String str) {
        if (str.length() > 256) return false;

        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }

        return true;
    }

    /**
     * Given two strings, write a method to decide
     * if one is a permutation of the other.
     * Example: abcdefg - fgadbce
     * Two ways to do this:
     * 1. Sort two strings then compare
     * 2. Identify the number of characters
     * @param str1: Input String1
     * @param str2: Input String2
     * @return boolean
     */
    public boolean permutation(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        int length = str1.length();
        int[] letters = new int[256];
        for(int i = 0; i < length; i++) {
            letters[str1.charAt(i)]++;
        }

        for(int i = 0; i < length; i++) {
            int j = str2.charAt(i);
            if (letters[j] - 1 < 0) return false;
            letters[j]--;
        }

        return true;
    }

    /**
     * Write a method to replace all spaces in a string with '%20'.
     * You may assume the string has sufficient space at the end
     * of the string to hold the additional characters, and that
     * you are given the "true" length of the string.
     * (Note: if implementing in java, please use a character array
     * so that you can perform this operation in place.)
     * Example:
     *      Input: "Mr John Smith    "
     *      Output: "Mr%20John%20Smith"
     */
    public String replaceSpaces(String str) {
        String new_str = new String(new char[str.length()]);
        return new_str;
    }
}
