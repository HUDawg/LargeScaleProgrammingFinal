import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String state;
	public int zip;
	public String phoneNumber;
	
	ArrayList<Person> contacts = new ArrayList<Person>();

	static Scanner input = new Scanner(System.in);
	
	public void display_Mailing_Label(){
		for(Person people : contacts){
			System.out.println(people.firstName + " " + people.lastName);
			System.out.println(people.address);
			System.out.println(people.city + ", " + people.state + " " + people.zip);
		}
	}

	public void sort_Contacts()
	{
		String sort_by = null;
		
		boolean done = false;
		do{
			try{
				System.out.println("Would you like to sort contacts by last name or by zip code");
				System.out.println("Enter 'LN' for last name or 'ZC' for zip code");
				System.out.println("Please Dont Use Any White Spaces");
				sort_by = input.next();
				input.close();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		if(sort_by.equalsIgnoreCase("ZC")){//////////////////////String Comparison
			for(Person person_zip : contacts){					
				for(Person people_zip : contacts){
					int person_index = contacts.indexOf(person_zip);
					int people_index = contacts.indexOf(people_zip);
					if(person_zip.zip > people_zip.zip && person_index != people_index)
					{
						Person temper = person_zip;
						contacts.set(person_index,people_zip);
						contacts.set(people_index,temper);
					}
				}
			}
			for(Person people : contacts){
				System.out.println(people.firstName);
			}
		}else if (sort_by.equalsIgnoreCase("LN")){//////////////////////String Comparison
			boolean one = true;
			for(Person person : contacts){
				for(Person people : contacts){
					String new_person = person.lastName;
					String new_people = people.lastName;
					int person_index = contacts.indexOf(person);
					int people_index = contacts.indexOf(people);
					if(person_index != people_index){
						System.out.println( person_index + " " + people_index );
						char [] person_Array = new_person.toCharArray();
						char [] people_Array = new_people.toCharArray();
						int size_used = person_Array.length > people_Array.length ? person_Array.length : people_Array.length;
						for(int j = 0 ; j < size_used ; j++){
							if(person_Array[j] > people_Array[j] && one == true){
								Person tmp = person;
								contacts.set(person_index,people);
								contacts.set(people_index,tmp);
								one = false;
							}else if(one == false){
								break;
							}
						}	
					}
				}
			}
			for(Person people : contacts){
				System.out.println(people.firstName);
			}
		}else{
			System.out.println("You may enter the worng letters.");
		}
	}
	
	public void delete_Contact()
	{
		String update_name = null;
		String answer = null;
		boolean contact_found = false;
		System.out.println("The contacts in address book are as follows.");
		for(Person people : contacts){
			System.out.println(people.firstName + " " + people.lastName);
		}
		
		boolean done = false;
		do{
			try{
				System.out.println("Enter the first name of the person to delete using same letter case as displayed.");
				System.out.println("Please Dont Use Any White Spaces");
				update_name = input.next();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		int person_index = 0;
		
		for(Person people : contacts){
			if(update_name.equalsIgnoreCase(people.firstName) && contact_found == false){//////////////////////String Comparison
				person_index = contacts.indexOf(people);
				contact_found = true;
			}
		}
		if(contact_found == true){
			System.out.println("Are you sure you want to delete contact " + update_name+"?" );
			System.out.println("choose 'y' for YES and 'n' for NO");
			System.out.println("Please Dont Use Any White Spaces");
			answer = input.next();
			if(answer.equalsIgnoreCase("y")){//////////////////////String Comparison
				contacts.remove(person_index);
			}
		}
	}
	
	public void Edit_Info()
	{
		String update_name = null;
		System.out.println("Contacts in address book list");
		for(Person people : contacts){
			System.out.println(people.firstName + " " + people.lastName);
		}
		
		boolean done = false;
		do{
			try{
				System.out.println("Enter the first name of the person you want to update.");
				System.out.println("Please Dont Use Any White Spaces");
				update_name = input.next();
				done = true;
			}catch(Exception e ){
				e.printStackTrace();
			}
		}while(done==false);
		
		int person_index = 0;
		
		for(Person people : contacts){
			if(update_name.equalsIgnoreCase(people.firstName)){//////////////////////String Comparison
				person_index = contacts.indexOf(people);
				break;
			}
		}
		
		Person edit_person = contacts.get(person_index);
		
		System.out.println("Enter number of Contact fields to update.");
		System.out.println("max 7 fields: First Name, Last Name, Address, City, State, Zip Code, Phone Number.");
		int fields = input.nextInt();
		
		for (int i = 0 ; i < fields ; i++){
			System.out.println("ie First Name = 1, Last Name = 2, Address = 3, City = 4, State = 5, Zip Code = 6, Phone Number = 7.");
			int test = input.nextInt();
			String edit = null;
			int num = 0;

			switch (test){
				case 1:
					
					done = false;
					do{
						try{
							System.out.println("Current First Name is " + edit_person.firstName );
							System.out.println("Enter First Name to update value");
							System.out.println("Please Dont Use Any White Spaces");
							edit = input.next();
							done = true;
						}catch(Exception o ){System.out.println(o.getMessage());}
					}while(done==false);
					edit_person.firstName = edit;
					break;
				case 2:
					
					done = false;
					do{
						try{
							System.out.println("Current Last Name is " + edit_person.lastName );
							System.out.println("Enter Last Name to update value");
							System.out.println("Please Dont Use Any White Spaces");
							edit = input.next();
							done = true;
						}catch(Exception k ){System.out.println(k.getMessage());}
					}while(done==false);
					edit_person.lastName = edit;
					break;
				case 3:
					
					input.nextLine();
					
					done = false;
					do{
						try{
							System.out.println("Current First Name is " + edit_person.address );
							System.out.println("Enter Address to update value");
							edit = input.nextLine();
							done = true;
						}catch(Exception d ){System.out.println(d.getMessage());}
					}while(done==false);
					edit_person.address = edit;
					break;
				case 4:
					
					input.nextLine();
					
					done = false;
					do{
						try{
							System.out.println("Current First Name is " + edit_person.city );
							System.out.println("Enter City to update value");
							edit = input.nextLine();
							done= true;
						}catch(Exception c ){System.out.println(c.getMessage());}
					}while(done==false);
					edit_person.city = edit;
					break;
				case 5:
					done = false;
					do{
						try{
							System.out.println("Current First Name is " + edit_person.state );
							System.out.println("Enter State to update value");
							System.out.println("Please Dont Use Any White Spaces");
							edit = input.next();
							done = true;
						}catch(Exception b ){System.out.println(b.getMessage());}
					}while(done==false);
					
					edit_person.state = edit;
					break;
				case 6:
		
					done = false;
					do{
						try{
							System.out.println("Current First Name is " + edit_person.zip );
							System.out.println("Enter Zip to update value");
							num = input.nextInt();
							done = true;
						}catch(Exception e ){System.out.println(e.getMessage());}
					}while(done==false);
					
					edit_person.zip = num;
					
					break;
				case 7:
					
					done = false;
					do{
						try{

							System.out.println("Current First Name is " + edit_person.phoneNumber );
							System.out.println("Enter Phone Number to update value");
							System.out.println("Please Dont Use Any White Spaces");
							edit = input.next();
							done = true;
						}catch(Exception e ){System.out.println(e.getMessage());}
					}while(done==false);
					
					edit_person.phoneNumber = edit;
					
					break;
			}	
		}
	}
	
	public void user_Input_Contact(){
		
		Person p = new Person();
		
		boolean done = false;
		do{
			try{
				System.out.println("Enter First Name");
				System.out.println("Please Dont Use Any White Spaces");
				p.firstName = input.next();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		done = false;
		do{
			try{
				System.out.println("Enter Last Name");
				System.out.println("Please Dont Use Any White Spaces");
				p.lastName = input.next();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		input.nextLine();
		
		done = false;
		do{
			try{
				System.out.println("Enter Address");
				p.address = input.nextLine();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		done = false;
		do{
			try{
				System.out.println("Enter City");
				p.city = input.nextLine();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		done = false;
		do{
			try{
				System.out.println("Enter State");
				System.out.println("Please Dont Use Any White Spaces");
				p.state = input.next();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		done = false;
		do{
			try{
				System.out.println("Enter Zip Code");
				System.out.println("Please Dont Use Any White Spaces");
				p.zip = input.nextInt();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);
		
		done = false;
		do{
			try{
				System.out.println("Enter Phone Number");
				System.out.println("Please Dont Use Any White Spaces");
				p.phoneNumber = input.next();
				done = true;
			}catch(Exception e ){System.out.println(e.getMessage());}
		}while(done==false);

		contacts.add(p);
	}
	
	public static void main(String[] args){
		
	}
}
