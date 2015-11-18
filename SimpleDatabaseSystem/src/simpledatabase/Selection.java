package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		Tuple temp = child.next();

		while (temp != null){
			if (child.from.equals(whereTablePredicate)){
				for (int i=0; i<temp.getAttributeList().size();i++){
					if(temp.getAttributeName(i).equals(whereAttributePredicate) && temp.getAttributeValue(i).equals(whereValuePredicate)){
						attributeList = new ArrayList<Attribute>();
						for (int j=0; j<temp.getAttributeList().size(); j++){
							attributeList.add(temp.getAttributeList().get(j));
						}
						Tuple tuple = new Tuple(attributeList);
						return tuple;
					}
				}
			} else {
				return temp;
			}
			temp = child.next();	
		}
		return null;
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}