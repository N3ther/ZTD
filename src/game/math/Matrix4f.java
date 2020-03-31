package game.math;

public class Matrix4f {
	
	public static final int SIZE = 4 * 4;
	public float[] elements = new float[4 * 4];
	
	public Matrix4f() {
		
	}
	
	public static Matrix4f identity() {
		Matrix4f result = new Matrix4f();
		for (int i = 0; i < SIZE; i++) {
			result.elements[i] = 0.0f;
		}
		result.elements[0 + 0 * 4] = 1.0f;
		result.elements[1 + 1 * 4] = 1.0f;
		result.elements[2 + 2 * 4] = 1.0f;
		result.elements[3 + 3 * 4] = 1.0f;
		
		return result;
		
	}
	
	public static Matrix4f projection(float west, float east, float bottom, float top, float north, float south) {
		Matrix4f result = identity();
		
		result.elements[0 + 0 * 4] = 2.0f / (west - east);
		result.elements[1 + 1 * 4] = 2.0f / (top - bottom);
		result.elements[2 + 2 * 4] = 2.0f / (north - south);
		return result;
	}

}
