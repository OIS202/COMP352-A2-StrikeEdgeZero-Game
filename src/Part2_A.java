import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
public class Part2_A {

	public static void main(String[] args) {
		Scanner scan = null;
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter your command: ");
		String command = kb.nextLine();
		System.out.println(command);
		String[] commandLiterals = command.split(" ");//split line to get individual elements
		PrintWriter pw = null;
		int[] arr;
		try {
			scan = new Scanner(new FileInputStream(commandLiterals[2]));//input file
			pw = new PrintWriter(new FileOutputStream(commandLiterals[3]));//output file
			scan.nextLine();
			String line = "";
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				String[] lineArr = line.split(" ");
				Integer num = Integer.valueOf(lineArr[0]);//size of sequence
				arr = new int[num];
				num = Integer.valueOf(lineArr[1]);//last element
				int last = num;
				arr[arr.length-1] = num;
				for (int i = 2; i < lineArr.length; i++) {//fill array
					num = Integer.valueOf(lineArr[i]);
					arr[i-2] = num;
				}
				for (int i = 0; i < arr.length; i++) {
					System.out.println(arr[i]);
				}
				int result = strikeEdge(last,arr);//first call to recursive method
				System.out.println(result);
				if(result == -1)
					pw.println(0);
				if(result == 0)
					pw.println(1);
				System.out.println();
			}
		}catch(FileNotFoundException e) {
				System.out.println("file not found");
		}finally {
			if(pw!=null)
				pw.close();
			kb.close();
		}
	}
	public static int strikeEdge(int i,int[] arr) {
		if(i == arr.length-1)//base case
			return arr[arr.length-1];
		if(((i-arr[i])<0)&&((i+arr[i]+arr[i+arr[i]])>arr.length-1)) {//check for special case that we are stuck in a loop
			if(arr[i] == arr[i+arr[i]]) {
				System.out.println(arr[i]+" "+arr[i+arr[i]]);
				System.out.println("special bound");
				return -1;
			}
		}
		if((i+arr[i])>arr.length-1) {//check if we are stuck on the right
			if((i-arr[i])<0) {
				System.out.println("first bound");
				return -1;
			}
		}
		if((i-arr[i])<0) {//check if we are stuck on the left
			if((i+arr[i])>arr.length-1) {
				System.out.println("second bound");
				return -1;
			}
		}
		if((i+arr[i]) <= arr.length-1) {//check if we can advance then advance
			System.out.println(i + ": increment");
			return strikeEdge(i+arr[i],arr);
		}
		if((i-arr[i]) >= 0){//if we cant advance then check if we cant go back then go back
			System.out.println(i + ": decrement");
			return strikeEdge(i-arr[i],arr);
		}
		System.out.println("third bound");
		return -1;//otherwise sequence empty
		
	}
}