import java.io.*;
import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;

 
public class Polynomial {
	
	// fields
	double [] coefficients;
	int [] exponents;
	
	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}
	
	public Polynomial(double[] A, int[] B) {
		
		int len = A.length;
		
		this.coefficients = new double[len];
		this.exponents = new int[len];
				
		for (int i = 0; i < len; i++) {
				this.coefficients[i] = A[i];
				this.exponents[i] = B[i];
		}
	}
	
	public Polynomial(double[] A, int[] B, int N) {
		
		int nonZero = 0;
		int len = A.length;
		
		for (int i = 0; i < len; i++) {
			if (A[i] != 0) {
				nonZero++;
			}
		}
		
		this.coefficients = new double[nonZero];
		this.exponents = new int[nonZero];
				
		int index = 0; // Track the insertion index
		for (int i = 0; i < len; i++) {
		    if (A[i] != 0) {
		        this.coefficients[index] = A[i];
		        this.exponents[index] = B[i];
		        index++; // Increment after insertion
		    }
		}
	}
	
	public Polynomial add(Polynomial B) {
	    int lenA = this.coefficients.length;
	    int lenB = B.coefficients.length;
	    
	    if (lenA == 0) {
	        return new Polynomial(B.coefficients, B.exponents);
	    }
	    
	    if (lenB == 0) {
	        return new Polynomial(this.coefficients, this.exponents);
	    }
	    
	    double[] rC = new double[lenA + lenB];
	    int[] rE = new int[lenA + lenB];
	    
	    int index = 0;
	    for (int i = 0; i < lenA; i++) {
	        rC[index] = this.coefficients[i];
	        rE[index] = this.exponents[i];
	        index++;
	    }
	    
	    for (int i = 0; i < lenB; i++) {
	        boolean found = false;
	        for (int j = 0; j < lenA; j++) {
	            if (this.exponents[j] == B.exponents[i]) {
	                rC[j] += B.coefficients[i];
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            rC[index] = B.coefficients[i];
	            rE[index] = B.exponents[i];
	            index++;
	        }
	    }
	    
	    double[] trimmedC = Arrays.copyOf(rC, index);
	    int[] trimmedE = Arrays.copyOf(rE, index);
	    
	    return new Polynomial(trimmedC, trimmedE);
	}

	
	
//	public Polynomial add(Polynomial B) {
//		
//		int lenA = this.coefficients.length;
//		int lenB = B.coefficients.length;
//		
//		if (lenA == 0) {
//			return new Polynomial(B.coefficients, B.exponents);
//		}
//		
//		if (lenB == 0) {
//			return new Polynomial(this.coefficients, this.exponents);
//		} 
//		
//		int diff = 0;
//		
//		for (int i = 0; i < lenA; i++) {
//			
//			int temp = 0;
//		
//			for (int j = 0; j < lenB; j++) {
//				
//				if (this.exponents[i] == B.exponents[j])
//					temp++;
//			}
//			if (temp > 0) {
//				diff++;
//			}
//		}
//		
//		int size = diff + lenA;
//		double[] rC = new double[size];
//		int[] rE = new int[size];
//		
//		int indA = 0, indB = 0;
//		
//		Polynomial result = new Polynomial(rC, rE);
//		
//		
//		for (int i = 0 ; i < lenA; i++) {
//			
//			result.coefficients[i] = this.coefficients[i];
//			result.exponents[i] = this.exponents[i];
//		}
//		
//		int indAddOn = lenA;
//		
//		for (int i = 0; i < lenB; i++) {
//			
//			int added = 0;
//			
//			for (int j = 0; j < size; j++) {
//				
//				if (j < indAddOn && (result.exponents[j] == B.exponents[i]) && added == 0) {
//					
//					result.coefficients[j] = result.coefficients[j] + B.coefficients[i];
//					added = 1;
//					
//				} else if (j >= indAddOn && added == 0) {
//					
//					result.coefficients[j] = B.coefficients[i];
//					result.exponents[j] = B.exponents[i];
//					added = 1;
//					indAddOn++;
//					
//				}
//				
//			}
//			
//		}
//		return new Polynomial(result.coefficients, result.exponents, 0);
//	}
	
	public double evaluate(double x) {
		
		double power = 0;
		double result = 0;
		int len = coefficients.length;
		
		for (int i = 0; i < len; i++) {
			
			result += coefficients[i] * Math.pow(x,exponents[i]);
		}
		return result;
		
	}
	
	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
	
	public Polynomial multiply(Polynomial B) {
		
		int size = B.coefficients.length * this.coefficients.length;
		int lenA = this.coefficients.length;
		int lenB = B.coefficients.length;
		
		if (lenA == 0 || lenB == 0) {
			return new Polynomial();
		}
		
		double[] rC = new double[size];
		int[] rE = new int[size];
		Polynomial result = new Polynomial(rC, rE, 0);
		
		for (int i = 0; i < lenA; i++) {
			
			double[] tC = new double[lenB];
			int[] tE = new int[lenB];
			
			for (int j = 0; j < lenB; j++) {
				
				tC[j] = this.coefficients[i] * B.coefficients[j];
				tE[j] = this.exponents[i] + B.exponents[j];
			}
			
			Polynomial tempResult = new Polynomial(tC, tE, 0);
			
			result = result.add(tempResult);
			
		}
		
		return result;
	}
	
	public Polynomial(File B) {
		
		try (Scanner scan = new Scanner(B)) {
			
			String text = scan.nextLine();
			String[] termArray = text.split("(?=[-])|[+]");
			int termLen = termArray.length;
			int indConstant = -1;
			
			for (int i = 0; i < termLen; i++) {
				
				int x = 0;
				
				for (int j = 0; j < termArray[i].length(); j++) {
					
					if (termArray[i].charAt(j) == 'x') {
						x++;
					}
				}
				
				if (x == 0)
					indConstant = i;
			}
			
			double[] rC = new double[termLen];
			int[] rE = new int[termLen];
			
			String[] numArray = text.split("(?=[-])|[+x]");
			int numLen = numArray.length;
			int rInd = 0;
			
			for (int i = 0; i < numLen; i += 2) {
				
				if ((int)(i/2.0) == indConstant) {
					
					rC[rInd] = Double.parseDouble(numArray[i]);
					rE[rInd] = 0;
					i--;
					rInd++;
					
				} else {
					
					rC[rInd] = Double.parseDouble(numArray[i]);
					rE[rInd] = Integer.parseInt(numArray[i+1]);
					rInd++;
				}
				
			}
			
			Polynomial result = new Polynomial(rC, rE);
			
		} catch (FileNotFoundException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	}
	
//	Add a method named saveToFile that takes one argument of type String representing a
//	file name and saves the polynomial in textual format in the corresponding file (similar to
//	the format used in part d)
	
	public void saveToFile(String string) {
		 try(FileWriter output = new FileWriter(string, true)){
		 
			 for (int i = 0; i < exponents.length; i++) {
				 
				 if (coefficients[i] == 0) {
					 
					 continue;
					 
				 } else if (exponents[i] == 0 && i != 0 && coefficients[i] > 0) {
					 
					 output.write("+" + coefficients[i]);
					 
				 } else if (exponents[i] == 0) {
					 
					 output.write("" + coefficients[i]);
					 
				 } else if (coefficients[i] > 0 && i != 0) {
					 
					 output.write("+" + coefficients[i] + "x");
					 
				 } else {
					 
					 output.write("" + coefficients[i] + "x");
					 
				 } if (exponents[i] > 1) {
					 
					 output.write("" + exponents[i]);
					 
				 }
			 }
			 
		 } catch (IOException e) {
		        System.err.println("Error writing to file: " + e.getMessage());
		 }
		 
		 
	}
	
	
	
	
}