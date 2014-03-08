import java.util.ArrayList;

public class Perceptron {

	public static void main(String[] args) {
		if (args.length == 2) {
			StdOut.println("FINDING OUT WHICH FEATURE IS IMPORTANT:");
			ExampleList e = new ExampleList(new In(args[1]), new In(args[0]));
			// StdOut.println(e);
			ArrayList<Integer> result = start(e);
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
			StdOut.println("Usage: java Perceptron <config file> <date file> <test file>");
		}

			
	}

	public static ArrayList<Integer> start(ExampleList el) {
		/*
		 * int[] y = new int[el.sizeOfFeature()]; for(int i =0; i<y.length;
		 * i++){ y[i] = 0; }
		 */

		ArrayList<Integer> w = new ArrayList<Integer>();
		for (int i = 0; i < el.sizeOfFeature(); i++) {
			w.add(0);
		}

		for (int i = 0; i < el.size(); i++) { // for each example
			if (predict(w, el.get(i)) == (el.getResult(i) == 1 ? true : false)) {
			} else {				
				if (el.getResult(i) == 1) {
					for (int j = 0; j < el.sizeOfFeature(); j++) {
						//int newW = w.get(j).intValue() + el.getResult(i);
						int newW = w.get(j).intValue() + el.getFeatureOfExample(i, j);
						w.set(j, newW);
					} // END OF FOR
				} else {
					for (int j = 0; j < el.sizeOfFeature(); j++) {
						//int newW = w.get(j).intValue() - el.getResult(i);
						int newW = w.get(j).intValue() - el.getFeatureOfExample(i, j);
						w.set(j, newW);
					} // END OF FOR
				}
			} // END OF IF
		} // END OF FOR
		ArrayList<Integer> correct = new ArrayList<Integer>();
		//StdOut.println("result:");
		for (int i = 0; i < el.sizeOfFeature(); i++) {
			//StdOut.print("feature="+i + "\t" + "weight="+w.get(i) + "  \n");
			if (w.get(i) > 0) {
				//StdOut.print("\t<-----it counts! " + el.getFeatureName(i));
				correct.add(i);
			}
			//StdOut.println();
		}
		return correct;
	} // END OF FUNCTION

	public static boolean predict(ArrayList<Integer> w, Example e) {
		int result = 0;
		for (int i = 0; i < w.size(); i++) {
			result += w.get(i) * e.getFeature(i);
		}
		//StdOut.println("result is "+result);
		return result > 0 ? true : false;
	}

}
