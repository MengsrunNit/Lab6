package input.visitor;
import java.util.*;
import org.json.*;
import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;

public class ToJSONVisitor implements ComponentNodeVisitor{
	@Override
public Object visitFigureNode(FigureNode node, Object o) {
		
		// create PointNodeDB JSONObject and add the PointNodeDatabase in
		JSONObject pointNodeDB = new JSONObject();
		pointNodeDB.put("Points", visitPointNodeDatabase(node.getPointsDatabase(), o));
		
		// crate description JSONObject and add the it in
		JSONObject description = new JSONObject();
		description.put("Description", node.getDescription());
		
		//SegemntNode Database
		JSONObject segmentDB = new JSONObject();
		segmentDB.put("Segments", visitSegmentDatabaseNode(node.getSegments(),o));
		
		JSONArray figure = new JSONArray();
		figure.put(description);
		figure.put(pointNodeDB);
		figure.put(segmentDB);
		
		JSONObject jsonFile = new JSONObject();
		jsonFile.put("Figure", figure);
		
		return jsonFile;
		
	}

	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o) {

		// initilize the JSONArray and Map
		JSONArray jPointArray = new JSONArray();
		Map<PointNode, Set<PointNode>> list = node.getAdj();
		Set<PointNode> keySet = list.keySet(); 
		ArrayList<PointNode> keyList = new ArrayList<PointNode>(keySet);
		Set<String> segList = new HashSet<>();

		//loop through the all the keys in the SegmentNodeDB, creating new JSONObject for each key
		for(PointNode pNode1: keySet) {
			JSONObject segDict = new JSONObject();
			int i = keyList.indexOf(pNode1);
			segList.clear();

			for(PointNode pNode2: list.get(pNode1)) {
				if(i < keyList.indexOf(pNode2)) {
					segList.add(pNode2.getName());
				}
			}

			if(!segList.isEmpty()) {
				segDict.put(pNode1.getName(), segList);
				jPointArray.put(segDict);
			}

		}
		return jPointArray;
	}

	public Object visitSegmentNode(SegmentNode node, Object o) {
		return null;
	}

	public Object visitPointNode(PointNode node, Object o) {

		//Putting point info. into a new JSONObject, to be added to the PND JSONArray
		JSONObject pt=new JSONObject();
		pt.put("name", node.getName());
		pt.put("X", node.getX());
		pt.put("Y", node.getY());

		return pt;

	}

	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o) {

		//Get points from PND, then loop through and add then to a JSONArray
		JSONArray jArray = new JSONArray();
		Set<PointNode> ptDatabase = node.getPoints();

		for(PointNode p: ptDatabase) {
			jArray.put(visitPointNode(p, o));
		}

		return jArray;
	}
}