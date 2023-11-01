package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import geometry_objects.points.*;

import org.junit.jupiter.api.Test;

class PointTest {

	@Test
	void lexTest() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 1);
		
		//assertTrue(LexicographicOrdering(p1, p2) == -1);
	}
	@Test
	void equalsTest() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 1);
		
		//assertFalse(equals(p1, p2));
	}

}
