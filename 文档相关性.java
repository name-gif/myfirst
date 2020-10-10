package �ĵ����;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class �ĵ������ 
{
	//�洢���е�txt�ļ�·��
	static ArrayList<String> filelist= new ArrayList<String>();
	//�洢���еĵ��ʣ�LinkedHashMap������ǲ��ظ��ģ�
	static LinkedHashMap<String,String> words=new LinkedHashMap<String,String>();
	//�洢������HashMap�����������ģ��ǹ�ϣ�㷨�Ľ����
	static HashMap<String,LinkedList<Integer>> doc_vector=new HashMap<String,LinkedList<Integer>>();
	//�洢TF
	static HashMap<String,HashMap<String,Double>> doc_TF=new HashMap<String,HashMap<String,Double>>();
	//�洢IDF
	static HashMap<String,Double> doc_IDF=new HashMap<String,Double>();
	//�洢TF-IDF
	static HashMap<String,HashMap<String,Double>> doc_TF_IDF=new HashMap<String,HashMap<String,Double>>();
	
	public static void GetFile_txt(String SumFilePath)//�õ����е�txt�ļ�·��
	{
		//���ļ���
		File file=new File(SumFilePath);
		
		//��ȡ�ļ����б�
		File[]files=file.listFiles();
		
		if(files !=null)//�ж��ļ����Ƿ�Ϊ��
		{
			//�����ļ��У���ȡ������txt�ļ�
			for(File s:files) 
			{
				if(s.isDirectory())//�ж��Ƿ�Ϊ�ļ���
				{
					GetFile_txt(s.getAbsolutePath());//�ݹ�
				}
				else 
				{
					if(s.getName().endsWith("txt"))//�ж��Ƿ�Ϊtxt�ļ�
					{
						filelist.add(s.getAbsolutePath());
					}
				}
			}
		}
		else 
		{
			System.out.println("���ļ���Ϊ��");
		}
	}
	
	public static void GetWords() throws IOException //�õ����е�word
	{
		for(String s:filelist)
		{
			@SuppressWarnings("resource")
			BufferedReader bf=new BufferedReader(new FileReader(new File(s)));
			String line;
			
			//һ��һ�еĶ�
			while((line=bf.readLine())!=null) 
			{
				String[] strs=line.trim().split(" ");//�ñ�ʶ���ָ�
				
				//���δ��뵥��
				for(String ss:strs) 
				{
					words.put(ss," ");
				}
			}
		}
	}
	
	//������е��ʳ��ֵ�Ƶ��
	public static void GetWordNUm(HashMap<String,String> map,LinkedList<Integer> list,HashMap<String, Integer> words_num) 
	{
		//����ȡmap�����ֵ�����������ֶ����������entry��Ч�ʸ�
		for (Map.Entry<String, String> entrywords : words.entrySet()) 
		{
			//��map���Ƿ�����õ��ʣ��������
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
	
	//��TF
	public static void GetTF(HashMap<String,String> map,HashMap<String, Integer> words_num,HashMap<String, Double> TF,Integer sumInteger)
	{
		//���α������ĵ��ĵ���
		for (Map.Entry<String, String> entrymap : map.entrySet())
		{
			String word=entrymap.getKey();//�õ�����
			Integer num=words_num.get(word).intValue();//�õ����ʳ��ִ���
			TF.put(word, num/(double)sumInteger);//TF=���ִ���/�ܵ��ʳ��ִ���
		}
	}
	
	//��IDF
	public static void GetIDF() 
	{
		//�������е���
		for (Map.Entry<String, String> entrywords : words.entrySet()) 
		{
			String word=entrywords.getKey();
			Integer k = 0;//���ʳ��ֵ��ĵ���
			//����TF����Ҫ��Ϊ����õ��ʳ��ֵ��ĵ���
			for (Map.Entry<String, HashMap<String,Double>> entryTF : doc_TF.entrySet()) 
			{
				HashMap<String,Double> wordTF=entryTF.getValue();
				if(wordTF.containsKey(word))
				{
					k++;//�����ڼ����ĵ������Ϊ1
				}
			}
			//IDF=ln���ĵ�����/�����õ��ʵ��ĵ�����
			doc_IDF.put(word, Math.log(filelist.size()/(double)k));
		}
	}
	
	public static void GetVetor() throws IOException //�������
	{
		//�ֱ��ȡÿ��text�ļ�������
		for(String s:filelist)
		{
			HashMap<String,String> map=new HashMap<String,String>();
			@SuppressWarnings("resource")
			BufferedReader bf=new BufferedReader(new FileReader(new File(s)));
			String line;
			Integer sumwordsnum = 0;
			
			//һ��һ�еĶ�
			while((line=bf.readLine())!=null) 
			{
				String[]strs=line.trim().split(" ");//�ÿո�Ϊ��ʶ���ָ�
				
				//���δ��뵥��
				for(String ss:strs)
				{
					sumwordsnum++;
					map.put(ss," ");
				}
			}
			
			//�洢����
			LinkedList<Integer> list = new LinkedList<Integer>();
			//�洢���ʺ����Ӧ�Ĵ�Ƶ
			HashMap<String, Integer> words_num=new HashMap<String, Integer>();
			HashMap<String, Double> TF = new HashMap<String,Double>();//�洢TF
			
			GetWordNUm(map, list, words_num);//������е��ʳ��ֵ�Ƶ��
			
			//��TF
			GetTF(map,words_num,TF,sumwordsnum);
		
			doc_vector.put(s, list);
			doc_TF.put(s,TF);
			
		}
		
		//��IDF
		GetIDF();
		
		System.out.println(doc_vector);
		System.out.println(doc_TF);
		System.out.println(doc_IDF);

	}
	
	//��TF-IDF
	public static void GetTF_IDF() 
	{
		//����TF
		for (Map.Entry<String, HashMap<String,Double>> entryTF : doc_TF.entrySet()) 
		{
			HashMap<String,Double> wordTF=entryTF.getValue();
			HashMap<String,Double> TF_IDF=new HashMap<String,Double>();
			//��ȱ���
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
		String FilePath="E:\\����\\�����ھ�\\�½��ļ���";
		GetFile_txt(FilePath);//�����е��ļ�·��
		GetWords();//�����еĲ��ظ�����
		GetVetor();//�����е������������Ƿ���ڣ�TF��IDF
		GetTF_IDF();//��TF-IDF
	}

}
