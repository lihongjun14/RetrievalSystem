package cn.iflin.spider.contentextractor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
/**
 * 获取文中附件的地址
 */
public class CrawlFile {
    /**1.
     * urlStr 文章链接
     * fileSource 文件源地址
     * filePath 文件存储路径
     * fileUrl 文件下载地址
     * fileName 文件名
     */
    public static void downLoadFromUrl(Element contentElement,String urlStr) throws IOException{
    	String fileSource = CrawlFile.getFileSourse(urlStr);
    	System.out.println("fileSource:"+fileSource);
        HashMap<String,String> fileUrlList = CrawlFile.getFileUrl(contentElement, urlStr,fileSource);
        String filePath = fileSource.replaceAll("http://", "");
        String fileName,fileUrl;
        Iterator<Entry<String, String>> iter = fileUrlList.entrySet().iterator();
    	while(iter.hasNext()){
    		Map.Entry entry = (HashMap.Entry) iter.next();
    		fileName = entry.getKey().toString();
    		fileUrl = entry.getValue().toString();
    		System.out.printf("filePath:"+filePath+"\tfileName:"+fileName+"\tfileUrl:"+fileUrl+"\n");
    		downLoadFromUrl(fileUrl,fileName,filePath);
    	}
    }
    /**2.
     * 获取附件的源地址
     */
      private static String getFileSourse(String htmlUrl){
      	String newUrl="";
      	String urlRegex = "[a-zA-z]+://([^']+?)/";
      	Pattern urlPattern = Pattern.compile(urlRegex);
      	Matcher urlMatcher = urlPattern.matcher(htmlUrl);
      	if(urlMatcher.find()){
      		newUrl = urlMatcher.group();
          }
      	return newUrl;
      }
	/**3.
	 * 获取到附件下载地址，
	 * fileName 附件名称
	 * 
	 */
    private static  HashMap<String, String>  getFileUrl(Element contentElement,String htmlUrl,String newUrl){  
    	HashMap<String,String> fileUrlList = new HashMap<String,String>();
    	String fileUrlRegex = "(\"[^\\s]*.xls*\")";
    	String fileNameRegex = "[^\\/s]*.xls*";
    	String fileName = "";
    	Pattern filePattern = Pattern.compile(fileUrlRegex);
    	Pattern fileNamePattern = Pattern.compile(fileNameRegex);
        Matcher m = filePattern.matcher(contentElement.select("a").toString());
        fileName = contentElement.select("a").text();
        while(m.find()){
        	String fileUrl= m.group().replaceAll("\"", "").replaceFirst("/", newUrl);
//        	Matcher nameMatcher = fileNamePattern.matcher(fileUrl);
//        	while(nameMatcher.find()){
//        		fileName = nameMatcher.group().toString();
//        	}
        	fileUrlList.put(fileName, fileUrl);
        }  
        return fileUrlList;
    }
    
    /** 4.
     * 从网络Url中下载文件 
     * @throws IOException 
     */  
    private static void  downLoadFromUrl(String urlStr,String fileName,String source) throws IOException{  
    	String savePath = "d:\\country\\"+source;
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
        //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
        System.out.println("info:"+url+" download success");   
  
    }  


    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    private static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
    
    
    
//    public static void main(String[] args) {  
//        try{  
//            downLoadFromUrl("https://www.sinoss.net/uploadfile/2017/0419/20170419090908792.doc");  
//        }catch (Exception e) {  
//            // TODO: handle exception  
//        }  
//    }
}
