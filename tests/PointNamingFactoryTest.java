package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.*;
class PointNamingFactoryTest {

	public PointNamingFactory build() {
		ArrayList<Point> l = new ArrayList<Point>();
		l.add(new Point(1, 2));
		l.add(new Point(9, 4));
		l.add(new Point(4, 12));
		l.add(new Point(0, 15));
		l.add(new Point(-5, 0));
		l.add(new Point(100, 0));
		
		//Testing constructor
		PointNamingFactory pnf = new PointNamingFactory(l);
		return pnf;
	}

	//Put test tested through build, because of the constructor, but to be safe.
	@Test
	void putAndGetTest() 
	{
		PointNamingFactory pnf = build();
		
		//Existing points, should be true
		assertTrue(pnf.get(1, 2).equals(new Point(1, 2)));
		assertTrue(pnf.get(new Point(-5, 0)).equals(new Point(-5, 0)));
		assertTrue(pnf.get(1, 2).equals(pnf.get(new Point(1, 2))));
		
		//Equating to wrong point/not in pnf
		assertFalse(pnf.get(1, 2).equals(new Point(9, 4)));
		assertFalse(pnf.get(new Point(0, 15)).equals(new Point(0, 5)));
		assertFalse(pnf.get(4, 12).equals(pnf.get(new Point(1, 2))));
		
		//Add a new one
		Point a = new Point(0, 0);
		assertTrue(pnf.get(a) == null);
		pnf.put(a);
	
		assertTrue(pnf.get(new Point("G", 0, 0)).equals(new Point("G", 0, 0)));
		assertTrue(pnf.get(a).equals(a));
		assertTrue(pnf.get(a).equals(new Point("G", 0, 0)));
		assertTrue(pnf.get(0, 0).getName().equals("*_G"));

		
	}
	@Test
	void containsTest() 
	{
		PointNamingFactory pnf = build();

		//Good and happy valuess
		assertTrue(pnf.contains(new Point("*_A", 1, 2)));
		assertTrue(pnf.contains(1, 2));
		assertTrue(pnf.contains(new Point("*_A", 1, 2)));
		assertTrue(pnf.contains(new Point(100, 0)));
		assertTrue(pnf.contains(0, 15));
		assertTrue(pnf.contains(new Point("*_E", -5, 0)));
		
		//Bad values
		assertFalse(pnf.contains(new Point(10, 2)));
		assertFalse(pnf.contains(1, 345));
		assertFalse(pnf.contains(new Point("*_A", 13, 2)));
		assertFalse(pnf.contains(new Point(1, 4)));
		assertFalse(pnf.contains(1, 0));
	}
	@Test
	void nameTest() 
	{
		PointNamingFactory pnf = build();
		pnf.put(new Point(1, 2));
		pnf.put(new Point(9, 4));
		pnf.put(new Point(4, 12));
		pnf.put(new Point(0, 15));
		pnf.put(new Point(-5, 0));
		pnf.put(new Point(100, 0));
		pnf.put(new Point(2, 2));
		pnf.put(new Point(3, 4));
		pnf.put(new Point(4, 12));
		pnf.put(new Point(5, 15));
		pnf.put(new Point(5, 0));
		pnf.put(new Point(50, 0));
		pnf.put(new Point(6, 2));
		pnf.put(new Point(7, 4));
		pnf.put(new Point(8, 12));
		pnf.put(new Point(9, 15));
		pnf.put(new Point(15, 0));
		pnf.put(new Point(1100, 0));
		pnf.put(new Point(12, 2));
		pnf.put(new Point(94, 4));
		pnf.put(new Point(6, 12));
		pnf.put(new Point(6, 15));
		pnf.put(new Point(75, 0));
		pnf.put(new Point(800, 0));
		pnf.put(new Point(4, 2));
		pnf.put(new Point(8, 4));
		pnf.put(new Point(13, 12));
		pnf.put(new Point(245, 15));
		pnf.put(new Point(-53, 0));
		pnf.put(new Point(1005, 0));
		pnf.put(new Point(1, 234));
		pnf.put(new Point(9, 4432));
		pnf.put(new Point(4, 12324));
		pnf.put(new Point(032, 15));
		pnf.put(new Point(-523, 0));
		pnf.put(new Point(100, 30));
		pnf.put(new Point(1, 24));
		pnf.put(new Point(9, 423));
		pnf.put(new Point(42, 12));
		pnf.put(new Point(21, 15));
		pnf.put(new Point(-35, 0));
		pnf.put(new Point(1070, 0));
		pnf.put(new Point(132, 12));
		pnf.put(new Point(2435, 15));
		pnf.put(new Point(-523, 0));
		pnf.put(new Point(10035, 0));
		pnf.put(new Point(1, 2334));
		pnf.put(new Point(93, 4432));
		pnf.put(new Point(432, 12324));
		pnf.put(new Point(0321, 15));
		pnf.put(new Point(-5233, 0));
		pnf.put(new Point(1004, 30));
		pnf.put(new Point(13, 24));
		pnf.put(new Point(92, 423));
		pnf.put(new Point(422, 12));
		pnf.put(new Point(21, 215));
		pnf.put(new Point(-35, 20));
		pnf.put(new Point(10701, 0));
		System.out.println(pnf);

	}
}

