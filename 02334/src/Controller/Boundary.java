package Controller;

import java.util.Scanner;

public class Boundary {

	private Scanner scanner;

	public String readString() {
		scanner = new Scanner(System.in);
		System.out.println("Skriv din text her:");
		// Read next line.
		String line = scanner.nextLine();

		// Check the line contains at least one character and is below 200.
		if (line.length() > 1000) {
			return "text to long";
		} else if (line != null && line.length() > 0) {
			return line;
		}

		return line;

	}

}