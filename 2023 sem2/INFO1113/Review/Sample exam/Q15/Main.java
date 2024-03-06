public class Main{
	public static void main(String[] args) {
        Node five = new Node(5);
        Node three = new Node(3);
        Node four = new Node(4);
        Node two = new Node(2);

        Node addition = new Node('+', five, three);
        Node subtraction = new Node('-', four, two);

        Node root = new Node('*', addition, subtraction);

        Rec_calculate(root);

        System.out.println("Result of the expression (5 + 3) * (4 - 2) is: " + root.number);
    }

	public static void Rec_calculate(Node start){
		if (start.left.type == NodeTypes.OPERATOR){
			Rec_calculate(start.left);
		}

		if (start.right.type == NodeTypes.OPERATOR){
			Rec_calculate(start.right);
		}

		if (start.left.type == NodeTypes.NUMBER && start.right.type == NodeTypes.NUMBER){
			start.type = NodeTypes.NUMBER;
			if (start.operator == '+'){
				start.number = start.left.number + start.right.number;
				return;
			}else if(start.operator == '-'){
				start.number = start.left.number - start.right.number;
				return;
			}else if(start.operator == '/'){
				start.number = start.left.number / start.right.number;
				return;
			}else if(start.operator == '*'){
				start.number = start.left.number * start.right.number;
				return;
			}
		}

	}

 }



