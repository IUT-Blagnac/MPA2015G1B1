package utils.annotations;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
 
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("utils.annotations.ExpensiveOperation")
public class ExpensiveOperationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
		
		 processingEnv.getMessager().printMessage(Kind.NOTE, "Démarrage du processor");

		for( Element elem : env.getElementsAnnotatedWith(ExpensiveOperation.class) ) {
			
			ExpensiveOperation operation = elem.getAnnotation(ExpensiveOperation.class);
			String message = "Traitement d'une annotation :"+operation;
			
			processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
		}

		return true; // no further processing of this annotation type
	}

}