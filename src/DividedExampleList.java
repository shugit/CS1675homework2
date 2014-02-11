import java.util.ArrayList;


public class DividedExampleList {
	ArrayList<ExampleList> list = new ArrayList<ExampleList>();
	
	public DividedExampleList(In dataIn, In configIn,int divident){
		String[] data = dataIn.readAllStrings();
		String[] config = configIn.readAllStrings();		
		
		
		ArrayList<ArrayList<String>> stringList = new ArrayList<ArrayList<String>>();
		for(int i = 0; i<divident; i++){
			stringList.add(new ArrayList<String>());
		}
		
		for(int i = 0; i<data.length; i++){
			int r = i%divident;
			ArrayList<String> theList = stringList.get(r);
			theList.add(data[i]);
		}
		
		for(int i = 0; i<divident; i++){
			String[] dividedData = new String[stringList.get(i).size()];
			for(int j = 0; j<dividedData.length; j++){
				dividedData[j] = stringList.get(i).get(j);				
			}
			list.add(new ExampleList(dividedData,config));
		}		
		
	}
	
	public ExampleList get(int i){
		return list.get(i);
	}

	public ExampleList getAnythingBut(int n,In configIn) {
		ExampleList r = new ExampleList(configIn);
		//ArrayList<String> a = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			if(i != n){
				for(int j = 0; j< list.get(i).size(); j++){
					r.add(list.get(i).get(j));
				}
			}
		}
		return r;
	}
}
