package tradesim.util.random;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * The Class RandomCloner creates exact copys of {@link Random} objects.
 */
public class RandomCloner {

	/**
	 * Clones the given {@link Random} object.
	 *
	 * @param src the random object to be cloned
	 * @return a copy of the given random object
	 */
	public static Random cloneRandom(Random src) {
	    ByteArrayOutputStream bo = new ByteArrayOutputStream();
	    ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bo);
			oos.writeObject(src);
		    oos.close();
		    ObjectInputStream ois = new ObjectInputStream(
		            new ByteArrayInputStream(bo.toByteArray()));
		    return (Random)(ois.readObject());
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;//TODO
	    
	}
	
}
