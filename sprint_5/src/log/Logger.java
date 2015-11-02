package log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import aspects.GlobalPointcuts;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;

@Aspect
public class Logger extends GlobalPointcuts { 
	
	private final static boolean IS_LOGGER_ACTIVATED = true;
	private final static String SEPARATOR = "----------------------------";
	
	@Pointcut("if() && !within(log.Logger)")
	public static boolean isLogActivated() {
		return IS_LOGGER_ACTIVATED;
	}
	
	// *****************************************************************************
	//				STUDENTS UPDATING
	// **********************************************************
	
	
	@AfterReturning("isLogActivated() && pcAddingStudent(student)")
	public void addingStudent(Student student) {
		System.out.println(SEPARATOR);
		System.out.println("[STUDENT id="+student.getId()+"] - [ADD]");
		String groupId = (student.getGroup() != null) ? student.getGroup().toString() : "pas de groupe";
		System.out.println(student + " (groupe: "+groupId+")");
	}
	
	@AfterReturning("isLogActivated() && pcRemovingStudent(student)")
	public void removingStudent(Student student) {
		System.out.println(SEPARATOR);
		System.out.println("[STUDENT id="+student.getId()+"] - [REMOVE]");
		String groupId = (student.getGroup() != null) ? student.getGroup().toString() : "pas de groupe";
		System.out.println(student + " ("+groupId+")");
	}
	
	@Around("isLogActivated() && pcModifyingStudent(student)")
	public Object modifyingStudent(ProceedingJoinPoint pjp, Student student) throws Throwable {
		System.out.println(SEPARATOR);
		System.out.println("[STUDENT id="+student.getId()+"] - [MODIFY]");
//		System.out.println("Before : "+((Model)pjp.getTarget()).getStudent((int)pjp.getArgs()[0]));
		Object o = pjp.proceed();
//		System.out.println("After : "+((Model)pjp.getTarget()).getStudent((int)pjp.getArgs()[0]));
		return o;
	}

	// *****************************************************************************
	//				SUBJECTS UPDATING
	// **********************************************************
	
	@AfterReturning("isLogActivated() && pcAddingSubject(subject)")
	public void addingSubject(Subject subject) {
		System.out.println(SEPARATOR);
		System.out.println("[SUBJECT id="+subject.getId()+"] - [ADD]");
		System.out.println(subject);
	}
	
	@AfterReturning("isLogActivated() && pcRemovingSubject(subject)")
	public void removingSubject(Subject subject) {
		System.out.println(SEPARATOR);
		System.out.println("[SUBJECT id="+subject.getId()+"] - [REMOVE]");
		System.out.println(subject);
	}
	
	@AfterReturning("isLogActivated() && pcModifyingSubject(subject)")
	public void modifyingStudent(Subject subject) {
		System.out.println(SEPARATOR);
		System.out.println("[SUBJECT id="+subject.getId()+"] - [MODIFY]");
	}
	
	// *****************************************************************************
	//				PROJECTS UPDATING
	// **********************************************************
	
	@AfterReturning("isLogActivated() && pcAddingProject(project)")
	public void addingSubject(Project project) {
		System.out.println(SEPARATOR);
		System.out.println("[PROJECT id="+project.getId()+"] - [ADD]");
		System.out.println(project);
	}
	
	@AfterReturning("isLogActivated() && pcRemovingProject(project)")
	public void removingProject(Project project) {
		System.out.println(SEPARATOR);
		System.out.println("[PROJECT id="+project.getId()+"] - [REMOVE]");
		System.out.println(project);
	}
	
	@AfterReturning("isLogActivated() && pcModifyingProject(project)")
	public void modifyingProject(Project project) {
		System.out.println(SEPARATOR);
		System.out.println("[PROJECT id="+project.getId()+"] - [MODIFY]");
	}
	
	// *****************************************************************************
	//				STAKEHOLDERS UPDATING
	// **********************************************************
	
	@AfterReturning("isLogActivated() && pcAddingStakeholder(stakeholder)")
	public void addingStakeholder(Stakeholder stakeholder) {
		System.out.println(SEPARATOR);
		System.out.println("[STAKEHOLDER id="+stakeholder.getId()+"] - [ADD]");
		System.out.println(stakeholder);
	}
	
	@AfterReturning("isLogActivated() && pcRemovingStakeholder(stakeholder)")
	public void removingStakeholder(Stakeholder stakeholder) {
		System.out.println(SEPARATOR);
		System.out.println("[STAKEHOLDER id="+stakeholder.getId()+"] - [REMOVE]");
		System.out.println(stakeholder);
	}
	
	@AfterReturning("isLogActivated() && pcModifyingStakeholder(stakeholder)")
	public void modifyingProject(Stakeholder stakeholder) {
		System.out.println(SEPARATOR);
		System.out.println("[STAKEHOLDER id="+stakeholder.getId()+"] - [MODIFY]");
	}
}
