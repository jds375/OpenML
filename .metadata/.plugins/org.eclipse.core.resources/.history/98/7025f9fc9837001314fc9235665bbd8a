package knn;

public class EuclideanDistance implements SimilarityMeasure {

	public EuclideanDistance() {
	}

	@Override
	public double getSimilarity(FeatureObject object1, FeatureObject object2) {
		return Math.pow(object1.getFeatures().minus(object2.getFeatures())
				.normF(), -1);
	}

}
