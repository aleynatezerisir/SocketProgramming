package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
	public static String numOfNodes = ""; 
	public static String numOfStrings = ""; 
	public static String nameOfFile = ""; 
	public static List<String> ClientList= new ArrayList<String>();
	public static List<String> StringList= new ArrayList<String>();
	
  public static void main(String[] args) { 
    try {
      File myObj = new File("conf.txt");
      Scanner myReader = new Scanner(myObj);
      List<String> l1 = new ArrayList<String>();
      int count=0;
      while (myReader.hasNextLine()) { 
        String data = myReader.nextLine();
        l1.add(data);
        //System.out.println(data);
        count=count+1;
      }
      numOfNodes = l1.get(0);
      numOfStrings = l1.get(count-1);
      nameOfFile = l1.get(count-2);
      while(count>3) {
    	  ClientList.add(l1.get(count-3));
    	  count=count-1;
      }
      
      //System.out.println(l1);
      //System.out.println(ClientList);
      //System.out.println(count);
      //System.out.println(numOfNodes);
      //System.out.println(numOfStrings);
      //System.out.println(nameOfFile);
      myReader.close();
      
      myObj = new File(nameOfFile);
      Scanner myReader2 = new Scanner(myObj);
      while (myReader2.hasNextLine()) { 
          String data = myReader2.nextLine();
          StringList.add(data);
          //System.out.println(data);
          count=count+1;
        }
      
      myReader2.close();
      //System.out.println(StringList);
      
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
