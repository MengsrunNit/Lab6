/**
* This class tests the capabilities of the PointDatabase Class
*
* @author Flynn Nisbet, Mengsrun Nit
* @date Nov. 1st, 2023
*/

package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList; // import the ArrayList class
import org.junit.jupiter.api.Test;
import geometry_objects.points.PointDatabase;
import geometry_objects.points.*;
class PointDatabaseTest {
	public PointDatabase build() {
		// create a PointNodeDatabase
		PointDatabase pd;
		
		//create a list for input the points in
		ArrayList<Point> myList = new ArrayList<Point>();
		
		Point p1 = new Point("A",6,3);
		Point p2 = new Point("B", 4.3, 7.2);
		Point p3 = new Point("C", 4.2, 3);
		Point p4 = new Point("D", 7, 7);
		
		
		// add Point into the list
		myList.add(p1);
		myList.add(p2);
		myList.add(p3);
		myList.add(p4);
		
		// add list into the database
		pd = new PointDatabase(myList);
		
		return pd;
		
	}
	
	@Test
	void getNameTest() {
		// initlized the build() function
		PointDatabase pd = build();
		
		// testing assert true with the build() function
		assertTrue("PointDatabase failed to receive the correct name based on point information", pd.getName(6, 3) == "A");
		assertTrue("PointDatabase failed to receive the correct name based on point information", pd.getName(4.3, 7.2) == "B");
		assertTrue("PointDatabase failed to receive the correct name based on point information", pd.getName(4.2, 3) == "C");
		assertTrue("PointDatabase failed to receive the correct name based on point information", pd.getName(7, 7) == "D");
		
		// checking the false case
		assertFalse("PointDatabase returned true for an invalid name", pd.getName(6, 3) == "B");
		assertFalse("PointDatabase returned true for an invalid name", pd.getName(4.3, 7.2) == "C");
		assertFalse("PointDatabase returned true for an invalid name", pd.getName(4.2, 3) == "A");
		assertFalse("PointDatabase returned true for an invalid name", pd.getName(7, 7) == "B");
		
		
		
	}
	@Test
	void getPointNameTest() {
		// initialized the build funciton
		PointDatabase pd = build();
		
		// add more point into the database
		Point p1 = new Point("A", 6,3);
		Point p2 = new Point("B", 4.3, 7.2);
		Point p3 = new Point("C", 4.2, 3);
		Point p4 = new Point("D", 7, 7);
		
		// checking true if it given the right name
		assertTrue("PointDatabase failed to receive the correct name based on point", pd.getName(p1) == "A");
		assertTrue("PointDatabase failed to receive the correct name based on point", pd.getName(p2) == "B");
		assertTrue("PointDatabase failed to receive the correct name based on point", pd.getName(p3) == "C");
		assertTrue("PointDatabase failed to receive the correct name based on point", pd.getName(p4) == "D");
		
	}
	@Test
	void sizeTest() {
		//initlize the build function
		PointDatabase pd = build();
		
		assertEquals(pd.size(),4);
		
		// add more point to count number of element in db
		pd.put("E", 1, 2);
		pd.put("F", 9, 0);
		pd.put("G", 3, 1);
		assertEquals("PointDatabse failed to correctly return its size", pd.size(),7);
		
		// add more point to db to count
		pd.put("H", 3, 0.3);
		pd.put("X", 7, 2);
		assertEquals("PointDatabse failed to correctly return its size", pd.size(),9);
		
		// adding point that already exist and with duplicate name
		pd.put("Y", 3, 1);
		pd.put("X", 7, 2);
		assertEquals("PointDatabse failed to correctly return its size", pd.size(),9);
		
		
		
		
	}
	
