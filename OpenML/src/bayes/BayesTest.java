package bayes;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.DataSet;
import parser.FeatureObject;
import Jama.Matrix;

public class BayesTest {

	@Test
	public void testNaiveBayes() {
		double[] feature1 = { 1, 1, 0};
		double[] feature2 = { 1, 0, 1};
		double[] feature3 = { 0, 1, 0};
		double[] feature4 = { 0, 1, 1};
		FeatureObject object1 = new FeatureObject(1, new Matrix(feature1, 1));
		FeatureObject object2 = new FeatureObject(1, new Matrix(feature2, 1));
		FeatureObject object3 = new FeatureObject(-1, new Matrix(feature3, 1));
		FeatureObject object4 = new FeatureObject(1, new Matrix(feature4, 1));
		FeatureObject[] objects = { object1, object2, object3, object4};
		DataSet trainingSet = new DataSet(objects);
		NaiveBayes naiveBayes = new NaiveBayes(trainingSet);
		naiveBayes.train();
		double[] testFeature = { 1, 0, 1};
		FeatureObject testObject = new FeatureObject(new Matrix(testFeature, 1));
		assertEquals(1, naiveBayes.classify(testObject));
	}
	
	@Test
	public void testSmoothedBayes() {
		double[] feature1 = { 1, 1, 0};
		double[] feature2 = { 1, 0, 1};
		double[] feature3 = { 0, 1, 0};
		double[] feature4 = { 0, 1, 1};
		FeatureObject object1 = new FeatureObject(1, new Matrix(feature1, 1));
		FeatureObject object2 = new FeatureObject(1, new Matrix(feature2, 1));
		FeatureObject object3 = new FeatureObject(-1, new Matrix(feature3, 1));
		FeatureObject object4 = new FeatureObject(1, new Matrix(feature4, 1));
		FeatureObject[] objects = { object1, object2, object3, object4};
		DataSet trainingSet = new DataSet(objects);
		SmoothedBayes smoothedBayes = new SmoothedBayes(trainingSet);
		smoothedBayes.train();
		double[] testFeature = { 1, 0, 1};
		FeatureObject testObject = new FeatureObject(new Matrix(testFeature, 1));
		assertEquals(1, smoothedBayes.classify(testObject));
	}

}
