import java.util.ArrayList;

public class CrossValidation {
	public static void main(String[] args) {
		if (args.length != 3) {
			StdOut.println("Wrong arguments number");
			StdOut.println("Usage: java CrossValidation <config file> <date file> <number of partition>");
		}
		// ExampleList e = new ExampleList(new In(args[1]), new In(args[0]));
		// StdOut.println(e);

		// Divide data into K partition
		int divide = Integer.parseInt(args[2]);
		DividedExampleList del = new DividedExampleList(new In(args[1]),
				new In(args[0]), divide);

		// For 2 algorithm: Perceptron and Winnow:
		// for each(1 partiton of data: all K data):
		int correct = 0;
		int all = 0;

		for (int i = 0; i < divide; i++) {
			ExampleList el = del.get(i);
			ExampleList el2 = del.getAnythingBut(i, new In(args[0]));
			StdOut.printf(
					"-----------group %d TEST-----------------LEARNING-------------------\n",
					i);
			StdOut.print("test " + Perceptron.start(el) + "\t");
			StdOut.println("learning " + Perceptron.start(el2));
			// compare(K-1 partition's prediction result, 1 's data real result)
			int[] res = compare(Perceptron.start(el), Perceptron.start(el2));
			// matching rate of algorithm(n) += rate(n-1)
			all += res[0];
			correct += res[1];
		}
		
		// compare(K-1 partition's prediction result, 1 's data real result)
		// matching rate of algorithm(n) += rate(n-1)

		int correct2 = 0;
		int all2 = 0;
		StdOut.printf("================================================================\n");
		for (int i = 0; i < divide; i++) {
			ExampleList el = del.get(i);
			ExampleList el2 = del.getAnythingBut(i, new In(args[0]));
			StdOut.printf(
					"-----------group %d TEST-----------------LEARNING-------------------\n",
					i);
			StdOut.print("test " + Winnow.start(el) + "\t");
			StdOut.println("learning " + Winnow.start(el2));
			int[] res = compare(Winnow.start(el), Winnow.start(el2));
			all2 += res[0];
			correct2 += res[1];
		}
		StdOut.printf("Perceptron correctRate is %.2f\n", 1.0 * correct / all);
		StdOut.printf("Winnow correctRate is %.2f\n", 1.0 * correct2 / all2);

	}

	/**
	 * 
	 * @param s1
	 *            test model
	 * @param s2
	 *            traning model
	 * @return
	 */
	public static int[] compare(ArrayList<Integer> s1, ArrayList<Integer> s2) {
		// double result = 1.0f;
		int correctCount = 0;
		int allCount = s1.size();
		for (int i = 0; i < s1.size(); i++) {
			for (int j = 0; j < s2.size(); j++) {
				if (s1.get(i) == s2.get(j)) {
					correctCount++;
				}
			}
		}
		int[] res = { allCount, correctCount };

		StdOut.println("correct rate is " + res[1] + "/" + res[0] + "");
		return res;
	}
}
