package web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utils.csv.CSVReader;

public class MakeOPTIweb { 
	
	public static void main( String[] args ) {
		try {
			new MakeOPTIweb("data/etudiants2014_2015.csv", 
							"data/sujets2014_2015.csv", 
							"data/intervenants2014_2015.csv", 
							"data/projets2014_2015.csv");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String content;
	private String ln = "\n";
	
	private ArrayList <String[]> students = null, subjects = null, stakeholders = null, projects = null;
	
	//GETTER POUR TESTS !
	
	public ArrayList<String[]> getStudents() {
		return students;
	}

	public ArrayList<String[]> getSubjects() {
		return subjects;
	}

	public ArrayList<String[]> getStakeholders() {
		return stakeholders;
	}
	
	public ArrayList<String[]> getProjects() {
		return projects;
	}
	
	public MakeOPTIweb(String studentFilePath, String subjectFilePath, String stakeholderFilePath, String projectFilePath) throws IOException {
		
		try {
			students = CSVReader.fromFile(studentFilePath);
			subjects = CSVReader.fromFile(subjectFilePath);
			stakeholders = CSVReader.fromFile(stakeholderFilePath);
			projects = CSVReader.fromFile(projectFilePath);
			
			students.remove(0);
			subjects.remove(0);
			stakeholders.remove(0);
			projects.remove(0);
			
		} catch( Exception e ) {
			System.err.println(e.getMessage());
		}
		
		content = "";
		
		content += createFirstStructurePart();
		content += createWelcomeDiv();
		content += createDivCredits();
		
		content += createDivProject(projects, stakeholders, subjects, students);
		content += createDivSubject(subjects, projects);
		content += createDivStudent(students);
		content += createDivStakeholder(stakeholders, projects);
		
		content += createLastStructurePart();
		
		File f = new File("OPTIweb.html");
		
		if( ! f.exists() )
			f.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(
						                new FileOutputStream("OPTIweb.html"), "UTF8"));
		
		bw.write(content);
		bw.close();
	}

	private String createFirstStructurePart() {
		return 	"<!DOCTYPE html>" + ln +
				"<html>" + ln +
				"<head>" + ln +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" + ln +
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + ln +
				"<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />" + ln +
				"<title>0.1 - V0.1</title>" + ln +
				"<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />" + ln +
				"<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />" + ln +
				"<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>" + ln +
				"<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>" + ln +
				"<style type='text/css'>" + ln +
				"@media all and (orientation:portrait) { .landscape {display: none;} }" + ln +
				"@media all and (orientation:landscape) { .landscape {display: inline;} }" + ln +
				"</style>" + ln +
				"</head><body>" + ln;
	}
	
	private String createWelcomeDiv() {
		return "<!-- DEBUT page accueil -->" + ln +
				"<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">" + ln +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" + ln +
				"<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">or�s</span> 2014-2015<br/>D�partement INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>" + ln +
				"<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Cr�dits</a>" + ln +
				"</div>" + ln +
				"<div data-role=\"content\">" + ln +
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">" + ln +
				  "\t<li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>" + ln +
				  "\t<li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>" + ln +
				  "\t<li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>" + ln +
				  "\t<li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>" + ln +
				"</ul>" + ln +
				"</div>" + ln +
				"<div data-role=\"footer\">" + ln +
				 "\t<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>" + ln +
				"</div>" + ln +
				"</div>" + ln +
				"<!-- FIN page accueil -->" + ln;
	}
	
	private String createDivCredits() {
		return "<!-- DEBUT page credits -->" + ln +
				"<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Cr�dits\">" + ln +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" + ln +
				"\t<h1>Cr�dits</h1>" + ln +
				"</div>" + ln +
				"<div data-role=\"content\">" + ln +
				"\t<p>Cette application a �t� r�alis�e dans le cadre du module M3301/MPA du DUT Informatique � l'IUT de Blagnac.</p>" + ln +
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">" + ln +
				"\t<li data-role=\"list-divider\">Product Owner</li>" + ln +
				"\t<li>Andr� P�NINOU</li>" + ln +
				"\t<li>Universit� Toulouse 2 - IUT de Blagnac" + ln +
				"\t<br/>D�partement INFORMATIQUE</li>" + ln +
				"</ul>" + ln +
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">" + ln +
				"\t<li data-role=\"list-divider\">Membres de l'�quipe enseignante</li>" + ln + 
				"\t<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>Andr� P�NINOU</li><li>Olivier ROQUES</li>" + ln +
				"</ul>" + ln +
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">" + ln +
				"\t<li data-role=\"list-divider\">Propuls� par</li>" + ln +
				"\t<li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>" + ln +
				"\t<li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>" + ln +
				"</ul>" + ln +
				"</div>" + ln +
				"<div data-role=\"footer\">" + ln +
				"\t<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>" + ln +
				"</div>" + ln +
				"</div>" + ln +
				"<!-- FIN page credits -->" + ln;
	}
	
	public String createDivProject(
			ArrayList <String[]> projects, ArrayList <String[]> stakeholders, ArrayList <String[]> subjects, ArrayList <String[]> students) {
		
		String divContent = "<!-- DEBUT page projets -->" + ln +
				"<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">" + ln +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" + ln +
				"\t<h1>Projets 2014-2015</h1>" + ln +
				"</div>" + ln +
				"<div data-role=\"content\">" + ln +
				"\t<form class=\"ui-filterable\">" + ln +
				"\t<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\">" + ln +
				"</form>" + ln +
				"<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">" + ln;
				
		for( String[] project : projects ) {
			
			// Retrieve project's subject
			for( String[] subject : subjects ) {
				
				if( ! project[2].equals(subject[0]) )
					continue;
				
				divContent += "<li>" + 
						"<p><b>["+subject[1].trim()+"]</b> "+subject[2]+ 
						"</p>" + ln;
				break;
			}
			
			String client = "", supervisor = "";
			
			// Retrieve project's stakeholders
			for( String[] stakeholder : stakeholders ) {
				
				if( stakeholder[0].equals(project[3]) )
					client = stakeholder[2] + " " + stakeholder[1];
				
				if( stakeholder[0].equals(project[4]) )
					supervisor = stakeholder[2] + " " + stakeholder[1];
			}
			
			divContent += "<p>" + ln +
					"<b>Client :</b> "+client+ ln + 
					"</p>" + ln + 
					"<p>" + ln +
					"<b>Superviseur :</b> "+supervisor + ln + 
					"</p>" + ln;
			
			String listStudents = "";
			
			// Retrieve project's students
			for( String[] student : students ) {
				if( student[0].equals(project[1]) )
					listStudents += student[3] + " " + student[2] + " - ";
			}
			
			divContent += "<p>" + ln +
					"<b>Groupe "+project[1]+" :</b> "+listStudents + ln +
					"</p>" + ln +
					"</li>" + ln;
		}

		divContent += "</ol>" + ln +
				"</div>" + ln +
				"<div data-role=\"footer\">" + ln +
				"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>" + ln +
				"</div>" + ln +
				"</div>" + ln +
				"<!-- FIN page projets -->" + ln;
		
		return divContent;
	}
	
	public String createDivSubject(ArrayList <String[]> subjects, ArrayList <String[]> projects) {
		String divContent = "<!-- DEBUT page sujets -->" + ln +
				"<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">" + ln + 
				"<div data-role=\"header\" data-add-back-btn=\"true\">" + ln +
				"<h1>Sujets 2014-2015</h1>" + ln +
				"</div>" + ln +
				"<div data-role=\"content\">" + ln + 
				"<form class=\"ui-filterable\">" + ln + 
				"\t<input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\">" + ln +
				"</form>" + ln +
				"<ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\">" + ln +
				"\t<li data-role=\"list-divider\">" + ln +
				"Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>" + ln +
				"</li>" + ln;
		
		for( String[] subject : subjects ) {
			divContent += "<li data-find=\"["+subject[1]+"]\">" + ln; 
			divContent += "<a href=\"#projets\">["+subject[1]+"] <br/>" + ln;
			divContent += "<div style=\"white-space:normal;\">" + ln;
			divContent += "<span><b>"+subject[2]+"</b>" + ln;
			
			String group = "";
			
			for( String[] project : projects )
				if( project[2].equals(subject[0]) )
					group += project[1] + " ";
			
			divContent += "</span><span class=\"ui-li-count\">"+group+"</span>" + ln;
			divContent += "</div>" + ln +
						"</a>" + ln +
						"</li>" + ln;
		}
		
		divContent += 
				"</ol>" + ln +
				"</div>" + ln +
				"<div data-role=\"footer\">" + ln +
				"\t<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>" + ln +
				"</div>" + ln +
				"</div>" + ln +
				"<!-- FIN page sujets -->" + ln;
		
		return divContent;
	}
	
	public String createDivStudent(ArrayList <String[]> students) {
		String divContent =	"<!-- DEBUT page etudiants -->" + ln +
				"<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">" + ln +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" + ln +
				"\t<h1>Etudiants 2014-2015</h1>" + ln +
				"</div>" + ln +
				"<div data-role=\"content\">" + ln +
				"<form class=\"ui-filterable\">" + ln +
				"\t<input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\">" + ln +
				"</form>" + ln +
				"<ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\">" + ln +
				"\t<li data-role=\"list-divider\">" + ln + 
				"Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>" + ln +
				"</li>" + ln;
		
		Collections.sort(students, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				String[] student1 = (String[]) o1;
				String[] student2 = (String[]) o2;
				
				return student1[2].compareTo(student2[2]);
			}
		});
		
