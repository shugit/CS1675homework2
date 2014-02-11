import java.util.ArrayList;


public class Example {
	int result;
	ArrayList<Integer> features = new ArrayList<Integer>();
	
	public Example(String s, String yes){
		String splits[] = s.split(",");
		//features = new int[splits.length-3];
		
		for(int i = 0; i<=splits.length-3; i++){
			if(splits[i+2].compareToIgnoreCase("y") == 0){
				features.add(1);
			} else {
				features.add(0);
			}
		}			
		if(splits[1].compareToIgnoreCase(yes) == 0){
			result = 1;
		} else {
			result = 0;
		}
	}
	
	public int getResult(){
		return result;
	}
	
	public int getFeature(int i){
		return features.get(i);
	}
	
	public int getFeatureSize(){
		return features.size();
	}
	
	public String toString(){
		String r = "result: "+result+"\t";
		for(int i : features){
			r += " "+i+" ";
		}
		r+="\n";
		return r;
	}
}
