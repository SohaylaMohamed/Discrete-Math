package eval.id32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class CallTable {
	private static CallTable instance = null;
	Expression ex;
	ArrayList<Expression> expression;

	private CallTable() {
		expression = new ArrayList<>();
	}

	public void setTable(String input) {

		this.expression.add(new Expression(input));

	}

	public static CallTable getInstance() {
		if (instance == null) {
			instance = new CallTable();
		}
		return instance;
	}

	public ArrayList<Expression> getTables() {
		return this.expression;
	}

	public boolean checkEquality(int index1, int index2) {
		if (expression.get(index1).getTruthTable().size() != expression.get(index2).getTruthTable().size()) {
			return false;
		}
		Hashtable<String, Boolean> test1 = expression.get(index1).getTruthTable();
		Hashtable<String, Boolean> test2 = expression.get(index2).getTruthTable();

		Object[] key =test1.keySet().toArray();
		StringBuilder s1 = new StringBuilder("");
		StringBuilder s2 = new StringBuilder("");

		for (Object x : key) {
			s1.append(test1.get(x.toString()));
			s2.append(test2.get(x.toString()));

		}
	
		return s1.toString().equals(s2.toString());

	}

	public boolean testForTaut(int i) {
		Hashtable<String, Boolean> test = expression.get(i).getTruthTable();
		Object[] key =test.keySet().toArray();
		StringBuilder s1 = new StringBuilder("");
		StringBuilder s2 = new StringBuilder("");

		for (Object x : key) {
			s1.append(test.get(x.toString()));
			s2.append("true");

		}
		return s1.toString().equals(s2.toString());
	}
	
	public boolean testForContr(int i) {
		Hashtable<String, Boolean> test = expression.get(i).getTruthTable();
		Object[] key =test.keySet().toArray();
		StringBuilder s1 = new StringBuilder("");
		StringBuilder s2 = new StringBuilder("");

		for (Object x : key) {
			s1.append(test.get(x.toString()));
			s2.append("false");

		}
		return s1.toString().equals(s2.toString());
	}
}
