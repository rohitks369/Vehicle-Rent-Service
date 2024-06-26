package driver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import service.RentService;

public class Driver {
	public static void main(String[] args) {
		RentService rentService = new RentService();

		addBranch(rentService);

		rentService.addVehicle("koramangala", "sedan", 1);

		// Renting vehicles for the specified time slots
		String rent1 = rentService.rentVehicle("suv", LocalDateTime.of(2024, 2, 20, 10, 0),
				LocalDateTime.of(2024, 2, 20, 12, 0)); 
		if(rent1!=null)
			System.out.println(rent1);// Should book from Malleshwaram
		else {
			System.out.println("No Vehicle");
		}
		String rent2 =rentService.rentVehicle("suv", LocalDateTime.of(2024, 2, 20, 10, 0), LocalDateTime.of(2024, 2, 20, 12, 0)); // Should
		System.out.println(rent2);																									// book
		// from
		// Koramangala
		String rent3 =rentService.rentVehicle("suv", LocalDateTime.of(2024, 2, 20, 10, 0), LocalDateTime.of(2024, 2, 20, 12, 0)); // Should
		if(rent3!=null)
			System.out.println(rent3);// Should no vehicle
		else {
			System.out.println("No Vehicle");
		}	
		
		
		LocalDateTime startTime = LocalDateTime.of(2024, 2, 20, 11, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 2, 20, 12, 0);

        // Print system view for the given time slot
        System.out.println("Printing system view for the time slot: " +
                formatTime(startTime) + " to " + formatTime(endTime));
        rentService.printSystemViewForTimeSlot(startTime, endTime);

	}

	private static void addBranch(RentService rentService) {
		List<String> koramangalaVehicles = List.of("1 suv for Rs.12 per hour", "3 sedan for Rs.10 per hour",
				"3 bikes for Rs.20 per hour");
		List<String> jayanagarVehicles = List.of("3 sedan for Rs.11 per hour", "3 bikes for Rs.30 per hour",
				"4 hatchback for Rs.8 per hour");
		List<String> malleshwaramVehicles = List.of("1 suv for Rs.11 per hour", "10 bikes for Rs.3 per hour",
				"3 sedan for Rs.10 per hour");

		rentService.addBranch("Koramangala", koramangalaVehicles);
		rentService.addBranch("Jayanagar", jayanagarVehicles);
		rentService.addBranch("Malleshwaram", malleshwaramVehicles);
		System.out.println("Addedd successfully");
	}
	
	private static String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("d MMM uuuu h:mm a"));
    }

}
