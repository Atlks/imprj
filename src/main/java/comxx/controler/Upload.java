package comxx.controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bson.Document;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import comxx.search.SearchService;
import comxx.util.mongodbConn;
@EnableAutoConfiguration
@RestController 
public class Upload {
	
	
    @RequestMapping( value = "/halo")
//	@RequestMapping(value = "/up", method = RequestMethod.POST)
	public String testUploadFile2( ) throws IOException {
    	return "ok";
    }
	
	/**
	 * this file1 as the form field name same...must
     * GET  è¿”å›žç”¨æˆ·åˆ—è¡¨ä¿¡æ�¯
     * */
    @RequestMapping( value = "/upload")
//	@RequestMapping(value = "/up", method = RequestMethod.POST)
	public Object testUploadFile2(@RequestParam MultipartFile file1,String uid,String other) throws IOException {
    	
    	
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
            
            
    	String uploaddir = props.getProperty("uploaddir");
		String saveDir= uploaddir;
		//, @RequestParam String saveDir
		String accurl_pre = props.getProperty("accurl_pre");
		
    	
    	//+dt+file.ge
      //ä¿�å­˜æ–‡ä»¶
		File dest = new File(saveDir + "\\" + file1.getOriginalFilename());
		System.out.println(dest);
    String ext=	FilenameUtils.getExtension(dest.getAbsolutePath());
    String rltpath = file1.getOriginalFilename()+dt+"."+ext;
	String savepath = saveDir + "\\" + rltpath;
	FileUtils.forceMkdir(new File(savepath));
    file1.transferTo(new File(savepath));
   String accurl=accurl_pre+rltpath;
    
    MongoClient mongoClient =mongodbConn. iniMongodb();
	MongoDatabase database = mongoClient.getDatabase("db1");
	MongoCollection<Document> collection = database.getCollection("uploadCollTable");
	
	
	if(other==null)other="";
    Document document2 = new Document("oriname",file1.getOriginalFilename()).  
            append("ext", ext).    append("uid", uid).    append("other", other).
            append("savepath", savepath).append("rltpath", rltpath).append("accurl",accurl ); 
   System.out.println(document2);
    collection.insertOne(document2);
    
    return document2;

	}
}
