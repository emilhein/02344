package controller;

import java.util.Scanner;

public class Boundary {

	private Scanner scanner;

	// Check på password
	public String passCheck(String pass) {
		scanner = new Scanner(System.in);
		// Read next line.
		String line = scanner.nextLine();

		// Check the line contains at least one character and is below 200.
		if (line.length() > 24) {
			return "text to long";
		} else if (line != null && line.length() > 0) {
			return line;
		}

		return line;

	}
//hash-creater. Creates hash of the given pass
	public static int passHash(String pass) {
		int hash = 7;
		for (int i = 0; i < pass.length(); i++) {
			hash = hash * 31 + pass.hashCode();
		}

		return hash;
	}
}