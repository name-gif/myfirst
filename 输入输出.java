package 尝试;

import java.util.Scanner;//这句话是eclipse给的警告，用输入的时候最好写上

public class 输入输出 {
	@SuppressWarnings("resource")//这句话是eclipse给的警告，用输入的时候最好写上
	public static void main(String[] args) 
	{
		/*Scanner in = new Scanner(System.in);//定义scanner，等待输入
		System.out.println("请输入你的姓名:");
		String name = in.nextLine();//字符类型的输入方式
		System.out.println(name);
		System.out.println("请输入你的年龄：");
		int age = in.nextInt();//整数类型的输入方式
		System.out.println(age);
		System.out.println("请输入苹果的英文：");
		//String s = in.next();
		String s = in.nextLine();
		System.out.println(s);
		System.out.println("请输入你的身高：");
		double height = in.nextDouble();//小数类型的输入方式
		System.out.println(height);*/

		Scanner newone=new Scanner(System.in);
		System.out.print("请输入规格：");
		int size=newone.nextInt();
		
		int num;
		num=1;
		while(num<=size*size)
		{
			System.out.print(num+" ");
			if(num%size==0)
			{
				System.out.println();
			}
			num++;
		}
	}
}
