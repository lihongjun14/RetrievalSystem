package cn.iflin.spider.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SpiderView extends JFrame {
	    private static JPanel buttonPanel,logPanel; 
	    private static JLabel jl3;
		private static JLabel jl2;
		private static JLabel jl1;
	    private static  JScrollPane scrollPane;
	    private static JTextArea logArea;
	    private static JButton startButton,stopButton;
	    private static JFrame frame ;
	    public static void main(String[] args) {
	    	viewGUI();
		}
	    //1.先运行界面
	    private static void viewGUI(){
	    	frame = new JFrame();
	    	viewButton();
	    	logPanel = new JPanel();
	    	logArea = new JTextArea();
	    	scrollPane = new JScrollPane(logArea);
	    	frame.add(scrollPane, BorderLayout.CENTER);
	    	frame.setTitle("爬虫控制后台");
	    	frame.setSize(500, 500);
	    	frame.setVisible(true);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//点关闭按钮即关闭java程序
	    }
	    //2.传入数据
	    public void viewLogDate(String text){
	    	logArea.append(text);
	    	scrollPane.setViewportView(logArea);
	    }
	    public void viewTitleDate(String text){
	    	logArea.append(text);
	    	logArea.setFont(new Font("宋体",Font.BOLD, 16));
	    	scrollPane.setViewportView(logArea);
	    }
	    //3.开始停止按钮
	    private static void viewButton(){
	    	startButton = new JButton("开始");
	        stopButton = new JButton("停止");
	    	buttonPanel = new JPanel();
	    	jl1 = new JLabel();
	    	jl2 = new JLabel();
	    	jl3 = new JLabel();
	        //开始事件
	        startButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent arg0){
	        		SpiderTask.startTask();;
	        	}
	        });
	        
	        //结束事件
	        stopButton.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent arg0){
	        		SpiderTask.stopTask();
	        	}
	        });
	        buttonPanel.add(startButton);
	    	buttonPanel.add(stopButton);
	    	buttonPanel.add(jl1);
	    	buttonPanel.add(jl2);
	    	buttonPanel.add(jl3);
	    	frame.add(buttonPanel,BorderLayout.NORTH);
	    }
	    
	        
	 

}
