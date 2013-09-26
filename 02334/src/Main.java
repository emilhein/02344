import controller.Boundary;
import database.Connector;

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
		Boundary boundary = new Boundary();
		for(int i=0;i<10;i++){
		System.out.println(Boundary.passHash(boundary.passCheck("hej")));
		}
		
		
		
	}

}