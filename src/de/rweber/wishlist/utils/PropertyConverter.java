package de.rweber.wishlist.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.google.appengine.api.datastore.Blob;

public class PropertyConverter {

    public static <T>Blob convertObjectToBlob(T o)
    {
    	
    	try 
    	{
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        	ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(o);
	    	return new Blob(bos.toByteArray());
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
			return null;
		}
    }
    
    @SuppressWarnings("unchecked")
	public static <T>T convertBlobToObject(Class<T> clazz, Blob b)
    {
    	
    	try 
    	{
    		ByteArrayInputStream bos = new ByteArrayInputStream(b.getBytes());
    		ObjectInputStream is = new ObjectInputStream(bos);
    	    return (T) is.readObject();
		} 
    	catch (IOException | ClassNotFoundException e) 
    	{
			e.printStackTrace();
			return null;
		}
    }
}
