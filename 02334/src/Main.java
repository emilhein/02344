import database.Connector;
import Controller.Boundary;

public class Main {

	public static void main(String[] args) {	
		
		try {
			Connector connector = new Connector("sql-lab1.cc.dtu.dk", 3306, "s123115", "s123115", "F5iCtVPs4rtHu4oM");
			connector.reset();
			connector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//tester her
		
		String passOk = Boundary.passCheck("hejsa1222");
		System.out.println(passOk);
		
		
		
		
		
	}

}