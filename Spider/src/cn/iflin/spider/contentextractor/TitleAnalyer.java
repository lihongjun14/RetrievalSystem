package cn.iflin.spider.contentextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TitleAnalyer {
	public static void main(String[] args) {
		try {
			System.out.println(projectJudgment("教育部社科司关于教育部哲学社会科学研究重大课题攻关项目2017年度中期检查工作的通知  "));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 对文章标题判断是否为项目申报信息
	 * @return
	 * @throws IOException
	 */
	public static boolean projectJudgment(String text) throws IOException {
        ArrayList<String> wordList = new ArrayList<String>();
        //创建分词对象  
        Analyzer anal=new IKAnalyzer(true);
        if(text==null){
        	return false;
        }
        StringReader reader=new StringReader(text);  
        //分词  
        TokenStream ts=anal.tokenStream("", reader);  
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
        //遍历分词数据  
        while(ts.incrementToken()){  
        	wordList.add(term.toString());
        }  
        reader.close();  
        int count =0;
        for(int i =0;i<wordList.size();i++){
//        	System.out.println(wordList.get(i));
        	if(NoProjectWordJudgment(wordList.get(i)))
				return false;
        	if(projectWordJudgment(wordList.get(i))){
        		count++;
        	}
        }
        
        if(count>=2){
        	return true;
        }
        return false;
	}
	
	private static boolean projectWordJudgment(String word){
		String stopWordsList[] ={"申报","通知","重大","项目","指南","通告","招标"};//常用项目判定词
		for(int i=0;i<stopWordsList.length;++i)
        {
			
            if(word.equalsIgnoreCase(stopWordsList[i]))
                return true;
            
        }
		return false;
	}
	
	private static boolean NoProjectWordJudgment(String word){
		String stopWordsList[] ={"公示","名单","清除","清理","结项","情况","终止","少先队",
				"暑期","防范","造假","2016","2016年","检查","2015","2015年","结","结果","推荐",
				"中期","检查工作","调查","调查工作"};//常用项目判定词
		for(int i=0;i<stopWordsList.length;++i)
        {
			
            if(word.equalsIgnoreCase(stopWordsList[i]))
                return true;
            
        }
		return false;
	}
}
