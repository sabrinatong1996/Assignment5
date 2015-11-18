package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	 
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple temp = child.next();
		newAttributeList = new ArrayList<Attribute>();
		
//		System.out.println("Projection: called next() " + temp);
		if (temp != null){
			for (int i=0; i<temp.getAttributeList().size();i++){
				if(temp.getAttributeName(i).equals(attributePredicate))
					newAttributeList.add(temp.getAttributeList().get(i));
			}
			Tuple tuple = new Tuple(newAttributeList);


//			System.out.println("Projection: have temp? " + tuple + " , size: " + tuple.getAttributeList().size());
			return tuple;
		}
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