/**
 * Author: Shiyu Sun
 * Description: This program allows user to input a dimension and generate a randonly 1 and 0 matrix with that dimension,
 *              then the program will calculate how much group of 1s in this random matrix using recursion method.
 * Date: 5/20/2015
 * */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class problem1 {
	private int row=4;
	private int col=5;
	private int[] matrix;
	private int[] cpy;
	private int result;
	public problem1(){
		initMatrix();
		System.out.println("Result is printed where each group of 1s present by same number");
		printResult();
	}
	private void initMatrix(){
		Scanner sc=new Scanner(System.in);
		while(true){
		System.out.println("Input the dimension of matrix in rows cols, (press enter to apply default 4x5) :");
		String tmp=sc.nextLine();
		if(tmp.length()>0&&!tmp.equals(" ")){
			try{
			String[] rc=tmp.split(" ");
			row=Integer.parseInt(rc[0]);
			col=Integer.parseInt(rc[1]);
			if(row<=0||col<=0){
				System.out.println("Input number should be positive");
				row=4;
				col=5;
				continue;	
			}
			if(row>100||col>100){
				row=4;
				col=5;
				System.out.println("Input number should be within 1-100");
				continue;	
			}
			}
			catch(Exception e){
				row=4;
				System.out.println("You should input two integers like: 4 5");
				continue;
			}
		}
		break;
		}
		int dim=row*col;
		matrix = new int[dim];
		Random random=new Random();
		for(int i=0;i<dim;i++){
			matrix[i]=random.nextInt(2);
		}
		System.out.println("Matrix with "+row+"x"+col+":");
		printMatrix();
		cpy=Arrays.copyOf(matrix, row*col);
		int cojs=processMatrix();
		System.out.println("There are "+cojs+" Group of 1s in this matrix.");
		//printmatrix();
	}
	private void printMatrix(){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(matrix[i*col+j]+" ");
			}
			System.out.println();
		}
	}
	private void printResult(){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(cpy[i*col+j]+"\t");
			}
			System.out.println();
		}
	}
	private int processMatrix(){
		result=0;
		for(int i=0;i<row*col;i++){
			if(matrix[i]==1){
				result++;
				search(i);
				}
		}
		return result;
	}
	private void search(int i){
		matrix[i]=0;
		cpy[i]=result;
		int r=i/col;
		int c=i%col;
		if(r>0&&matrix[i-col]==1)
			search(i-col);
		if(r<row-1&&matrix[i+col]==1)
			search(i+col);
		if(c>0&&matrix[i-1]==1)
			search(i-1);
		if(c<col-1&&matrix[i+1]==1)
			search(i+1);
	}
public static void main(String args[]){
	problem1 p=new problem1();
}
}