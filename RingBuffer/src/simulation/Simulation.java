package simulation;

import java.util.Scanner;
import ring_buffer.*;

public class Simulation {

	
	public Simulation() {
		
	}
	
	private void start() {
		this.testUserFunctionality();
	}
	
	/**
	 * Little terminal function to let users try out certain 
	 * commands of the ring buffer.
	 * It uses an ArrayRingBuffer of type String to store Objects.
	 * 
	 * However the general implementation could be used for all
	 * objects except null objects.
	 */
	private void testUserFunctionality() {
		
		HashRingBuffer<String> buffer;
		Scanner sc = new Scanner(System.in);

		System.out.println("Note: Currently this terminal only supports certain commands.");
		System.out.println("Type in /h to get a list of supported commands.\n");
		System.out.println("Please enter the size of the ring buffer.");
		
		int size = 10;
		
		String sizeString = sc.nextLine();
		if(sizeString.equals("/h")) {
			System.out.println("/p \t-> \tPrints the current state \n"
					+ "/df \t-> \tDrops the first element \n"
					+ "/dl \t-> \tDrops the last element \n"
					+ "/gf \t-> \tPrints the first element \n"
					+ "/gl \t-> \tPrints the last element \n"
					+ "/c \t-> \tClears all entries \n"
					+ "/h \t-> \tOpens this view \n"
					+ "/q, /Q \t-> \tExits the terminal\n"
					+ "To append a new element, just type it in and confirm with enter.");
		}else {
			size = Integer.parseInt(sizeString);
		}
		buffer = new HashRingBuffer<String>(size);
		
		String input = new String();
		input = "";
		while(!input.equals("/q") && !input.equals("/Q")){
			input = sc.nextLine();
			
			
				switch(input) {
				
				case "/p": // print
					buffer.print();
					break;
				case "/df": // drop first element
					buffer.dropFirst();
					break;
				case "/c": // clear ring buffer
					buffer.reset(size);
					break;
				case "/dl": // drop last element
					buffer.dropLast();
					break;
				case "/gl": // get last element
					System.out.println(buffer.getLast());
					break;
				case "/gf": // get first element
					System.out.println(buffer.getFirst());
					break;
				case "/h":
					System.out.println("/p \t-> \tPrints the current state \n"
							+ "/df \t-> \tDrops the first element \n"
							+ "/dl \t-> \tDrops the last element \n"
							+ "/gf \t-> \tPrints the first element \n"
							+ "/gl \t-> \tPrints the last element \n"
							+ "/c \t-> \tClears all entries \n"
							+ "/h \t-> \tOpens this view \n"
							+ "/q, /Q \t-> \tExits the terminal\n"
							+ "To append a new element, just type it in and confirm with enter.");
					break;
				default:
					buffer.append(input);
					break;
				}
			
			
			

			
		}
		sc.close();
		System.out.println("Exiting...");
	}
	
	public static void main(String[] args) {
		Simulation sim = new Simulation();
		sim.start();
	}
}
