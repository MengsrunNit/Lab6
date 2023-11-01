/**
 * This class tests the capabilities of the InputFacade class
 * 
 * @author Flynn Nisbet, Mengsrun Nit
 * @date Nov. 1st, 2023
 */
package tests;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import geometry_objects.Segment;
import geometry_objects.points.*;
import input.InputFacade;
import input.components.FigureNode;
class InputFacadeTest {

	@Test
	void single_traingle_Test() {
		FigureNode n = InputFacade.extractFigure("single_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> m = InputFacade.toGeometryRepresentation(n);
		PointDatabase pn = m.getKey();

		//Creates points mathching those in the figure - checks if there and equal
		Point p1 = new Point("A", 0, 0);
		Point p2 = new Point("B", 1, 1);
		Point p3 = new Point("C", 1, 0);
		assertTrue("Point is not within the figure's PD", pn.getPoint("A").equals(p1));
		assertTrue("Point is not within the figure's PD", pn.getPoint("B").equals(p2));
		assertTrue("Point is not within the figure's PD", pn.getPoint("C").equals(p3));

		//Creates segments that should be in figure, checks containment
		Set<Segment> segSet =  m.getValue();
		Segment ab = new Segment(p1,p2);
		Segment ac = new Segment(p1,p3);
		Segment bc = new Segment(p2,p3);
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ab));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ac));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(bc));

	}
	@Test
	void collinear_line_segmentsTest() {
		FigureNode fn = InputFacade.extractFigure("collinear_line_segments.json");
		Map.Entry<PointDatabase, Set<Segment>> m = InputFacade.toGeometryRepresentation(fn);

		//Creates points mathching those in the figure - checks if there and equal
		PointDatabase pn = m.getKey();
		Point p1 = new Point("A", 0, 0);
		Point p2 = new Point("B", 4, 0);
		Point p3 = new Point("C", 9, 0);
		Point p4 = new Point("D", 11, 0);
		Point p5 = new Point("E", 16, 0);
		Point p6 = new Point("F", 26, 0);
		assertTrue("Point is not within the figure's PD", pn.getPoint("A").equals(p1));
		assertTrue("Point is not within the figure's PD", pn.getPoint("B").equals(p2));
		assertTrue("Point is not within the figure's PD", pn.getPoint("C").equals(p3));
		assertTrue("Point is not within the figure's PD", pn.getPoint("D").equals(p4));
		assertTrue("Point is not within the figure's PD", pn.getPoint("E").equals(p5));
		assertTrue("Point is not within the figure's PD", pn.getPoint("F").equals(p6));

		//Creates segments that should be in figure, checks containment
		Set<Segment> segSet =  m.getValue();
		Segment ab = new Segment(p1,p2);
		Segment bc = new Segment(p2,p3);
		Segment cd = new Segment(p3,p4);
		Segment de = new Segment(p4,p5);
		Segment ef = new Segment(p5,p6);
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ab));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(bc));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(cd));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(de));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ef));
	}
	
	@Test
	void crossing_symmetric_triangleTest() {
		FigureNode fn = InputFacade.extractFigure("crossing_symmetric_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> m = InputFacade.toGeometryRepresentation(fn);

		//Creates points mathching those in the figure - checks if there and equal
		PointDatabase pn = m.getKey();
		Point p1 = new Point("A", 3, 6);
		Point p2 = new Point("B", 2, 4);
		Point p3 = new Point("C", 4, 4);
		Point p4 = new Point("D", 0, 0);
		Point p5 = new Point("E", 6, 0);
		assertTrue("Point is not within the figure's PD", pn.getPoint("A").equals(p1));
		assertTrue("Point is not within the figure's PD", pn.getPoint("B").equals(p2));
		assertTrue("Point is not within the figure's PD", pn.getPoint("C").equals(p3));
		assertTrue("Point is not within the figure's PD", pn.getPoint("D").equals(p4));
		assertTrue("Point is not within the figure's PD", pn.getPoint("E").equals(p5));

		//Creates segments that should be in figure, checks containment
		Set<Segment> segSet =  m.getValue();
		Segment ab = new Segment(p1,p2);
		Segment ac = new Segment(p1,p3);
		Segment bc = new Segment(p2,p3);
		Segment bd = new Segment(p2,p4);
		Segment be = new Segment(p2,p5);
		Segment cd = new Segment(p3,p4);
		Segment ce = new Segment(p3,p5);
		Segment de = new Segment(p4,p5);
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ab));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ac));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(bc));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(bd));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(be));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(cd));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(ce));
		assertTrue("Segment is not within the figure's segSet", segSet.contains(de));
	}
}