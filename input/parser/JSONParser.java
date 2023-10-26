/**
 * This class parses JSON files, extracting the figure and its underlying features.
 * 
 * @author Flynn Nisbet, Mengsrun Nit
 * @since 10 - 05 - 2023
 */


package input.parser;
import java.util.ArrayList;
import org.json.*;
import input.builder.*;
import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

public class JSONParser
{
	protected ComponentNode  _astRoot;
	protected DefaultBuilder _builder;

	public JSONParser(DefaultBuilder b)
	{
		_astRoot = null;
		_builder = b;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
	}

	public ComponentNode parse(String str) throws ParseException {
		try{
			// Parsing is accomplished via the JSONTokenizer class.
			JSONTokener tokenizer = new JSONTokener(str);
			JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();

			if(!JSONroot.has("Figure")) {
				throw new ParseException("Invalid JSON: missing 'figure'");
			}

			// extracting everything inside FigureNode 
			JSONObject figureObj = JSONroot.getJSONObject("Figure"); 
			String description = figureObj.getString("Description"); 
			PointNodeDatabase pointNodeDB = extractPointNodeDB(figureObj); 
			SegmentNodeDatabase segmentNodeDB = extractSegmentNodeDB(figureObj, pointNodeDB); 

			return _builder.buildFigureNode(description, pointNodeDB, segmentNodeDB);

		} catch(JSONException e) {
			throw new ParseException("Error parsing JSONS: " );
		}
	}

	public PointNodeDatabase extractPointNodeDB(JSONObject figureObj) {
		// Retrieve the JSONarray from the JSONObj: Point
		JSONArray pointArray = figureObj.getJSONArray("Points");
		ArrayList<PointNode> pointNodeDB = new ArrayList<PointNode>();

		//iterate over each point object and add to the database 
		for(int i =0; i< pointArray.length(); i++) {

			JSONObject pointObj = pointArray.getJSONObject(i); 

			String pointName = pointObj.getString("name"); 
			Double pointX = pointObj.getDouble("x");
			Double pointY = pointObj.getDouble("y");

			//put that PointNode into PointNote Database
			pointNodeDB.add(_builder.buildPointNode(pointName, pointX, pointY));
		}
		return _builder.buildPointDatabaseNode(pointNodeDB); 
	}


	public SegmentNodeDatabase extractSegmentNodeDB(JSONObject figureObj, PointNodeDatabase _pointNodeDB) {

		// Copy the segments into segmentArray
		JSONArray segmentArray = figureObj.getJSONArray("Segments");
		SegmentNodeDatabase _segmentNodeDb = _builder.buildSegmentNodeDatabase();

		// Iterate over each segment object and add to the database
		for (int i = 0; i < segmentArray.length() ; i++) {
			// Getting each heading in segment
			JSONObject segmentObj = segmentArray.getJSONObject(i);

			//getting a firstPoiintKey
			String startPointName = segmentObj.keys().next();

			//getting the key Array JSONObject to extract
			JSONArray endPointNames = segmentObj.getJSONArray(startPointName);
			PointNode startPointNode = _pointNodeDB.getPoint(startPointName);

			if (startPointNode == null) {
				throw new ParseException("Invalid Segment:" + startPointName);
			}

			// Iterate over the array of the end points name and create edge
			for (int j = 0; j < endPointNames.length(); j++) {

				//getting the PointNode Name from the JSONArray
				String endPointName = endPointNames.getString(j);

				//retrieve the pointNode usingName
				PointNode endPointNode = _pointNodeDB.getPoint(endPointName);

				// If the end point could not be found, throw an exception
				if (endPointNode == null) {
					throw new ParseException("Invalid Segment:" + endPointName);
				}
				//Use builder to add segments to the DB
				_builder.addSegmentToDatabase(_segmentNodeDb, startPointNode, endPointNode);
			}

		}
		return _segmentNodeDb;
	}
}