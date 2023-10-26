package input.components.segment;

import java.util.*;

import input.components.ComponentNode;
import input.components.point.PointNode;
import input.visitor.ComponentNodeVisitor;
import utilities.io.StringUtilities;

public class SegmentNodeDatabase implements ComponentNode{

	protected Map<PointNode, Set<PointNode>> _adjLists;

	public SegmentNodeDatabase() {
		_adjLists = new LinkedHashMap<>();

	}

	public SegmentNodeDatabase(Map <PointNode, Set<PointNode>> m){
		_adjLists = m;
	}
	
	public Map <PointNode, Set<PointNode>> getAdj() {
		return _adjLists;
	}

	public int numDirectedEdges() {
		int count = 0;

		for (PointNode start : _adjLists.keySet()) {
			count += _adjLists.get(start).size();
		}

		return count;
	}

	public int numUndirectedEdges() {
		int count = 0;

		for (PointNode start : _adjLists.keySet()) {
			for (PointNode end : _adjLists.get(start)) {
				// Check if the edge (pt1, pt2) and (pt2, pt1) both exist
				if (_adjLists.containsKey(end) && _adjLists.get(end).contains(start)) {
					count++;
				}
			}
		}

		// Divide by 2 since each undirected edge is counted twice
		return count / 2;
	}


	//Supposed to be private? How to test?
	public void addDirectedEdge(PointNode pt1, PointNode pt2) {

		//Checks for the keys existence, so it doesn't override.
		if(_adjLists.containsKey(pt1)) _adjLists.get(pt1).add(pt2);

		//Creates new Set, so that pt2 can be added to the _adjLists.
		else
		{
			Set<PointNode> val = new HashSet<>();
			val.add(pt2);
			_adjLists.put(pt1, val);
		}
	}

	public void addUndirectedEdge(PointNode pt1, PointNode pt2) {
		//Two directed edges, forming an undirected edge.
		addDirectedEdge(pt1, pt2);
		addDirectedEdge(pt2, pt1);
	}


	public void addAdjacencyList(PointNode key, List<PointNode> values) {

		//Checks for the keys existence, so that it doesn't override existing set values.
		if(_adjLists.containsKey(key)) _adjLists.get(key).addAll(values);

		//If it doesn't exist, creates a new Set, adds values and key to _adjLists
		else {
			Set<PointNode> val = new HashSet<>();
			val.addAll(values);
			_adjLists.put(key, val);
		}

	}

	public List<SegmentNode> asSegmentList() {

		//Loops through all keys, then their sets, adding them to a list.
		List<SegmentNode> segList = new ArrayList<>();
		for(PointNode pt: _adjLists.keySet()) {
			for(PointNode val: _adjLists.get(pt)) {

				SegmentNode n = new SegmentNode(pt, val);
				segList.add(n);
			}
		}
		return segList;
	}

	public List<SegmentNode> asUniqueSegmentList() {

		List<SegmentNode> segList = new ArrayList<>();
		for(PointNode pt: _adjLists.keySet()) {
			for(PointNode val: _adjLists.get(pt)) {

				//Same as asSL, but only adds to the list if its opposite segment, which is the same, is in the list
				SegmentNode n = new SegmentNode(pt, val);
				SegmentNode temp = new SegmentNode(val, pt);
				if(!(segList.contains(temp)))segList.add(n);
			}
		}
		return segList;
	}
	public Object accept(ComponentNodeVisitor node, Object o) {

		return node.visitSegmentDatabaseNode(this, o);
	}
}