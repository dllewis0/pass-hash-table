import java.util.*;

/**
 * Driver class.
 * 
 * @author Danielle Lewis
 * @version 4-13-2017
 */
public class Login
{
   public static void main(String args[])
   {
       HashTableInterface theDB = new HashTable();
       Scanner theScanner = new Scanner(System.in);
       
       int exit = 0;
       
       while(exit==0)
       {   
           System.out.println("(1)New account or (2)change password?");
           System.out.println("(3) to quit.");
           
           int result = theScanner.nextInt();
           
           while(result != 1 && result != 2 && result!=3)
           {
               System.out.println("Invalid input.");
               System.out.println("(1)New account or (2)change password?");
               System.out.println("(3) to quit.");
               result = theScanner.nextInt();
           }
           
           if(result==1)
           {
               System.out.println("Login ID?");
               
               String UID = theScanner.next();
               
               while(theDB.isUniqueID(UID)==false)
               {
                   System.out.println("That ID is taken, sorry.");
                   System.out.println("ID?");
                   
                   UID = theScanner.next();
               }
               
               System.out.println("Password?");
               
               String pw = theScanner.next();
               
               theDB.addToHash(UID,pw);
               System.out.println("Successful.");
           }
           else if(result==2)
           {
               System.out.println("Login ID?");
               
               String UID = theScanner.next();
               
               while(theDB.isUniqueID(UID)==true)
               {
                   System.out.println("That ID doesn't exist, sorry.");
                   System.out.println("Login ID?");
                   UID = theScanner.next();
               }
               
               System.out.println("Current pw?");
               
               String currentPW = theScanner.next();
               
               if(theDB.validatePassword(UID, currentPW)==true) //no multiple chances allowed!
               {
                   System.out.println("Valid. Change pw to?");
                   
                   String newPW = theScanner.next();
                   
                   theDB.changePassword(UID, newPW);
                   
                   System.out.println("Successful!");
               }
               else
               {
                   System.out.println("Not valid. Sorry!");
               }
           }
           else if(result==3)
           {
               exit = 1;
           }
        }
   }
}
