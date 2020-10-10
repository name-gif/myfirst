package 文档相关;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class 文档相关性 
{
	//存储所有的txt文件路径
	static ArrayList<String> filelist= new ArrayList<String>();
	//存储所有的单词（LinkedHashMap存入的是不重复的）
	static LinkedHashMap<String,String> words=new LinkedHashMap<String,String>();
	//存储向量（HashMap存入的是无序的，是哈希算法的结果）
	static HashMap<String,LinkedList<Integer>> doc_vector=new HashMap<String,LinkedList<Integer>>();
	//存储TF
	static HashMap<String,HashMap<String,Double>> doc_TF=new HashMap<String,HashMap<String,Double>>();
	//存储IDF
	static HashMap<String,Double> doc_IDF=new HashMap<String,Double>();
	//存储TF-IDF
	static HashMap<String,HashMap<String,Double>> doc_TF_IDF=new HashMap<String,HashMap<String,Double>>();
	
	public static void GetFile_txt(String SumFilePath)//得到所有的txt文件路径
	{
		//打开文件夹
		File file=new File(SumFilePath);
		
		//获取文件夹列表
		File[]files=file.listFiles();
		
		if(files !=null)//判断文件夹是否为空
		{
			//遍历文件夹，获取到所有txt文件
			for(File s:files) 
			{
				if(s.isDirectory())//判断是否为文件夹
				{
					GetFile_txt(s.getAbsolutePath());//递归
				}
				else 
				{
					if(s.getName().endsWith("txt"))//判断是否为txt文件
					{
						filelist.add(s.getAbsolutePath());
					}
				}
			}
		}
		else 
		{
			System.out.println("该文件夹为空");
		}
	}
	
	public static void GetWords() throws IOException //得到所有的word
	{
		for(String s:filelist)
		{
			@SuppressWarnings("resource")
			BufferedReader bf=new BufferedReader(new FileReader(new File(s)));
			String line;
			
			//一行一行的读
			while((line=bf.readLine())!=null) 
			{
				String[] strs=line.trim().split(" ");//用标识符分割
				
				//依次存入单词
				for(String ss:strs) 
				{
					words.put(ss," ");
				}
			}
		}
	}
	
	//获得所有单词出现的频率
	public static void GetWordNUm(HashMap<String,String> map,LinkedList<Integer> list,HashMap<String, Integer> words_num) 
	{
		//依次取map里面的值，网上有五种多遍历方法，entry的效率高
		for (Map.Entry<String, String> entrywords : words.entrySet()) 
		{
			//看map中是否包含该单词，有则计数
			String word=entrywords.getKey();
			if(map.containsKey(word))
			{
				list.add(1);
				if(words_num.containsKey(word))
				{
					Integer NewNum=words_num.get(word).intValue();
					words_num.put(word, NewNum+1);
				}
				else 
				{
					words_num.put(word, 1);
				}
			}
			else 
			{
				list.add(0);
			}
		}
	}
	
	//求TF
	public static void GetTF(HashMap<String,String> map,HashMap<String, Integer> words_num,HashMap<String, Double> TF,Integer sumInteger)
	{
		//依次遍历该文档的单词
		for (Map.Entry<String, String> entrymap : map.entrySet())
		{
			String word=entrymap.getKey();//得到单词
			Integer num=words_num.get(word).intValue();//得到单词出现次数
			TF.put(word, num/(double)sumInteger);//TF=出现次数/总单词出现次数
		}
	}
	
	//求IDF
	public static void GetIDF() 
	{
		//遍历所有单词
		for (Map.Entry<String, String> entrywords : words.entrySet()) 
		{
			String word=entrywords.getKey();
			Integer k = 0;//单词出现的文档数
			//遍历TF，主要是为了求得单词出现的文档数
			for (Map.Entry<String, HashMap<String,Double>> entryTF : doc_TF.entrySet()) 
			{
				HashMap<String,Double> wordTF=entryTF.getValue();
				if(wordTF.containsKey(word))
				{
					k++;//出现在几个文档里，至少为1
				}
			}
			//IDF=ln（文档总数/包含该单词的文档数）
			doc_IDF.put(word, Math.log(filelist.size()/(double)k));
		}
	}
	
	public static void GetVetor() throws IOException //获得向量
	{
		//分别获取每个text文件的内容
		for(String s:filelist)
		{
			HashMap<String,String> map=new HashMap<String,String>();
			@SuppressWarnings("resource")
			BufferedReader bf=new BufferedReader(new FileReader(new File(s)));
			String line;
			Integer sumwordsnum = 0;
			
			//一行一行的读
			while((line=bf.readLine())!=null) 
			{
				String[]strs=line.trim().split(" ");//用空格为标识符分割
				
				//依次存入单词
				for(String ss:strs)
				{
					sumwordsnum++;
					map.put(ss," ");
				}
			}
			
			//存储向量
			LinkedList<Integer> list = new LinkedList<Integer>();
			//存储单词和其对应的词频
			HashMap<String, Integer> words_num=new HashMap<String, Integer>();
			HashMap<String, Double> TF = new HashMap<String,Double>();//存储TF
			
			GetWordNUm(map, list, words_num);//求得所有单词出现的频率
			
			//求TF
			GetTF(map,words_num,TF,sumwordsnum);
		
			doc_vector.put(s, list);
			doc_TF.put(s,TF);
			
		}
		
		//求IDF
		GetIDF();
		
		System.out.println(doc_vector);
		System.out.println(doc_TF);
		System.out.println(doc_IDF);

	}
	
	//求TF-IDF
	public static void GetTF_IDF() 
	{
		//遍历TF
		for (Map.Entry<String, HashMap<String,Double>> entryTF : doc_TF.entrySet()) 
		{
			HashMap<String,Double> wordTF=entryTF.getValue();
			HashMap<String,Double> TF_IDF=new HashMap<String,Double>();
			//深度遍历
			for (Map.Entry<String, Double> entrywordTF : wordTF.entrySet())
			{
				String word=entrywordTF.getKey();
				Double TF=entrywordTF.getValue();
				Double IDF=doc_IDF.get(word).doubleValue();
				TF_IDF.put(word, TF*IDF);
			}
			doc_TF_IDF.put(entryTF.getKey(), TF_IDF);//TF-IDF=TF*IDF
		}
		System.out.println(doc_TF_IDF);
	}
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		String FilePath="E:\\待用\\数据挖掘\\新建文件夹";
		GetFile_txt(FilePath);//求所有的文件路径
		GetWords();//求所有的不重复单词
		GetVetor();//求所有的向量，包括是否存在，TF，IDF
		GetTF_IDF();//求TF-IDF
	}

}
