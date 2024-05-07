package service;

import java.util.List;

import model.Booking;
import model.Branch;

import java.time.LocalDateTime;
import java.util.*;
import repository.BranchRepository;

public class RentService {
	private BranchRepository branchRepository;
	
	public void addBranch(String branchName, List<String> vehicleDetails) {
		Map<String, Integer> vehicles = new HashMap<>();
	    Map<String, Integer> prices = new HashMap<>();
	    Branch branch=new Branch(branchName);
		for (String detail : vehicleDetails) {
			String[] parts = detail.split(" ");
			int quantity = Integer.parseInt(parts[0]);
			String type = parts[1];
			double pricePerHour = Double.parseDouble(parts[3].substring(3));
			branch.addVehicle(type, quantity, pricePerHour);
			
		}
		branchRepository.addBranch(branch);
	}
	public  void addVehicle(String branchName, String type, int quantity) {
        Branch branch = BranchRepository.getBranch(branchName);
        if (branch != null) {
            Map<String, Integer> vehicles = branch.getVehicles();
            int currentQuantity = vehicles.getOrDefault(type, 0);
            vehicles.put(type, currentQuantity + quantity);
        }
    }
	
	public  String rentVehicle(String type, LocalDateTime startTime, LocalDateTime endTime) {
        List<Branch> branches = BranchRepository.getAllBranches();
        branches.sort((b1, b2) -> {
            double price1 = b1.getPrices().getOrDefault(type, Double.MAX_VALUE);
            double price2 = b2.getPrices().getOrDefault(type, Double.MAX_VALUE);
            return Double.compare(price1, price2);
        });

        for (Branch branch : branches) {
            if (branch.getVehicles().getOrDefault(type, 0) > 0) {
            	if (!isOverlapping(branch, type, startTime, endTime)) {
                    branch.getVehicles().put(type, branch.getVehicles().get(type) - 1);
                    Booking booking = new Booking(branch.getName(), type, startTime, endTime);
                    branch.addBooking(booking); // Add booking to the branch's bookings list
                    
                    return "Book from "+branch.getName();
                }
            }
        }
        return null; // No vehicle available or overlapping booking
    }

    private static boolean isOverlapping(Branch branch, String type, LocalDateTime startTime, LocalDateTime endTime) {
        for (Booking booking : branch.getBookings()) {
            if (booking.getVehicleType().equals(type)) {
                LocalDateTime bookingStartTime = booking.getStartTime();
                LocalDateTime bookingEndTime = booking.getEndTime();
                if (startTime.isBefore(bookingEndTime) && endTime.isAfter(bookingStartTime)) {
                    return true; // Overlapping booking found
                }
            }
        }
        return false; // No overlapping booking found
    }
	 
    public static void printSystemViewForTimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
        List<Branch> branches = BranchRepository.getAllBranches();

        // Print the header
        System.out.println("Output:");

        // Iterate over each branch
        for (Branch branch : branches) {
            System.out.println("■ '" + branch.getName() + "':");

            // Check each vehicle type
            for (String type : branch.getVehicles().keySet()) {
                boolean isBooked = false;
                for (Booking booking : branch.getBookings()) {
                    if (booking.getVehicleType().equals(type) && booking.getStartTime().isBefore(endTime) && booking.getEndTime().isAfter(startTime)) {
                        isBooked = true;
                        break;
                    }
                }
                if (isBooked) {
                    System.out.println("\t● All \"" + type + "\" are booked.");
                } else {
                    double price = branch.getPrices().getOrDefault(type, 0.0);
                    System.out.println("\t● \"" + type + "\" is available for Rs." + price);
                }
            }
        }
    }
	
}
