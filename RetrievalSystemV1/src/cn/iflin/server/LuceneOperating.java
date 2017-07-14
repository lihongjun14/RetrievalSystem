package cn.iflin.server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.iflin.model.ArticleModel;
import cn.iflin.model.MysqlConnection;
/**
 * 函数功能
 * 1.对新的一篇文章添加索引
 * 2.更新某一篇文章索引，并更新数据库同一条数据
 * 3.为数据库所有内容添加索引
 * 4.查询文章
 * @author Jaypan
 *
 */
public class LuceneOperating {
	/**
	 * 对新的一篇文章添加索引
	 * 文章从数据库中导入
	 * @throws IOException
	 */
	private  void addIndex() throws IOException {
		Analyzer analyzerIKA = new IKAnalyzer();
		IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_40, analyzerIKA);
		Directory fileindex;
		fileindex = FSDirectory.open(new File("D:\\LuceneIndexTest"));
		IndexWriter filew=new IndexWriter(fileindex, configfile);
		String text = "秋夜带给我的凄凉是任何喧闹都无法覆盖的。餐厅里生意很好，老板、服务员们和我都很熟悉，就像我对秋天一样的熟悉与敏感。突然我觉得这家餐厅就像我的家，它像家人一样在为我准备着晚餐，那个嘎小子服务员总是对着我笑。顺着这个突如其来的怪想法，我跳了出来：我坐在旁边的椅子上看着那个正在端着酒杯流泪的男人，不知道该和他说些什么，我能理解他的悲伤，却无法为他清唱一支歌。我为自己起了个新名字，那是从母亲的名字延续而来的。“米尔”！这名字把我和母亲绑在一起。然而我又很讨厌这名字，它提醒着我，催促我走出那片时光乐土。我为这个餐厅也起了新名字“吉母餐厅”，因为在这里吃饭是要付钱的，所以“吉母”变成“吉姆”。";
		String id = "1";
//		addDoc(filew, id , text);
		filew.close();
	}

	/**
	 * 用来更新某一篇文章索引
	 * 更新完成之后根据文章编号在数据库中更新内容
	 * @throws IOException
	 */
	private static void upadeIndex() throws IOException{
		Analyzer analyzerIKA = new IKAnalyzer();
		IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_40, analyzerIKA);
		Directory fileindex;
		fileindex = FSDirectory.open(new File("D:\\LuceneIndexTest"));
		IndexWriter filew=new IndexWriter(fileindex, configfile);
		Document docNew = new Document();
        FieldType articleIdType = new FieldType();
        articleIdType.setIndexed(true);
        articleIdType.setStored(true);
        articleIdType.setTokenized(false);	//不分词
        docNew.add(new Field("ArticleId", "1",articleIdType));
        docNew.add(new TextField("Context", "更新成功",Field.Store.YES));
        Term term = new Term("ArticleId","1");
        filew.updateDocument(term, docNew);
        filew.close();
	}
	
	
	/**
	 * 3将数据库中所有内容添加索引
	 */
	private static void addAllIndex() throws IOException {
		Connection conn = MysqlConnection.getConnection();
		// 1.建立索引
		// 实例化对象来连接数据库，同时用sql语句调用数据库中指定的数据表
		String sql = "SELECT * FROM context";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Analyzer analyzerIKA = new IKAnalyzer();
		IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_40, analyzerIKA);
		Directory fileindex;
		fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
		IndexWriter filew=new IndexWriter(fileindex, configfile);
		System.out.println(fileindex);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("Title"));
				addDoc(filew,  rs.getString("AticleId") + "", rs.getString("Title") + "", rs.getString("Time") + "",rs.getString("Context")+"",rs.getString("Url")+"",rs.getString("Source"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 统一释放内存
			try {				
				filew.close();
				rs.close();
				pstmt.close();
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		addAllIndex();
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
	
	/**
	 * 4.查询文章
	 * @param queryRange
	 * @param querystr
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ArticleModel> getResult(String queryRange,String querystr,int hitsPerPage)throws IOException, ParseException{
		ArrayList<ArticleModel> aticles;
		Query q = null;
		Analyzer analyzerIKA = new IKAnalyzer();
		Directory fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
		System.out.println(fileindex);
		q = new QueryParser(Version.LUCENE_40, queryRange, analyzerIKA).parse(querystr);

		// 3.搜索
		// 创建一个Searcher对象并使用之前创建的Query对象来进行搜索，匹配到的前10个结果封装在TopScoreDocCollector对象里返回。
		IndexReader reader = DirectoryReader.open(fileindex);
		IndexSearcher searcher = new IndexSearcher(reader);
		//把这里的number改为数据库中存储时间的列
		Sort sort = new Sort(new SortField("Time", SortField.Type.STRING, true));
		TopDocs topdocs = searcher.search(q, hitsPerPage);	 
		searcher.search(q, hitsPerPage, sort);
		ScoreDoc[] hits = topdocs.scoreDocs;
//		// 4.打印结果
		aticles = new ArrayList<ArticleModel>();
		String title = null,time = null,context = null,url=null;
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			url = d.get("Url");
			ArticleModel article = new ArticleModel(d.get("Title"),d.get("Time"),d.get("Context"),d.get("ArticleId"),d.get("Source"));
			aticles.add(article);
		}
		// 此时不需要再访问文档，关闭reader
		reader.close();
		return aticles;
	}
}
