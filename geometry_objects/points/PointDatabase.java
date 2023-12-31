/**
* Stores all points in a geometry figure by allowing the user to
* add new points to the database as well as look up points by 
* name or by coordinate values.
*
* @author Flynn Nisbet, Mengsrun Nit
* @date Nov. 1st, 2023
*/

package geometry_objects.points;
import java.util.*;
/**
 * This class represents a bi-directional database of points.
 * 
 * We can lookup a point given:
 *   (a) coordinates --> name
 *   (b) name --> coordinates
 * 
 * This is a Decorator class with the PointNamingFactory in the background
 * 
 * @author xxx
 */
public class PointDatabase
{
	//
	// The factory is the central means of representing all
    // points in a figure; all functionality in this class
	// will defer to the factory.
    //
    protected PointNamingFactory _factory;

    public Set<Point> getPoints() 
    { 
    	return _factory.getAllPoints(); 
    }
    
	public PointDatabase()
	{
		_factory = new PointNamingFactory(); 
	}

	public PointDatabase(List<Point> pts)
	{
		_factory = new PointNamingFactory();
       for(Point p : pts) {
    	   _factory.put(p);
       }
	}

	public int size() { return _factory.size(); }
	
	/**
	 * Add a point to the database.
	 */
	
	public void put(String name, double x, double y){ _factory.put(name, x, y); }

	/**
	 * Given raw coordinates of a point, determine if it is named.
	 * 
	 * @param x,y -- doubles defining a point (x,y)
	 * @return a string corresponding to that point, if it is named.
	 */
	
	public String getName(double x, double y){ return getName(new Point(x, y)); }
	
	public String getName(Point p)
	{
		//Gives name of point in the factory if exists
		if (_factory.get(p) != null) return _factory.get(p).getName();
		return null;
		
	}

	/**
	 * Given the name of a point, determine the coordinates.
	 * 
	 * @param name -- a String name
	 * @return a Point object containing (x,y) corresponding to name, if it has been defined.
	 */
	
	public Point getPoint(String name)
	{
		// iterate through all the point in the database and compare to the name
		for(Point p: _factory.getAllPoints()) {
			if(name.equals(p.getName())) {
				return p; 
			}
		}
		// if there is no point name exist return null
		return null; 	
	}

	/**
	 * Given a point, acquire the stored database object; facilitates
	 * singular objects and mitigates lingering copies of points.
	 * 
	 * @param pt -- a basic point
	 * @return the database entry for the point
	 */
	public Point getPoint(Point p) { return _factory.get(p); }

	/**
	 * Given a raw point (x, y), acquire the stored database object.
	 * 
	 * @param x,y -- doubles defining a point (x,y)
	 * @return the database entry for the point
	 */
	public Point getPoint(double x, double y){ return getPoint(new Point(x, y)); }
}