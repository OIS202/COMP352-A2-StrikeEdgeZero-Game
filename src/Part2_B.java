import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
public class Part2_B {

	public static void main(String[] args) {
		Scanner scan = null;
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter your command: ");
		String command = kb.nextLine();
		System.out.println(command);
		String[] commandLiterals = command.split(" ");//split line to get individual elements
		PrintWriter pw = null;
		MyList list;
		try {
			scan = new Scanner(new FileInputStream(commandLiterals[2]));//input file
			pw = new PrintWriter(new FileOutputStream(commandLiterals[3]));//output file
			scan.nextLine();
			String line = "";
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				String[] lineArr = line.split(" ");
				Integer num = Integer.valueOf(lineArr[0]);//size of sequence
				list = new MyList(num);
				int last = Integer.valueOf(lineArr[1]);//last element
				for (int i = 2; i < lineArr.length; i++) {//fill list
					num = Integer.valueOf(lineArr[i]);                      
					list.add(num);
				}
				list.add(last);
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				int j = last;
				int result = 0;
				while(j<list.size()) {
					if(j == list.size()-1) {
						result = 1;
						break;
					}
					if(((j-list.get(j))<0)&&((j+list.get(j)+list.get(j+list.get(j)))>list.size()-1)) {//check for special case that we are stuck in a loop
						if(list.get(j) == list.get(j+list.get(j))) {
							System.out.println(list.get(j)+" "+list.get(j+list.get(j)));
							System.out.println("special bound");
							result = 0;
							break;
						}
					}
					if(j+list.get(j)>list.size()-1) {//check if we are stuck on the right
						if(j-list.get(j)<0) {
							System.out.println("first bound");
							result = 0;
							break;
						}
					}
					if(j-list.get(j)<0) {//check if we are stuck on the left
						if(j+list.get(j)>list.size()-1) {
							System.out.println("second bound");
							result = 0;
							break;
						}
					}
					if((j+list.get(j))<=list.size()-1) {//check if we can advance then advance
						System.out.println(j+" "+list.get(j)+": increment");
						j = j+list.get(j);
						continue;
					}
					if((j-list.get(j)) >= 0) {//if we cant advance then check if we cant go back then go back
						System.out.println(j+" "+list.get(j)+": decrement");
						j = j-list.get(j);
						continue;
					}
					System.out.println("third bound");
					result = 0;//otherwise sequence empty
					break;
				}
				System.out.println("result is: "+result);
				pw.println(result); 
			}
		}catch(FileNotFoundException e) {
				System.out.println("file not found");
		}finally {
			if(pw!=null)
				pw.close();
			kb.close();
		}
	}
		
		
}


