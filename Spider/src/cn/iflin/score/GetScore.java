package cn.iflin.score;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
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

public class GetScore {
	public static  Double  getResult(String querystr)throws IOException, ParseException{
		Query q = null;
		Analyzer analyzerIKA = new IKAnalyzer();
		Directory fileindex = FSDirectory.open(new File("C:\\ScoreIndex"));
		q = new QueryParser(Version.LUCENE_40, "Context", analyzerIKA).parse(querystr);
		int hitsPerPage = 1;
		IndexReader reader = DirectoryReader.open(fileindex);
		IndexSearcher searcher = new IndexSearcher(reader);
		Sort sort = new Sort(new SortField("Context", SortField.Type.STRING, true));
		TopDocs topdocs = searcher.search(q, hitsPerPage);	 
		searcher.search(q, hitsPerPage, sort);
		ScoreDoc[] hits = topdocs.scoreDocs;
		// 4.打印结果
		double score=0;
		for (int i = 0; i < hits.length; ++i) {
			score = hits[i].score;
		}
		reader.close();
		return score; 
	}
	
	
//	public static void main(String[] args) throws IOException, ParseException {
//		String info = "微博环境下高校网络舆情的监测与引导研究——以政治敏感信息的监测与引导为例基金：广东省高等学校思想政治理论课2012年度一般自筹课题“微博环境下高校网络舆情的监测与引导研究”(课题批准号:2012CY053)；2012年度教育部人文社会科学研究一般项目“基于维度本体的数字图书馆情境敏感知识管理研究”(项目编号:12YJC870012)研究成果；"
//                  +"关键词：微博;  高校网络舆情; 政治敏感信息; 监测; 引导;LanguageTool中文语法校对XML规则定制方法基金：教育部人文社会科学研究青年基金项目“基于维度本体的数字图书馆情境敏感知识管理研究”(项目编号:12YJC870012)；"
//                  +"国家社会科学基金青年项目“数字图书馆动态知识管理研究”(项目编号:12CTQ004)研究成果之一；"
//                +" 关键词：中文语法;"
//                  +"校对;"
//                  +"            XML;"
//                 +" 基于本体的医疗卫生政策法律知识管理系统基金：广东省自然科学基金项目(2016A030313386)；"
//                 +" 文化部科技创新项目(201505)；"
//                  +"关键词：卫生政策法律;"
//                  +"知识管理;"
//                  +"本体;"
//                  +"基于Cytoscape的生物医学本体参数化SPARQL查询系统基金：广东省高等学校优秀青年教师培养计划项目(YQ2015239)；"
//                  +"广东省自然科学基金项目(2016A030313386)；"
//                  +"关键词：SPARQL;"
//                  +"参数化查询;"
//                  +"Cytoscape;"
//		+"本体;"
//                  +"生物医学;"
//                 +" 高校区域大学生微博身份的精确识别方法基金：广东省省级学校德育创新项目(2015DYZD015)；"
//                  +"广东省科技计划(2014A080804001)；"
//                  +"关键词：网络舆情;"
//                  +"微博帐号;"
//                +"  身份识别;"
//                  +"模式匹配;"
//                  +"学生微博;"
//                  +"导师制下的个性化专业素质及能力培养模式研究与实践基金：广东教育教学成果奖(高等教育)培育项目《导师引导下的专业素质养成模式探索与实践》；"
//                  +" 广东省高等教育教学研究和改革项目《导师工作坊下的专业素质自主养成模式研究》资助；"
//                  +" 关键词：导师制;"
//                +"  本科教育;"
//                  +"个性化培养;"
//                  +" 自主学习;"
//                  +"导师工作坊;"
//                  +"   基于描述逻辑本体推理的语义级中文校对方法基金：国家社会科学基金青年项目(14CTQ041)；"
//                  +" 关键词：中文校对;"
//                  +"语义校对;"
//                  +"本体推理;"
//                  +"描述逻辑;"
//                  +" 推理机;"
//                  +"  基于大数据分析的民办高校教学成本问题诊断与优化基金：广东省自然科学基金项目“基于本体推理演化的财经大数据分析与预测研究”(项目编号:2016A030313386)；"
//                  +"  全国统计科学研究项目(重点项目)“基于大数据舆情本体推理的经济趋势预测研究”(项目编号:2015LZ43)；"
//                  +"广东省高等学校优秀青年教师培养计划项目“面向大数据的生物通路本体知识图谱可视化研究”(YYQ2015239)资助；"
//                  +" 关键词：民办高校;"
//                  +" 大数据;"
//                  +"  教学成本;"
//                  +"我国碎片化劳动监察模式的困境、挑战与发展基金：国家社会科学基金项目“基于本体推理演化的语义级中文校对模型研究”；"
//                  +"项目编号:14CTQ041；"
//                  +" 关键词：劳动监察;"
//                  +"碎片化;"
//                  +" 属地管理;"
//                  +"  单中心监管;"
//                  +"多层感知机权值扰动敏感性计算算法研究基金：国家自然科学基金青年基金资助项目(61403205)；"
//                  +"关键词：敏感性;"
//                  +" 多层感知机;"
//                  +"权值;"
//                  +"扰动;"
//                  +" 期望;"
//                  +"导师引导下的自主发展平台教育改革——以微博环境下高校政治敏感信息引导为例关键词：自主发展;   ;"
//                  +" 微博;   ;"
//                +"政治敏感信息;   ;"
//                +"基金：广东省高等学校思想政治理论课2012年度一般自筹课题《微博环境下高校网络舆情的监测与引导研究》(课题批准号:2012CY053)；"
//                +"             2012年度教育部人文社会科学研究一般项目《基于维度本体的数字图书馆情境敏感知识管理研究》(项目编号:12YJC870012)研究成果；";
//		
//		getResult(info);
//	}
}
