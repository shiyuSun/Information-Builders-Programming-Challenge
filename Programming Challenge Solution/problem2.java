/**
 * Author: Shiyu Sun
 * Description: This program is used to process the puzzle of a problem about a family cross river,
 * 	             here I use the Depth-first search to go through all the possible valid operations without 
 * 	             repeat and show all the result.
 * Date: 5/20/2015
 * */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class problem2{
	private String[] describtion={"father","mother","son1","son2","daughter1","daughter2","guard","prisoner"};
	private int[] lastmov=new int[2];
	//0:father,1:mother,23:sons,4,5:daughters,6:guard,7:prisoner
	private int[] thisside=new int[8];
	//File f=new File("D://abc.txt");
	//FileWriter fileWriter;
	private int[][] canuse={{1,2,3,-1},{4,5,-1},{1,2,3,4,5,7,-1}};
	public boolean checkfamily(int[] thisside){
		if(thisside[1]==0&&thisside[0]==1){
			//father this side and mother other side
			if(thisside[4]==1||thisside[5]==1)
				return false;
			if(thisside[2]==0||thisside[3]==0)
				return false;
		}
		if(thisside[1]==1&&thisside[0]==0){
			//Mother this side and father other side
			if(thisside[2]==1||thisside[3]==1)
				return false;
			if(thisside[4]==0||thisside[5]==0)
				return false;
		}
		return true;
	}
	public boolean checkprisoner(int[] thisside){
		if(thisside[7]==0&&thisside[6]==1){
			for(int i=0;i<6;i++){
				if(thisside[i]==0)
					return false;
			}
		}
		if(thisside[7]==1&&thisside[6]==0){
			for(int i=0;i<6;i++){
				if(thisside[i]==1)
					return false;
			}
		}
		return true;
	}
	public boolean isRepeat(int[] now,int[] last){
		if(now[0]==last[0]&&now[1]==last[1])
			return true;
		return false;
	}
	public boolean isFinish(int[] thisside){
		for(int i=0;i<8;i++){
			if(thisside[i]==1)
				return false;
		}
		return true;
	}
	public void move(int[] thisside,boolean inthisside,int[] last,String path) throws IOException{
		if(inthisside==true){
		int[] with = null;
		for(int iden=0;iden<3;iden++){
			if(iden!=2&&thisside[iden]==0)
				continue;
			if(iden==2&&thisside[6]==0)
				break;
		with=canuse[iden];
		for(int i=0;i<with.length;i++){
			if(with[i]!=-1&&thisside[with[i]]==0)
				continue;

			int[] tmpside=Arrays.copyOf(thisside, thisside.length);
			if(iden==2){
				int[] t={6,with[i]};

				if(isRepeat(t,last))
					continue;
				
				tmpside[6]=0;
				if(with[i]!=-1)
				tmpside[with[i]]=0;
				if(checkprisoner(tmpside)&&checkfamily(tmpside)){
					String tp=path+(" "+6+" "+with[i]);
					if(!isFinish(tmpside)){
						if(tp.length()<80){
							//fileWriter.write(tp+"\n");
							move(tmpside,!inthisside,t,tp);
						}
					}
					else
						describeResult(tp);
				}
			}
			else{
				int[] t={iden,with[i]};
				if(isRepeat(t,last))
					continue;
			tmpside[iden]=0;
			if(with[i]!=-1)
			tmpside[with[i]]=0;
			if(checkfamily(tmpside)&&checkprisoner(tmpside)){
				String tp=path+(" "+iden+" "+with[i]);
				if(!isFinish(tmpside)){
					if(tp.length()<80){
					//fileWriter.write(tp+"\n");
					move(tmpside,!inthisside,t,tp);
					}
				}
				else
					describeResult(tp);
			}
			}
		}
		}
		}
		else{
			int[] with=null;
			for(int iden=0;iden<3;iden++){
				if(iden!=2&&thisside[iden]==1)
					continue;
				if(iden==2&&thisside[6]==1)
					break;
				with=canuse[iden];
				for(int i=0;i<with.length;i++){
					if(with[i]!=-1&&thisside[with[i]]==1)
						continue;
					int[] tmpside=Arrays.copyOf(thisside, thisside.length);
					if(iden==2){
						int[] t={6,with[i]};
						if(isRepeat(t,last))
							continue;
						tmpside[6]=1;
						if(with[i]!=-1)
						tmpside[with[i]]=1;
						if(checkprisoner(tmpside)&&checkfamily(tmpside)){
							String tp=path+(" "+6+" "+with[i]);
							if(!isFinish(tmpside)){
								if(tp.length()<80){
									//fileWriter.write(tp+"\n");
									move(tmpside,!inthisside,t,tp);
								}
							}
							else
								describeResult(tp);
						}
					}
					else{
						int[] t={iden,with[i]};
						if(isRepeat(t,last))
							continue;
					tmpside[iden]=1;
					if(with[i]!=-1)
					tmpside[with[i]]=1;
					if(checkfamily(tmpside)&&checkprisoner(tmpside)){
						String tp=path+(" "+iden+" "+with[i]);
						if(!isFinish(tmpside)){
							if(tp.length()<80){
							//fileWriter.write(tp+"\n");
							move(tmpside,!inthisside,t,tp);
							}
						}
						else
							describeResult(tp);
					}
					}
				}
			}
		}
	}
	private void describeResult(String result){
		System.out.println("One possible solution:");
		String[] sr=result.split(" ");
		int len=sr.length;
		int[] index=new int[len];
		for(int i=1;i<len;i++){
			index[i]=Integer.parseInt(sr[i]);
			System.out.print(index[i]+" ");
		}
		System.out.println();
		for(int i=1;i<len;i+=4){
			System.out.print(describtion[index[i]]);
			if(index[i+1]!=-1){
				System.out.print(" and "+describtion[index[i+1]]);
			}
			System.out.println(" cross the river");
			if(i+2<len){
			System.out.print(describtion[index[i+2]]);
			if(index[i+3]!=-1){
				System.out.print(" and "+describtion[index[i+3]]);
			}
			System.out.println(" back");
			}
		}
		System.out.println("Then everybody could cross the river!");
	}
	public problem2(){
		//This part can be used to write result to the text file
		/*try {
			fileWriter=new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		for(int i=0;i<8;i++){
			thisside[i]=1;
			}
		try {
			move(thisside,true,lastmov,"");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void main(String args[]){
		problem2 p=new problem2();
	}
}
