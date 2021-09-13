package com.company;

import java.util.Scanner;

public class Main {
    // master alphabet
    final static char[] ALPHABET = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å', ',', '.', '!', '?'};
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String selector = "";
        System.out.println("""
                
                Dette progam kan konvertere tekst til en række tal, og omvendt
                """);

        while (!selector.equals("q")){
            if (selector.equals("e")){
                System.out.println("Den encode tekst er: " + encode());
                System.out.println();
            } else if (selector.equals("d")){
                System.out.println("Den decode tekst er: " + decode());
                System.out.println();
            }
            System.out.print("Skriv 'e' for at encode, skriv 'd' for at decode en bedsked, eller q for at stoppe: ");
            selector = input.nextLine();
        }
    }

    // To decode a text
    public static String decode(){
        String decoded;
        int[] ints;

        // get input from user
        System.out.print("Du har valgt decode - indtast en liste af tal: ");
        String toDecode = input.nextLine();

        // convert string to array of ints
        ints = stringToInts(toDecode);

        // convert to string, from indexes
        decoded = intsToString(ints);
        return decoded;
    }

    // takes the patteren for the string of the int array, and converts it to an array of ints
    public static int[] stringToInts(String text){
        // creates an array, with the length of the text, because we cannot adjust the lenght of the array later.
        // therefore, it is better to have a larger array than neccesary, and adjust later
        int[] intsPlaceholder = new int[text.length()],
                ints;

        // index for the while loop
        int i = 0;

        // finds the indexes of "{" and "}"
        int indexFirst = text.indexOf("{"),
                indexLast = text.indexOf("}"),
                workingIndex;

        // get only the numbers, needed along with commas
        String placeholder = text.substring(indexFirst + 1,indexLast);

        // find the first "," in placholder
        workingIndex = placeholder.indexOf(", ");

        // using workingIndex
        while (workingIndex != -1){
            // takes the string before the workingIndex and parses it to an int,
            // and saves it in the respective index of ints
            intsPlaceholder[i] = Integer.parseInt(placeholder.substring(0,workingIndex));

            // removes the number just found, along with the ", "
            placeholder = placeholder.substring(workingIndex + 2);

            // gets new workingIndex
            workingIndex = placeholder.indexOf(", ");
            i++;
        }
        // adds the remaining number
        intsPlaceholder[i] = Integer.parseInt(placeholder);

        // creates a new int array, of proper size
        ints = new int[i+1];

        // copies the working array to the returning array
        for (int j = 0; j < ints.length; j++) {
            ints[j] = intsPlaceholder[j];
        }

        return ints;
    }

    // converts an array of ints to the corresponding string of chars
    public static String intsToString(int[] ints){
        // makes a StringBuilder, and appends the corresponding chars to the StringBuilder
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            result.append(ALPHABET[ints[i]]);
        }
        return result.toString();
    }

    // To encode a text
    public static String encode(){
        String encoded;
        int[] ints;

        //get string from user
        System.out.print("Du har valgt encode - indtast en tekst: ");
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
            result.append(", ").append(ints[i]);
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