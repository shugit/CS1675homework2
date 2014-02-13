import java.util.ArrayList;

public class Winnow {

	public static void main(String[] args) {
		if (args.length == 2) {
			StdOut.println("FINDING OUT WHICH FEATURE IS IMPORTANT:");
			ExampleList e = new ExampleList(new In(args[1]), new In(args[0]));
			// StdOut.println(e);
			ArrayList<Integer> result = start(e);
			StdOut.println();
			for(Integer i : result){
				StdOut.println(e.getFeatureName(i));
			}	
		} else if (args.length == 3){
			StdOut.println("COMPARING FILES");
			ExampleList e = new ExampleList(new In(args[1]), new In(args[0]));
			ExampleList el = new ExampleList(new In(args[2]), new In(args[0]));
			int[] res = CrossValidation.compare(Winnow.start(e), Winnow.start(el));
			int all = res[0];
			int correct = res[1];
			StdOut.printf("correctRate is %.2f\n", 1.0 * correct / all);
			
		} else {
			StdOut.println("Wrong arguments number");
			StdOut.println("Usage: java Perceptron <config file> <date file>");
		}
	}

	public static ArrayList<Integer> start(ExampleList el) {
		/*
		 * int[] y = new int[el.sizeOfFeature()]; for(int i =0; i<y.length;
		 * i++){ y[i] = 0; }
		 */

		ArrayList<Double> w = new ArrayList<Double>();
		for (int i = 0; i < el.sizeOfFeature(); i++) {
			w.add((double) 1.0);
		}

		int threshold = el.sizeOfFeature();
		int fault = 0;
		// double faultMax = 2.0 + 3 * el.sizeOfFeature()
		// * (1 + Math.log(el.sizeOfFeature()));
		//double faultMax = el.size() * Math.log(el.sizeOfFeature());
		double faultMax = (double)Integer.MAX_VALUE;
		for (int i = 0; i < el.size() && fault < faultMax; i++) {
			// StdOut.println(el.get(i));
			if (hypo(w, el.get(i), threshold) == (el.getResult(i) == 1 ? true
					: false)) {
			} else {
				fault++;
				if (!hypo(w, el.get(i), threshold)) {
					// If the algorithm predicts negative on a positive
					// example, then
					// for each x_i equal to 1, double the value of w_i.
					for (int j = 0; j < el.sizeOfFeature() && fault < faultMax; j++) {
						if (el.get(i).getFeature(j) == 1) {
							double newW = w.get(j) * 2.0;
							w.set(j, newW);
						}
					}
				} else {
						// If the algorithm predicts positive on a negative
						// example, then
						// for each x_i equal to 1, cut the value of w_i in
						// half.
					for (int j = 0; j < el.sizeOfFeature() && fault < faultMax; j++) {
						if (el.get(i).getFeature(j) == 1) {
							//double newW = w.get(j) / 2.0;
							w.set(j, 0.0);
						}
					}// END OF IF
				}// END OF FOR
			}// END OF IF
		} // END OF FOR
		ArrayList<Integer> correct = new ArrayList<Integer>();
		//StdOut.println("result:");
		for (int i = 0; i < el.sizeOfFeature(); i++) {
			//StdOut.printf("%d\t %.8f", i, w.get(i).doubleValue());
			if (w.get(i).compareTo(1.0) >= 0) {
				//StdOut.print("\t<-----it counts! " + el.getFeatureName(i));
				correct.add(i);
			}
			//StdOut.println();
		}
		return correct;
	} // END OF FUNCTION

	public static boolean hypo(ArrayList<Double> w, Example e, int threshold) {
		double a = 0.0f;
		for (int i = 0; i < w.size(); i++) {
			a = a + w.get(i) * e.getFeature(i);
		}
		// StdOut.println("a is " + a + " " + threshold);
		return (a>threshold/2) ? true : false;
	}
}
