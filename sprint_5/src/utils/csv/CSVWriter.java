package utils.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter {

	public static void toFile(File f, ArrayList<String[]> output)
			throws IOException {

		String path = f.getPath();
		String[] pathSplited = path.split("\\.");
		if (pathSplited.length == 1) {
			path = path + ".csv";
			f = new File(path);
		}

		FileWriter fw = new FileWriter(f);

		for (int line = 0; line < output.size(); line++) {

			for (int value = 0; value < output.get(line).length; value++) {
				fw.write(output.get(line)[value]);

				// Si ce n'est pas la dernière valeur, on rajoute une virgule
				if (value < output.get(line).length - 1) {
					fw.write(";");
				}
			}

			fw.write("\r\n");
		}

		fw.close();

	}

}
