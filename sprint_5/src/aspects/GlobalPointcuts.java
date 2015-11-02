package aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import data.Group;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;

@Aspect
public abstract class GlobalPointcuts {
	
	/* --------------------------------------------------------------------------------
	 * 								SUBJECT POINCUTS
	 * ---------------------------------------------------------------- */
	
	@Pointcut("call(* model.Model.loadSubjects(..))")
	protected void pcLoadingSubjects() {}
	
	@Pointcut("call(* model.Model.addSubject(data.Subject)) && args(addedSubject)")
	protected void pcAddingSubject(Subject addedSubject) {}
	
	@Pointcut("call(* model.Model.removeSubject(data.Subject)) && args(removedSubject)")
	protected void pcRemovingSubject(Subject removedSubject) {}
	
	@Pointcut("call(* model.Model.modifySubject(..,data.Subject)) && args(..,modifiedSubject)")
	protected void pcModifyingSubject(Subject modifiedSubject) {}
		
	/* --------------------------------------------------------------------------------
	 * 								STUDENT POINCUTS
	 * ---------------------------------------------------------------- */
	
	@Pointcut("call(* model.Model.loadStudents(..))")
	protected void pcLoadingStudents() {}
	
	@Pointcut("call(* model.Model.addStudent(data.Student)) && args(addedStudent)")
	protected void pcAddingStudent(Student addedStudent) {}
	
	@Pointcut("call(* model.Model.removeStudent(data.Student)) && args(removedStudent)")
	protected void pcRemovingStudent(Student removedStudent) {}
	
	@Pointcut("call(* model.Model.modifyStudent(..,data.Student)) && args(..,modifiedStudent)")
	protected void pcModifyingStudent(Student modifiedStudent) {}
	
	/* --------------------------------------------------------------------------------
	 * 								PROJECT POINCUTS
	 * ---------------------------------------------------------------- */
	
	@Pointcut("call(* model.Model.initProjects(..))")
	protected void pcLoadingProjects() {}
	
	@Pointcut("call(* model.Model.addProject(data.Project)) && args(addedProject)")
	protected void pcAddingProject(Project addedProject) {}
	
	@Pointcut("call(* model.Model.removeProject(data.Project)) && args(removedProject)")
	protected void pcRemovingProject(Project removedProject) {}
	
	@Pointcut("call(* model.Model.modifyProject(..,data.Project)) && args(..,modifiedProject)")
	protected void pcModifyingProject(Project modifiedProject) {}
	
	/* --------------------------------------------------------------------------------
	 * 								STAKEHOLDER POINCUTS
	 * ---------------------------------------------------------------- */
	
	@Pointcut("call(* model.Model.initStakeholder(..))")
	protected void pcLoadingStakeholders() {}
	
	@Pointcut("call(* model.Model.addStakeholder(data.Stakeholder)) && args(addedStakeholder)")
	protected void pcAddingStakeholder(Stakeholder addedStakeholder) {}
	
	@Pointcut("call(* model.Model.removeStakeholder(data.Stakeholder)) && args(removedStakeholder)")
	protected void pcRemovingStakeholder(Stakeholder removedStakeholder) {}
	
	@Pointcut("call(* model.Model.modifyStakeholder(..,data.Stakeholder)) && args(..,modifiedStakeholder)")
	protected void pcModifyingStakeholder(Stakeholder modifiedStakeholder) {}
	
	/* --------------------------------------------------------------------------------
	 * 								Group POINCUTS
	 * ---------------------------------------------------------------- */
	
	@Pointcut("call(* model.Model.addGroup(data.Group)) && args(addedGroup)")
	protected void pcAddingGroup(Group addedGroup) {}

}
