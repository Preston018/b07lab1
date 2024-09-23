
public class Polynomial {
	
	// fleids
	
	double [] coefficients;
	
	public Polynomial() {
//		for (int i = 0; i < coefficients.length; i++) {
//			coefficients[i] = 0;
//		}
		coefficients = new double[] {0};
	}
	
	public Polynomial(double[] A) {
		int len = A.length;
		
		coefficients = new double[len];
		
		for (int i = 0; i < len; i++)
		{
			coefficients[i] = A[i];
		}
	}
	
	public Polynomial add(Polynomial A) {
		int lenA = coefficients.length;
		int lenB = A.coefficients.length;
		
		if (lenA >= lenB) {
			
			Polynomial result = new Polynomial(coefficients);
			
			for (int i = 0; i < lenB; i++) {
				result.coefficients[i] += A.coefficients[i];
			}
			
			return result;
		}
		else {
			
			Polynomial result = new Polynomial(A.coefficients);
			
			for (int i = 0; i < lenA; i++) {
				result.coefficients[i] += coefficients[i];
			}
			
			return result;
		}
	}
	
	public double evaluate(double x) {
		double power = x;
		double result = coefficients[0];
		int len = coefficients.length;
		for (int i = 1; i < len; i++) {
			result += coefficients[i]*power;
			power = power*x;
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
	
	
	
	
	
	
	
	
	
}