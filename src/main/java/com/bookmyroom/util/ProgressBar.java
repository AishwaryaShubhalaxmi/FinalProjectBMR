package com.bookmyroom.util;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class ProgressBar extends JFrame {
	
		JProgressBar jb;    
		int i=0,num=0,step; 
		
		public ProgressBar(){
		jb=new JProgressBar(0,100);    
		jb.setBounds(40,40,160,30);         
		jb.setValue(0);    
		jb.setStringPainted(true);    
		add(jb);    
		setSize(250,150);    
		setLayout(null);    
		}    
		public void setStep(int count)
		{
			
			step=100/count;
		}
		public void setProgress(){ 
			num=num+step;
		  jb.setValue(num);        
		} 
		
		public void setVal(){
		  jb.setValue(100);        
		} 

}
