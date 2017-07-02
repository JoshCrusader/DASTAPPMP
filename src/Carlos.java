import java.util.*;
public class Carlos 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        Stack pf = new Stack();
        Stack op = new Stack();
        
        System.out.print("Enter a problem: ");
        String problem = sc.nextLine();
        
        for (int ctr = 0; ctr < problem.length(); ctr ++)
        {
            if (problem.charAt(ctr) == '+' || problem.charAt(ctr) == '-' || problem.charAt(ctr) == '*' || problem.charAt(ctr) == '/')
            {
                if (op.isEmpty())
                {
                    op.push(problem.charAt(ctr));
                }
                
                else
                {
                    if ( ( (char)op.peek() == '+' || (char)op.peek() == '-' ) && ( problem.charAt(ctr) == '*' || problem.charAt(ctr) == '/' ) )
                    {
                        pf.push(problem.charAt(ctr+1));
                        pf.push(problem.charAt(ctr));
                        ctr++;
                    }
                    
                    else
                    {
                        pf.push(op.pop());
                        op.push(problem.charAt(ctr));
                    }
                }
            }
            
            else
                pf.push(problem.charAt(ctr));
        }
        
        pf.push(op.pop());
        
        System.out.println("Post Fix: " + pf);
    }
}