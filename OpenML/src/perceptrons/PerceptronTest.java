package perceptrons;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.DataSet;
import parser.FeatureObject;
import Jama.Matrix;

public class PerceptronTest {

	@Test
	public void testPrimalPerceptron() {
		double[] feature1 = { 1, 2 };
		double[] feature2 = { 2, 1 };
		double[] feature3 = { -1, -1 };
		double[] feature4 = { -1, 1 };
		FeatureObject object1 = new FeatureObject(1, new Matrix(feature1, 1));
		FeatureObject object2 = new FeatureObject(1, new Matrix(feature2, 1));
		FeatureObject object3 = new FeatureObject(-1, new Matrix(feature3, 1));
		FeatureObject object4 = new FeatureObject(-1, new Matrix(feature4, 1));
		FeatureObject[] objects = { object1, object2, object3, object4 };
		DataSet trainingSet = new DataSet(objects);
		PrimalPerceptron primalPerceptron = new PrimalPerceptron(trainingSet);
		primalPerceptron.train();
		assertEquals(-5, primalPerceptron.getBias(), 0.0001);
		assertEquals(3, primalPerceptron.getWeight().get(0, 0), 0);
		assertEquals(2, primalPerceptron.getWeight().get(0, 1), 0);
	}

	@Test
	public void testDualPerceptron() {
		double[] feature1 = { 1, 2 };
		double[] feature2 = { 2, 1 };
		double[] feature3 = { -1, -1 };
		double[] feature4 = { -1, 1 };
		FeatureObject object1 = new FeatureObject(1, new Matrix(feature1, 1));
		FeatureObject object2 = new FeatureObject(1, new Matrix(feature2, 1));
		FeatureObject object3 = new FeatureObject(-1, new Matrix(feature3, 1));
		FeatureObject object4 = new FeatureObject(-1, new Matrix(feature4, 1));
		FeatureObject[] objects = { object1, object2, object3, object4 };
		DataSet trainingSet = new DataSet(objects);
		DualPerceptron dualPerceptron = new DualPerceptron(trainingSet);
		dualPerceptron.train();
		assertEquals(-5, dualPerceptron.getBias(), 0.0001);
		assertEquals(1, dualPerceptron.getAlpha()[0], 0);
		assertEquals(0, dualPerceptron.getAlpha()[1], 0);
		assertEquals(1, dualPerceptron.getAlpha()[2], 0);
		assertEquals(1, dualPerceptron.getAlpha()[3], 0);
	}

}
