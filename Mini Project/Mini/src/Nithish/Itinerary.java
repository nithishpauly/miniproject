package Nithish;

import java.sql.Date;

public class Itinerary {
    private int id;
    private int userId;
    private int destinationId;
    private Date startDate;
    private Date endDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Itinerary(int id, int userId, int destinationId, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.destinationId = destinationId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Itinerary() {
		super();
		
	}

    
}
