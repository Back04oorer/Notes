enum NodeTypes { NUMBER, OPERATOR }

class Node {
    NodeTypes type;   // Which type of node is this?
    double number;    // The value in a node of type NUMBER.
    char operator;    // The operator in a node of type OPERATOR.
    Node left;        // Pointer to left subtree
    Node right;       // Pointer to right subtree

    // Constructor for creating a node of type OPERATOR
    Node(char operator, Node left, Node right) {
        this.type = NodeTypes.OPERATOR;
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    // Constructor for creating a node of type NUMBER
    Node(double number) {
        this.type = NodeTypes.NUMBER;
        this.number = number;
        this.left = null;  // Numbers won't have child nodes
        this.right = null; // Numbers won't have child nodes
    }
}

