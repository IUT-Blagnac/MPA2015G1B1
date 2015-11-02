package utils.function;

/**
 * A simple functor.
 * 
 * This interface is a generic type which takes two types : 
 * the function's return type and the number of arguments taken
 * by the function. <br />
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
 * @param <R> the function's return type
 * @param <A> the class representing the number of arguments taken by the function
 * 
 * @see Predicate
 * @see Procedure
 */
public interface Function<R, A extends Arg0> { 
    
	/**
	 * @param 	arguments An instance of A which represents the real arguments
	 * @return 	what you want it to return
	 */
    R invoke (A arguments); 
    
}