	@Test
	void getPointTest() {
		// initlize the build function
		PointDatabase pd = build();
		
		// create the point to test
		Point p1 = new Point("A", 6,3);
		Point p2 = new Point("B", 4.3, 7.2);
		Point p3 = new Point("C", 4.2, 3);
		Point p4 = new Point("D", 7, 7);
		
		// checking whether it return the right point
		assertTrue("PointDatabse failed to get a point based on name", pd.getPoint("A").equals(p1));
		assertTrue("PointDatabse failed to get a point based on name", pd.getPoint("B").equals(p2));
		assertTrue("PointDatabse failed to get a point based on name", pd.getPoint("C").equals(p3));
		assertTrue("PointDatabse failed to get a point based on name", pd.getPoint("D").equals(p4));
		
		// checking getPoint with no-name in the db exist which return null
		assertTrue("PointDatabse failed to return a null name for a non-existant point", pd.getPoint("E") == null);
		assertTrue("PointDatabse failed to return a null name for a non-existant point", pd.getPoint("X") == null);
		assertTrue("PointDatabse failed to return a null name for a non-existant point", pd.getPoint("Y") == null);
		assertTrue("PointDatabse failed to return a null name for a non-existant point", pd.getPoint("Z") == null);
	}
	@Test
	void putTest() {
		// initlized the build() funciton
		PointDatabase pd = build();
		
		// add more points into the database
		pd.put("E", 1, 1);
		pd.put("F", 3, 0.7);
		pd.put("G", 3, 2.3);
		pd.put("H", 1.2, 1.6);
		
		// checking whether it is the point return the right name
		assertTrue("PointDatabse failed to get a point based on name after putting", pd.getName(1, 1)== "E");
		assertTrue("PointDatabse failed to get a point based on name after putting", pd.getName(3, 0.7)== "F");
		assertTrue("PointDatabse failed to get a point based on name after putting", pd.getName(3, 2.3)== "G");
		assertTrue("PointDatabse failed to get a point based on name after putting", pd.getName(1.2, 1.6)== "H");
		
		// checking the point return false if it is not the right name
		assertFalse("PointDatabse failed to return a null name for a non-existant point", pd.getName(1,4.2) == "E");
		assertFalse("PointDatabse failed to return a null name for a non-existant point", pd.getName(9.3,41) == "F");
		assertFalse("PointDatabse failed to return a null name for a non-existant point", pd.getName(1.3,1) == "G");
		assertFalse("PointDatabse failed to return a null name for a non-existant point", pd.getName(2.3,41) == "H");
		
	}
	
	@Test
	void getPoint_pTest() {
		// initlized the build() funciton
		PointDatabase pd = build();
		
		// initlize the point
		Point p1 = new Point("A", 6,3);
		Point p2 = new Point("B", 4.3, 7.2);
		Point p3 = new Point("C", 4.2, 3);
		Point p4 = new Point("D", 7, 7);
		
		// compare the point after getting it
		assertTrue("PointDatabase failed to recognize a point that should be in the system", pd.getPoint(p1).equals(p1));
		assertTrue("PointDatabase failed to recognize a point that should be in the system", pd.getPoint(p2).equals(p2));
		assertTrue("PointDatabase failed to recognize a point that should be in the system", pd.getPoint(p3).equals(p3));
		assertTrue("PointDatabase failed to recognize a point that should be in the system", pd.getPoint(p4).equals(p4));
		
		// checking the false case
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(p1).equals(p2));
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(p2).equals(p1));
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(p3).equals(p4));
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(p4).equals(p1));
		
	}
	
	@Test
	void getPoint_xyTest(){
		// initlized the build() funciton
		PointDatabase pd = build();
		
		// initlie the point to compare
		Point p1 = new Point("A", 6,3);
		Point p2 = new Point("B", 4.3, 7.2);
		Point p3 = new Point("C", 4.2, 3);
		Point p4 = new Point("D", 7, 7);
		
		// comparing the x,y with the point
		assertTrue("PointDatabase failed to recognize a point that should be in the system based on coordinates", pd.getPoint(6, 3).equals(p1));
		assertTrue("PointDatabase failed to recognize a point that should be in the system based on coordinates", pd.getPoint(4.3, 7.2).equals(p2));
		assertTrue("PointDatabase failed to recognize a point that should be in the system based on coordinates", pd.getPoint(4.2, 3).equals(p3));
		assertTrue("PointDatabase failed to recognize a point that should be in the system based on coordinates", pd.getPoint(7,7).equals(p4));
		
		// checking the false case
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(6, 3).equals(p3));
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(4.3, 7.2).equals(p1));
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(4.2, 3).equals(p4));
		assertFalse("PointDatabase recognized a point that should not be in the system", pd.getPoint(7,7).equals(p2));
		
	}
	
}