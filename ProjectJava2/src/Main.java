import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Main {
	Scanner scan = new Scanner(System.in);
	Long timeStamp;
	Date currentDate;
	SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String currentFormatedDate;
	ArrayList<String> names = new ArrayList<>();
	ArrayList<Receptionist> listOfRecep = new ArrayList<Receptionist>();
	ArrayList<Rooms> listOfRooms = new ArrayList<>();
	ArrayList<Bookings> listOfBookings = new ArrayList<>();
	
	void clearScreen() {
		for (int i = 0;i < 50;i++) System.out.println();
	}
	
	void currentInGameTime() {
		try {
			Scanner timeFile = new Scanner(new File("time.csv"));
			timeStamp = timeFile.nextLong();
			currentDate = new Date(timeStamp*1000L);
			currentFormatedDate = jdf.format(currentDate);
			timeFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error! time.csv File Not Found");
		}
	}
	
	String getFormatedDate(Long timeStamp) {
		SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date (timeStamp*1000L);
		String formatedDate = jdf.format(date);
		return formatedDate;
	}
	
	void addNamesData() {
		try {
			Scanner namesFile = new Scanner(new File("names.csv"));
			while(namesFile.hasNextLine()){
				String line = namesFile.nextLine();
				names.add(line);
			}
			namesFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error! names.csv File Not Found");
		}
	}
	
	void addReceptionistsData(){
		String username;
		String password;
		String name;
		Float rating;
		Integer money;
		Integer receptionistStatus;
		Long unixTimeStamp;
		try {
			Scanner recepFile = new Scanner(new File("receptionists.csv"));
			while(recepFile.hasNextLine()){
				String line = recepFile.nextLine();
				String[] temp = line.split(";");
				username = temp[0];
				password = temp[1];
				name = temp[2];
				rating = Float.parseFloat(temp[3]);
				money = Integer.parseInt(temp[4]);
				receptionistStatus = Integer.parseInt(temp[5]);
				unixTimeStamp = Long.parseLong(temp[6]);
				listOfRecep.add(new Receptionist(username, password, name, rating, money, receptionistStatus, unixTimeStamp));
			}
			recepFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error! receptionists.csv File Not Found");
		}
	}
	
	void addRoomsData() {
		Integer roomTypeCode;
		Integer roomNo;
		Integer roomStatusCode;
		Integer roomViewCode;
		try {
			Scanner roomsFile = new Scanner(new File("rooms.csv"));
			while(roomsFile.hasNextLine()){
				String line = roomsFile.nextLine();
				String[] temp = line.split(";");
				roomTypeCode = Integer.parseInt(temp[0]);
				roomNo = Integer.parseInt(temp[1]);
				roomStatusCode = Integer.parseInt(temp[2]);
				roomViewCode = Integer.parseInt(temp[3]);
				listOfRooms.add(new Rooms(roomTypeCode, roomNo, roomStatusCode, roomViewCode));
			}
			roomsFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error! rooms.csv File Not Found");
		}
	}
	
	void addBookingsData() {
		String bookingCode;
		String phone;
		String name;
		Long createdDateUnix;
		Long bookedCheckInUnix;
		Integer duration;
		Long realcheckinUnix;
		Long realcheckoutUnix;
		Integer bookingStatus;
		Integer roomno;
		try {
			Scanner bookingsFile = new Scanner(new File("bookings.csv"));
			while(bookingsFile.hasNextLine()){
				String line = bookingsFile.nextLine();
				String[] temp = line.split(";");
				bookingCode = temp[0];
				phone = temp[1];
				name = temp[2];
				createdDateUnix = Long.parseLong(temp[3]);
				bookedCheckInUnix = Long.parseLong(temp[4]);
				duration = Integer.parseInt(temp[5]);
				realcheckinUnix = Long.parseLong(temp[6]);
				realcheckoutUnix = Long.parseLong(temp[7]);
				bookingStatus = Integer.parseInt(temp[8]);
				roomno = Integer.parseInt(temp[9]);
				listOfBookings.add(new Bookings(bookingCode, phone, name, createdDateUnix, bookedCheckInUnix, duration, realcheckinUnix, realcheckoutUnix, 
												bookingStatus, roomno));
			}
			bookingsFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("bookings.csv File Not Found");
		}
	}
	
	void printRecepData() {
		for (Receptionist r:listOfRecep) {
			System.out.println(r.getName() + " \t| " + r.getFormatedDate() + "\t| " + r.getStatus() + "\t| " + r.getRating());
		}
	}
	
	void printRoomsData() {
		System.out.println("No - Room Type - Room Status - Room View");
		for (Rooms ro:listOfRooms) {
			System.out.println(ro.getRoomNo() + " - " + ro.getRoomType() + " - " + ro.getRoomStatus() + " - " + ro.getRoomView());
		}
	}
	
	void printBookingsData() {
		for (Bookings b:listOfBookings) {
			System.out.println(b.getBookingCode() + " " + b.getCreatedDate() + " " + b.getStatus());
		}
	}
	
	Integer loginCredential(String username, String password) {
		for (int i = 0;i < listOfRecep.size();i++) {
			Receptionist curr = listOfRecep.get(i);
			if (curr.getUsername().equals(username) && curr.getPassword().equals(password)) {
				return i;
			}
		}
		return -1;
	}
	
	boolean validateUsernameRegis(String username) {
		for (int i = 0;i < listOfRecep.size();i++) {
			Receptionist curr = listOfRecep.get(i);
			if (curr.getUsername().equals(username)) {
				System.out.println("Username must be unique!");
				return false;
			}
		}
		
		boolean numFlag = false;
		for (int i = 0;i < username.length();i++) {
			if (username.charAt(i) >= '1' && username.charAt(i) <= '9') {
				numFlag = true;
				break;
			}
		}
		if (!numFlag) System.out.println("Username must include at lease one number!");
		if (username.length() < 6) {
			System.out.println("Username must be 6 characters or more!");
		}
		if (numFlag && username.length() >= 6) {
			return true;
		}else return false;
	}
	
	boolean validatePasswordRegis(String password) {
		boolean numFlag = false;
		boolean capFlag = false;
		for (int i = 0;i < password.length();i++) {
			char ch = password.charAt(i);
			if (ch >= '1' && ch <= '9') {
				numFlag = true;
			}
			
			if (ch >= 'A' && ch <= 'Z') {
				capFlag = true;
			}
			if (numFlag && capFlag) break;
		}
		if (!numFlag) {
			System.out.println("Password must include at least one number!");
		}
		if (!capFlag) {
			System.out.println("Password must include at least one capital letter!");
		}
		if (password.length() < 8) {
			System.out.println("Password must be 8 characters or more!");
		}
		if (numFlag && capFlag && password.length() >= 8) {
			return true;
		}
		else return false;
	}
	
	
	void mainMenu() {
		int option;
		while(true) {
			do {
				clearScreen();
				System.out.println("--------------");
				System.out.println("ABORIGIN HOTEL");
				System.out.println("--------------");
				System.out.println("Authentication");
				System.out.println("1. Log In");
				System.out.println("2. Register");
				System.out.println("[0] Exit");
				System.out.print(" >> ");
				option = scan.nextInt();
				scan.nextLine();
			}while(option < 0 || option > 2);
			while(true) {
				if (option == 0) {
					clearScreen();
					System.out.println("Through Hardwork And Dedication We Hold Our Future In Our Hands");
					System.out.println("\t\t\t22-1");
					scan.nextLine();
					System.exit(0);
				}
				else if (option == 1) {
					String username;
					String password = null;
					boolean goBack = false;
					do {
						clearScreen();
						System.out.println("RECEPTIONIST LOGIN");
						System.out.println("[0] to go back");
						System.out.println("------------------");
						System.out.print("USERNAME\t: ");
						username = scan.nextLine();
						if (username.equals("0")) {
							goBack = true; 
							break;
						}
						System.out.print("PASSWORD\t: ");
						password = scan.nextLine();		
						if (password.equals("0")) {
							goBack = true; 
							break;
						}
						if (loginCredential(username, password) == -1) {
							System.out.println("Wrong username or password! Press Enter!");
							scan.nextLine();
						}
						else {
							System.out.println("Welcome, " + username + "!");
							scan.nextLine();
						}
					}while(loginCredential(username, password) == -1);
					if (goBack) break;
					int index = loginCredential(username, password);
					Receptionist update = listOfRecep.get(index);
					update.setReceptionistStatus(1);
					receptionistMenu(index);
				}
				else if (option == 2) {
					String name;
					clearScreen();
					System.out.println("New Aborigin Hotel Receptionist Registration");
					System.out.println("[0] to go back");
					System.out.println("-------------------");
					do {
						System.out.print("Enter your name\t\t\t: ");
						name = scan.nextLine();
						if (name.equals("0")) break;
						if (name.length() < 5) {
							System.out.println("Name must be 5 characters or more");
						}
						else if(!name.contains(" ")) {
							System.out.println("Name must contain at least one space character");
						}
					}while(name.length() < 5 || !name.contains(" "));
					if (name.equals("0")) break;
					
					String username;
					do {
						System.out.print("Enter username\t\t\t: ");
						username = scan.nextLine();
						if (username.equals("0")) break;
					}while(!validateUsernameRegis(username));
					if (username.equals("0")) break;
					
					String password;
					do {
						System.out.print("Enter password\t\t\t: ");
						password = scan.nextLine();
						if (password.equals("0")) break;
					}while(!validatePasswordRegis(password));
					if (password.equals("0")) break;
					
					Integer money = 0;
					Float rating = 5.0F;
					Integer receptionistStatus = 1;
					Long unixTimeStamp = timeStamp;
					listOfRecep.add(new Receptionist(username, password, name, rating, money, receptionistStatus, unixTimeStamp));
					String fileName = "receptionists.csv";
					String writeText = username + ";" + password + ";" + name + ";" + rating + ";" + money + ";" + receptionistStatus + ";" + unixTimeStamp + "\n";
//					System.out.println(writeText);
					try {
						// Write text to file on appending mode.
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
						writer.write(writeText);
						writer.close();
					} catch (IOException e) {
						System.out.println("Error! Write failed!");
					}
					
					System.out.println("Receptionist Registered!");
					scan.nextLine();
					
					int index = listOfRecep.size() - 1;
					receptionistMenu(index);
				}
			}
		}
	}
	
	
	void receptionistMenu(int index) {
		Receptionist r = listOfRecep.get(index);
		currentInGameTime();
		MyThread myThread = new MyThread();
		myThread.start();
		int option;
		do {
			clearScreen();
			System.out.println("ABORIGIN HOTEL");
			System.out.println("");
			System.out.println("Name\t: " + r.getName());
			System.out.println("Rating\t: " + r.getRating());
			System.out.println("Money\t: " + r.getMoney());
			System.out.println("Last Login : " + r.getFormatedDate());
			System.out.println("1. Start Working");
			System.out.println("2. See colleagues");
			System.out.println("[0] Log Out");
			System.out.println(" >> ");
			option = scan.nextInt();
			scan.nextLine();			
		}while(option < 0 || option > 2);
		if (option == 0) {
			myThread.interrupt();
			mainMenu();
		}
		else if (option == 1) {
			int recepInput = -1;
			do {
				clearScreen();
//				System.out.println(getFormatedDate(timeStamp));
				System.out.printf("ABORIGIN HOTEL\t\tNAME\t\t:%s\t\tNOTIFICATIONS\n", r.getName());
				System.out.printf("1. Open Queue\t\tRATING\t\t:%.2f", r.getRating());
				if (bookingCount >= 1) {
					System.out.print("\t\t\tA new booking is received!\n");
				}else System.out.print("\n");
				
				System.out.printf("2. Check In Guest\tMONEY\t\t:%d", r.getMoney());
				if (bookingCount >= 2) {
					System.out.print("\t\t\tA new booking is received!\n");
				}else System.out.print("\n");
				
				System.out.printf("3. Check Out Guest\tLAST LOGIN\t:%s", r.getFormatedDate());
				if (bookingCount >= 3) {
					System.out.print("\tA new booking is received!\n");
				}else System.out.print("\n");
				
				System.out.print("4. See Rooms\t\t--------------------------------------");
				if (bookingCount >= 4) {
					System.out.print("\tA new booking is received!\n");
				}else System.out.print("\n");
				
				System.out.print("[0] Stop Working\t--------------------------------------");
				if (bookingCount >= 5) {
					System.out.print("\tA new booking is received!\n");
				}else System.out.print("\n");
				
				System.out.println("-------------------------------------------------------------------------------------------------------");
				System.out.print(" >> ");
				
				// Cuma bisa refresh setiap 3 detik
				// Bila saya ganti jadi if (refresh) malah jadi loop terus-terusan
				// Jadi program akan refresh setiap 3 detik dimana notifikasi dapat masuk.
				long sTime = System.currentTimeMillis();
				// 3000 millisecond = 3 second
				while (System.currentTimeMillis() - sTime < 3000)
				{
				    try {
						if (System.in.available() > 0)
						{
							recepInput = scan.nextInt();
							scan.nextLine();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				refresh = false;
			}while(recepInput < 0 || recepInput > 4);
		}
	}
	Integer bookingCount = 0;
	boolean refresh = false;
	// 15 minute in game time and 3 second in real life.
	private class MyThread extends Thread{

		@Override
		public void run() {
			while(true) {
				// add 15 minutes to time stamp
				timeStamp += 15 * 60;
				writeTimeFile(timeStamp);
				int randChance = getRandomNumber(1, 100);
				
				// 3% chance (3/100)
				if (randChance == 33 || randChance == 66 || randChance == 99) {
					String bookingCode;
					String name;
					String phone;
					Integer duration;
					// set created date, booked check in, and real check in to in game time stamp.
					Long createdDateUnix = timeStamp;
					Long bookedCheckInUnix = timeStamp;
					Long realcheckinUnix = timeStamp;
					
					// booking status is 0 because they were not checked in yet.
					Integer bookingStatus = 0;
					Long realcheckoutUnix = 0L;
					Rooms room = null;
					
					UUID uuid = UUID.randomUUID();
					bookingCode = uuid.toString(); 
					
					int randIndex = getRandomNumber(0, names.size());
					name = names.get(randIndex);
					phone = getPhoneString();
					duration = getRandomNumber(0, 10);
					
					int randRoom;
					do {
						randRoom = getRandomNumber(0, listOfRooms.size());
					}while(listOfRooms.get(randRoom).getRoomStatusCode() != 0);
						
					// re-check if random room is vacant, then that will be the room.
					if (listOfRooms.get(randRoom).getRoomStatusCode() == 0) {
						room = listOfRooms.get(randRoom);
					}
					listOfBookings.add(new Bookings(bookingCode, phone, name, createdDateUnix, bookedCheckInUnix, duration, realcheckinUnix, realcheckoutUnix, bookingStatus, room.getRoomNo()));
					bookingCount++;
					refresh = true;
				}
				
				// remove booking if date is passed.
				for (int i = 0;i < listOfBookings.size();i++) {
					Bookings b = listOfBookings.get(i);
					Date bookedDate = new Date(b.getBookedCheckInUnix()*1000L);
					Calendar cal = Calendar.getInstance();
					cal.setTime(bookedDate);
					int checkInDate = cal.get(Calendar.DAY_OF_MONTH);
					
					Calendar inGameCal = Calendar.getInstance();
					inGameCal.setTime(currentDate);
					int inGameDate = cal.get(Calendar.DAY_OF_MONTH);
					
					if (checkInDate > inGameDate) {
						listOfBookings.remove(i);
						bookingCount--;
					}
				}
				
				// 5% chance
				if (randChance == 20 || randChance == 40 || randChance == 60 || randChance == 80 || randChance == 100) {
					if (listOfBookings.size() > 0) {
						int roomRandIndex = getRandomNumber(0, listOfBookings.size());
						Bookings b = listOfBookings.get(roomRandIndex);
						Rooms r;
						// to get the room type.
						for (int i = 0;i < listOfRooms.size();i++) {
							r = listOfRooms.get(i);
							if (r.getRoomNo() == b.getRoomno()) {
								// add duration if room type is suite.
								if (r.getRoomType().equals("Suite")) {
									int randAddDuration = getRandomNumber(1, 3);
									b.setDuration(b.getDuration() + randAddDuration);
								}
								break;
							}
						}
						
					}
				}
				try {
					// run the loop for every 3000 millisecond (3 second)
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	String getPhoneString() {
		String CHARS = "0123456789";
		StringBuilder salt = new StringBuilder();
		Random rand = new Random();
		while(salt.length() < 10) {
			int index = (int) (rand.nextFloat() * CHARS.length());
			salt.append(CHARS.charAt(index));
		}
		String phoneString = salt.toString();
		return phoneString;
	}
	
	void writeTimeFile(Long timeStamp) {
		try {
			FileWriter writer = new FileWriter("time.csv");
			String writeText = String.valueOf(timeStamp);
			writer.write(writeText);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public Main() {
		addNamesData();
		addReceptionistsData();
		addRoomsData();
		addBookingsData();
//		printRecepData();
//		printRoomsData();
//		printBookingsData();
		mainMenu();
	}

	public static void main(String[] args){
		new Main();
	}

}
