package com.amazon.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class AmazonMarketPlace {

	public static void main(String[] args) throws FileNotFoundException {
		
		 String customerDataFromFile =null; 
		 String productsFromFile =null;
		 String[] customerDetails = null;
		 String[] customerPassword=null;
		 String[] customerEmail=null;
		 String[] customerFirstName=null;
		 String[] customerLastName=null;
		 int flag =0;
		 String[] productSplit = null;
		 String  selectedItemNumber = null;
		 String price=null;
		 String[] prices = null;
		 double productPrice=0;
		 double tax = 0.07;
		 double totalPrice =0;
		 int itemIndex =0;
		 DecimalFormat df = new DecimalFormat("#.00");
		 
		 ArrayList<String> products = new ArrayList<>();
		 ArrayList<String> productsSplitOfPrice = new ArrayList<>();
		 ArrayList<String> priceList = new ArrayList<>();
		 ArrayList<String> cartList = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		
		Scanner customersFile = new Scanner(new File("C:\\\\Users\\\\balum\\\\OneDrive\\\\Documents\\\\Java Training\\\\AmazonMarketPlace\\\\src\\\\com\\\\amazon\\\\resource\\\\customers"));
		Scanner productsFile = new Scanner(new File("C:\\\\Users\\\\balum\\\\OneDrive\\\\Documents\\\\Java Training\\\\AmazonMarketPlace\\\\src\\\\com\\\\amazon\\\\resource\\\\products"));
	 
		 System.out.println("Enter Email: ");
		 String userEmail = scanner.next();
		 
		 System.out.println("Enter Password: ");
		 String userPassword = scanner.next();
		 
		 while(customersFile.hasNext()) {
			 
			 customerDataFromFile = customersFile.next();
			 
			 customerDetails =  customerDataFromFile.split(";");
			 
			 customerEmail = customerDetails[2].split(":");
			 customerPassword=customerDetails[3].split(":");
			 customerFirstName=customerDetails[0].split(":");
			 customerLastName=customerDetails[1].split(":");
			
			 if(customerPassword[1].equals(userPassword) && customerEmail[1].equals(userEmail)) {
				 flag=1;
				}
		 }
			 if(flag == 1) {
			 System.err.println("Login Successfull");
			 System.out.println("Welcome " +customerFirstName[1]+" " + customerLastName[1] + " to the Amazon Store. Please find the products below.");

			 while(productsFile.hasNext()) {
			 productsFromFile = productsFile.next();
			 products.add(productsFromFile); //Adding products from file to products variable.
			 }
			 
			 for(int i=0;i<products.size();i++) {
				System.out.println(i+1+"."+products.get(i));
				productSplit = products.get(i).split("-");
				
				for(int j=0;j<productSplit.length;j++) {
					productsSplitOfPrice.add(productSplit[j]);
				}
			 }
			 
          System.out.println("Please choose your items by selecting respected number(Ex: 1). If you want to select more than one item please choose like this (Ex: 1,3,4) : 1,4");
          selectedItemNumber = scanner.next();
          
		String[] selectedProductNumbers = selectedItemNumber.split(",");
		
		 for(int i = 0; i < productsSplitOfPrice.size(); i++){
	           if(i % 2 == 1){
	        	  priceList.add( productsSplitOfPrice.get(i));
	           }
		 }
		 
		 for (int j=0; j<selectedProductNumbers.length;j++) {
	            String index = selectedProductNumbers[j].trim();
	             itemIndex = Integer.parseInt(index) - 1;
		 
	            if (itemIndex >= 0 && itemIndex < products.size()) {
	            	
	            	 cartList.add(products.get(itemIndex));
	                 prices=products.get(itemIndex).split("-");
	                 price= prices[1].substring(1);
	                 productPrice = Integer.parseInt(price);
	                totalPrice=totalPrice+productPrice;
	            } else {
	                System.out.println("You chose invalid product number:"+ (itemIndex + 1)+". Please choose by giving the valid product number:(1,2,3,4,5)" );
	            }
		 }
		 if(totalPrice != 0) {
			 System.out.println("=============================");
	   		 System.out.println("your cart");
	   		 System.out.println("=============================");
	   		 for(int i=0;i<cartList.size();i++) {
	   		 System.out.println(cartList.get(i));
	   		 }
	   		 System.out.println("=============================");
	   		 System.out.println("Total without Tax - "+df.format(totalPrice));
	   		 System.out.println("Tax (7%): "+(totalPrice*tax));
	   		 System.out.println("Total with Tax :"+(totalPrice+(totalPrice*tax)));
	   		 System.out.println("=============================");	
		 }
 		
			 }else {
			 System.out.println("Login Failed");
		 }
			 scanner.close();
	}
}
