 package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private String attributeLine;
	private String dataTypeLine;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
	 * @throws IOException 
     */
	@Override 
	public Tuple next(){
		if (getAttribute == true){
			try{
				String value = br.readLine();
				if (value != null){
				tuple = new Tuple(attributeLine, dataTypeLine, value);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				}
				else
					return null;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	
		else {
			try{
				attributeLine = br.readLine();
				dataTypeLine = br.readLine();
				String tupleLine = br.readLine();
				tuple = new Tuple(attributeLine, dataTypeLine, tupleLine);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			
			}
		}
		
//		System.out.println("Table, got tuple " + tuple + " with " + tuple.getAttributeList().size() + " attrs.");
		return tuple;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}