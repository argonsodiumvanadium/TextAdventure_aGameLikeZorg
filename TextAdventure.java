import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class TextAdventure{

	public static void main(String[] args) { //MAIN function all the good stuff goes here
	int inventory;
	String s = null;
	s = checkSave();
	spawnLoc_b1 action1 = new spawnLoc_b1();
	hallway action2 = new hallway();
	room_b1 action3 = new room_b1();
	if(s.equals("spawnLoc_b1"))
		action2.describe();
	else if(s.equalsIgnoreCase("hallway")){
		action3.describe();
	}
	else
		action1.describe();
	}

	//this function is basically used for DRAMATIC delays in the gameplay... maybe not dramatic
	public static void timer (long time){

		try{
   			Thread.sleep(time);			// the parameters of the function require a long and in miliseconds
		}
		catch(InterruptedException ex){
   			Thread.currentThread().interrupt();
		}
	}

	//this function is used for refresh like the "clear" statement in the terminal
	public static void clearScreen() {
	    System.out.print("\033[H\033[2J");
    	System.out.flush();
   }

	static void p(String a){
		System.out.println(a);timer(2000);
   }

   public static void parse (String s){
  	 	try{
			FileWriter writer = new FileWriter("TextAdventureDataSave.txt");
			writer.write(s);
			writer.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}
   }

   public static String checkSave(){
   		String s = "null";
   		try {

			File myFile = new File ("TextAdventureDataSave.txt");
			FileReader fileReader = new FileReader (myFile);
			BufferedReader reader = new BufferedReader (fileReader);
			String line = null;

			while ((line = reader.readLine()) != null){
				s = line;
			}

			reader.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return s;
   }
}

class house extends TextAdventure{

	public static boolean check (String s){
		try {
			File myFile = new File ("TextAdventureCharacter.txt");
			FileReader fileReader = new FileReader (myFile);
			BufferedReader reader = new BufferedReader (fileReader);
			String line = null;
			while ((line = reader.readLine()) != null){
				 if (line.equals(s)){
				 	return true;
				 }
			}
			reader.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean take (String s){
		Scanner sc = new Scanner(System.in);
		boolean b = true;
		System.out.print("You found a "+s+" do you want to take it ? (yes/no) :-");
		String option = sc.next();
		if (option.equalsIgnoreCase("yes")||option.equalsIgnoreCase("y")){
		//this will write all the inventory items down
		try{
			FileWriter writer = new FileWriter("TextAdventureCharacter.txt");
			writer.write(s);
			writer.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}

			return true;
		}
		else{
			return false;
		}
	}

	public static void savingAction(String str){
		System.out.print("uno momento please\n Saving ");
		for(int i = 0;i<3;i++){
			System.out.print(". ");
			timer(1500);
		}
		p("");
		parse(str);
	}

	public static void reboot (){
		File file1 = new File("TextAdventureDataSave.txt");
		File file2 = new File("TextAdventureCharacter.txt");

        if(file1.delete()&&file2.delete())
        {
            try{
				PrintWriter writer1 = new PrintWriter("TextAdventureCharacter.txt", "UTF-8");
				PrintWriter writer2 = new PrintWriter("TextAdventureDataSave.txt","UTF-8");
				writer1.close();
				writer2.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
        } 
        else
        {
            System.out.println("An error occured please check if you messed with the file positons as the were");
            System.out.println("not deleted please keep the .txt files and the code together");
        }
	}
}

class basement extends house{
	public void surrounding(){};
	public void describe(){};
	public void decision(){};
}

class spawnLoc_b1 extends basement{
	static 	spawnLoc_b1 action = new spawnLoc_b1();
	static int findFrequency = 0;
	static Scanner sc = new Scanner (System.in);
	static int [] num = new int [4];
	static int chance=3;
	static boolean b,bl;

	public void describe(){
		System.out.println("Don't be confused my friend, because all that is happening right now is that you");timer(3000);
		System.out.println("Are trapped in a random unknown house in god knows where, definately not a reason");timer(3000);
		System.out.println("         To freak out, well just kidding YOU SHOULD BE FREAKING OUT !!! ");timer(3500);
		clearScreen();
		System.out.println("---------------------------------TEXT ADVENTURE---------------------------------");timer(3000);
		System.out.println("                               LET THE GAME BEGIN");timer(1500);
		action.surrounding();
	}

	public void surrounding(){

		if (findFrequency==0){
			p("YOU are in absolute  darkness you  can see  nothing, you (as expected)  have  no");
			p("Recolection of why you are here and what you were doing, all you can do here is ");
			p("1)search around (blindly)");System.out.println("2)waddle like a penguin(great fun in tough situations guaranteed!!)");
			System.out.print("So what do you do :- ");String option = sc.nextLine();
			action.decision(option);
		}

		if (findFrequency>=1){

			if (findFrequency>1 && b == true ){

				p("With a torch searching could not be such a drag as it is so and well  if  there ");
				p("Any hidden clues (well kinda expected) we will see them all now because now  we");
				p("Have the power !!! Here are your rational options ");
				p("1)search around (again ugghhhh)");System.out.println("2)try to open the combination lock");
				System.out.print("What do you do ?? :- ");String option = sc.nextLine();
				action.decision(option);
			}
			else{

				p("While fiddling around you found a door it appears to be locked with a combination ");
				p("(also kinda expected) so again  you have  two rational  options ( yes these are )");
				System.out.println("1)search around (don't give up the chance of succeding is 25%)\n2)try to open the combination lock");
				System.out.print("So what do you do ?? :- ");String option = sc.nextLine();
				action.decision(option);
		}
		}
	}

	public void decision(String str){
		if (str.equalsIgnoreCase("search")||str.equalsIgnoreCase("search around")||str.equalsIgnoreCase("1")){
			action.find();
		}
		if (findFrequency==0){
			if (str.equalsIgnoreCase("waddle like a penguin")||str.equalsIgnoreCase("waddle")||str.equalsIgnoreCase("2")){
				System.out.println("you waddle like a penguin and waste an hour, just great  but  you  find  a  coin");
				take("coin");
				action.surrounding();
			}
		}

		if(str.equalsIgnoreCase("try")||str.equalsIgnoreCase("try to open the combination lock")||str.equalsIgnoreCase("2")){
			action.combinationLock();
		}

		else{
			System.out.println(" not an option kiddo--------------------------------------------------------");
			action.surrounding();
		}
	}

	public void combinationLock(){
		if (chance > 0){
			p("So you are going to try your luck eh, just telling you, you have "+chance+" chances it is");
			System.out.println("Do or die, so you inspect the lock and it requires a 4 digit  number  so \n enter the 4 digit number");
			int number = sc.nextInt();

			if(number>999&&number<10000){

				int temp = number;

				for(int i=0;i<4;i++){
					num[i]= temp%10;
					temp /=10;
				}timer(2000);

				System.out.println("_ _ _ _");
				System.out.println(""+num[3]+" "+num[2]+" "+num[1]+" "+num[0]);
				System.out.println("_ _ _ _");

				for(int i=0;i<3;i++){
					System.out.print("beep ");
					timer(1500);
				}
				timer(1500);

				if(number==1024){
				p("CLICK !");
					p("The door opened !!! So the birch wood door gives way to you to procede  and  you");
					p("see darkness outside so looks like it is your first step into DARKNESSSSSSSSSSSS");
					hallway teleporter = new hallway();
					teleporter.describe();

				}

				else{
					p("annnndddd ...........	");
					chance-=1;
					System.out.println("Nothing happens and now you have "+chance+" chance/s left");
					if(chance == 0){
						System.out.println(" you are out of chances well looks like it is annn.....");timer(2500);System.out.print("       EXTRA CHANCE");timer(2000);
						System.out.println(" just kidding");timer(1000);
						System.out.println("===================================GAME OVER===================================");timer(3000);// delete files remember....
						reboot(); //I remembered ...
						System.exit(0);
					}
					action.surrounding();
				}
			}

			else{
				System.out.print("I clearly said four digits!! RETRY imbecile,");timer(2000); p("okay just retry");
				action.combinationLock();
			}
		}
		else{
			System.out.println(" you are out of chances well looks like it is annn.....");timer(2500);System.out.print("              EXTRA CHANCE");timer(2000);
			System.out.println(" just kidding");timer(1000);
			System.out.println("===================================GAME OVER===================================");timer(3000);// delete files remember....
			reboot(); //I remembered ...
			System.exit(0);
		}
	}

	public void find (){
		System.out.print("Searching the room");

		for (int i = 0;i<3;i++){
			action.timer(1000);
			System.out.print(" .");
		}
		System.out.println("");

		bl = check("torch");
		if (bl==true){
			p("VOILA!! You found an old stone tablet with a heading in huge letters and it says");
			p("PASSWORD BELOW DECODE IF YOU CAN HAHAHAHAH(emphasize on laugh to be  super evil)");
			p("Extreamly scared (definately) you read what is writen on the tablet  sloowwwlyyy");
			p("3234341432234^64565464^t56546674^566776756^767567657574^776877676^66876867458^57");
			p("7678865867^7676758756____7654765476788^675676547657^776675677665^567647674665766");
			p("(2^10)6745756487876578787889434434344^65765765765657^756476776564^56767654767677");
			p("gibberishffdhvfdc^7436547846726473^56473254457^34645873tyfedyg43rg384f636tgd34dg");
			p("you stop reading as it was filled with da word gibberish so you take these lines");
			p("and proceed to DECODE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			action.surrounding();
		}
		else{
			long randnum = Math.round(100*Math.random());

			System.out.print("\n");
			if(findFrequency>0){
				if(randnum<48){
					b = take("torch");
				}
			}
			findFrequency+=1;
			action.surrounding();
		}
	}
}

class hallway extends basement{
	static hallway action = new hallway();
	static Scanner sc = new Scanner(System.in);
	static int freq = 0;
	static String checkers = null;
	static room_b1 teleporter = new room_b1();

	public void describe(){
		savingAction("spawnLoc_b1");
		p("You take your first step into the darkness it is truly terifying but you will get");
		p("Over it so relax well looks like you are in the most classic of all places ......");timer(1500);
		clearScreen();
		System.out.println("-----------------------------------THE HALLWAY----------------------------------");timer(3000);
		p("                                LET THE GAME continue");
		action.surrounding();

	}
	public void surrounding(){
		boolean b = action.check("torch");
		if(b==true){
			p("The surroundings were dark confusion ruled your mind, using an iota of your brain");
			p("You switch on your torch, you look around yourself and see another door near the");
			p("Previous door you touch the door and it slowly creeks open and you see an axe and");
			p("Two glowing lights in the room away from your reach, you are about to enter when ");
			p("You hear a terrifying snarl scared you panic and you ...");
			System.out.println("1)Run inside the room and close the door\n2)Look behind ");
			System.out.print("what do you do ? :-");String option = sc.nextLine();
			action.decision(option);
		}
		else{
			p("yOU HAVE NO TORCH you feel around and find nothing but then you touch  something");
			Double a = Math.random();
			if(a<=0.1){
				p("You found a secret cellar behind a painting and you enter it, it has a sphere");
				p("Inside with million grooves and you enter it and teleport it back in time and");
				p("Blah Blah blah imean sphere is amazing but this isnt about it, well this is my");
				p("Random game to check my skills in oop and watever, well anyways this is kind of");
				p("Easter egg it is I ArNaV who made this and this is the only way out, till now ");
				p("But I will improve the game and probably with GUI so yah you win !!!!");
				p("victory--- YAAHHH");
				reboot();
				System.exit(0);
			}
			else{
				p("It is hairy and Majestic well it is a lion after all and it gets angry and eats");
				p("=====================================THE END====================================");timer(1500);
				reboot();
				System.exit(0);
			}
		}
	}
	public void decision(String s){
		if (freq == 0){
			if(s.equalsIgnoreCase("run inside")||s.equalsIgnoreCase("run")||s.equals("1")){
				teleporter.describe("FromHallway");
			}
			else if(s.equalsIgnoreCase("look")||s.equalsIgnoreCase("look behind")||s.equalsIgnoreCase("2")){
				coinFlip();
			}
		}
	}

	public static void coinFlip(){
		p("You turn around seeing a huge majestic lion scared you fling your puny torch  at");
		p("The king and he looks at you confused then tosses a coin (in case you dont know");
		p("lions can do so) saying if heads you are saved");
		System.out.print("Do you agree or no (yes/no) :-");String option = sc.next();

		if(option.equalsIgnoreCase("yes")){
			boolean boo = check("coin");

			if(boo==true){
				p("luckily you have a coin and you give it to him, he with regret takes it and then");
				p("HE FLIPS IT");

				for(int i = 0;i<3;i++){
					System.out.print(" flip ");
					timer(2000);
				}

				p("CLINK !!");timer(1500);
				double a = Math.random();

				if (a>=0.5){
					p("The lion looks at the coin with anger and shrieks in disbelief,this  momment  of");
					p("disorientation is enough for you to escape and dash into the room nearby.. pheww");
					teleporter.describe("FromHallway");
				}
				else{
					p("The lion looks at you with the expression of utmost relish and proceeds towards you");
					p("what happens next is best to be left as undescribed");
					p("===================================GAME OVER===================================");timer(1500);
					reboot();
					System.exit(0);
				}
			}
			else{
				p("He takes out a coin with an evil grin and he\nFLIPS IT!!!");
				for(int i = 0;i<3;i++){
					System.out.print(" flip ");
					timer(2000);
				}
				p("CLINK !!");timer(1500);
				p("The lion looks at you with a grin of victory (does that exist??) and  shows  you ");
				p("The coin and it shows heads, he then goes forth to finish yourou in your last moments");
				p("You see the coin is heads on both sides, angry on being fooled, you should  have");
				p("been the one to chose the coin, or atleast waddled like a penguin XD");
				p("===================================GAME OVER===================================");timer(1500);
				reboot();
				System.exit(0);
			}
		}
		if(option.equalsIgnoreCase("no")){
			p("Well I wont waste my effort telling you what happens when you mess with the king");
			p("Of Beasts I mean seriously");
			p("===================================GAME OVER===================================");timer(1500);
			reboot();
			System.exit(0);
		}

		else{
			p(" Not an option kiddo");
			action.coinFlip();
		}
	}
}
class room_b1 extends basement{
	static room_b1 action = new room_b1();
	static TimeBomb teleporter1 = new TimeBomb();
	static int freq=0;
	static String checkers = null;
	static Scanner sc = new Scanner(System.in);

	public void describe(String s){

		if(s.equalsIgnoreCase("FromHallway")){
			p("-----the game would not save as hallway is not completed--- ");
			p("In a hurry you dash inside the room scared of what that was, and your torchlight ");
			p("Is dimming scared out of your wits you scan the room with the torch light and it");
			p("Looks like you are in a");timer(1500);
			clearScreen();
			System.out.println("-----------------------------------RANDOM ROOM----------------------------------");timer(3000);
			p("                                LET THE GAME continue");
			action.surrounding();
		}
	}

	public void surrounding(){
		switch(freq){

			case 0:
			p("You look around in panic as you hear a scraping noise from behind, realising that ");
			p("the Door was not going to hold for long you take a quick look around the room and ");
			p("See an axe and a peper spray, and the peper spray is as big as a fire extinguisher");
			System.out.println("(I will leave the imagination to you) you can only pick one to fend of  the  cat");
			System.out.print("Orrrr something else, so is it the axe or the peper spray :-");
			String option = sc.nextLine();
			action.decision(option);
			break;

			case 1:
			p("you take one of the objects but the real question is that are you gonna attack or");
			System.out.println("Just wait for the worst to come so options are\n1)wait\n2)attack");
			System.out.print("And you :-");option = sc.nextLine();
			action.decision(option);

			case 2:
			if(checkers.equalsIgnoreCase("wait")){
				boolean b = check("axe");
				if(b==true){
					p("You wait for the beast to enter and so it does breaking down the door, a feel of ");
					p("Power was radiated from the majestic lion, it looked at you  with  a  dominating");
					p("Posture and glared with the intent to kill scared you pick up your axe and swing");
					p("killing the poor beast .... yes sorry in case you didnt check this was pg-16 for");
					p("violence");
					savingAction("hallway");//after hallway regardless of anything you cannot use the torch..................................important********
					teleporter1.describe();
					freq +=1;
				}
				else{
					p("You wait for the beast to enter and so it does breaking down the door, a feel of ");
					p("Power was radiated from the majestic lion, it looked at you  with  a  dominating");
					p("Posture and glared with the intent to save the poor soul  you  spray  the  peper");
					p("On his face, the lion gets enraged and goes on a blind rampage attacking anything");
					p("in his sight (maybe not because he can't see), he also blocks your only way  out");
					p("so you can't escape and become his meal well byyyyyyeeeee");
					p("===================================GAME OVER===================================");timer(1000);
					reboot();
					System.exit(0);
				}
			}
			if(checkers.equalsIgnoreCase("attack")){
				boolean b = check("axe");
				if(b==true){
					p("You kick the door and come face to face with a lion,  a truly majestic one with");
					p("Power which radiated from the majestic lion, it looked at you  with  a  dominating");
					p("Posture and glared with the intent to kill scared you pick up your axe and swing");
					p("killing the poor beast .... yes sorry in case you didnt check this was pg-16 for");
					p("violence");
					savingAction("hallway");//after hallway regardless of anything you cannot use the torch..................................important********
					teleporter1.describe();
					freq +=1;
				}
				else{
					p("You kick the door and come face to face with a lion,  a truly majestic one with ");
					p("Power which radiated from the majestic lion, it looked at you  with  a  dominating");
					p("Posture and glared with the intent to save the poor soul  you  spray  the  peper");
					p("On his face, the lion gets enraged and goes on a blind rampage attacking anything");
					p("which sadly included you so you died ");
					p("===================================GAME OVER===================================");timer(1000);
					reboot();
					System.exit(0);
				}
			}
			else{
				p("Not an option kiddo");
				action.surrounding();
			}
		}
	}

	public void decision(String s){
		if(freq == 0){
			if(s.equalsIgnoreCase("axe")){
				boolean b = take("axe");
				if(b==true){
					freq+=1;
				}
				else{
					p("Well you should have taken something because the animal  outside  found  insane");
					p("Strength and broke the door and the majestic lion ate you ,now you are a zombie");
					p("And as per our User agreements you are not allowed to play, check clause(3^50) so");
					p("===================================GAME OVER===================================");timer(1000);
					reboot();
					System.exit(0);
				}
			}
			if(s.equalsIgnoreCase("peper spray")||s.equalsIgnoreCase("spray")){
				boolean b = take("p spray");
				if(b==true){
					freq+=1;
				}
				else{
					p("Well you should have taken something because the animal  outside  found  insane");
					p("Strength and broke the door and the majestic lion ate you up now you are a zombie");
					p("And as per our User agreements you are not allowed to play, check clause(3^50) so");
					p("===================================GAME OVER===================================");timer(1000);
					reboot();
					System.exit(0);
				}

			}
			else{
				p(" not an option kiddo");
				action.surrounding();
			}
			action.surrounding();
		}
		if(freq==1){
			if(s.equalsIgnoreCase("wait")||s.equalsIgnoreCase("1")){
				checkers = "wait";
				freq+=1;
				action.surrounding();
			}
			else if (s.equalsIgnoreCase("attack")||s.equalsIgnoreCase("2")){
				checkers = "attack";
				freq+=1;
				action.surrounding();
			}
			else{
				p("wrong option big kiddo");
				action.surrounding();
			}
		}
	}
}

class TimeBomb extends room_b1{
	static TimeBomb action = new TimeBomb();
	static Scanner sc = new Scanner(System.in);
	static int freq = 0;
	static trapDoor teleporter = new trapDoor();

	public void  describe(){
		p("You take in this immediate victory and look around in the room and then you  see");
		p("Something you need to hurry against something you need to dismantle or escape to");
		System.out.print("To live , you see a a ..");timer(3000);System.out.print("..(gulp)..");timer(1500);System.out.print("A TIME BOMB");timer(2000);
		p("THis is the start of a new plot a plot in which speed matters more than iq good luck");
		p("-------------------------------THE TIME BOMB PLOT--------------------------------");timer(1500);
		p("                               And the REVELATION");timer(500);
		action.surrounding();
	}

	public void surrounding(){
		p("The two LEDs behind you and the lcd display freak you out, the lcd was slowly ");
		p("counting 3:01:54 and on so you had 3 hours till dooms day well lets pull up our");
		p("Socks and start this so two easy as pie options come to your head which are....");
		System.out.println("1)Deactivate the bomb (after all you are an amazing programmer)\n2)Run from the place(You are good in sports as well so)");
		System.out.print  ("So what do you do :- ");
		String option = sc.nextLine();
		decision(option);
	}

	public void decision(String str){
		if(freq == 0){
			if(str.equalsIgnoreCase("deactivate")||str.equalsIgnoreCase("Deactivate the bomb")||str.equalsIgnoreCase("1")){
				p("So you are gonna decode the program, i am telling you now that it is a bad idea");
				p("as I have written the probablity as 25% lets hope you are lucky so lets begin..");
				p("Just kidding I am god here but I give you chance so here we go chose a wire over");
				p("here --->  |red  |       |       |");
				p("           |     |black  |       |");
				p("           |     |       |brown  |");
				p("           |     |       |       |green");
				p("well the probability is still 25%.. maybe not if you have seen movies  so  which");
				System.out.print("color do you pick:-");
				String option = sc.next();
				if(option.equalsIgnoreCase("green")){
					p("Neutrality is the best option even atoms speak so, no one likes a greedy atom so");
					p("You survived this then how will you escape the house, pondering  & thinking, you");
					p("Dash towards the stairs at the end of the hallway and climb up, but see something");
					p("Unexpectingly expected heheheh");
					savingAction("room_b1");
					freq+=1;
					teleporter.describe();

				}
				else{
					p("You are right the answer is right and you get happy and suddenly you become paste");
					p("yes child this is your story as god narates himself so be proud because its over");
					p("And go to the first pit of dante's hell HAHAHAHAHHAHA");timer(1500);
					p("1_010101010_11110___001_00_111_101_GAmE OvEr1011_1110000_10011_10101_1101001_100");timer(1500);
					reboot();
					System.exit(0);
				}
			}
			if(str.equalsIgnoreCase("run")||str.equalsIgnoreCase("Run from the place")||str.equalsIgnoreCase("2")){
				p("So running is a wise option but is it wise in every scenario but are you sure..");
				p("Dash towards the stairs at the end of the hallway and climb up, but see something");
				p("Unexpectingly expected heheheh  ");
				p("Well if put in simple words it is a structure made of iron and wood and it is  a");
				p("Door or more precisely");timer(1000);
				System.out.print("A TRAPDOOR");timer(2500);p(" and so the reveletion begins and time starts leaking so you think again");
				p("1)Deactivate(25% success rate)\n");
				String option = sc.next();
				if (option.equalsIgnoreCase("deactivate")||option.equalsIgnoreCase("Deactivate the bomb")||option.equalsIgnoreCase("1")){
					action.decision("deactivate");
				}
				else{
					freq+=1;
				}
				freq =1000;
			}
		}
	}
}

class trapDoor extends basement{
	public void describe(){
		p("Well if put in simple words it is a structure made of iron  aaannnndddd it is  a");
		p("Door or more precisely");timer(1000);
		System.out.print("A TRAPDOOR");timer(2500);////////////////////////////////inherit time class but not the variable time
		p("well you are screwed now as you cant leave and eventhough you search the room you");
		p("find no trace or Idea of how to break the door , even the axe didnt work so you die");
		p("There is no way out HAHAHAHAHHAHA , well GAME OVER !!!!! or more like ");
		timer(1000);
		p("++----++_--*=+==-+-+++$@%+%$##@+= GAME OVER __(*&)__(*((*7676758756____7654765476788");
		timer(1500);
		p("I know the ending suckked but I got an amazing idea thanks to this programmer, and I");
		p("idk what");
	}
}
