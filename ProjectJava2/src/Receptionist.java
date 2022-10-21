import java.text.SimpleDateFormat;
import java.util.Date;

public class Receptionist {

	private String username;
	private String password;
	private String name;
	private Float rating;
	private Integer money;
	private Integer receptionistStatus;
	private String status;
	private Long unixTimeStamp;
	private Date date;
	SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String formatedDate;
	public Receptionist(String username, String password, String name, Float rating, Integer money, Integer receptionistStatus, Long unixTimeStamp) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.rating = rating;
		this.money = money;
		this.receptionistStatus = receptionistStatus;
		this.status = (receptionistStatus == 1) ? "ONLINE" : "OFFLINE";
		this.unixTimeStamp = unixTimeStamp;
		date = new Date(unixTimeStamp*1000L);
		formatedDate = jdf.format(date);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getReceptionistStatus() {
		return receptionistStatus;
	}
	public void setReceptionistStatus(Integer receptionistStatus) {
		this.receptionistStatus = receptionistStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUnixTimeStamp() {
		return unixTimeStamp;
	}
	public void setUnixTimeStamp(Long unixTimeStamp) {
		this.unixTimeStamp = unixTimeStamp;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SimpleDateFormat getJdf() {
		return jdf;
	}
	public void setJdf(SimpleDateFormat jdf) {
		this.jdf = jdf;
	}
	public String getFormatedDate() {
		return formatedDate;
	}
	public void setFormatedDate(String formatedDate) {
		this.formatedDate = formatedDate;
	}
	
	
}
