package cn.iflin.spider.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 自动爬虫程序
 * @author Jaypan
 *
 */
public class SpiderTask {
	static SpiderView view = new SpiderView();
	private static Timer timer;
	private static TimerTask task;
	public static void startTask(){
		timer = new Timer();
		task = new TimerTask(){
			public void run(){
				SpiderMain spider = new SpiderMain();
				try {
					spider.startSpider();
					view.viewTitleDate("下一次将于"+endDate()+"开始\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		long delay = 0;
		long intevalPeriod = 12*60*60*1000;
		view.viewTitleDate("爬虫开始\n");
		timer.schedule(task, delay, intevalPeriod);
	}
	public static void stopTask(){
		view.viewTitleDate("爬虫停止，如果停止不了请立即关闭程序\n");
		timer.cancel();	//直接将timer关掉
	}
	
	private static String endDate(){
		String endDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 12);
		endDate = sdf.format(calendar.getTime()).toString();
		return endDate;
	}
}
