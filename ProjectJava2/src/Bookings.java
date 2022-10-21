import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Bookings{
	
	private String bookingCode;
	private String phone;
	private String name;
	private Long createdDateUnix;
	private String createdDate;
	private Long bookedCheckInUnix;
	private String bookedCheckIn;
	private Integer duration;
	private Long realcheckinUnix;
	private String realcheckin;
	private Long realcheckoutUnix;
	private String realcheckout;
	private Integer bookingStatus;
	private String status;
	private Integer roomno;
	
	public String formatDate(Long UnixTimeStamp) {
		SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date (UnixTimeStamp*1000L);
		String formatedDate = jdf.format(date);
		return formatedDate;
	}
	
	public Bookings(String bookingCode, String phone, String name, Long createdDateUnix, Long bookedCheckInUnix,
			Integer duration, Long realcheckinUnix, Long realcheckoutUnix, Integer bookingStatus, Integer roomno) {
		super();
		this.bookingCode = bookingCode;
		this.phone = phone;
		this.name = name;
		this.createdDateUnix = createdDateUnix;
		this.bookedCheckInUnix = bookedCheckInUnix;
		this.duration = duration;
		this.realcheckinUnix = realcheckinUnix;
		this.realcheckoutUnix = realcheckoutUnix;
		this.bookingStatus = bookingStatus;
		this.roomno = roomno;
		
		this.createdDate = formatDate(createdDateUnix);
		this.bookedCheckIn = formatDate(bookedCheckInUnix);
		this.realcheckin = formatDate(realcheckinUnix);
		this.realcheckout = formatDate(realcheckoutUnix);
		
		switch(bookingStatus) {
		case 1:
			this.status = "Checked In";
			break;
		case 2:
			this.status = "Checked Out";
			break;
		}
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreatedDateUnix() {
		return createdDateUnix;
	}

	public void setCreatedDateUnix(Long createdDateUnix) {
		this.createdDateUnix = createdDateUnix;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Long getBookedCheckInUnix() {
		return bookedCheckInUnix;
	}

	public void setBookedCheckInUnix(Long bookedCheckInUnix) {
		this.bookedCheckInUnix = bookedCheckInUnix;
	}

	public String getBookedCheckIn() {
		return bookedCheckIn;
	}

	public void setBookedCheckIn(String bookedCheckIn) {
		this.bookedCheckIn = bookedCheckIn;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getRealcheckinUnix() {
		return realcheckinUnix;
	}

	public void setRealcheckinUnix(Long realcheckinUnix) {
		this.realcheckinUnix = realcheckinUnix;
	}

	public String getRealcheckin() {
		return realcheckin;
	}

	public void setRealcheckin(String realcheckin) {
		this.realcheckin = realcheckin;
	}

	public Long getRealcheckoutUnix() {
		return realcheckoutUnix;
	}

	public void setRealcheckoutUnix(Long realcheckoutUnix) {
		this.realcheckoutUnix = realcheckoutUnix;
	}

	public String getRealcheckout() {
		return realcheckout;
	}

	public void setRealcheckout(String realcheckout) {
		this.realcheckout = realcheckout;
	}

	public Integer getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRoomno() {
		return roomno;
	}

	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}
	
	

}
