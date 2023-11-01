/**
* This class allows for interaction with underlying classed in the input package. It
* gives users a better sense of what they can do with this system.
*
* @author Flynn Nisbet, Mengsrun Nit
* @date Nov. 1st, 2023
*/

package input;

import java.util.*;
import geometry_objects.*;
import geometry_objects.points.*;
import input.builder.*;
import input.components.*;
import input.components.point.*;
import input.parser.*;


public class InputFacade
{
	/**
	 * A utility method to acquire a figure from the given JSON file:
	 *     Constructs a parser
	 *     Acquries an input file string.
	 *     Parses the file.
     *
	 * @param filepath -- the path/name defining the input file
	 * @return a FigureNode object corresponding to the input file.
	 */
	public static FigureNode extractFigure(String filepath)
	{
		//Parses the JSON file in the same way as we have done previously, but returns FigureNode
		GeometryBuilder GeoBuilder = new GeometryBuilder();
		JSONParser parser = new JSONParser(GeoBuilder);
		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filepath);
		return (FigureNode) parser.parse(figureStr);
	}
	
	/**
	 * 1) Convert the PointNode and SegmentNode objects to a Point and Segment objects 
	 *    (those classes have more meaningful, geometric functionality).
	 * 2) Return the points and segments as a pair.
     *
	 * @param fig -- a populated FigureNode object corresponding to a geometry figure
	 * @return a point database and a set of segments
	 */
	
	public static Map.Entry<PointDatabase, Set<Segment>> toGeometryRepresentation(FigureNode fig)
	{
		// Calls helper methods, returns map of their products
		PointDatabase pd = toPointDatabase(fig.getPointsDatabase().getPoints());
		Set<Segment> segSet = toSegmentSet(fig.getSegments().getAdj());
		return new AbstractMap.SimpleEntry<>(pd, segSet);
		
	}
	
	private static PointDatabase toPointDatabase(Set<PointNode> pnd) {
		
		//Loops through the given PND, adds each PN as Points in PointDatabase
		PointDatabase pd = new PointDatabase();
		for(PointNode pn: pnd) {
			pd.put(pn.getName(), pn.getX(), pn.getX());
		}
		return pd;
	}
	private static HashSet<Segment> toSegmentSet(Map<PointNode, Set<PointNode>> ss) {
		
		//Loops throught the given _adjLists, making Segment objects for each value in a given key
		HashSet<Segment> segSet = new HashSet<Segment>();
		for (PointNode k : ss.keySet()) {
			Set<PointNode> start = ss.get(k);
			for (PointNode v : start) {
				//Adds them to our Segment Set by making them Points
				segSet.add(new Segment(new Point(k.getName(), k.getX(), k.getY()), new Point(v.getName(), v.getX(), v.getY())));
			}
		}
		return segSet;
	}
}
