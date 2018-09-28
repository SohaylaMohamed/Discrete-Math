package eval.id32;
import java.util.Stack;

public class Evaluation {
	public boolean evaluate(String expression) {
		Stack<Character> s = new Stack<Character>();
		int counter = 0;
		char element1 = '0';
		char element2 = '0';
		char result;
		char operation;

		while (counter < expression.length()) {
			if (expression.charAt(counter) != '+' && expression.charAt(counter) != '~'
					&& expression.charAt(counter) != '*') {
				s.push(expression.charAt(counter));
			} else {
				operation = expression.charAt(counter);
				switch (operation) {
				case '+': {
					if (!(s.isEmpty())) {
						element1 = s.peek();
						s.remove(s.size()-1);
						if (!(s.isEmpty())) {
							element2 = s.peek();
							s.remove(s.size()-1);
						}
					}
					if (element1 == '1' || element2 == '1') {
						result = '1';
					} else {
						result = '0';
					}
					s.push(result);
					break;
				}
				case '*': {
					if (!(s.isEmpty())) {
						element1 = s.peek();
						s.remove(s.size()-1);

						if (!(s.isEmpty())) {
							element2 = s.peek();
							s.remove(s.size()-1);
						}
					}
					if (element1 == '1' && element2 == '1') {
						result = '1';
					} else {
						result = '0';
					}
					s.push(result);
					break;
				}
				case '~': {
					if (!(s.isEmpty())) {
						element1 = s.peek();
						s.remove(s.size()-1);
					}
					if (element1 == '0') {
						result = '1';
					} else {
						result = '0';
					}
					s.push(result);
					break;
				}

				}
			}
			counter = counter + 2;

		}

		if (s.size() == 1) {
			if (s.pop() == '1') {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

}
