package hw2;

public class PostfixCalculator {

    private static boolean isOperator(String token){
       switch(token){
        case "+":
        case "-":
        case "*":
        case "/":
        case "^":
            return true;
        default:
            return false;
       }

    }

    public static double evaluate (String postfix){
        Stack<Double> stack = new ArrayStack<>();

        String [] tokens = postfix.trim().split("\\s+");

        for(String token : tokens){
            if(!isOperator(token)){
                stack.push(Double.parseDouble(token));
            }
            else{
                double first = stack.pop();
                double second = stack.pop();
                double result = 0;

                switch(token){
                    case "+": result = second + first; break;
                    case "-": result = second - first; break;
                    case "*": result = second * first; break;
                    case "/": 
                        if(first == 0){
                            throw new ArithmeticException("Cannot divivde by zero"); 
                }
                result = second / first; break;
                    case "^": result = Math.pow(second,first); break;
            }
                stack.push(result);
            }



        }

        return stack.pop();
    }
}






















    

