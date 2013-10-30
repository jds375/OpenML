package knn;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.DataSet;
import Jama.Matrix;

public class KNNTest {

	@Test
	public void testUnweightedKNN() {
		double[] feature1 = { 4.0, 6.0, 10.3, 0, 2.5 };
		double[] feature2 = { 3.2, 5.0, 7.0, 1.1, 12.3 };
		double[] feature3 = { 1.1, 6.3, 0, 0, 15.8 };
		double[] feature4 = { 7.2, 2.9, 6.6, 10.0, 1.1 };
		double[] feature5 = { 3.0, 7.7, 19.2, 0, 0 };
		FeatureObjectSimilarity object1 = new FeatureObjectSimilarity(4, new Matrix(feature1, 1));
		FeatureObjectSimilarity object2 = new FeatureObjectSimilarity(7, new Matrix(feature2, 1));
		FeatureObjectSimilarity object3 = new FeatureObjectSimilarity(1, new Matrix(feature3, 1));
		FeatureObjectSimilarity object4 = new FeatureObjectSimilarity(3, new Matrix(feature4, 1));
		FeatureObjectSimilarity object5 = new FeatureObjectSimilarity(7, new Matrix(feature5, 1));
		FeatureObjectSimilarity[] objects = { object1, object2, object3, object4, object5 };
		DataSet trainingSet = new DataSet(objects);
		UnweightedKNN unweightedKNN = new UnweightedKNN(trainingSet, 1,
				new EuclideanDistance());
		double[] testFeature = { 3.7, 5.7, 10.0, 0.5, 2.3 };
		FeatureObjectSimilarity testObject = new FeatureObjectSimilarity(new Matrix(testFeature, 1));
		assertEquals(4, unweightedKNN.classify(testObject), 0);
		unweightedKNN.setK(3);
		assertEquals(7, unweightedKNN.classify(testObject), 0);
	}

	@Test
	public void testWeightedKNN() {
		double[] feature1 = { 4.0, 6.0, 10.3, 0, 2.5 };
		double[] feature2 = { 3.2, 5.0, 7.0, 1.1, 12.3 };
		double[] feature3 = { 1.1, 6.3, 0, 0, 15.8 };
		double[] feature4 = { 7.2, 2.9, 6.6, 10.0, 1.1 };
		double[] feature5 = { 3.0, 7.7, 19.2, 0, 0 };
		FeatureObjectSimilarity object1 = new FeatureObjectSimilarity(4, new Matrix(feature1, 1));
		FeatureObjectSimilarity object2 = new FeatureObjectSimilarity(7, new Matrix(feature2, 1));
		FeatureObjectSimilarity object3 = new FeatureObjectSimilarity(1, new Matrix(feature3, 1));
		FeatureObjectSimilarity object4 = new FeatureObjectSimilarity(3, new Matrix(feature4, 1));
		FeatureObjectSimilarity object5 = new FeatureObjectSimilarity(7, new Matrix(feature5, 1));
		FeatureObjectSimilarity[] objects = { object1, object2, object3, object4, object5 };
		DataSet trainingSet = new DataSet(objects);
		WeightedKNN weightedKNN = new WeightedKNN(trainingSet, 1,
				new EuclideanDistance());
		double[] testFeature = { 3.7, 5.7, 10.0, 0.5, 2.3 };
		FeatureObjectSimilarity testObject = new FeatureObjectSimilarity(new Matrix(testFeature, 1));
		assertEquals(4, weightedKNN.classify(testObject), 0);
		weightedKNN.setK(3);
		assertEquals(4, weightedKNN.classify(testObject), 0);
	}
	
	@Test
	public void testRegressiveKNN() {
		double[] feature1 = { 4.0, 6.0, 10.3, 0, 2.5 };
		double[] feature2 = { 3.2, 5.0, 7.0, 1.1, 12.3 };
		double[] feature3 = { 1.1, 6.3, 0, 0, 15.8 };
		double[] feature4 = { 7.2, 2.9, 6.6, 10.0, 1.1 };
		double[] feature5 = { 3.0, 7.7, 19.2, 0, 0 };
		FeatureObjectSimilarity object1 = new FeatureObjectSimilarity(4, new Matrix(feature1, 1));
		FeatureObjectSimilarity object2 = new FeatureObjectSimilarity(7, new Matrix(feature2, 1));
		FeatureObjectSimilarity object3 = new FeatureObjectSimilarity(1, new Matrix(feature3, 1));
		FeatureObjectSimilarity object4 = new FeatureObjectSimilarity(3, new Matrix(feature4, 1));
		FeatureObjectSimilarity object5 = new FeatureObjectSimilarity(7, new Matrix(feature5, 1));
		FeatureObjectSimilarity[] objects = { object1, object2, object3, object4, object5 };
		DataSet trainingSet = new DataSet(objects);
		RegressiveKNN regressiveKNN = new RegressiveKNN(trainingSet, 1,
				new EuclideanDistance());
		double[] testFeature = { 3.7, 5.7, 10.0, 0.5, 2.3 };
		FeatureObjectSimilarity testObject = new FeatureObjectSimilarity(new Matrix(testFeature, 1));
		assertEquals(4, regressiveKNN.classify(testObject), 0);
		regressiveKNN.setK(3);
		assertEquals(4.3872, regressiveKNN.classify(testObject), 0.011);
	}

}
