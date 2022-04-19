package tradesim.util.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class Time represents a point in time in the simulation or a time delta.
 *
 * @author Jelle Kübler
 */
public class Time {
	
	/** The colon separated time format: e.g. '16:42:13'. */
	public static String COLON_SEPARATED = "hh:mm:ss";
	/** The h m s time format: e.g. '14H42M13S'. */
	public static String H_M_S = "hh'H'mm'M'ss'S'";
	
	private final int seconds;
	
	/**
	 * Instantiates a new {@link Time} of 0 seconds.
	 */
	public Time() {
		this(0);
	}
	
	/**
	 * Instantiates a new {@link Time} of the given seconds.
	 *
	 * @param seconds the seconds
	 */
	public Time(int seconds) {
		super();
		this.seconds = seconds;
	}
	
	
	/**
	 * Creates a new {@link Time} of the given minutes.
	 *
	 * @param minutes the minutes
	 * @return the time
	 */
	public static Time ofMinutes(int minutes) {
		return new Time().plusMinutes(minutes);
	}
	
	/**
	 * Creates a new {@link Time} of the given hours.
	 *
	 * @param hours the hours
	 * @return the time
	 */
	public static Time ofHours(int hours) {
		return new Time().plusHours(hours);
	}
	
	/**
	 * Parses the given {@link String} as {@link Time}
	 * using the given time format regex String.
	 *
	 * @throws IllegalArgumentException if the string cannot be parsed
	 * @param timeString the time string
	 * @param format the time format
	 * @return the time
	 */
	public static Time parse(String timeString, String format) {
		
		try {
			Time t = new Time();
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date date = dateFormat.parse(timeString.replace(" ", ""));
			
			return t.plusHours(date.getHours())
				.plusMinutes(date.getMinutes())
				.plusSeconds(date.getSeconds());
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse the given time string '" + timeString + "' to the format '" + format + "'", e);
		}
		
	}
	
	/**
	 * Parses the given {@link String} as {@link Time}
	 * using the default {@link Time#COLON_SEPARATED} time format.
	 *
	 * @param timeString the time string
	 * @return the time
	 */
	public static Time parse(String timeString) {
		return parse(timeString, COLON_SEPARATED);
	}
	
	
	
	
	/**
	 * Returns the number of seconds since the the start of {@link Time} 00:00:00.
	 *
	 * @return seconds since start
	 */
	public int toSeconds() {
		return this.seconds;
	}
	
	/**
	 * Returns the number of minutes since the the start of {@link Time} 00:00:00.
	 *
	 * @return minutes since start
	 */
	public int toMinutes() {
		return this.seconds / 60;
	}
	
	/**
	 *  Returns the number of hours since the the start of {@link Time} 00:00:00.
	 *
	 * @return the int
	 */
	public int toHours() {
		return this.seconds / 3600;
	}
	
	
	/**
	 * Returns time of day.
	 *
	 * @return the time of day
	 */
	public TimeOfDay toTimeOfDay() {
		int hour = this.getHour() % 24; 
		
		if (4 <= hour && hour < 11) {
			return TimeOfDay.morning;
			
		} else if (11 <= hour && hour < 21) {
			return TimeOfDay.day;
			
		} else {
			return TimeOfDay.night;
		}
	}
	
	
	
	/**
	 * Gets the second within the current minute.
	 *
	 * @return the current second
	 */
	public int getSecond() {
		return this.toSeconds() % 60;
	}
	
	/**
	 * Gets the minute within the current hour.
	 *
	 * @return the current minute
	 */
	public int getMinute() {
		return this.toMinutes() % 60;
	}
	
	/**
	 * Gets the current hour.
	 *
	 * @return the current hour
	 */
	public int getHour() {
		return this.toHours();
	}
	
	
	
	/**
	 * Returns a new {@link Time} as result of this 
	 * time plus the given amount of seconds.
	 *
	 * @param seconds the seconds to be added
	 * @return the time sum
	 */
	public Time plusSeconds(int seconds) {
		return new Time(this.seconds + seconds);
	}
	
	/**
	 *  Returns a new {@link Time} as result of this 
	 * time plus the given amount of minutes.
	 *
	 * @param minutes the minutes
	 * @return the time sum
	 */
	public Time plusMinutes(int minutes) {
		return new Time(this.seconds + minutes*60);
	}
	
	/**
	 * Returns a new {@link Time} as result of this 
	 * time plus the given amount of hours.
	 *
	 * @param hours the hours
	 * @return the time sum
	 */
	public Time plusHours(int hours) {
		return new Time(this.seconds + hours*3600);
	}
	
	/**
	 * Returns a new {@link Time} as result of this 
	 * {@link Time} plus the given other {@link Time}.
	 *
	 * @param time the time
	 * @return the time sum
	 */
	public Time plus(Time time) {
		return new Time(time.toSeconds() + this.seconds);
	}
	
	/**
	 * Returns a new {@link Time} as result of this 
	 * time minus the given other {@link Time}.
	 *
	 * @param time the time
	 * @return the time
	 */
	public Time minus(Time time) {
		return new Time(this.seconds - time.toSeconds());
	}
		
	/**
	 * Returns the string representation of the {@link Time}.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return pad(this.getHour()) + ":" + pad(this.getMinute()) + ":" + pad(this.getSecond());
	}

	
	/**
	 * Pads the given number with 0 if less than 10.
	 *
	 * @param number the number
	 * @return the padded number string
	 */
	private String pad(int number) {
		return (number < 10) ? "0"+number : ""+number;
	}
}
