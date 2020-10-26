package comxx.controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import comxx.search.SearchService;

public class t {

	public static void main(String[] args) {
	 	
	  	  InputStream in = SearchService.class.getResourceAsStream("/cfg.ini"); 
	  			//ä¹Ÿå�¯ä»¥è¯»å�–æŒ‡å®šç›®å½•ä¸‹çš„æŒ‡å®šæ–‡ä»¶
	  			//InputStream in = new FileInputStream(new File("D:/config/config.ini"));
	  	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	  		Properties props = new Properties();  
	          try {
	  			props.load(br);
	  		} catch (IOException e) {
	  			throw new RuntimeException(e);
	  		}
	         
	          
	          Date date = new Date();
	          SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd_hhmmss");
	          String dt = dateFormat.format(date);
			System.out.println(dt);
	            
	            
	    	String saveDir= props.getProperty("uploaddir");
	    	System.out.println(saveDir);

	}

}
