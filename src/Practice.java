import java.util.*;

public class Practice
{
    public static void main(String[] args)
    {
        Practice pf = new Practice ();
        Stack s1 = new Stack ();
        Stack s2 = new Stack ();
        Scanner sc = new Scanner (System.in);
        System.out.print("Enter problem: ");
        String problem = sc.next();
        int ctr = 0;
        while (ctr != problem.length())
        {
            if (!problem.substring(ctr,ctr+1).equals("+") && !problem.substring(ctr,ctr+1).equals("-") && !problem.substring(ctr,ctr+1).equals("*") && !problem.substring(ctr,ctr+1).equals("/"))
                s1.push(problem.substring(ctr,ctr+1));
            else
            {
                if (s2.size() == 0)
                    s2.push(problem.substring(ctr,ctr+1));
                else if ((s2.peek().equals("+") || s2.peek().equals("-")) && (problem.substring(ctr,ctr+1).equals("*") || problem.substring(ctr,ctr+1).equals("/")))
                {
                    ctr ++;
                    s1.push(problem.substring(ctr,ctr+1));
                    s1.push(problem.substring(ctr-1,ctr));
                }
                else
                {
                    s1.push(s2.pop());
                    s2.push(problem.substring(ctr,ctr+1));
                }
            }
            ctr ++;
        }
        s1.push(s2.pop());
        System.out.println("Post Fix: " + s1.toString());
        Stack evaluation = new Stack ();
        int answer, R, L;
        while (s1.size() != 0)
            evaluation.push(s1.pop());
        while (evaluation.size() != 0)
        {
            if (!evaluation.peek().equals("+") && !evaluation.peek().equals("-") && !evaluation.peek().equals("*") && !evaluation.peek().equals("/"))
                s1.push(evaluation.pop());
            else
                s1.push(pf.evaluate((String) evaluation.pop(), Integer.parseInt(s1.pop().toString()), Integer.parseInt(s1.pop().toString())));
        }
        answer = Integer.parseInt(s1.pop().toString());
        System.out.println("Answer: " + answer);
    }
    
    public String evaluate (String symbol, int r, int l)
    {
        if (symbol.equals("+"))
            return (l+r)+"";
        else if (symbol.equals("-"))
            return (l-r)+"";
        else if (symbol.equals("*"))
            return (l*r)+"";
        else if (symbol.equals("/"))
            return (l/r)+"";
        return null;
    }
}