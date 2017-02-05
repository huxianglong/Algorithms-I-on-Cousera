import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class OutputTest {

	public static void main(int k, String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> randomizedqueue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item != "<") randomizedqueue.enqueue(item);
        }
		for (String s : randomizedqueue)
			StdOut.println(s);
		StdOut.println(randomizedqueue.dequeue());
		StdOut.println(randomizedqueue.size());
	}
}
