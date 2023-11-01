/**
* This class tests the capabilities of the Point Class
*
* @author Flynn Nisbet, Mengsrun Nit
* @date Nov. 1st, 2023
*/
package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;

class PointTest {
	@Test
	void lexTest() {
		
		//Relying on x, both ways
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 1);
		assertEquals("Point class did not order points correctly", Point.LexicographicOrdering(p1, p2),-1);
		Point p3 = new Point(100, 0);
		Point p4 = new Point(-100, 100);
		assertEquals("Point class did not order points correctly", Point.LexicographicOrdering(p3, p4), 1);
		
		//Relying on y, both ways
		Point p5 = new Point(100, 0);
		Point p6 = new Point(100, 100);
		assertEquals("Point class did not order points correctly", Point.LexicographicOrdering(p5, p6), -1);
		Point p7 = new Point(100, 1000);
		Point p8 = new Point(100, 100);
		assertEquals("Point class did not order points correctly", Point.LexicographicOrdering(p7, p8), 1);
		
		//Equal
		Point p9 = new Point(100, 100);
		Point p10 = new Point(100, 100);
		assertEquals("Point class did not order points correctly", Point.LexicographicOrdering(p9, p10), 0);
	}
	
	@Test
	void compareTest() {
		
		//Same tests as above, pushed through compareTo
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 1);
		assertEquals("Point class did not order points correctly through compareTo", p1.compareTo(p2),-1);
		Point p3 = new Point(100, 0);
		Point p4 = new Point(-100, 100);
		assertEquals("Point class did not order points correctly through compareTo", p3.compareTo(p4), 1);
		Point p5 = new Point(100, 0);
		Point p6 = new Point(100, 100);
		assertEquals("Point class did not order points correctly through compareTo", p5.compareTo(p6),-1);
		Point p7 = new Point(100, 1000);
		Point p8 = new Point(100, 100);
		assertEquals("Point class did not order points correctly through compareTo", p7.compareTo(p8),1);
		Point p9 = new Point(100, 100);
		Point p10 = new Point(100, 100);
		assertEquals("Point class did not order points correctly through compareTo", p9.compareTo(p10),0);
		assertEquals("Point class did not order points correctly through compareTo", p1.compareTo(null),1);
	}
	
	@Test
	void equalsTest() {
		
		//Tests different
		Point p1 = new Point("A", 1, 2);
		Point p2 = new Point("A", 2, 1);
		assertFalse("Point class equated different points", p1.equals(p2));
		
		//Tests same
		Point p3 = new Point("A", 2, 1);
		assertTrue("Point class did not equate equivalent points", p3.equals(p2));
		
		//Tests same point, but different name 
		Point p4 = new Point("B", 1, 2);
		assertTrue("Point class failed to equate points with the same coordinates", p4.equals(p1));
	}
	
}
