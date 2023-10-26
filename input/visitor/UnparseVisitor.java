package input.visitor;

import java.util.AbstractMap;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.io.StringUtilities;

//
// This file implements a Visitor (design pattern) with 
// the intent of building an unparsed, String representation
// of a geometry figure.
//
public class UnparseVisitor implements ComponentNodeVisitor
{

	public Object visitFigureNode(FigureNode node, Object o)
	{
		// Unpack the input object containing a Stringbuilder and an indentation level
		@SuppressWarnings("unchecked")

		//Disect pair into its two pieces so they're accessable
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//Formatting output
		sb.append("Figure:"+ "\n{\n");
		sb.append(StringUtilities.indent(level + 1) + "Description : \"" + node.getDescription() + "\"\n");
		sb.append(StringUtilities.indent(level + 1) + "Points:" + " \n" + StringUtilities.indent(level + 1) + "{\n");
		node.getPointsDatabase().accept(this, pair);
		sb.append(StringUtilities.indent(level + 1) + "}\n");
		sb.append(StringUtilities.indent(level + 1) + "Segments:" + " \n" + StringUtilities.indent(level + 1) + "{\n");
		node.getSegments().accept(this, pair);
		sb.append(StringUtilities.indent(level + 1) + "}\n}");

		return sb;
	}

	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o)
	{
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//Unparsing, loop through _adjLists key set, then its values, formatting them appropriately
		for(PointNode k: node.getAdj().keySet()) {
			sb.append(StringUtilities.indent(level + 2) + k.getName() + ": ");
			for (PointNode v: node.getAdj().get(k)) {
				sb.append(v.getName() + " ");		
			}	
			sb.append("\n");
		}
		return sb;
	}

	/**
	 * This method should NOT be called since the segment database
	 * uses the Adjacency list representation
	 */

	public Object visitSegmentNode(SegmentNode node, Object o)
	{
		return null;
	}

	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		//Loops through database, unparses nodes and formats
		for(PointNode point: node.getPoints()) {
			sb.append(StringUtilities.indent(level + 2) + "Point(" +point.getName() + ")(" + point.getX() + ", " + point.getY() + ")");
			sb.append("\n");
		}
		return sb;
	}

	public Object visitPointNode(PointNode node, Object o)
	{
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = (AbstractMap.SimpleEntry<StringBuilder, Integer>)(o);
		StringBuilder sb = pair.getKey();
		int level = pair.getValue();

		sb.append(StringUtilities.indent(level + 2) + node.toString());

		return sb;
	}
}