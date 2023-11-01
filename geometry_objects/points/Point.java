/**
* This class represents a Point, while housing
* methods to create and compare them.
*
* @author Flynn Nisbet, Mengsrun Nit
* @date Nov. 1st, 2023
*/

package geometry_objects.points;

import utilities.math.MathUtilities;

/**
 * A 2D Point (x, y) only.
 * 
 * Points are ordered lexicographically (thus implementing the Comparable interface)
 * 
 * @author xxx
 */
public class Point implements Comparable<Point>
{
	public static final String ANONYMOUS = "__UNNAMED";

	public static final Point ORIGIN;
	static
	{
		ORIGIN = new Point("origin", 0, 0);
	}

	protected double _x;
	public double getX() { return this._x; }

	protected double _y; 
	public double getY() { return this._y; }

	protected String _name; 
	public String getName() { return _name; }

	// BasicPoint objects are named points (from input)
	// ImpliedPoint objects are unnamed points (from input)
	public boolean isGenerated() { return false; }

	/**
	 * Create a new Point with the specified coordinates.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public Point(double x, double y)
	{
		this(ANONYMOUS, x, y);
	}

	/**
	 * Create a new Point with the specified coordinates.
	 * @param name -- The name of the point. (Assigned by the UI)
	 * @param x -- The X coordinate
	 * @param y -- The Y coordinate
	 */
	public Point(String name, double x, double y)
	{
		_name = (name == null || name == "") ? ANONYMOUS : name;
		this._x = x;
		this._y = y;
	}

	/**
	 * @return if this point has not user-defined name associated with it
	 */
	public boolean isUnnamed()
	{
		return _name == ANONYMOUS;
	}

	@Override
	public int hashCode()
	{
		return Double.valueOf(MathUtilities.removeLessEpsilon(_x)).hashCode() +
			   Double.valueOf(MathUtilities.removeLessEpsilon(_y)).hashCode();
	}

	/**
	 * 
	 * @param p1 Point 1
	 * @param p2 Point 2
	 * @return Lexicographically: p1 < p2 return -1 : p1 == p2 return 0 : p1 > p2 return 1
	 *         Order of X-coordinates first; order of Y-coordinates second
	 */
	public static int LexicographicOrdering(Point p1, Point p2)
	{
		//Returns correct value based on how the x values relate
		if(p1._x > p2._x) return 1;
		if(p1._x < p2._x) return -1;
		
		//If x is equal, move on to y
		if(p1._y > p2._y) return 1;
		if(p1._y < p2._y) return -1;
		
		//If both are equal, return 0
		return 0;		  
		
	}

	@Override
	public int compareTo(Point that)
	{
		if (that == null) return 1;
		return Point.LexicographicOrdering(this, that);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		//Weed out bad stuff
		if (obj == null || (!(obj instanceof Point))) return false;
		Point n = (Point) obj;
		//Test equivalence
        return MathUtilities.doubleEquals(n._x, _x) && MathUtilities.doubleEquals(n._y, _y);
	}

}