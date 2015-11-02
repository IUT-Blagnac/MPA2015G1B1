package view;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import aspects.GlobalPointcuts;
import data.Group;
import data.Project;
import data.Stakeholder;
import data.Student;
import data.Subject;
 
@Aspect
public class ViewUpdater extends GlobalPointcuts {
	
	private View view = null;
	
	 @AfterReturning("initialization(view.View.new(..)) && target(view)")
	 public void retrieveView(View view) {
		 this.view = view;
	 }

	// *****************************************************************************
	//				STUDENTS UPDATING
	// **********************************************************
	
	
	@AfterReturning("pcAddingStudent(student)")
	public void addingStudent(Student student) {
		view.addStudent(student);
	}
	
	@AfterReturning("pcRemovingStudent(student)")
	public void removingStudent(Student student) {
		view.removeStudent(student);
	}
	
	@AfterReturning("pcModifyingStudent(student)")
	public void modifyingStudent(Student student) {
		view.modifySelectedStudent(student);
		view.setStudentSelected(student);
	}

	// *****************************************************************************
	//				SUBJECTS UPDATING
	// **********************************************************
	
	@AfterReturning("pcAddingSubject(subject)")
	public void addingSubject(Subject subject) {
		view.addSubject(subject);
	}
	
	@AfterReturning("pcRemovingSubject(subject)")
	public void removingSubject(Subject subject) {
		view.removeSubject(subject);
	}
	
	@AfterReturning("pcModifyingSubject(subject)")
	public void modifyingStudent(Subject subject) {
		view.modifySelectedSubject(subject);
		view.setSubjectSelected(subject);
	}
	
	// *****************************************************************************
	//				PROJECTS UPDATING
	// **********************************************************
	
	@AfterReturning("pcAddingProject(project)")
	public void addingSubject(Project project) {
		view.addProject(project);
	}
	
	@AfterReturning("pcRemovingProject(project)")
	public void removingProject(Project project) {
		view.removeProject(project);
	}
	
	@AfterReturning("pcModifyingProject(project)")
	public void modifyingProject(Project project) {
		view.modifySelectedProject(project);
		view.setProjectSelected(project);
	}
	
	// *****************************************************************************
	//				STAKEHOLDER UPDATING
	// **********************************************************
	
	@AfterReturning("pcAddingStakeholder(stakeholder)")
	public void addingStakeholder(Stakeholder stakeholder) {
		view.addStakeholder(stakeholder);
	}
	
	@AfterReturning("pcRemovingStakeholder(stakeholder)")
	public void removingStakeholder(Stakeholder stakeholder) {
		view.removeStakeholder(stakeholder);
	}
	
	@AfterReturning("pcModifyingStakeholder(stakeholder)")
	public void modifyingStakeholder(Stakeholder stakeholder) {
		view.modifySelectedStakeholder(stakeholder);
		view.setStakeholderSelected(stakeholder);
	}
	
	// *****************************************************************************
		//				GROUP UPDATING
		// **********************************************************
		
		@AfterReturning("pcAddingGroup(group)")
		public void addingGroup(Group group) {
			view.addGroup(group);
		}
}