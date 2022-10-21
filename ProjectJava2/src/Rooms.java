
public class Rooms {
	private Integer roomTypeCode;
	private String roomType;
	private Integer roomNo;
	private Integer roomStatusCode;
	private String roomStatus;
	private Integer roomViewCode;
	private String roomView;
	
	public Rooms(Integer roomTypeCode, Integer roomNo, Integer roomStatusCode, Integer roomViewCode) {
		super();
		
		this.roomTypeCode = roomTypeCode;
		switch(roomTypeCode) {
		case 0:
			this.roomType = "Standard";
			break;
		case 1:
			this.roomType = "Deluxe";
			break;
		case 2:
			this.roomType = "Suite";
			break;
		}
		
		this.roomNo = roomNo;
		
		this.roomStatusCode = roomStatusCode;
		switch(roomStatusCode) {
		case 0:
			this.roomStatus = "Vacant";
			break;
		case 1:
			this.roomStatus = "Occupied";
			break;
		case 2:
			this.roomStatus = "Cleaning";
		}
		
		this.roomViewCode = roomViewCode;
		switch(roomViewCode) {
		case 0:
			this.roomView = "Street";
			break;
		case 1:
			this.roomView = "Mountain";
			break;
		case 2:
			this.roomView = "Sea";
			break;
		}
	}

	public Integer getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(Integer roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getRoomStatusCode() {
		return roomStatusCode;
	}

	public void setRoomStatusCode(Integer roomStatusCode) {
		this.roomStatusCode = roomStatusCode;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public Integer getRoomViewCode() {
		return roomViewCode;
	}

	public void setRoomViewCode(Integer roomViewCode) {
		this.roomViewCode = roomViewCode;
	}

	public String getRoomView() {
		return roomView;
	}

	public void setRoomView(String roomView) {
		this.roomView = roomView;
	}
	
	
}
