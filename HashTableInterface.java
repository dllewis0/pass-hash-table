import java.util.*;

/**
 * An interface for hash tables.
 * 
 * @author Danielle Lewis
 * @version 4-12-2017
 */
public interface HashTableInterface
{
    /**
     * Creates a hash using SHA-256.
     */
    public String hashFunction(String withWhat);
    
    /**
     * displayHashTable(), which converts a hash from hex to base64
     */
    public byte[] displayHashTable(String flipHash);
    
    /**
     * Determines whether a given ID is unique or not
     */
    public boolean isUniqueID(String checkedID);
    
    /**
     * Creates a password based on a String
     */
    public Password makePassword(String startPhrase);
    
    /**
     * Alters an existing password object
     */
    public void changePassword(String UID, String newPW);
    
    /**
     * Confirms a Password is valid
     */
    public boolean validatePassword(String UID, String thePass);
    
    /**
     * Creates a new entry in the hash table.
     */
    public void addToHash(String UID, String pass);
}
