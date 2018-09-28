package eval.id32;
import java.util.ArrayList;
import java.util.Hashtable;

public class MyTruthTable {
	private String syntax;
	private Hashtable<String, Boolean> truthTable = new Hashtable<String, Boolean>();
	private ArrayList<Character> variables = new ArrayList<Character>();
	private Integer numOfvariables;
	private Expression exp ;
	public MyTruthTable(String syntax, Expression exp) {
		this.exp = exp;
		this.syntax = syntax;
	}
	
	
	public ArrayList<Character> getVariables(){
		return variables;
	}
	public Integer countVarialbe(){
		if (syntax.length() != 0) {
			int counter = 0; 
			while (counter < syntax.length()) {
				if (syntax.charAt(counter) >= 'A' && syntax.charAt(counter) <= 'z') {
					if (!(variables.contains(syntax.charAt(counter)))) {
						variables.add(syntax.charAt(counter));
					}
				}
				counter++;
			} 
		}
		exp.setVariables(variables);
		numOfvariables = variables.size();
		System.out.println("Number Of Variable: " + numOfvariables);
		return variables.size();
		
	} 
	public void setTable() {
		int numberOfRow = calculatePowerOfTwo(numOfvariables);
		Evaluation evaluation = new Evaluation();
		for (int i = 0; i< numberOfRow ; i++) {
			String BinaryNumber = convertToBinary(i);
			String newExpression = SubInPostfix (BinaryNumber);	
			boolean value = evaluation.evaluate(newExpression); 
			System.out.println(value);
			truthTable.put(BinaryNumber, value);
		}
		exp.setTruthTable(truthTable);
	}

	private String SubInPostfix(String binaryNumber) {
		String postfixWithSub = new String ();
		postfixWithSub = "";
		for (int i = 0; i < exp.getPostfix().length(); i++) {
			postfixWithSub += exp.getPostfix().charAt(i);
		}
		for (int i = 0; i < postfixWithSub.length() ; i++) {
			if (variables.contains(postfixWithSub.charAt(i))) {
				int index = variables.indexOf(postfixWithSub.charAt(i));
				postfixWithSub = postfixWithSub.replace(postfixWithSub.charAt(i),
						binaryNumber.charAt(index));
			}
		}
		System.out.println("after Subistution:  " +postfixWithSub );
		return postfixWithSub;
	}

	public String convertToBinary(int i) {
		String s = new String();
		int counter = 0;
		s = "";
		while (i != 0) {
			if (i % 2 == 1) {
				s += "1";
			} else {
				s += "0";
			}
			i /= 2;
			counter++;
		}
		while (counter < numOfvariables) {
			s += "0";
			counter++;
		}
		String reversed = new String ();
		reversed = "";
		for (int j = s.length() - 1; j >= 0; j--){
			reversed += s.charAt(j); 
		}
		return reversed;

	}

	private int calculatePowerOfTwo(Integer numOfvariables2) {
		int re = 1;
		for (int i=0; i<numOfvariables2; i++) {
			re = re*2;
		}
		return re;
	}
}
