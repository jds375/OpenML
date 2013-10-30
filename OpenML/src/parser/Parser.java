package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Jama.Matrix;

public class Parser {

	/**
	 * @param filepath
	 *            - A file in SVM-light format with the exception that all
	 *            feature vectors must have values for each feature and all
	 *            feature vectors must be the same size.
	 * @return A dataset representative of the data in the file.
	 */
	public static DataSet createDataSet(final String filepath) {
		Scanner parser;
		ArrayList<FeatureObject> featureObjects = new ArrayList<FeatureObject>();
		try {
			parser = new Scanner(new File(filepath));
			while (parser.hasNextLine()) {
				String line = parser.nextLine();
				final int spaceIndex = line.indexOf(" ");
				final int label = Integer.parseInt(line
						.substring(0, spaceIndex));
				ArrayList<Double> features = new ArrayList<Double>();
				while (line.length() > 0) {
					final int colonIndex = line.indexOf(":");
					line = line.substring(colonIndex + 1);
					int endIndex = line.indexOf(" ");
					if (endIndex == -1) {
						endIndex = line.length();
					}
					features.add(Double.parseDouble(line.substring(0, endIndex)));
					line = line.substring(endIndex);
				}
				featureObjects.add(new FeatureObject(label, new Matrix(
						toDoubleArray(features), 1)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return toDataSet(featureObjects);
	}

	private static double[] toDoubleArray(final ArrayList<Double> features) {
		double[] result = new double[features.size()];
		for (int i = 0; i < features.size(); i++) {
			result[i] = features.get(i);
		}
		return result;
	}

	private static DataSet toDataSet(
			final ArrayList<FeatureObject> featureObjects) {
		FeatureObject[] result = new FeatureObject[featureObjects.size()];
		for (int i = 0; i < featureObjects.size(); i++) {
			result[i] = featureObjects.get(i);
		}
		return (new DataSet(result));
	}
}
