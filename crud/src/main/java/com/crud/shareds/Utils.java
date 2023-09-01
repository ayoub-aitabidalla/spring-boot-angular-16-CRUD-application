package com.crud.shareds;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component 
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String Alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYYZabcdefijklmnopqrstuvwxyz";
	public String generateStringId(int length)
	{
		StringBuilder returnValue = new StringBuilder(length);
		for(int i = 0; i<length; i++)
		{
			returnValue.append(Alphabet.charAt(RANDOM.nextInt(Alphabet.length())));
		}
		return new String (returnValue);
	}
}
