package model;

import java.time.LocalDateTime;

public class Booking {
	private String branchName;
    private String vehicleType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking(String branchName, String vehicleType, LocalDateTime startTime, LocalDateTime endTime) {
        this.branchName = branchName;
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
    
}
