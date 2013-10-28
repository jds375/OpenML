package bayes;

class DataSet {
	
	private FeatureObject[] dataSet;
	
	public DataSet(final FeatureObject[] dataSet) {
		this.setDataSet(dataSet);
	}

	public FeatureObject[] getDataSet() {
		return dataSet;
	}

	public void setDataSet(FeatureObject[] dataSet) {
		this.dataSet = dataSet;
	}

}
