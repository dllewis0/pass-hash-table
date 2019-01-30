import java.util.Random;

/**
 * A password object.
 * Added storage of the hash here.
 * 
 * @author Danielle Lewis
 * @version 4-13-2017
 */
public class Password
{
    final int LENGTH_OF_SALT = 15;
    
    String userID;
    String salt;
    String hash;
    
    /**
     * Constructor for objects of class Password
     */
    public Password()
    {
        setSalt();
    }
    
    /**
     * Returns the user ID of this Password object.
     */
    public String getUID()
    {
        return userID;
    }
    
    public String getHash()
    {
        return hash;
    }
    
    public void setHash(String newHash)
    {
        hash = newHash;
    }
    
    /**
     * Assigns a new user ID to this Password object.
     */
    public void setUID(String newUserID)
    {
        userID = newUserID;
    }
    
    /**
     * Gets the salt associated with this Password object.
     */
    
    public String getSalt()
    {
        return salt;
    }
    
    /**
     * Sets a new, random salt for this Password object.
     */
    
    public void setSalt()
    {
        //algorithm used here credit http://stackoverflow.com/questions/3114606/random-character-generator-with-a-range-of-a-z-0-9-and-punctuation
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        Random theRandomizer = new Random();
        
        salt = "";
        
        for(int i = 0; i < LENGTH_OF_SALT; i++)
        {
            salt += alphabet.charAt(theRandomizer.nextInt(alphabet.length()));
        }
    }
}
