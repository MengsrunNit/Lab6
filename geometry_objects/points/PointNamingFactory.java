/**
 * All points in our database must be named. If a name is not provided, the PointNamingFactory will
 * generate one. It also hosts some other functions for the database.
 *
 * @author Flynn Nisbet, Mengsrun Nit
 * @date Nov. 1st, 2023
 */

package geometry_objects.points;
import java.util.*;

/*
 * Given a pair of coordinates; generate a unique name for it;
 * return that point object.
 *
 * Names go from A..Z..AA..ZZ..AAA...ZZZ  (a name such as ABA does not occur)
 */
public class PointNamingFactory
{
	// Prefix associated with each generated name so those names are easily distinguishable
	private static final String _PREFIX = "*_";

	// Constants reflecting our naming characters for generated names.
	private static final char START_LETTER = 'A';
	private static final char END_LETTER = 'Z';

	//
	// the number of characters in the generated names:
	// "A" and 1 -> "A"
	// "B" and 3 -> "BBB"
	//
	private String _currentName = "A";
	private int _numLetters = 1;

	//
	// A hashed container for the database of points; this requires the Point
	// class implement equals based solely on the individual coordinates and
	// not a name. We need a get() method; HashSet doesn't offer one.
	// Each entry is a <Key, Value> pair where Key == Value
	//
	protected Map<Point, Point> _database;

	public PointNamingFactory()
	{
		_database = new LinkedHashMap<Point, Point>(); 
	}

	/**
	 * Initialize the database with points; must call put() to ensure all points are named
	 * @param points -- a list of points, named or not named
	 */

	public PointNamingFactory(List<Point> points)
	{
		_database = new LinkedHashMap<Point, Point>();
		for (Point p : points) {
			put(p);
		}
	}

	/**
	 * Overloaded add / lookup mechanism for this database.
	 *
	 * @param pt -- a Point object (may or may not be named)

	 * @return THE point object in the database corresponding to its coordinate pair
	 * the object in the database if it already exists or
	 * a completely new point that has been added to the database
	 */

	public Point put(Point p)
	{
	   
	    // If it exists, return the existing point
	    if (get(p.getX(), p.getY()) != null) return get(p.getX(), p.getY());
	    
	    // If the point is unnamed, generate a name for it
	    if (p.getName().equals("__UNNAMED")) {
	        p = new Point(getCurrentName(), p.getX(), p.getY());
	    }
	   
	    // Add the point to the database
	    _database.put(p, p);
	   
	    // Return the added or generated point
	    return p;
	}

	/**
	 * Overloaded add / lookup mechanism for this database for an unnamed coordinate pair.
	 *
	 * @param x -- single coordinate
	 * @param y -- single coordinate

	 * @return THE point object in the database corresponding to its coordinate pair
	 * the object in the database if it already exists or
	 * a completely new point that has been added to the database (with generated name)
	 */

	public Point put(double x, double y)
	{
		//Calls other
		return put(new Point(x, y));
	}

	/**
	 * The 'main' overloaded add / lookup mechanism for this database.
	 * 
	 * @param name -- the name of the point 
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * 
	 * @return a point (if it already exists in the database) or a completely new point that
	 *         has been added to the database.
	 *         
	 *         If the point is in the database and the name differs from what
	 *         is given, nothing in the database will be changed; essentially
	 *         this means we use the first name given for a point.
	 *            e.g., a valid name cannot overwrite an existing valid name ;
	 *                  a generated name cannot be overwritten by another generated name
	 *         
	 *         The exception is that a valid name can overwrite an unnamed point.
	 */

	public Point put(String name, double x, double y)
	{
		//Calls other
		return put(new Point(name, x, y));
	}    

	/**
	 * Strict access (read-only of the database)
	 * 
	 * @param x
	 * @param y
	 * @return stored database Object corresponding to (x, y) 
	 */
	public Point get(double x, double y)
	{
		//Calls other
		return get(new Point(x, y));
	}	

	public Point get(Point p)
	{
		//If contains, loop through and find the match, then return the one in the database to ensure name
		if (_database.containsValue(p)) {
			for (Point pt: _database.values()) {
				if (p.getX() == pt.getX() && p.getY() == pt.getY()) return pt;
			}
		}
		//Return null if doesn't contain
		return null;	
	}

	/**
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return simple containment; no updating
	 */

	public boolean contains(double x, double y) 
	{ 
		//Calls other
		return contains(new Point(x, y)); 
	}

	public boolean contains(Point p) 
	{ 
		return _database.containsValue(p);
	}


	/**
	 * Constructs the next (complete with prefix) generated name.
	 * Names should be of the form PREFIX + current name
	 *
	 * This method should also invoke updating of the current name
	 * to reflect the 'next' name in the sequence.
	 *	 
	 * @return the next complete name in the sequence including prefix.
	 */
	private String getCurrentName()
	{
		//Saves current name before updating, then returns it.
		String currentName = _PREFIX + _currentName;
		updateName();
		return currentName; 


	}

	/**
	 * Advances the current generated name to the next letter in the alphabet:
	 * 'A' -> 'B' -> 'C' -> 'Z' --> 'AA' -> 'BB'
	 */
	private void updateName()
	{

		//'Incrementing' the character
		char currentChar = _currentName.charAt(0);
		int intChar = currentChar + 1; 
		char updateChar = (char) intChar;

		//Increases numLetters after Z, then resets update charater to A
		if(updateChar > END_LETTER ) {
			_numLetters++; 
			updateChar = START_LETTER; 
		}

		//Updates current characters to the new character
		_currentName = "";
		for (int i=0; i< _numLetters; i++) {
			_currentName += updateChar; 
		}
	}

	/**
	 * @return The entire database of points.
	 */
	public  Set<Point> getAllPoints()
	{
		//Loop through database - add to new set
		Set<Point> pointSet = new HashSet<Point>(); 
		for(Point pt: _database.keySet()) {
			pointSet.add(pt);
		}
		return pointSet; 
	}

	public void clear() { _database.clear(); }
	public int size() { return _database.size(); }

	@Override
	public String toString()
	{
		//Loop through each point, make a big string to print of its info. 
		String s = "";
		for(Point p : _database.keySet()) {
			s += p._name + ": (" + p._x + ", " + p._y + ") \n";
		}
		return s;
	}
}