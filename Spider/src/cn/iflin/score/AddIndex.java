package cn.iflin.score;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 此函数用来将数据库中的文章标题等建立索引并存储在文件夹中
 */
public class AddIndex {
	public static class ScoreIndex{
		public static  void addIndex(String content) throws IOException {
			String path = "C:\\ScoreIndex";
			File file =new File(path);  
			 if (file.exists()) {
//				 System.out.println("file exists");
				 delAllFile(path);
			}
			Analyzer analyzerIKA = new IKAnalyzer();
			IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_40, analyzerIKA);
			Directory fileindex;
			fileindex = FSDirectory.open(file);
			IndexWriter filew=new IndexWriter(fileindex, configfile);
//			System.out.println(fileindex);
			try {
				 addDoc(filew,content);
			} finally {
				// 统一释放内存
				filew.close();
			}
		}
		
		private static void addDoc(IndexWriter w,String context) throws IOException {
				Document doc = new Document();
				doc.add(new TextField("Context", context,Field.Store.YES));
				w.addDocument(doc);
			}
		
	    private static boolean delAllFile(String path) {
	        boolean flag = false;
	        File file = new File(path);
	        if (!file.exists()) {
	          return flag;
	        }
	        if (!file.isDirectory()) {
	          return flag;
	        }
	        String[] tempList = file.list();
	        File temp = null;
	        for (int i = 0; i < tempList.length; i++) {
	           if (path.endsWith(File.separator)) {
	              temp = new File(path + tempList[i]);
	           } else {
	               temp = new File(path + File.separator + tempList[i]);
	           }
	           if (temp.isFile()) {
	              temp.delete();
	           }
	           if (temp.isDirectory()) {
	              delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	              flag = true;
	           }
	        }
	        return flag;
	      }
	}
	
	/**
	 * 2.为查询添加索引
	 * @author Jaypan
	 *
	 */
	public static class SystemIndex{
		public static void addAllIndex( String articleId,String title, String time, String context,String url,String sourceName) throws IOException {
			Analyzer analyzerIKA = new IKAnalyzer();
			IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_40, analyzerIKA);
			Directory fileindex;
			fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
			IndexWriter filew=new IndexWriter(fileindex, configfile);
//			System.out.println(fileindex);
			try {
				 addDoc(filew,articleId,title,time,context,url,sourceName);
			} finally {
				// 统一释放内存
				filew.close();
			}
		}
		private static void addDoc(IndexWriter w, String articleId,String title, String time, String context,String url,String sourceName) throws IOException {
			Document doc = new Document();
			FieldType fieldType = new FieldType();  
		    fieldType.setIndexed(false);//set 是否索引  
		    fieldType.setStored(true);//set 是否存储  
		    fieldType.setTokenized(false);//set 是否分类
		    FieldType articleIdType = new FieldType();
		    articleIdType.setIndexed(true);
		    articleIdType.setStored(true);
		    articleIdType.setTokenized(false);	//不分词
		    doc.add(new Field("ArticleId", articleId,articleIdType));
			doc.add(new TextField("Title", title, Field.Store.YES));
			doc.add(new Field("Time",time, fieldType));
			doc.add(new TextField("Context", context,Field.Store.YES));
			doc.add(new TextField("Url", url, Field.Store.YES));
			doc.add(new Field("Source", sourceName,articleIdType));
			w.addDocument(doc);
		}
	}
}
