package com.base.utils;

public class CaesarCipher  
{  
    private static final char[] UC_ENCRYPT_CHARS = { 'C', 'T', 'N',   'X','G',
        'K', 'O',  'W', 'R', 'S', 'F', 'Y', 'V', 'L', 'Z', 'Q', 'A', 'H', 'M', 'D',  'U', 'P', 'I', 'B', 'E', 'J' };  
  
    private static final char[] LC_ENCRYPT_CHARS = { 'c', 't', 'n',   'x', 'g', 
        'k', 'o', 'w', 'r', 's', 'f', 'y', 'v', 'l', 'z', 'q', 'a', 'h', 'm', 'd', 'u', 'p', 'i', 'b', 'e', 'j' };  
  
    private static char[] UC_DECRYPT_CHARS = new char[26];  
  
    private static char[] LC_DECRYPT_CHARS = new char[26];  
  
    static {  
        for (int i = 0; i < 26; i++) {  
            char b = UC_ENCRYPT_CHARS[i];  
            UC_DECRYPT_CHARS[b - 'A'] = (char) ('A' + i);  
  
            b = LC_ENCRYPT_CHARS[i];  
            LC_DECRYPT_CHARS[b - 'a'] = (char) ('a' + i);  
        }  
    }  
  
    public static char encrypt(char b) {  
        if (b >= 'A' && b <= 'Z') {  
            return UC_ENCRYPT_CHARS[b - 'A'];  
        } else if (b >= 'a' && b <= 'z') {  
            return LC_ENCRYPT_CHARS[b - 'a'];  
        } else {  
            return b;  
        }  
    }  
  
    public static char decrypt(char b) {  
        if (b >= 'A' && b <= 'Z') {  
            return UC_DECRYPT_CHARS[b - 'A'];  
        } else if (b >= 'a' && b <= 'z') {  
            return LC_DECRYPT_CHARS[b - 'a'];  
        } else {  
            return b;  
        }  
    }  
      
    public static String encrypt(String input){  
        StringBuilder sb = new StringBuilder();  
        for(int i = 0; i < input.length(); i++){  
            sb.append(encrypt(input.charAt(i)));  
        }  
        return sb.toString();  
    }  
      
    public static String decrypt(String input){  
        StringBuilder sb = new StringBuilder();  
        for(int i = 0; i < input.length(); i++){  
            sb.append(decrypt(input.charAt(i)));  
        }  
        return sb.toString();  
    }  
  
}  
