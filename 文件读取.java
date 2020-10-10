package 尝试;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class 文件读取 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=null;

		br=new BufferedReader(new FileReader("C:\\\\Users\\\\ASUS\\\\Desktop\\\\新建文件夹\\1.txt"));

		String contentLine=br.readLine();
		while(contentLine!=null) {
			System.out.println(contentLine);
			contentLine=br.readLine();
		}
	}
}
