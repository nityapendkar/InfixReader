import java.io.*;
import java.lang.Math; 
/**
 *The class InfixReader reads a line of input entered by the user. The user is expected to enter 
 *the input in the form of Infix.
 * @author Nitya Pendkar
 *
 */
public class InfixReader {
	
	/**
	 * The static int precedence function wihin the class determines and assigns a numerical value in 
	 *order of precedence of operators to each operator- used during infix to postfix conversion to 
	 *determine order.
	 * @param x It is the operator in the form of a string variable which is passed to the function 
	 * whose numerical value of precedence is to be determined.
	 * @return The function returns the integer value assigned to determine the precedence
	 * of each operator.
	 */
	static int precedence(String x)
	{
		if (x.equals("+") || x.equals("-"))
			return 0;
		else if (x.equals("*") || x.equals("/"))
			return 1;
		else if (x.equals("^"))
		    return 2;
		return -1;
	}
	
	
	public static void main(String[] args) {
		
		InfixReader myAnswer = new InfixReader();
		myAnswer.doConversion();
	}

	/**
	 *The function doConversion() converts a string passed to it in infix form to postfix form.
	 */
	public void doConversion() {
		// TODO: read infix from input using readInfix(), then convert it to postfix and
		//making new array to store input value separated by a space
		
		
		String postfix="";
		
		Stack myStack = new Stack();
		String[] store = new String[1000];
		store= readInfix();
		String currentTop = "";
	
		for(int i=0; i< store.length;i++) {
			if (!myStack.isEmpty()) {
				currentTop = myStack.top();
			}
			else {
				currentTop = "";
			}
			
			if((store[i]).equals("+") || (store[i]).equals("-") ||(store[i]).equals("*") 
			   ||(store[i]).equals("/") ||(store[i]).equals("^") )  
			{
				int x = InfixReader.precedence(store[i]);
				int y = InfixReader.precedence(currentTop);
				if (x < y)
				{
					String popped="";
					if (!myStack.isEmpty()) {
						popped = myStack.pop();
					}
					else {
						popped = "";
					}
					
					if (popped.equals("")) {
						postfix= postfix + popped;
						myStack.push(store[i]);
						
					}
					else {
						postfix= postfix + " " + popped;
						myStack.push(store[i]);
						
					}
					
					
				}
			else {
				
			myStack.push(store[i]);
			
				//System.out.print(currentTop);
				}
			}
			 else if (store[i].equals("(")) 
			 {  
				 myStack.push(store[i]); 
			 }
	              
	            //  If the scanned character is an ')', pop and output from the stack  
	            // until an '(' is encountered. 
	            else if (store[i].equals(")")) 
	            { 
	            	
	            	if (!myStack.isEmpty()) {
	    				currentTop = myStack.top();
	    			}
	    			else {
	    				currentTop = "";
	    			}

	                while (!myStack.isEmpty() && !currentTop.equals("(")) 
	                {
	                	String popped="";
						popped = myStack.pop();
						currentTop = myStack.top();
	                    postfix =postfix + " " + popped;
	                }
	                if (!myStack.isEmpty()) {
	    				currentTop = myStack.top();
	    			}
	    			else {
	    				currentTop = "";
	    			}
	                
	                if (!myStack.isEmpty() && currentTop.equals("(")) 
	                	myStack.pop();
	               
	            } 
			else 
			{
				postfix= postfix +" "+ store[i];
				
			}
			
		}
		while (!myStack.isEmpty()) 
		{
					
			postfix = postfix +" "+ myStack.pop();
		}	
		
		System.out.println("Postfix:" + postfix);
		evalPostfix(postfix);
	
	}

