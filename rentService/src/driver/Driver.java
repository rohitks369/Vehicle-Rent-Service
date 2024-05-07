package driver;

import java.util.List;

import service.RentService;

public class Driver {
	public static void main(String[] args) {
		RentService rentService=new RentService();
		
		addBranch(rentService);
		
		
		rentService.addVehicle("koramangala", "sedan",1);

	}

	private static void addBranch(RentService rentService) {
			List<String> koramangalaVehicles = List.of("1 suv for Rs.12 per hour", "3 sedan for Rs.10 per hour", "3 bikes for Rs.20 per hour");
		    List<String> jayanagarVehicles = List.of("3 sedan for Rs.11 per hour", "3 bikes for Rs.30 per hour", "4 hatchback for Rs.8 per hour");
		    List<String> malleshwaramVehicles = List.of("1 suv for Rs.11 per hour", "10 bikes for Rs.3 per hour", "3 sedan for Rs.10 per hour");

		    rentService.addBranch("Koramangala", koramangalaVehicles);
		    rentService.addBranch("Jayanagar", jayanagarVehicles);
		    rentService.addBranch("Malleshwaram", malleshwaramVehicles);
		    System.out.println("Addedd successfully");
	    }
		
	
}
	


