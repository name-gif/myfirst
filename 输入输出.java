package ����;

import java.util.Scanner;//��仰��eclipse���ľ��棬�������ʱ�����д��

public class ������� {
	@SuppressWarnings("resource")//��仰��eclipse���ľ��棬�������ʱ�����д��
	public static void main(String[] args) 
	{
		/*Scanner in = new Scanner(System.in);//����scanner���ȴ�����
		System.out.println("�������������:");
		String name = in.nextLine();//�ַ����͵����뷽ʽ
		System.out.println(name);
		System.out.println("������������䣺");
		int age = in.nextInt();//�������͵����뷽ʽ
		System.out.println(age);
		System.out.println("������ƻ����Ӣ�ģ�");
		//String s = in.next();
		String s = in.nextLine();
		System.out.println(s);
		System.out.println("�����������ߣ�");
		double height = in.nextDouble();//С�����͵����뷽ʽ
		System.out.println(height);*/

		Scanner newone=new Scanner(System.in);
		System.out.print("��������");
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
