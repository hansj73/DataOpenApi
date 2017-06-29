import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;




public class Hellow {

	//private static final Logger logger = LoggerFactory.getLogger(Hellow.class);
	
	private static final Logger logger = Logger.getLogger(Hellow.class);

	
	public static void main(String[] args) {
		
		 /*String filename = System.getProperty("G:/study/workspace/DataOpenApilog4j.xml");
		 
		 ClassLoader cl=ClassLoader.getSystemClassLoader();
         URL url = cl.getResource( "log4j.xml" );
         
		 System.out.println("::filename::"+url.getPath());*/
		 //File file = new File(filename);
//		 PropertyConfigurator.configure(url.getFile());
//		 DOMConfigurator.configure(url.getFile());
		// TODO Auto-generated method stub

		Map<Integer,String> m1=new HashMap<Integer,String>();
		Map<Integer,String> m2=new HashMap<Integer,String>();
		Map<Integer,String> m3=new HashMap<Integer,String>();
		m1.put(1,"one");
		m1.put(2,"two");
		m2.put(3,"three");
		m2.put(2,"two");
		m3.putAll(m1);
		/*Set<Integer> s=m2.keySet();
		for(int i:s){
		    if(m1.get(i)==null){
		        m1.put(i,m2.get(i));
		    }
		}
		System.out.println(m1);*/
		/* m3=ImmutableMap.<Integer,String>builder()
			      .putAll(m1)
			      .putAll(m2)
			      .build();
		 
		 System.out.println(m3);*/
	/*		
		for(Integer key : m2.keySet()) {
		    if(m3.containsKey(key)) {
		       System.out.println("::: m3.get(key)::"+ m3.get(key)+":::"+m2.get(key));
		       m3.put(key, m2.get(key));
		       
		    } else {
		        m3.put(key,m2.get(key));
		        System.out.println("::key::"+key+":::"+m2.get(key));
		    }
		}
		for(Integer key : m3.keySet()){
			System.out.println(m3.get(key));
		}
		*/
	/*	boardListDto bDto = new boardListDto();
		try {
			Class c = Class.forName("boardListDto");
			Method m[] = c.getDeclaredMethods();
//			Field  m[] = c.getDeclaredFields();
			for (int i = 0; i < m.length; i++){
				System.out.println(m[i].getName());
			}
			
		} catch (Throwable e) {
			System.err.println(e);
		}
*/
		
	
      	
      	// 디렉토리
      	
      	// /년/달/contentId/cat2/파일명
      	
      /*	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yy", Locale.KOREA );
      	Date currentTime = new Date ();
      	String mTime = mSimpleDateFormat.format ( currentTime );
      	System.out.println ( mTime );

      Calendar cal = Calendar.getInstance();
      	 
      //현재 년도, 월, 일
      int year = cal.get ( cal.YEAR );
      int month = cal.get ( cal.MONTH ) + 1 ;
      int date = cal.get ( cal.DATE ) ;
      	
      int startDay = cal.get(cal.DAY_OF_MONTH);
      int endDay = cal.getActualMaximum(cal.DAY_OF_MONTH); 
      
      
      String smonth=(month<10)?"0"+month:month+"";
      String fromYMD = year+""+smonth+""+"01";
      String toYMD = year+""+smonth+""+endDay;
      
      System.out.println("fromYMD::"+fromYMD+":::month::"+toYMD);
      
      
      Date date1 = new Date();
      
      System.out.println(date1);*/
      
   /*   
      SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
      Calendar c1 = Calendar.getInstance();
	 String strToday = sdf.format(c1.getTime());

	 String year=strToday.substring(0,2);
	  String month=strToday.substring(2,4);

	  String imageSubDir="/"+year+"/"+month+"/"+DataUtil.getProperty("cat2");
	 
	 String ImageSaveDir=DataUtil.getProperty("ImageSaveDir")+imageSubDir; // 썸네일이미지
	 
	 System.out.println("::::ImageSaveDir::"+ImageSaveDir);
     //파일 객체 생성
     File file = new File(ImageSaveDir);
     //!표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
     if(!file.exists()){
         //디렉토리 생성 메서드
         file.mkdirs();
         System.out.println("created directory successfully!");
     }
     
     
 	String firstImageUrlThum="http://tong.visitkorea.or.kr/cms/resource/42/689242_image3_1.jpg";
 	
 	String[] ff=firstImageUrlThum.replaceAll("http://tong.visitkorea.or.kr/cms/resource/", "").split("/");
 	System.out.println("::::fff:::::"+ff[0]+":::"+ff[1]);
 	
 	String[] fff = ff.split("/");
 	
 	System.out.println(":::fff:::"+fff[0]+"::::"+fff[1]);
 			
 	int aa=firstImageUrlThum.lastIndexOf('/');
 	System.out.println("::::"+firstImageUrlThum.length());
 	
 	System.out.println("::::"+firstImageUrlThum.substring(firstImageUrlThum.lastIndexOf('/')+1,firstImageUrlThum.length()));
 	String lastFileName=firstImageUrlThum.substring(firstImageUrlThum.lastIndexOf('/')+1,firstImageUrlThum.length());
 	
	String contentTypeId="15";
	String firstImagerUrl2=DataUtil.getProperty("firstImageUrl2"); // 썸네일이미지
  	String cultureUrl=DataUtil.getProperty("cultureSaveUrl")+imageSubDir;
  	
  	
//  	String ImageSaveDir=DataUtil.getProperty("ImageSaveDir"); // 썸네일이미지
  	
  	firstImageUrlThum=firstImageUrlThum.replaceAll(firstImagerUrl2+"/"+ff[0], cultureUrl);
  	
  	System.out.println(":::::::firstImagerUrl2:::"+firstImagerUrl2);
  	System.out.println(":::::::cultureUrl:::"+cultureUrl);
  	System.out.println(":::::::firstImageUrlThum:::"+firstImageUrlThum);
*/  	
  	
//      logger.debug("::::date1:::"+date1);
  
	}
	
	

}
