package com.plthanh.util;

public class Encryption {
	public static String encode(String input, int key) {
		String output = "";
		for (int i=0; i<input.length(); i++) {
			int temp = input.charAt(i);
			int tempkey;
			
			if (temp >= 48 && temp <= 57) { // number
				tempkey = key % 10;
				temp += tempkey;
				if (temp > 57) {
					temp = (temp - 57) + 48;
				}
			} else if (temp >= 65 && temp <= 90) { // uppercase letter
				tempkey = key % 26;
				
				temp += tempkey;
				if (temp > 90) {
					temp = (temp - 90) + 65;
				}
				
			} else if (temp >= 97 && temp <= 122) { // lowercase letter
				tempkey = key % 26;
				temp += tempkey;
				if (temp > 122) {
					temp = (temp - 122) + 97;
				}
			}
			output += (char) temp;
		}
		return output;
	}
	
	public static String decode(String input, int key) {
		String output = "";
		for (int i=0; i<input.length(); i++) {
			int temp = input.charAt(i);
			int tempkey;
			
			if (temp >= 48 && temp <= 57) { // number
				tempkey = key % 10;
				temp -= tempkey;
				if (temp < 48) {
					temp = (57 - (48 - temp));
				}
			} else if (temp >= 65 && temp <= 90) { // uppercase letter
				tempkey = key % 26;
				temp -= tempkey;
				if (temp < 65) {
					temp = (90 - (65 - temp));
				}
			} else if (temp >= 97 && temp <= 122) { // lowercase letter
				tempkey = key % 26;
				temp -= tempkey;
				if (temp < 97) {
					temp = (122 - (97 - temp));
				}
			}
			output += (char) temp;
		}
		return output;
	}
}
