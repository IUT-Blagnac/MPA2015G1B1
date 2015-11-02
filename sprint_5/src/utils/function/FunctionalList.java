package utils.function;

import java.util.ArrayList;

/**
 * An ArrayList which owns new functions to handle elements.
 * 
 * This class brings new functions which take a {@link Function} 
 * instance to customize handling on elements.
 * 
 * @author Emmanuel Chebbi
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class FunctionalList<T> extends ArrayList<T> {

	/**
	 * Default constructor.
	 */
	public FunctionalList() {
		super();
	}

	/**
	 * Apply given {@link Procedure} on each element of the list.
	 * 
	 * @param func The {@link Procedure} to apply on the elements.
	 */
	public void for_each( Procedure <Arg2<T, Integer>> func ) {
	    for (int i = 0; i < size(); i++) {
	        func.invoke( new Arg2<T, Integer>(this.get(i), i) );
	    }
	}

	/**
	 * Apply given {@link Function} on each element of the list, then 
	 * returns a new {@link FunctionalList} which contains the values
	 * returned by the {@link Function}.
	 * 
	 * @param 	func The {@link Function} to apply on the elements.
	 * @return 	a new {@link FunctionalList} which contains the values
	 * 			returned by the {@link Function}.
	 */
	public <R> FunctionalList<R> map( Function <R, Arg2<T, Integer>> func) {
	    FunctionalList<R> result = new FunctionalList<R>();
	    for (int i = 0; i < size(); i++) {
	        result.add(i, func.invoke(new Arg2<T, Integer>(this.get(i), i)));
	    }
	    return result; // cascade
	}

	/**
	 * Apply given {@link Predicate} on each element of the list.
	 * If the value returned by the {@link Predicate} is true,
	 * the element is added to a {@link FunctionalList}.<br />
	 * Finally, the {@link FunctionalList} is returned.
	 * 
	 * @param func The {@link Predicate} to apply on the elements.
	 * @return a new {@link FunctionalList} which contains the elements
	 * 			of the list which satisfy the {@link Predicate}.
	 */
	public FunctionalList<T> filter( Predicate <Arg2<T, Integer>> func) {
	    FunctionalList<T> result = new FunctionalList<T>();
	    for (int i = 0; i < size(); i++) {
	        if (func.invoke(new Arg2<T, Integer>(this.get(i), i))) {
	            result.add(this.get(i));
	        }
	    }
	    return result; // cascade
	}

}
