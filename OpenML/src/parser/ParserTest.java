package parser;

import static org.junit.Assert.*;

import org.junit.Test;

import Jama.Matrix;
import bayes.NaiveBayes;

public class ParserTest {

	@Test
	public void test() {
		DataSet trainingSet = Parser.createDataSet("parserTest.txt");
		NaiveBayes naiveBayes = new NaiveBayes(trainingSet);
		naiveBayes.train();
		double[] testFeature = { 1, 0, 1};
		FeatureObject testObject = new FeatureObject(new Matrix(testFeature, 1));
		assertEquals(1, naiveBayes.classify(testObject));
	}

}
