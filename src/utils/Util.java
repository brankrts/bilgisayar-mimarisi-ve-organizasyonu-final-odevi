package utils;

import java.util.ArrayList;

public class Util {

    static Util _instance;

    public static Util instance() {

        if (Util._instance == null) {
            return new Util();
        }
        return _instance;
    }

    public ArrayList<Integer> parseFileLines(String line) {
        ArrayList<Integer> bytes = new ArrayList<Integer>();
        char[] helloArray = line.toCharArray();

        for (var element : helloArray) {
            bytes.add(Integer.parseInt(String.valueOf(element)));
        }

        return bytes;
    }

    public int complement(int s) {
        s = (s == 1 ? 0 : 1);
        return s;
    }

    public ArrayList<Integer> hexToBinary(String hex) {
        StringBuilder binary = new StringBuilder();
        String hexChars = "0123456789ABCDEF";
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            int hexValue = hexChars.indexOf(Character.toUpperCase(hexChar));
            String binaryValue = Integer.toBinaryString(hexValue);

            while (binaryValue.length() < 4) {
                binaryValue = "0" + binaryValue;
            }
            binary.append(binaryValue);
        }
        char[] charArray = binary.toString().toCharArray();
        for (char c : charArray) {
            result.add(Integer.parseInt(String.valueOf(c)));
        }

        return result;
    }

    public ArrayList<Integer> incrementBinary(ArrayList<Integer> binaryList) {
        if (binaryList.size() > 4) {
            System.out.println("Listenin boyutu 4'ten büyük olamaz!");
            return binaryList;
        }

        int carry = 1;

        for (int i = binaryList.size() - 1; i >= 0 && carry > 0; i--) {
            int binaryValue = binaryList.get(i);

            if (binaryValue == 0) {
                binaryList.set(i, 1);
                carry = 0;
            } else {
                binaryList.set(i, 0);
            }
        }

        if (carry > 0 && binaryList.size() < 4) {
            binaryList.add(0, 1);
        }

        return binaryList;
    }

    public int bitToInteger(ArrayList<Integer> bitArray) {
        int result = 0;
        for (int bit : bitArray) {
            result = (result << 1) | bit;
        }
        return result;
    }

}
