package eval.id32;
import java.util.Stack;

import Evaluater.IExpressionEvaluator;


public class CheckSyntax implements IExpressionEvaluator {
	@Override
	public String infixToPostfix(String expression) {
		Stack<Character> s = new Stack<Character>();
		StringBuilder exp = new StringBuilder();
		int counter = 0;
		Character get = null;
		while (counter < expression.length()) {
			if (expression.charAt(counter) >= 'A' && expression.charAt(counter) <= 'z') {
				exp.append(expression.charAt(counter));
				exp.append(' ');
			} else if (expression.charAt(counter) == '(') {
				s.push(expression.charAt(counter));
			} else if (expression.charAt(counter) == ')') {
				if (!(s.isEmpty()))
					get = (Character) s.peek();
				int flag = 1;
				while (s.size() > 0 && flag == 1) {
					if (get != '(') {
						exp.append(s.pop());
						exp.append(' ');
						if (!(s.isEmpty()))
							get = s.peek();
					} else {
						flag = 0;
					}
				}
				if (!(s.isEmpty())) {
					s.pop();
				}
			} else if (expression.charAt(counter) == '+' || expression.charAt(counter) == '~'
					|| expression.charAt(counter) == '*') {
				if (s.isEmpty() || (Character) s.peek() == '(') {
					s.push(expression.charAt(counter));
				} else {
					if (!(s.isEmpty())) {
						get = (Character) s.peek();
					}
					if (expression.charAt(counter) == '+') {
						int flag = 1;
						while (s.size() > 0 && flag == 1) {
							if (get != '(') {
								exp.append((Character) s.pop());
								exp.append(' ');
								if (!(s.isEmpty()))
									get = (Character) s.peek();
							} else {
								flag = 0;
							}
						}
						s.push(expression.charAt(counter));
					} else if (expression.charAt(counter) == '*') {

						if (!(s.isEmpty())) {
							get = (Character) s.peek();
						}
						if (get == '+') {
							s.push(expression.charAt(counter));
						} else {
							int flag = 1;
							while (s.size() > 0 && flag == 1) {
								if (get != '(') {
									exp.append((Character) s.pop());
									exp.append(' ');
									if (!(s.isEmpty()))
										get = (Character) s.peek();
								} else {
									flag = 0;
								}
							}
							s.push(expression.charAt(counter));
						}

					} else if (expression.charAt(counter) == '~') {
						s.push(expression.charAt(counter));
					}
				}
			}
			counter++;
		}

		while (!(s.isEmpty())) {
			exp.append((Character) s.pop());
			exp.append(' ');
		}
		exp.deleteCharAt(exp.length() - 1);
		String evaluate = new String();
		evaluate = exp.toString();
		return evaluate;
	}

	public Boolean IsValid(String expression) {
		Boolean valid = true;
		int counter = 0;
		Stack<Character> brackets = new Stack<Character>();
		if (expression.charAt(counter) == '*' || expression.charAt(counter) == '+') {
			System.err.println("Invalid Expression");
			valid = false;
		}
		while (counter < expression.length() - 1) {
			if (expression.charAt(counter) >= 'A' && expression.charAt(counter) <= 'z') {
				if (expression.charAt(counter + 1) >= 'A' && expression.charAt(counter + 1) <= 'z') {
					System.err.println("Invalid Expression");
					valid = false;
					break;
				}
				if (expression.charAt(counter + 1) == '~') {
					System.err.println("Invalid Expression");
					valid = false;
					break;
				}
			} else if (expression.charAt(counter) == '*' || expression.charAt(counter) == '+'
					|| expression.charAt(counter) == '~') {
				if (expression.charAt(counter + 1) == '+' || expression.charAt(counter + 1) == '*') {
					System.err.println("Invalid Expression");
					valid = false;
					break;
				}
			} else if (expression.charAt(counter) == '(') {
				brackets.push('(');
			} else if (expression.charAt(counter) == ')') {
				if (expression.charAt(counter+1) == '(' || expression.charAt(counter+1) == '~') {
					System.err.println("Invalid Expression");
					valid = false;
					break;
				}
				if (brackets.isEmpty()) {
					System.err.println("Invalid Expression");
					valid = false;
					break;
				} else {
					brackets.pop();
				}
			} else {
				if (expression.charAt(counter) == ' ') {
					System.out.println("Expression can not contain white spaces");
					valid = false;
				} else {
					System.err.println("Invalid Expression 1");
					valid = false;
				}
			}

			counter++;
		}
		if ((expression.charAt(counter) == '*' || expression.charAt(counter) == '+'
				|| expression.charAt(counter) == '~') && valid) {
			System.err.println("Invalid Expression");
			valid = false;
		} else if (expression.charAt(counter) == ')' && valid) {
			brackets.pop();
			if (!(brackets.isEmpty())) {
				System.err.println("Invalid Expression");
				valid = false;
			}
		} else if (!(expression.charAt(counter) >= 'A' && expression.charAt(counter) <= 'z') && valid) {
			System.err.println("Invalid Expression");
			valid = false;
		}
		return valid;

	}
}
