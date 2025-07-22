import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class Address_Book extends Person {
	
	/*
	 * Warnings
	 * File currently exists.
	 * Nothing in file
	 * String Comparisons
	 * 
	 * */
	
	private static final long serialVersionUID = 1L;
	public String address_book_name;
	
	static ArrayList<String> file_Names = new ArrayList<String>(10);
	
	int fname = 1;
	
	int saved = 0;
	
	String filename = null;
	int startup = 1;
	
	boolean exit = false;
	
	public void make_Address_Book_Name()
	{
		System.out.println("You can make " + (10 - fname) + " more address book names.");
		boolean once = false;
		System.out.println("Enter the name of your new Address Book?");
		String fName = input.next();/////////Sys IN
		if(fname == 1){
			file_Names.add(fName);
			filename = fName + ".txt";
			try (FileWriter new_file = new FileWriter(filename);){
				System.out.println("File " + filename.toString() + " has been created.");
			} catch (IOException e){
				e.printStackTrace();
			}
			fname++;
		}
		else
		{
			for(String names : file_Names){
				System.out.println(names);
				if(fName.equalsIgnoreCase(names) == true)
				{
					once = false;
					break;
				}
				else{
					once = true;
				}
			}
			if( fname < 10 && once == true ){
				file_Names.add(fName);
				filename = fName + ".txt";
				try (FileWriter new_file = new FileWriter(filename);){
					System.out.println("File " + filename.toString() + " has been created.");
				} catch (IOException e) {
					e.printStackTrace();
				}
				fname++;
			}else{
				System.out.println("The Book Name Might Have Already Been Used Try Again");
				this.make_Address_Book_Name();
			}
		}
	}
	
	public void display_Address_Book_Names(){
		System.out.println("Address Book Names Created Are.");
		for(String names : file_Names ){
			System.out.println( "----> " + names);
		}
	}
	
	public void open_Address_Book(){
		this.display_Address_Book_Names();
		String p = null;
		boolean done = false;
		do{
			try{
				System.out.println("List a name of a file you want to open");
				p = input.next();/////////Sys IN
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		String filename = null;
		boolean file_found = false;
		for(String DST : file_Names){
			if( p.equalsIgnoreCase(DST) )	//////////////////////String Comparison
			{
				filename = DST + ".txt";
				file_found = true;
			}
		}
		if(file_found != true){
			System.out.println("Name not added to list of names to create file.");
			System.out.println("Call make address book names");
		}
		else{
			try( BufferedReader sd = new BufferedReader(new FileReader(filename));){
				String line = null;
				while((line = sd.readLine())!= null)
				{
					String [] split = line.split(",");
					{
						Person dope = new Person();
						dope.firstName = split[0];
						dope.lastName = split[1];
						dope.address = split[2];
						dope.city = split[3];
						dope.state = split[4];
						int number = new Integer(split[5]).intValue();
						dope.zip = number;
						dope.phoneNumber = split[6];
						contacts.add(dope);
					}		
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void save_Address_Book(){
		this.display_Address_Book_Names();
		String A = null;
		boolean done = false;
		do{
			try{
				System.out.println("Enter file to Save Contacts Too.");
				A = input.next();/////////Sys IN
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		String filename = null;
		boolean name_found = false;
		for(String f : file_Names){
			if(A.equals(f) && name_found == false){
				filename = A + ".txt";
				name_found = true;
			}
		}
		if(name_found != true){
			System.out.println("Name not added to list of names to create file.");
			System.out.println("Call make address book names");
		}
		else{
			try(BufferedWriter sd = new BufferedWriter(new FileWriter(filename));){
				for(Person people : contacts){
					String zip_String = String.valueOf(people.zip);
					sd.write(people.firstName);
					sd.write(",");
					sd.write(people.lastName);
					sd.write(",");
					sd.write(people.address);
					sd.write(",");
					sd.write(people.city);
					sd.write(",");
					sd.write(people.state);
					sd.write(",");
					sd.write(zip_String);
					sd.write(",");
					sd.write(people.phoneNumber);
					sd.newLine();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			System.out.println("Address Book " + filename.toString() + " created and closed.");
			
			
			contacts.clear();///////////Stuuff
			
			System.out.println("Address Book emptied");
		}
	}
	
	
	
	public void exit_Address_Book(){
		System.out.println("Thanks For Using My Application ;-)");
	}
	
	public void menu_Address_Book(){
		if(startup == 1){
			startup++;
			System.out.println("Welcome To Multi-Address Book Application");
			this.make_Address_Book_Name();
			this.menu_Person();
		}
		else
		{
			while(exit == false)
			{
				System.out.println("You can move throughtout the app using numbers to operate the Menu");
				System.out.println("Corresponding numbers are:");
				System.out.println("1----> Creates New Name For Saving Contacts To Address Book");
				System.out.println("2----> Open New or Existing Address Book");
				System.out.println("3----> Save Address Book for Later Use");
				System.out.println("4----> Enter Menu To Manipulate Contacts");
				System.out.println("5----> Exit Application");
				int number = input.nextInt();/////////Sys IN
				switch(number)
				{
					case 1:
						this.make_Address_Book_Name();
						break;
					case 2:
						if(saved >= 1){
							this.open_Address_Book();
							break;
						}else{
							System.out.println();
							this.menu_Address_Book();
						}
					case 3:
						this.save_Address_Book();
						saved++;
						break;
					case 4:
						this.menu_Person();
						break;
					case 5:
						exit = true;
						break;
				}
			}
		}
	}
	
	public void menu_Person(){
		while(exit == false)
		{
			System.out.println("You can manipulate the Contacts Menu using numbers");
			System.out.println("Corresponding numbers are:");
			System.out.println("1----> Add new contact to list");
			System.out.println("2----> Editing a contact in the list");
			System.out.println("3----> Deleting a contact from the list");
			System.out.println("4----> Sort the list of contacts");
			System.out.println("5----> Display contacts from the list in Mailing Label format");
			System.out.println("6----> Return Address Book Menu");
			int number = input.nextInt();/////////Sys IN
			switch(number)
			{
			case 1:
				this.user_Input_Contact();
				break;
			case 2:
				this.Edit_Info();
				break;
			case 3:
				this.delete_Contact();
				break;
			case 4:
				this.sort_Contacts();
				break;
			case 5:
				this.display_Mailing_Label();
				break;
			case 6:
				this.menu_Address_Book();
				break;
			}
		}
		this.exit_Address_Book();
	}
	
public static void main(String[] args) {
	
		Address_Book p = new Address_Book();
	
		p.menu_Address_Book();
		
		input.close();
	}
}
