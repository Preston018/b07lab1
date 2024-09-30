import java.util.Arrays;

public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		
		double [] c1 = {6,2,2,5};
		int [] e1 = {2,4,5,1};
		Polynomial p1 = new Polynomial(c1,e1);
		double [] c2 = {1,-2,3,4,-9};
		int [] e2 = {1,2,0,4,3};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		
		if (Arrays.equals(s.coefficients, new double[]{4,6,2,6,3,-9}) && Arrays.equals(s.exponents, new int[]{2,4,5,1,0,3})) {
			System.out.println("add slay");
		}
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
		
		double [] c3 = {2,-2,1};
		int [] e3 = {2,0,1};
		Polynomial p3 = new Polynomial(c3,e3);
		
		if (Arrays.equals(p3.coefficients, c3) && Arrays.equals(p3.exponents, e3)) {
			System.out.println("same");
		}
		
		double [] c4 = {3,-9};
		int [] e4 = {1,3};
		Polynomial p4 = new Polynomial(c4,e4);
		Polynomial t = p3.multiply(p4);
		
		if (Arrays.equals(t.coefficients, new double[]{24,-18,-6,3,-9}) && Arrays.equals(t.exponents, new int[]{3,5,1,2,4})) {
			System.out.println("mult slay");
		} else {
			for (int i = 0; i < t.coefficients.length; i++) {
				System.out.println(t.coefficients[i] + "x" + t.exponents[i]);
			}
				
		}
	}
}