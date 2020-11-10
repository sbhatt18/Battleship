import java.util.*;
import java.util.ArrayList;

public class Battleship {
	private ArrayList<String> location = new ArrayList<String>();//declaring and initializing Arraylist for location
	private String shipName;//instance variables
	private int shipSize;//instance variables
	
	public Battleship(String shipName,int shipSize) {// creating constructor (runs when obj is created)
		this.shipName=shipName;//Initializing variable
		this.shipSize=shipSize;//Initializing variable
	}
	
	public ArrayList<String>getLocation(){//getter method for getting Location
		return location;
	}
	
	public int getshipSize() {//getter method for getting ship size
		return shipSize;
	}
	
	public void setLocation(ArrayList<String> locToSet) {//creating method for setLocation and passing Arraylist as parameter
		this.location.addAll(locToSet);//adding all string to the arraylist
	}
	
	public String checkHit(String guess) {//creating method to check if the hit is valid with a string as parmeter
		String result="miss";
		if(location.contains(guess)) {//checking if location has users guess
			location.remove(guess);//if yes them remove it
				result=location.isEmpty() ? "kill":"hit";//if location is empty(hit everother ship) then it is a 'kill' otherwise hit
			}
			return result;
		}
	
	public String getshipName() {//getter method for getting ship name
		return shipName;
	}
}
	

class Gameboard{
	
	private ArrayList<Battleship> battleship = new ArrayList<Battleship>();//contains all battleships
	int guessNumber=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Gameboard gameboard=new Gameboard();//creating object
		gameboard.create();//method call for create method with no parameters
		
	}

	private void create() {//defining create method that will create gameboard
		// TODO Auto-generated method stub
		//gameboard will be initialized with the following:
		battleship.add(new Battleship("Carrier",5));//adding 5 carrier 
		battleship.add(new Battleship("Battleship",4));//adding 4 battleships
		battleship.add(new Battleship("Patrol Boat",3));//adding 3 patrol boats
		battleship.add(new Battleship("Submarine",3));//adding 3 submarine
		battleship.add(new Battleship("Destroyer",2));//adding 2 destroyers
		setLocation();//method call for setLocation method with no parameters 
		
		System.out.println("Welcome to Battleship!\n An Opposing nation vessel have been dectected using rader\n As a Lieutenant in the Navy your mission "
				+ "is to sink all of opponent's ships\n Good Luck Lieutenant");
		playGame();//method call for playGame method with no parameters
		
		
	}

	private void playGame() {//method that will keep playing the game until a condition is met and then end the game
		// TODO Auto-generated method stub
		String guess;//declaring variables
		String result;//declaring variable
		Scanner input= new Scanner(System.in);//scanner object
		while (!battleship.isEmpty()) {//while loop to check if ships are present, once there are no ships in arraylist, program breaks out of while loop
			result="miss";//intiialize result to miss
			guessNumber++;//increment guess number by 1
			System.out.println("Enter a guess");//user input
			guess=input.nextLine();//Storing input in guess variable
			guess=guess.toUpperCase();//covert input to uppercase (for error handling)
			for(int a=0;a<battleship.size();a++) {//for loop to traverse the number of battleships in arraylist
				result=battleship.get(a).checkHit(guess);//whichever battleship we are checking,we call the checkhit method on
				if(result.equals("kill")) {//if the result is kill do the following below
					result=("You sank "+battleship.get(a).getshipName());//update result and display ship
					battleship.remove(a);//delete the ship from arraylist once it gets displayed
					break;
				}//else if the result is hit 
				else if(result.equals("hit")){
					break;//break out of else if statement
				}	
				
				}
			System.out.println(result);//print result
			}
		input.close();//close scanner object
		finish();//method call for finish method
		
	}
	
	private void finish() {//method to output ranking system once game is finished 
		// TODO Auto-generated method stub
		if(guessNumber==17) {//if score is equal to 17
			System.out.println("Congrats Lieutenant, due to your exellent performance you have been promoted as Captain !!!");
		}
		else if (guessNumber<28) {//if score is between 17-28
			System.out.println("Congrats Lieutenant, due to your exellent performance you have been promoted as Lieutenant Commander. Your Score was "+ guessNumber);
		}
		else if (guessNumber<38) {//if score is between 28-38
			System.out.println("Lieutenant, due to your satisfactory performance you have not been promoted. Your Score was "+ guessNumber);
		}
		else {//if score is more that 39
			System.out.println("Lieutenant, due to your below average performance you have been recommended additional training. Your Score was "+ guessNumber);
		}
			
		
	}
	
	private void setLocation() {
		// TODO Auto-generated method stub
		Random randomNumber= new Random();//creating object for random number
		ArrayList<String> locToSet= new ArrayList<String>();//arraylist to holds all locations
		ArrayList<String> temp= null;
		int letter;//declaring variable
		int number;//declaring variable
		int incrementLetter;//declaring variable, check if ship is placed horizontal or vertical 
		int incrementNumber;//declaring variable, check if ship is placed horizontal or vertical
		String alphabet="ABCDEFGHIJ";
		boolean isShipThere;//checks if 2 ship exists in the same location (can break the program)
		
		for(int a=0;a<battleship.size();a++){//traverse thru all ships in Arraylist
			isShipThere=false;//set isShipThere to false so program can continue execution
			start://start execution 
				while(!isShipThere) {
					locToSet.clear();//clear the location in the arraylist
					isShipThere=true;//
					letter=randomNumber.nextInt(9);//10 letters(10x10 Grid)
					number=1+randomNumber.nextInt(9);//10 numbers(10x10 Grid)
					//following code below will decide the ship positioning (vert/horiz)
					if(number%2==0) {//if number is even increment letter
						incrementLetter=1;//vertical
						incrementNumber=0;
					}
					else {//otherwise increment number
						incrementLetter=0;
						incrementNumber=1;//horizontal
					}
					
					for(int b=0;b<battleship.get(a).getshipSize();b++) {//for loop to retrieve the size of the specific ship inside the ( )
						String location=""+alphabet.charAt(letter)+number;//retrieves the character at the letter position and concatenate it with number
						letter +=incrementLetter;//increment letter
						number += incrementNumber;//increment number
						
						for(int c=0;c<battleship.size();c++) {//nested for loop to check for duplicates
							if(c != a) {// check to see that we are not comparing new Location with current batteship
								temp=battleship.get(c).getLocation();
								if(temp.contains(location)) {
									isShipThere=false;
									continue start;//re-executes code from start command if problems occur
								}
							}
						}//if no problems occurred with duplicate locations 
						locToSet.add(location);//add new setted location to location arraylist
					}//once we traverse entire for loop for battleship size 
					battleship.get(a).setLocation(locToSet);//set location to locatio
					
				}
		 }	
	}
}	