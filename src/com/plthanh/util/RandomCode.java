package com.plthanh.util;

import java.util.Random;

public class RandomCode {
	public static int getRandomCode() {
		Random generator = new Random();
		int code = 1000 + generator.nextInt(8999);
		return code;
	}
}
