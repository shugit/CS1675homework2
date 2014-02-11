import java.util.ArrayList;


public class ExampleList {
	ArrayList<Example> list = new ArrayList<Example>();
	String[] featureName;

	
	public ExampleList(In dataIn, In configIn){		
		this(dataIn.readAllStrings(),configIn.readAllStrings());			
	}
	
	public ExampleList(String[] data, String[] config){
		String yes = config[0].split(",")[0];		
		for(String string: data){
			Example e = new Example(string,yes);
			list.add(e);
		}
		
		featureName = new String[config.length-1];
		for(int i = 0; i < config.length-1; i++){
			featureName[i] = config[i+1].split(",")[0];
		}		
	}

	public ExampleList(In configIn) {
		String[] config = configIn.readAllStrings();
		featureName = new String[config.length-1];
		for(int i = 0; i < config.length-1; i++){
			featureName[i] = config[i+1].split(",")[0];
		}	
	}

	public void add(Example e){
		list.add(e);
	}
	
	public Example get(int i){
		return list.get(i);
	}
	public int size(){
		return list.size();
	}
	
	public int getResult(int i){
		return list.get(i).getResult();
	}
	
	/**
	 * 
	 * @param i list number
	 * @param j feature number
	 * @return
	 */
	public int getFeatureOfExample(int i, int j){
		return list.get(i).getFeature(j);
	}
	
	public String getFeatureName(int i){
		return featureName[i];
	}
	
	public int sizeOfFeature(){
		return featureName.length;
	}
	public String toString(){
		String a = "features:-----------------------\n";
		for(String s : featureName){
			a += "\t"+s+"\n";
		}		
		String r ="Examples:-----------------\n";
		for(Example e: list){
			r += "\t"+list.indexOf(e)+" "+e.toString();
		}
		return a+r;
	}
	
}
