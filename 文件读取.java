package ����;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class �ļ���ȡ {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=null;

		br=new BufferedReader(new FileReader("C:\\\\Users\\\\ASUS\\\\Desktop\\\\�½��ļ���\\1.txt"));

		String contentLine=br.readLine();
		while(contentLine!=null) {
			System.out.println(contentLine);
			contentLine=br.readLine();
		}
	}
}
