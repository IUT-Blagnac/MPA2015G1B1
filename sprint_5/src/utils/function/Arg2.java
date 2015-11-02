package utils.function;

public class Arg2<A1, A2> extends Arg1<A1> { 

    private A2 argument2; 

    public A2 getArg2() { 
        return argument2; 
    } 

    public Arg2(A1 argument1, A2 argument2) { 
        super(argument1); 
        this.argument2 = argument2; 
    } 
}
