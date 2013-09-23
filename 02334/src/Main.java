import database.Connector;

public class Main {

	public static void main(String[] args) {	
		
		try {
			Connector connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			connector.reset();
			//connector.Close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Hej");
		
	}

}