package tradesim.simulation.opportunities;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * The Class PrivatePerson is a data class for matsim persons.
 * 
 * @author Jelle Kübler
 *
 */
@Getter
@AllArgsConstructor
public class PrivatePerson {
	
	private String person_id;
	
	private String sex;
	private int age;
	private String occupation;
	private double netIncomeHH;
	private String econStatus;
	
	//private boolean hasSeasonTicket;
	//private int personsInHH;
	//private int RegioStaRGem5;
	
	private double home_x;
	private double home_y;
	
	//private double work_x;
	//private double work_y;
	
	private String home_link;


}
