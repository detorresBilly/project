package project;

public class Memory {

	public static int DATA_SIZE = 2048;
	private int[] data = new int[DATA_SIZE];
	
	int[] getData() {
		return data;
	}
	
	public int getData(int index) {
		return data[index];
	}
	
	
	public void setData(int[] data) {
		this.data = data;
	}
	
}
