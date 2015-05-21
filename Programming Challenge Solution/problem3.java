/**
 * Author: Shiyu Sun
 * Description: This program allows user to input a level and generate a triangle with that level and each points are represent as following form:
 * 							(0,0)
 * 						(1,-1)	(1,1)
 * 					(2,-2)	(2,0)	(2,2)
 * 				(3,-3)	(3,-1)	(3,1)	(3,3)
 * 			(4,-4)	(4,-2)	(4,0)	(4,2)	(4,4)
 *              then the program will calculate how many triangles are there by go through each point.
 * Date: 5/20/2015
 * */
import java.util.ArrayList;
import java.util.Scanner;


public class problem3 {
	private int level=4;
	private ArrayList<String> arr=new ArrayList<String>();
	public problem3(){
		initialTriangle();
		buildTriangle();
		System.out.println("There are "+searchTriangles()+" triangles here.");
	}
	public void initialTriangle(){
		Scanner sc=new Scanner(System.in);
		while(true){
		System.out.println("How many level (level of triangles) you want? (press enter to apply default 4)");
		String tmp=sc.nextLine();
		if(tmp.length()>0&&!tmp.equals(" ")){
		try{
			level=Integer.parseInt(tmp);
			if(level<=0){
				System.out.println("You need to input a positive number!");
				continue;
			}
			if(level>100){
				System.out.println("You need to input a number in range 1-100!");
				continue;
			}
		}
		catch(Exception e){
			System.out.println("You need to input a integer number!");
			continue;
		}
		}
		break;
		}
		while(true){
			System.out.println("The result can be derive either from the function or simulation, do you want to use the formula to calculate? Y/N");
			String tmp=sc.nextLine();
			if(tmp.equals("Y")||tmp.equals("y")||tmp.equals("Yes")||tmp.equals("yes"))
			{
			int r;
			if(level%2==0){
				r=level*(level+2)*(level*2+1)/8;
			}
			else{
				r=(level+1)*(2*level*level+3*level-1)/8;
			}
			System.out.println("There are "+r+" triangles here.");
			System.exit(0);	
			}
			if(tmp.equals("N")||tmp.equals("n")||tmp.equals("No")||tmp.equals("no")){
				break;
			}
			System.out.println("Please input Y/N");
		}
		sc.close();
	}
	public void buildTriangle(){
		arr.add("0 0");
		for(int i=1;i<=level;i++){
			for(int j=-i;j<=i;j+=2)
			arr.add(i+" "+j);
		}
	}
	public int searchTriangles(){
		int result=0;
		int size=arr.size();
		for(int i=0;i<size;i++){
			String[] tmp=arr.get(i).split(" ");
			int x=Integer.parseInt(tmp[0]);
			int y=Integer.parseInt(tmp[1]);
			//number of regular triangles from that point
			result+=(level-x);
			int id=1;
			//number of inverted triangles from that point
			while(true){
				if(y<0){
					if(arr.contains((x-id)+" "+(y-id))){
						result++;
						id++;
						continue;
					}
					else
						break;
				}
				else{
					if(arr.contains((x-id)+" "+(y+id))){
						result++;
						id++;
						continue;
					}
					else
						break;
				}
				
			}
		}
		return result;
	}
	public static void main(String args[]){
		problem3 p=new problem3();
}
}
