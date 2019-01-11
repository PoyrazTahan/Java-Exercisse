import java.util.Scanner;


/**
 * @Author: Doga Poyraz Tahan   041503044
 * @Date: 11-11-18
 * With a given expression controls the mathematical correctness of the expression
 */
public class Doga_Poyraz_Tahan {

    public static void main(String[] args) {
        System.out.println("Enter a mathematical Expression: ");

        Scanner reader = new Scanner(System.in);
        String str = reader.next();// reads the expression

        if(isSyntaxCorrect(str))
            System.out.println("Expression is Correct"); //gives a response
        else
            System.out.println("Syntax Error");

        reader.close();
    }

    public static boolean isSyntaxCorrect(String str){
        myStack<Character> stack = new myStack<>();

        for(char c : str.toCharArray()) {
            if (c == '(' || c == '[') // if it is an openning it registers it to the stack
                stack.push(c);

            else if (c == ')' && (stack.isEmpty() || '(' != stack.pop() ))
                // if it is a clogng paranthesis and if the stack is not empty it compares if they are complimentary

                return false;
            else if (c == ']' && (stack.isEmpty()|| '[' != stack.pop() ))
                // if it is a clogng paranthesis and if the stack is not empty it compares if they are complimentary

                return false;

            if (c == '(' || c == '[' )
                stack.printStack();
        }

        if(!stack.isEmpty()){ // if stack is not empty either there is a missing or an excess parenthesis
            System.out.println("There is too much opening paranthesis");
            return false;
        }


        return true;
    }

}
