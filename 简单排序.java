package ����;

import java.util.Scanner;

class MyNumber
{
	int num;
}

public class ������ 
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);

		int a = 0;
		int b = 0;
		int c = 0;
		
		//����hasNextXXX()�ж��Ƿ�����һ������
		
		System.out.print("�������һ�����֣�");
        a = sc.nextInt();
        System.out.print("������ڶ������֣�");
        b = sc.nextInt();
        System.out.print("��������������֣�");
        c = sc.nextInt();

        if(a==0 || b==0 || c==0)
        {
        	System.out.println("���벻��Ϊ0");
        	System.exit(-1);
        }
        
        MyNumber obj1=new MyNumber();
        MyNumber obj2=new MyNumber();
        MyNumber obj3=new MyNumber();
        
        obj1.num=a;
        obj2.num=b;
        obj3.num=c;
        
        swap(obj1,obj2);
        swap(obj1,obj3);
        swap(obj2,obj3);
        
        System.out.println(obj1.num);
        System.out.println(obj2.num);
        System.out.println(obj3.num);
	}
	public static void swap(MyNumber m, MyNumber n)
	{
		if(m.num > n.num)

		{
			int s = m.num;
			m.num = n.num;
			n.num = s;
		}
	}
}

