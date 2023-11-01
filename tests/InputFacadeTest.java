package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.InputFacade;
import input.components.FigureNode;

class InputFacadeTest {

	@Test
	void extractFigureTest() {
		FigureNode n = InputFacade.extractFigure("single_triangle.json");
		Map.Entry<PointDatabase, Set<Segment>> m = InputFacade.toGeometryRepresentation(n);
		m.getKey().getPoints()
	}

}
