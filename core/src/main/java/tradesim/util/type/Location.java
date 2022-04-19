package tradesim.util.type;

import static java.lang.Math.abs;
import static java.lang.Math.hypot;

import java.awt.geom.Point2D;

/**
 * The Class Location represents a point of interest with a 2D coordinate..
 */
public class Location {
	private Point2D.Double coordinate;

	/**
	 * Instantiates a new location with the given coordinates.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Location(double x, double y) {
		this(new Point2D.Double(x,y));
	}
	
	/**
	 * Instantiates a new location with the given {@link Point2D.Double}.
	 *
	 * @param coordinate the coordinate
	 */
	public Location(Point2D.Double coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * Gets the x coordinate.
	 *
	 * @return the x
	 */
	public double getX() {
		return this.coordinate.getX();
	}
	
	/**
	 * Gets the y coordinate.
	 *
	 * @return the y
	 */
	public double getY() {
		return this.coordinate.getY();
	}
	
	/**
	 * Returns string representation of the coordinates.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "(" + coordinate.x + "," + coordinate.y + ")";
	}
	
	/**
	 * Returns the distance between this {@link Location} and the other {@link Location}.
	 *
	 * @param location the other location
	 * @return distance between this and location
	 */
	public double distance(Location location) {
		double x1 = this.coordinate.getX();
		double y1 = this.coordinate.getY();
		
		double x2 = location.coordinate.getX();
		double y2 = location.coordinate.getY();
		
		return hypot(abs(y2-y1), abs(x2-x1));
	}
}