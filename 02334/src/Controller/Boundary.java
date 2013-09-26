package Controller;



public class Boundary {



	// Check på password
	public static String passCheck(String pass) {
	
		if (pass.length() > 24) {
			return "text to long";

		} else if (pass != null && pass.length() > 3) {
			System.out.println("Før hash er pass: " + pass);
			int hash = Boundary.passHash(pass);
			System.out.println("Efter hash er pass blevet til: " + hash);
			return pass;
						
		}else return "password ikke godkendt";
		

	}

	// hash-creater. Creates hash of the given pass
	public static int passHash(String pass) {
		int hash = 7;
		for (int i = 0; i < pass.length(); i++) {
			hash = hash * 31 + pass.hashCode();
		
		}

		return hash;
	}
}