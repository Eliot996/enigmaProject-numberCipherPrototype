package com.company;


import java.util.Scanner;

public class Main {
    // master alphabet
    final static char[] ALPHABET = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(encode());

    }

    // To encode a text
    public static String encode(){
        String encoded;
        int[] ints;

        //get string from user
        System.out.print("Skriv en text, der skal konverters til tal: ");
        String toEncode = input.nextLine();

        // convert string into an int array, and save it in ints
        ints = toInts(toEncode.toUpperCase());

        //convert ints to a string, and return the string
        encoded = toString(ints);
        return encoded;
    }

    // converts a string to an array of ints
    public static int[] toInts(String text) {
        // create an array of ints, with the same size as the length of text
        int[] ints = new int[text.length()];

        // for each index of text, add the corresponding int into the respective place in the int array
        for (int i = 0; i < text.length(); i++) {
            ints[i] = charToInt(text.charAt(i));
        }
        return ints;
    }

    // converts an array of ints to a String
    public static String toString(int[] ints){
        StringBuilder result = new StringBuilder("{" + ints[0]);
        for (int i = 1; i < ints.length; i++) {
            result.append(",").append(ints[i]);
        }
        result.append("}");
        return result.toString();
    }

    // takes a char and returns the index of that char in ALPHABET, else return -1
    public static int charToInt(char charater) {
        // loops through ALPHABET, until it finds the right value, and returns the index
        for (int i = 0; i < ALPHABET.length; i++) {
            if (charater == ALPHABET[i]) return i;
        }
        return -1;
    }
}