	/**
	 * It evaluates the postfix expression applying the operators on the operands in the order 
	 * determined by the received postfix expression to calculate the final result.
	 * @param postfix A string of the postfix expression from doCOnversion function.
	 */
	public void evalPostfix(String postfix) {
		// TODO: evaluate the postfix representation of the input arithmetic expression, 
		// and then print the result of the evaluation of the expression on the next 
		// line.
		Stack1 myStack1 = new Stack1();
		String[] postfixed = new String[1000];
		postfixed = postfix.split(" ");
		int result=0;
		//int result = Integer.parseInt(postfix);	
		
		for(int i=1; i< postfixed.length;i++) {
			if( postfixed[i].equals("+") ||postfixed[i].equals("-") ||
			   postfixed[i].equals("*") || postfixed[i].equals("/") ||
			   postfixed[i].equals("^")  ) {
				
				int val1 = myStack1.pop(); 
                int val2 = myStack1.pop(); 
                if(postfixed[i].equals("+")) {
                	myStack1.push(val2+val1);
                }
                else if(postfixed[i].equals("-")) {
                	myStack1.push(val2-val1);
                }
                else if(postfixed[i].equals("*")) {
                	myStack1.push(val2*val1);
                }
                else if(postfixed[i].equals("/")) {
                	myStack1.push(val2/val1);
                } 
                else if(postfixed[i].equals("^")) {
                	int ans = (int) Math.pow(val2, val1);
                	myStack1.push(ans);
                }
			}
			
			else {
				int digit = Integer.parseInt(postfixed[i]);
				myStack1.push(digit);
				
			}
		}
		
		result=myStack1.pop();
		
		
		System.out.println("Result: "+result);
		
	}

	/**
	 * It reads the input in the infix form entered by the user.
	 * @return a string array of the user input.  
	 */
	public String[] readInfix() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine;
		try {
			System.out.print("Input infix: ");
			inputLine = input.readLine();
			return inputLine.split(" ");
		} catch (IOException e) {
			System.err.println("Input ERROR.");
		}

		// return empty array if error occurs
		return new String[] {};
	}

}

/**
 *This class implements the functionality of a string stack which has push, pop, check for empty
 *stack and returning top most element of stack for the created stack.
 * @author Nitya Pendkar
 */
class Stack {
	// TODO: implement Stack in this class
		private String[] stackArray = new String[1000];
	    private int top;
	    
	    //size of array?
	    
	    //assign size to array?
	    /**
	     * Pushes(adds) a string to the top of the stack 
	     * @param j it is the string variable which is sent to be pushed to the top of the stack
	     */
	    public void push(String j) {
	        stackArray[top] = j;
	        //System.out.print(stackArray[top]);
	        top++;
	        //System.out.print(top);
	     }
	     /**
	      * Pops(removes) the top most string of the stack.
	     * @return returns the top most element of the stack which has been removed in the function
	     */
	    public String pop() { 
	    	
	        String element = stackArray[top-1];
	        top--;
	        //System.out.println("Popped: " + element);
	        return element;
	    	
	     }
	     /**
	     *Checks if the stack is empty or has string elements in it 
	     * @return returns true or false depending if the stack is empty or not
	     */
	    boolean isEmpty()
	 	{
	    	 if (top == 0) {
	    		 return true;
	    	 }
	    	 else
	    	 {
	    		 return false;
	    	 }
	 	}
	     
	     /**
	     *Returns the top most element in the stack 
	     * @return top most element in the stack 
	     */
	    public String top() {
	    		 return stackArray[top-1];
	    	 
	     }
	    
}

/**
 *This class implements the functionality of an integer stack which has push, pop, check for empty
 *stack and returning top most element of stack for the created stack.
 * @author Nitya Pendkar
 *
 */
class Stack1 {
	// TODO: implement Stack in this class
		private int[] stackArray = new int[1000];
	    private int top;
	    //size of array?
	    
	    //assign size to array?
	    /**
	     * Pushes(adds) an integer to the top of the stack 
	     * @param j it is the integer variable which is sent to be pushed to the top of the stack
	     */
	    public void push(int j) {
	        stackArray[top] = j;
	        //System.out.print(stackArray[top]);
	        top++;
	        //System.out.print(top);
	     }
	     /**
	    * Pops(removes) the top most integer of the stack.
	     * @return returns the top most element of the stack which has been removed in the function
	     */
	    public int pop() { 
	    	
	        int element = stackArray[top-1];
	        top--;
	        //System.out.print(top+" ");
	        return element;
	    	
	     }
	     /**
	     *Checks if the stack is empty or has integer elements in it 
	     * @return returns true or false depending if the stack is empty or not
	     */
	    boolean isEmpty()
	 	{
	    	 if (top == 0) {
	    		 return true;
	    	 }
	    	 else
	    	 {
	    		 return false;
	    	 }
	 	}
	     
	     /**
	     *Returns the top most element in the stack 
	     * @return top most element in the stack 
	     */
	    public int top() {
	    		 return stackArray[top-1];
	    	 
	     }
	    
}

