package com.crystalsantos.queues.and.stacks;

public class ArithmeticExpressionEvaluation {
	public static void main(String[] args) {
		Stack<String> operations = new StackWithArray<String>();
		Stack<Double> values = new StackWithArray<Double>();

		String[] items = {"(", "1", "+", "(", "(", "2", "+", "3", ")", "*", "(", "4", "*", "5", ")", ")", ")"};
		for (String item : items) {
			if (item.equals("*") || item.equals("-") || item.equals("/") || item.equals("+")) {
				operations.push(item);
			} else if (item.equals("(")) {

			} else if (item.equals(")")) {
				String operation = operations.pop();
				if(operation.equals("+")) {
					values.push(values.pop() + values.pop());
				} else if(operation.equals("-")) {
					values.push(values.pop() - values.pop());
				} else if(operation.equals("*")) {
					values.push(values.pop() * values.pop());
				} else { //operation = "/"
					values.push(values.pop() / values.pop());
				}
			} else {
				values.push(Double.parseDouble(item));
			}
		}
		
		System.out.println("The arithmetic expresion = " + values.pop());
		
		if(!values.isEmpty() || !operations.isEmpty()) {
			System.out.println("The expression is wrong.");
		}

	}
}
