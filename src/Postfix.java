
import java.util.*;
public class Postfix {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack();
        String operand = sc.nextLine();
        Postfix me = new Postfix();
        /*
        String hello = "";
        String hello2 = "";
        String hello3 = "";
        int prev = 0;int last = 0;
        int before = 0;
        for(int i = 0; i < operand.length();i++){
            if(operand.charAt(i) == '*' || operand.charAt(i) == '/' || operand.charAt(i) == '+' || operand.charAt(i) == '-' ){
                if(!hello2.equals(""))stack.push(hello2);
                hello2="";   
                stack.push(operand.substring(before,i));
                before = i+1;
                if(!hello3.equals(""))stack.push(hello3);
                 hello3="";     
                if(operand.charAt(i) == '+' || operand.charAt(i) == '-'){
                    hello2 += hello;
                    hello ="";
                    hello += operand.charAt(i);
                }
                else{
                    hello3 += operand.charAt(i);
                } 

            }
            else if(i+1 == operand.length()){
                if(!hello2.equals(""))stack.push(hello2);
                stack.push(operand.substring(before));
                if(!hello3.equals(""))stack.push(hello3);
                if(!hello.equals(""))stack.push(hello);
            }
        }
        
        
        Stack stack2 = new Stack();
        System.out.println("Postfix: "+stack);
        while(!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        Stack stack3 = new Stack();
        while(!stack2.isEmpty()){
            String data = stack2.pop().toString();
            if(data.equals("+") || data.equals("-")|| data.equals("/")|| data.equals("*")){
                int num1 = Integer.parseInt(stack3.pop().toString());
                int num2 = Integer.parseInt(stack3.pop().toString());
                if(data.equals("+")){
                    stack3.push(num1+num2);
                }
                else if(data.equals("-")){
                    stack3.push(num2-num1);
                }
                else if(data.equals("*")){
                    stack3.push(num1*num2);
                }
                else if(data.equals("/")){
                    stack3.push(num2/num1);
                }
            }
            else{
                stack3.push(data);
            }
        }
        System.out.println("Evaluation: "+stack3);
        //System.out.println(hello);
        */
        System.out.println(me.postfix(operand));
    }
    public String postfix(String operand){
        System.out.println("Operand: "+operand);
        Stack stack = new Stack();
        String hello = "";
        String hello2 = "";
        String hello3 = "";
        int prev = 0;int last = 0;
        int before = 0;
        for(int i = 0; (i < operand.length() && operand.charAt(i) != ')');i++){
            if(operand.charAt(i) == '*' || operand.charAt(i) == '/' || operand.charAt(i) == '+' || operand.charAt(i) == '-' ){
                if(!hello2.equals(""))stack.push(hello2);
                hello2="";   
                stack.push(operand.substring(before,i));
                before = i+1;
                if(!hello3.equals(""))stack.push(hello3);
                 hello3="";     
                if(operand.charAt(i) == '+' || operand.charAt(i) == '-'){
                    hello2 += hello;
                    hello ="";
                    hello += operand.charAt(i);
                }
                else{
                    hello3 += operand.charAt(i);
                } 

            }
            else if(operand.charAt(i) == '('){
                before = i+1;
                stack.push(postfix(operand.substring(i+1)));
            }
            else if(i+1 == operand.length() || operand.charAt(i+1) == ')'){ 
                if(!hello2.equals(""))stack.push(hello2);
                if(i+1 == operand.length()){
                    stack.push(operand.substring(before));
                }
                else{
                    stack.push(operand.substring(before,i+1));
                }
                if(!hello3.equals(""))stack.push(hello3);
                if(!hello.equals(""))stack.push(hello);
            }
        }
        
        
        Stack stack2 = new Stack();
        System.out.println("Postfix: "+stack);
        while(!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        Stack stack3 = new Stack();
        while(!stack2.isEmpty()){
            String data = stack2.pop().toString();
            if(data.equals("+") || data.equals("-")|| data.equals("/")|| data.equals("*")){
                int num1 = Integer.parseInt(stack3.pop().toString());
                int num2 = Integer.parseInt(stack3.pop().toString());
                if(data.equals("+")){
                    stack3.push(num1+num2);
                }
                else if(data.equals("-")){
                    stack3.push(num2-num1);
                }
                else if(data.equals("*")){
                    stack3.push(num1*num2);
                }
                else if(data.equals("/")){
                    stack3.push(num2/num1);
                }
            }
            else{
                stack3.push(data);
            }
        }
        System.out.println("Evaluation :"+stack3);
        return stack3.peek()+"";
    }
    
}
