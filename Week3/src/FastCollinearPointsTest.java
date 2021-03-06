import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FastCollinearPointsTest {

	private int n = 20;
	private Point[] points = new Point[n]; 
	private FastCollinearPoints collinear;
	private LineSegment line;
	@Before
	public void setUp() {
		for (int i = 0; i < n; i++) {
        	int x = 1000 * i;
         	int y = 10000;
         	points[i] = new Point(x, y);
      	}
		collinear = new FastCollinearPoints(points);
		line = new LineSegment(points[0], points[n-1]);
	}
	
	
	public void maxLineSegmentTest_1() {
		assertEquals(line, collinear.maxLineSegment(points, 1, 9));
	}

	
	public void maxLineSegmentTest_2() {
		points[0] = new Point(3000, 10000);
		collinear = new FastCollinearPoints(points);
		assertEquals(line, collinear.maxLineSegment(points, 1, 2));
	}
}
