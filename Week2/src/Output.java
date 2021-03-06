import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 */

/**
 * @author qwe95
 *
 */
public class Output {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> randomizedqueue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			randomizedqueue.enqueue(item);
			StdOut.println(item);
        }
		for (String s : randomizedqueue)
			StdOut.println(s);
		StdOut.println(randomizedqueue.dequeue());
		StdOut.println(randomizedqueue.size());
	}
}
