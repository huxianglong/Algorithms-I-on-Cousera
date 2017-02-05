import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
/*
 * compile: javac-algs4 FastCollinearPoints.java
 * run:     java-algs4 FastCollinearPoints input50.txt
 * any line with #### is used for testing.
 */

public class FastCollinearPoints {
	/*  This is intended to use quick sort to solve the problem of collinear points.
	 *	For every point in the array, sort it using Arrays.sort() to select the collinear points.
	 *  Once finished, remove it from the arrays to fasten the process of next point.
	 */
	private LineSegment[] s;  //  array of segments.
	private int n;            //  the number of segments.

	public FastCollinearPoints(Point[] points) {
		int N = points.length;
		//  This paragraph is to throw the exception.
		for (int i = 0; i < N; i++) {
            if (points[i] == null) 
                throw new NullPointerException();
            for (int j = i+1; j < N; j++)
                if (points[i].compareTo(points[j]) == 0) 
                    throw new IllegalArgumentException();
        }		 
		Point[] p = points.clone();  //  a copy of the parameter array, inherited from the Object class.
		//####for (Point b : p) StdOut.println(b);
		ArrayList<LineSegment> container = new ArrayList<LineSegment>(); //  use this ArrayList to store the information.
		
		for (int i = 0; i < N; i++){
			Point point = points[i];
			Arrays.sort(p, point.slopeOrder());
			/*####StdOut.println("--------------------------------------------"); 
			for (Point m : p) 
        		StdOut.println(m);
        	StdOut.println("--------------------------------------------");
        	*/
			assert p[0] == point;  //  To check the validity of slopeOrder.
   		/*  
   		 * 
   		 *  p is the array that we use to find the collinear points, while points is the 
   		 *  original array that we use to control the loop.
   		 *  The next problem is to find the collinear points, it is a little complicated.
   		 *  The general idea is to utilize the fact that the array is sorted.
   		 *  The difficulty is in that the problem of segment, it's really hard to find the longest segment.
   		 *  Plus, it's also hard to avoid subsegments, but it can be fixed in the segments() methods, 
         *
         *  After one day, it's quite simple to solve the problem of maximal segment, since Array.sort()
         *  seems to use compareTo() method to sort the array, which means the start point is the first one
         *  and the end point is the last one.
   		 */
   			for (int j = 1, k = j + 2; k < N; ) {
   				//####StdOut.printf("%d, %d, %d\n", i, j, k);
   				// Loop until the one that is not collinear.
   				while (isCollinear(point, p[j], p[k])) {
   					if (k < N - 1) k++;
   					else if ( k == N - 1) { k++; break;}
   				}
   				/*  k is the point after the end point
   				 *  If k-j>2, means that we've found some points which are collinear,
   				 *  we will only add it once if that is the start point.
				 */
   				if (k - j > 2) {
   					//#####StdOut.printf("%d, %d, %d\n", i, j, k);
               		LineSegment item = maxLineSegment(p, j, k - 1);
               		if (item != null) container.add(item);
               		j = k; k = j + 2;
            	}
            	else {  j++; k++;  }
			}
		}
     	n = container.size();
     	s = new LineSegment[n];
      	// Only if there are collinear segments
      	for (int i = 0; i < n; ++i)
        	s[i] = container.get(i);
        /* ####StdOut.println("#################################################");
        for (LineSegment o : s) {
         	StdOut.println(o);
        
      }
      StdOut.println("##########################################");
         */

   	}
  	/*
     *  To check whether the given three points are collinear.
     *  
     */  	
	private boolean isCollinear(Point p1, Point p2, Point p3) {
		double slope_1 = p1.slopeTo(p2);
		double slope_2 = p1.slopeTo(p3);
		if (Double.compare(slope_1, slope_2) != 0) return false;
		else return true;
	}
  	/*
     *  This returns a LineSegment if the given point is the start point. This is designed
     *  to avoid the problem of repeated LineSegments, the problem of subsegments is automatically
     *  solved.
     *  
     */
   	private LineSegment maxLineSegment(Point[] p, int i, int j) {
   		//  The following is copying the collinear points.
    	int size = j - i + 2;
      	Point[] q = new Point[size];
      	q[0] = p[0];
      	for (int k = 1; k < size; k++)
         	q[k] = p[i + k - 1];
      	Arrays.sort(q);
      	//#####LineSegment item;// = new LineSegment(q[0], q[size-1]);
      	//  only add the segment once, and Arrays.sort() automatically arrange them 
      	//  natural order.
      	if (q[0] == p[0]) {
      		/*####StdOut.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
      		for (Point w : q) {
         		StdOut.println(w);}
         		StdOut.println(new LineSegment(q[0], q[size-1]));
         		StdOut.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         	*/
      		return new LineSegment(q[0], q[size-1]);
      	}
      	else return null;
   	}   

  	public int numberOfSegments()
   	{  return n;  }
   
   	public LineSegment[] segments()
   	{  return s;  }


	//  This is a test client.
    public static void main(String[] args) {

    	// read the n points from a file
      	In in = new In(args[0]);
        int n = in.readInt();
      	Point[] points = new Point[n];
      	for (int i = 0; i < n; i++) {
        	int x = in.readInt();
         	int y = in.readInt();
         	points[i] = new Point(x, y);
      	}

      	// draw the points
      	StdDraw.enableDoubleBuffering();
      	StdDraw.setXscale(0, 32768);
      	StdDraw.setYscale(0, 32768);
      	for (Point p : points) {
         	p.draw();
      	}
      	StdDraw.show();

      	// print and draw the line segments
      	FastCollinearPoints collinear = new FastCollinearPoints(points);
      	//#####StdOut.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      	StdOut.println(collinear.numberOfSegments());
      	for (LineSegment segment : collinear.segments()) {
         	StdOut.println(segment);
         	segment.draw();
      }
      StdDraw.show();
   }
}