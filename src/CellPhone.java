/**
 * Written By Honorine Ishimwe Twahirwa, ID 40300265
 * COMP 249
 * Assignment 3
 * Due Dec 1, 2024, 9:00 am
 */

import java.util.Scanner;

public class CellPhone implements Cloneable {
    /**
     * attributes
     */
    private long serialNum;
    private String brand;
    private int year;
    private double price;

    /**
     * default constructor
     */
    public CellPhone () {
        serialNum = 0;
        brand = "";
        year = 1900;
        price= 0.0;

    }

    /**
     * parametarized constructor
     * @param serialNum
     * @param brand
     * @param year
     * @param price
     */
    public CellPhone (long serialNum, String brand, int year, double price ){
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    /**
     * copy constructor with a long serial number to be able to modify serial number
     * @param C1
     * @param newSerialNum
     */

    public CellPhone(CellPhone C1, long newSerialNum){
        this.serialNum = newSerialNum ;
        this.brand = C1.brand;
        this.year = C1.year;
        this.price = C1.price;
    }

    /**
     * @param serialNum
     */
  public void setSerialNum(long serialNum){
      this.serialNum = serialNum;
  }

    /**
     * @param brand
     */
  public void setBrand(String brand){

      System.out.println("Please enter brand name as a one word.");
      if (brand.contains(" ")) {
          System.out.println("The brand cannot have spaces in between.");
          return;
      }

      this.brand = brand;
  }

    /**
     * @param year
     */
  public void setYear(int year ){
      this.year = year;
  }

    /**
     * @param price
     */
  public void setPrice(double price){
      this.price = price;
  }

    /**
     * @return
     */
  public long getSerialNum(){
      return serialNum;
  }

    /**
     * @return
     */
  public String getBrand(){
      return brand;
  }

    /**
     * @return
     */
  public int getYear(){
      return year;
  }

    /**
     * @return
     */
  public double getPrice(){
      return price;
  }

    /**
     * clone method that make sure user puts a different serialNumber all the time.
     * @return
     */
  public CellPhone clone () {

      //creating scanner object for input
      Scanner kb = new Scanner(System.in);
      CellPhone copiedCell = null;
      try{
          copiedCell = (CellPhone) super.clone();
        System.out.println("Please enter a new serial number: ");
        long newSerialNum = kb.nextLong();
        copiedCell.serialNum = newSerialNum;

      } catch (CloneNotSupportedException e) {
          System.out.println("Cannot copy object" + e.getMessage());
      } finally {
          if (kb != null ) {
              kb.close();
          }

      }
      return copiedCell;

  }

    /**
     * equals method to compare two objects
     * @param obj
     * @return a boolean
     */
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || obj.getClass() != getClass()) return false;
      CellPhone other = (CellPhone) obj;

      return serialNum == other.getSerialNum() &&
              brand.equals(other.getBrand()) &&
              year == other.getYear() &&
              price == other.getPrice();
  }

    /**
     * override java toString method.
     * @return a string
     */
  public String toString(){
      return "The Cellphone's brand is " + brand + ", it was made in  year " + year + ", costs " + price + ", and it's serial number is " + serialNum + ".";
  }
}