		for( String[] student : students ) {
			divContent += "<li data-find=\""+student[3]+" "+student[2]+"\">" + ln;
			divContent += "<a href=\"#projets\">"+student[2]+" "+student[3]+"<span class=\"ui-li-count\" title=\"Groupe\">Groupe "+student[0]+"</span>" + ln;
			divContent += "</a>" + ln;
			divContent += "</li>" + ln;
		}

		divContent += "</ol>" + ln +
				"</div>" + ln +
				"<div data-role=\"footer\">" + ln +
				"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>" + ln +
				"</div>" + ln +
				"</div>" + ln +
				"<!-- FIN page etudiants -->" + ln;
		
		return divContent;
	}
	
	public String createDivStakeholder(ArrayList <String[]> stakeholders, ArrayList <String[]> projects) {
		String divContent = "<!-- DEBUT page intervenants -->" + ln +
				"<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">" + ln +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" + ln +
				"<h1>Intervenants 2014-2015</h1>" + ln +
				"</div>" + ln +
				"<div data-role=\"content\">" + ln +
				"<form class=\"ui-filterable\">" + ln +
				"<input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\">" + ln +
				"</form>" + ln +
				"<ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\">" + ln +
				"<li data-role=\"list-divider\">" + ln +
				"Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span>" + ln +
				"</li>" + ln;
		
		for( String[] stakeholder : stakeholders ) {
			divContent += "<li data-find=\""+stakeholder[2]+" "+stakeholder[1]+"\">" + ln;
			divContent += "<a href=\"#projets\">" + ln;
			divContent += stakeholder[2]+" "+stakeholder[1] + ln;
			
			int nbrClient = 0, nbrSupervisor = 0;
			
			for( String[] project : projects ) {
				if( project[3].equals(stakeholder[0]) )
					nbrClient++;
				if( project[4].equals(stakeholder[0]) )
					nbrSupervisor++;
			}
			
			divContent += "<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">"+nbrClient+"</span>" + ln;
			divContent += "<span class=\"ui-li-count\" title=\"Superviseur\">"+nbrSupervisor+"</span>" + ln;
			divContent += "</a>" + ln;
			divContent += "</li>" + ln;
		}

		divContent += "</ul>" + ln +
				"</div>" + ln +
				"<div data-role=\"footer\">" + ln +
				"\t<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>" + ln +
				"</div>" + ln +
				"</div>" + ln +
				"<!-- FIN page intervenants -->" + ln;
		
		return divContent;
	}
	
	private String createLastStructurePart() {
		return	"<script>" + ln +
				"$( 'li[data-find]' ).on( 'click',function(event){" + ln +
				"$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');" + ln +
				"});" + ln +
				"</script>" + ln +
				"</body>" + ln +
				"</html>";
	}
	
}
