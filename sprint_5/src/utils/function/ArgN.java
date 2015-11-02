package utils.function;

/**
 * Represents an undefined number of arguments.
 * 
 * Designed to be used by Functor classes.
 * 
 * @see Arg0 
 * @see Function
 * 
 * @author Emmanuel Chebbi
 */
public class ArgN extends Arg0 { 

    private String[] arguments;
    
    public ArgN(String... args) { 
    	this.arguments = args; 
    } 
    
    public ArgN(String args) {
    	this.arguments = args.split("\\s+");
    }

    public String[] getArgs() { 
        return arguments; 
    } 
    
    public String get(int i) {
    	return arguments[i];
    }
}

