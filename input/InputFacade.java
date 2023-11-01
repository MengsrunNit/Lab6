package input;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import geometry_objects.*;
import geometry_objects.points.*;
import input.builder.*;
import input.components.*;
import input.components.point.PointNode;
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
		GeometryBuilder GeoBuilder = new GeometryBuilder();
		JSONParser parser = new JSONParser(GeoBuilder);
		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filepath);
		return (FigureNode)parser.parse(figureStr);
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
		// TODO
		PointDatabase pd = new PointDatabase();
		for(PointNode pn: fig.getPointsDatabase().getPoints()) {
			pd.put(pn.getName(), pn.getX(), pn.getX());
		}
		
		return
		
	}

    //	
	// TODO: implement other support methods to facilitate the toGeometryRepresentation method
	//
}
