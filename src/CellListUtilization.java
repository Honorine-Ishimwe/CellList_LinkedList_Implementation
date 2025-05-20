/**
 * Written By Honorine Ishimwe Twahirwa, ID 40300265
 * COMP 249
 * Assignment 3
 * Due Dec 1, 2024, 9:00 am
 */
import java.util.Scanner;
import java.io.*;

public class CellListUtilization {

    public static void main(String [] args) {
/**
 * creating two CellList objects, reader object and local variables
 */
  CellList list1 = new CellList();
  CellList list2 = new CellList();
  CellList list3 = new CellList();
  CellList list4 = new CellList();
  BufferedReader reader = null;
  Scanner kb = new Scanner(System.in);
  int serialCnt=0;
  long serialNum = 0;
  String brand ="";
  int year = 0;
  double price =0;
        String line;

  try{
      reader = new BufferedReader (new FileReader("Cell_Info.txt"));
      while((line = reader.readLine())!= null) {
          //String line = reader.nextLine();

          String [] fields = line.split("\\s+");
          serialNum = Long.parseLong(fields[0]);
          brand = fields[1];
          price = Double.parseDouble(fields[2]);
          year = Integer.parseInt(fields[3]);

          CellPhone phone = new CellPhone (serialNum, brand, year, price);

          /**
           * check of serial numbers are the same
           */
          if (!list1.contains(serialNum)) {
              list1.addToStart(phone);
          } else {
              System.out.println("Duplicate serial number found: " + serialNum);
          }
      }

  } catch(FileNotFoundException e){
      System.out.println("File not found");
  } catch(IOException e){
      System.out.println("Something is wrong with reading");
  }

        /**
         * display the information of the list
         */

  list1.showContents();

        /**
         * asking the user to enter a serial number.
         */
        while (serialCnt <= 3) {
            System.out.println("Please enter a serial number you are looking for");
            serialNum = kb.nextLong();
            list1.find(serialNum);
            serialCnt++;
        }

        /**
         * creating object to test the other methods
         */
        CellPhone P1 = new CellPhone(8887644,"Apple", 2023, 1000);
        CellPhone P2 = new CellPhone(8887644,"Apple", 2023, 1000);
        CellPhone P3 = new CellPhone(6667890,"Samsung", 2013, 200);
        CellPhone P4 = new CellPhone(7785464,"Motorolla", 2006, 20);
        CellPhone P5 = new CellPhone(8855548,"Nokia", 2020, 600);
        CellPhone P6 = new CellPhone(8990764,"Apple", 2024, 1600);
        CellPhone P7 = new CellPhone(9008887,"Apple", 2021, 900);
        CellPhone P8 = new CellPhone(8778004,"Samsung", 2024, 1500);

        list2.addToStart(P1);
        list2.addToStart(P3);
        list2.addToStart(P4);
        list2.insertAtIndex(P6,0);
        list2.replaceAtIndex(P5,2);
        System.out.println("Are the lists equal?" +  (list2.equals(list1) ? "Yes" : "No" ));
        list2.showContents();
        list2.deleteFromIndex(0);
        list3.deleteFromStart();
        list4.deleteFromIndex(0);

        System.out.println("End of program!!");
       

kb.close();

    }
}
