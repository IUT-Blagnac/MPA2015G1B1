package data;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareWarning;

/**
 * This aspect check that some data policies aren't broken.
 * <br>
 * For example, it could check that some methods
 * are successfully called instead any others. 
 * 
 * @author Emmanuel Chebbi
 */
@Aspect
public class DataPoliciesChecker {
	
	/* **************************** DATA CHECKER *********************************** */
	
	@DeclareWarning("call(* data.Student.setGroup(..)) && ! within(data.Group)")
	static final String warnSetGroup = "The method Group.addStudent(student) should be called instead";
	
	@DeclareWarning("call(* data.Group.setProject(..)) && ! within(data.Project)")
	static final String warnSetProject = "The method Project.setGroup(group) should be called instead";
	
	@DeclareWarning("call(* data.Stakeholder.follow(..)) && ! within(data.Project)")
	static final String warnFollow = "The method Project.setStakeholder(category, stakeholder) should be called instead";
	
	@DeclareWarning("call(* data.Stakeholder.disfollow(..)) && ! within(data.Project)")
	static final String warnDisfollow = "The method Project.removeStakeholder(stakeholder) should be called instead";
	
	/* **************************** ANNOTATIONS CHECKER *********************************** */
	
	@DeclareWarning("call(@utils.annotations.ExpensiveOperation  * *.*(..)) && ! withincode(@utils.annotations.ExpensiveOperation * * (..))")
	static final String warnExpensiveCall = "Caution: this method is tagged as expensive.";
}
