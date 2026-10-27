package hw2;
public class Converter {

    private String infix;

    public Converter(String infix){         //constructor 
        this.infix = infix;
    }

    private int pemdas(String string){    //helper 
        if(string.equals("^")){
            return 3;               //highest precendence
        }
        if (string.equals("*") || string.equals("/")){      
            return 2;
        }
        if (string.equals("+") || string.equals("-")){  //smallest precendence
            return 1;
        }
        return 0;
    }  
    
    public String toPostFix(){

        if(infix.length () > 0 && infix.charAt(0) == '-'){
            infix = "0" + infix;                                  //if negarive
        }
        var tokens = ParserHelper.parse(infix.toCharArray());

        Stack <String> stack = new ArrayStack<>();
        String postfix = "";

        for (String token : tokens){
            if(Character.isDigit(token.charAt(0))){
                postfix += token + " ";
            }
            else if(!token.equals("(") && !token.equals(")")){
                while (!stack.isEmpty() && !stack.top().equals("(")){
                    int top = pemdas(stack.top());
                    int current = pemdas(token);

                    if(top < current || (top == current && token.equals("^"))){
                        break;
                    }
                    postfix += stack.pop() + " ";
                }  
                stack.push(token);
            }
            else if (token.equals("(")){            //"For an open parenthesis, push it onto the stack.""
                stack.push(token);
            }
            else if(token.equals(")")){
                while(!stack.isEmpty() && !stack.top().equals("(")){
                    postfix += stack.pop() + " ";       //For a closed parenthesis, pop and append all operators from the stack to
                                                            //  the output string until an open parenthesis is encountered    
                }
                stack.pop();
            }
        }
            while(stack.size()> 0){
                postfix += stack.pop()+ " ";
            }
           

        return postfix;
    }

}
/**For an operator, check the top of the stack to ensure lower precedence. If
the token has higher precedence, push it onto the stack. If it has lower
precedence, pop operators from the stack, appending them to the output
string until you encounter a lower precedence operator or the stack is
empty.
/**/