package utils.function;

/**
 * A particular functor which represents a procedure.
 * 
 * A procedure is a function which returns nothing <br />
 * <br />
 * 
 * The number of arguments taken is represented by a simple class :
 * <ul>
 * 	<li> {@link Arg0} : the function takes no argument.</li>
 * 	<li> {@link Arg1} : the function takes 1 argument.</li>
 * 	<li> {@link Arg2} : the function takes 2 arguments.</li>
 * </ul>
 * 
 * @author Emmanuel Chebbi
 *
 * @param <A> the class representing the number of arguments taken by the function
 * 
 * @see Function
 * @see Predicate	
 */
public interface Procedure<A extends Arg0> {
    
    void invoke (A arguments);
}