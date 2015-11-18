package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple temp = leftChild.next();
		if (tuples1.isEmpty()){
			while (temp != null){
				tuples1.add(temp);
				temp = leftChild.next();
			}
			if (tuples1.size() == 0) {
				return null;
			}
		}
		
		temp = rightChild.next();
		while (temp != null){
			for (int i=0; i<tuples1.size(); i++){
				newAttributeList = new ArrayList<Attribute>();
				for (int j=0; j<tuples1.get(i).getAttributeList().size() ; j++){
					for (int k=0; k<temp.getAttributeList().size(); k++){
						if (tuples1.get(i).getAttributeName(j).equals(temp.getAttributeName(k)) && tuples1.get(i).getAttributeValue(j).equals(temp.getAttributeValue(k))){
							newAttributeList.addAll(tuples1.get(i).getAttributeList());
							Tuple tuples2 = new Tuple(newAttributeList);
							return tuples2;
						}
					}
				}
			}
			temp = rightChild.next();
			
		}
		return null;
	}
					

	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}