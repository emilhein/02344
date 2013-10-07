package database;

import java.security.MessageDigest;

public class Tools {
	
	// Functions

	public static byte[] hash(String password) {
		
		// TODO: Tilføj salt
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes("UTF-8"));
			return messageDigest.digest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void validateUserMail(String mail) throws Exception {

		if (!mail.matches("^[A-z0-9]+@[A-z0-9]+\\.[A-z0-9]+$")) {
			throw new IllegalArgumentException("Mail is invalid.");
		}
		
	}
	public static void validateUserName(String name) throws Exception {

		// MÅ IKKE TILLADE MAIL ADRESSER
		
		if (!name.matches("^[A-z0-9]{2,}$")) {
			throw new IllegalArgumentException("Name is invalid.");
		}
		
	}
	public static void validateUserPassword(String password) throws Exception {

		if (!password.matches("^.{8,}$")) {
			throw new IllegalArgumentException("Password is too short.");
		}
		
	}
	public static void validateUserType(int type) throws Exception {

		if (type != User.ADMINISTRATOR && type != User.MODERATOR && type != User.USER) {
			throw new IllegalArgumentException("Type is invalid.");
		}
		
	}
	
	public static void validateCategoryName(String name) throws Exception {

		if (!name.matches("^.+$")) {
			throw new IllegalArgumentException("Name is invalid.");
		}
		
	}
		
}
