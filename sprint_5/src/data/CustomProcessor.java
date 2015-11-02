package data;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes(value = { "*" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class CustomProcessor extends AbstractProcessor {

	@Override
	public boolean process(
			Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		
		Messager messager = processingEnv.getMessager();

		messager.printMessage(Kind.ERROR, "une erreur");
		
		for (TypeElement te : annotations) {
			messager.printMessage(Kind.NOTE, "Traitement annotation " 
					+ te.getQualifiedName());

			for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
				messager.printMessage(Kind.NOTE, "  Traitement element " 
						+ element.getSimpleName());
				NotNull notNull = element.getAnnotation(NotNull.class);

				if (notNull != null) {
					messager.printMessage(Kind.NOTE, "annotation"+notNull);
				}
				else {
					messager.printMessage(Kind.NOTE, "autre chose");
				}
			}
		}

		return true;
	}
}
