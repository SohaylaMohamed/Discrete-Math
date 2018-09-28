package eval.id32;
import java.util.ArrayList;
import java.util.Hashtable;

public class Expression {
	private String syntax;
	private Hashtable<String, Boolean> truthTable = new Hashtable<String, Boolean>();
	private ArrayList<Character> variables = new ArrayList<Character>();
	private String postfix;
	private CheckSyntax checker = new CheckSyntax();
	private MyTruthTable t; 
	public Expression (String syntax){
		this.syntax = syntax;
		if (checker.IsValid(syntax)){
			postfix = checker.infixToPostfix(syntax);
			System.out.println("First step:" + postfix);
			t = new MyTruthTable (syntax,this);
			t.countVarialbe();
			t.setTable();
		}
		
		
	} 
	
	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public Hashtable<String, Boolean> getTruthTable() {
		return truthTable;
	}

	public void setTruthTable(Hashtable<String, Boolean> truthTable) {
		this.truthTable = truthTable;
	}

	public ArrayList<Character> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Character> variables) {
		this.variables = variables;
	}
	public String getPostfix() {
		return postfix;
	}
	
	

}
