package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	int j = -1;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple temp = child.next();
		if (tuplesResult.isEmpty()){
			while (temp != null){
				tuplesResult.add(temp);
				temp = child.next();
			}
		}
		Collections.sort(tuplesResult,new Comparator<Tuple>(){
            public int compare(Tuple tuple1,Tuple tuple2){
                  for (int i=0; i<tuple1.getAttributeList().size(); i++){
                	  if (tuple1.getAttributeName(i).equals(orderPredicate)){
                		  return ((String) tuple1.getAttributeValue(i).toString()).compareTo((String)tuple2.getAttributeValue(i).toString());
                	  }
                  }
                  return 0;
            }});
		j++;
		if (j < tuplesResult.size())
			return tuplesResult.get(j);
		else 
			return null;
		
		
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}