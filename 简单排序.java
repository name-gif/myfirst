package 尝试;

import java.util.Scanner;

class MyNumber
{
	int num;
}

public class 简单排序 
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);

		int a = 0;
		int b = 0;
		int c = 0;
		
		//利用hasNextXXX()判断是否还有下一输入项
		
		System.out.print("请输入第一个数字：");
        a = sc.nextInt();
        System.out.print("请输入第二个数字：");
        b = sc.nextInt();
        System.out.print("请输入第三个数字：");
        c = sc.nextInt();

        if(a==0 || b==0 || c==0)
        {
        	System.out.println("输入不能为0");
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

