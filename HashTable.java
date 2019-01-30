import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * A hash table for passwords.
 * I admit I couldn't figure out how to implement separate chaining outside of the HashMap. Assuming it's unsorted, I could write something like if(theChain.contains(somePWObject)) { theChain.add(theChain.lastIndexOf(somePWObject), somePWObject) } else { theChain.add(somePWObject);
 * ...but I couldn't figure out how to make that do anything in conjunction with the HashMap, since java.util.HashMap doesn't seem to have an option to use the results of theChain operations to decide where to place a given PWObject.
 * theChain seems like it would have to be part of its own HashMap implementation, not java.util's. Not sure what I'm missing here :(
 * 
 * @author Danielle Lewis
 * @version 4-12-2017
 */
public class HashTable implements HashTableInterface
{
    LinkedList<Password> theChain = new LinkedList();
    HashMap<String,Password> theHash = new HashMap(); //<UID,Password object containing UID and salt and hash>; was initially using hash as key, but that left no way to easily search HashMap for a given UID (for uniqueID check)
    
   /**
     * Constructor for objects of class HashTable
     */
    public HashTable()
    {
        
    }
    
    /**
     * Creates a hash using SHA-256.
     * Did have to look this one up a bit as well. http://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
     * It seems to make sense.
     */
    public String hashFunction(String withWhat)
    {   
        try {
            MessageDigest theDigest = MessageDigest.getInstance("SHA-256");
            
            byte[] baseHash = theDigest.digest(withWhat.getBytes("UTF-8"));
            StringBuffer hashToHex = new StringBuffer();
            
            for(int i = 0; i < baseHash.length; i++)
            {
                String resultHex = Integer.toHexString(0xff & baseHash[i]);
                
                if(resultHex.length() == 1)
                {
                    hashToHex.append('0');
                    hashToHex.append(resultHex);
                }
            }
            
            return hashToHex.toString();
        } catch(NoSuchAlgorithmException|UnsupportedEncodingException e) {
            System.out.println(e);
        };
        
        return "";
    }
    
    /**
     * displayHashTable(), which converts a hash from hex to base64
     */
    public byte[] displayHashTable(String flipHash)
    {
        byte[] results = flipHash.getBytes();
        
        return Base64.getEncoder().encode(results); //yay java.util.Base64!
    }
    
    /**
     * Creates a new entry in the hash table.
     */
    public void addToHash(String UID, String pass)
    {
        Password tempPW = makePassword(UID);

        String resultHash = hashFunction(pass + tempPW.getSalt());
        
        tempPW.setHash(resultHash);
        
        theHash.put(UID, tempPW);
    }
    
    /**
     * Determines whether a given ID is unique or not
     */
    public boolean isUniqueID(String checkedID)
    {
        if(theHash.containsKey(checkedID))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Creates a password based on a String
     */
    public Password makePassword(String UID)
    {
        Password returnMe = new Password();
        
        returnMe.setSalt();
        returnMe.setUID(UID);
        
        return returnMe;
    }
    
    /**
     * Alters an existing password object
     */
    public void changePassword(String UID, String newPW)
    {
        if(theHash.containsKey(UID))
        {
            Password currentPW = theHash.get(UID);
            String newHash = newPW + currentPW.getSalt();
            newHash = hashFunction(newHash);
            currentPW.setHash(newHash);
            theHash.replace(UID, currentPW);
        }
        else
        {
            throw new IllegalArgumentException(); //only thrown if UID does not exist in hash - Login driver class should prevent this exception from ever being thrown
        }
    }
    
    /**
     * Confirms a password is valid
     */
    public boolean validatePassword(String UID, String thePass)
    {
        Password getMe = theHash.get(UID);
     
        thePass = hashFunction(thePass + getMe.getSalt());
        
        if(thePass.equalsIgnoreCase(getMe.getHash()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
