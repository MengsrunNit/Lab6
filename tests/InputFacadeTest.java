/**
* This class tests the capabilities of the InputFacade Class
*
* @author Flynn Nisbet, Mengsrun Nit
* @date Nov. 1st, 2023
*/

package tests;

import java.util.*;
import org.junit.jupiter.api.Test;
import geometry_objects.Segment;
import geometry_objects.points.*;
import input.InputFacade;
import input.components.FigureNode;

class InputFacadeTest {

	@Test
	void extractFigureTest() {
		FigureNode n = InputFacade.extractFigure("single_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> m = InputFacade.toGeometryRepresentation(n);
		System.out.println(m.getKey().getPoint(new Point( 0, 0)));
		
	}

}
