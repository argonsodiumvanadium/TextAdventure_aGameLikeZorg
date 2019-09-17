import java.io.*;
public class wipe {
	public static void main(String [] args) {
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
