import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;



public class DetailIntroExplorer {
	
		
		public static Map<Integer,ArrayList<boardListDto>> DetailIntro(ArrayList<boardListDto> dataList)  {
		ArrayList<boardListDto> datailIntrolist=null;
		Map<Integer,ArrayList<boardListDto>> map = new HashMap<Integer,ArrayList<boardListDto>>();
		
		  try {
			  
			  for(int i=0; i< dataList.size(); i++ ){
				  String apiXml= detailIntroApi(dataList.get(i).getContenttypeid(),dataList.get(i).getContentid());
//				  ArrayList<boardListDto> datailIntrolist=DetailIntroXML(apiXml);
				 datailIntrolist=DetailIntroXML(apiXml);
				 
				 map.put(i, datailIntrolist);
				/* for(int j=0; j<datailIntrolist.size(); j++){
					 System.out.println("::::"+datailIntrolist.get(j).getEventplace());
				 }*/
//				 System.out.println(datailIntrolist);
				 
			  }
			  

				
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return map;
    }
	
	public static String detailIntroApi(String contentTypeId,String contentId) throws IOException{
		
			String serviceKey=DataUtil.getProperty("serviceKey");// serviceKey 인증키
		    
	        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro");
	        urlBuilder.append("?" +URLEncoder.encode("ServiceKey","UTF-8")+serviceKey); /*Service Key*/
	        urlBuilder.append("&MobileOS=ETC&MobileApp=web&introYN=Y");
	        urlBuilder.append("&contentTypeId="+contentTypeId+"&contentId="+contentId+""); 
	        
	        System.out.println("::url::"+urlBuilder.toString());
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/xml");
	        /*System.out.println("Response code: " + conn.getResponseCode());*/
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        //System.out.println(sb.toString());
	        return sb.toString();
		
	}
	
	private static ArrayList<boardListDto>  DetailIntroXML(String apiXml)  {
	    
	    // xmlPullParser
		ArrayList<boardListDto> arrayList = new ArrayList<boardListDto>();
	    try {
	    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	    	XmlPullParser parser = factory.newPullParser();
	    	parser.setInput(new StringReader(apiXml));
	    	
	        int eventType = parser.getEventType();
	        boardListDto openData = null;
	        while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = parser.getName();
                        if(startTag.equals("item")) {
                        	openData = new boardListDto();
                        }
                        if(startTag.equals("sponsor1tel")) {
                        	openData.setSponsor1tel(parser.nextText());
                        }
                        if(startTag.equals("sponser1")) {
                        	openData.setSponser1(parser.nextText());
                        }
                        if(startTag.equals("eventplace")) {
                        	openData.setEventplace(parser.nextText());
                        }
                        if(startTag.equals("eventstartdate")) {
                        	openData.setEventstartdate(parser.nextText());
                        }
                        if(startTag.equals("eventenddate")) {
                        	openData.setEventenddate(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("item")) {
                            arrayList.add(openData);
                        }
                        break;
                }
                eventType = parser.next();
            }// while end
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return arrayList;
	}
	
/*	
	public static  ArrayList<boardListDto> processEtcElement (String apiXml)
    {
		ArrayList<boardListDto> arrayList = new ArrayList<boardListDto>();
		try
		{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	XmlPullParser parser = factory.newPullParser();
    	parser.setInput(new StringReader(apiXml));
    	
		int eventType = parser.getEventType();
		boardListDto openData = null;
		 while(eventType != XmlPullParser.END_DOCUMENT) {
             switch (eventType) {
                 case XmlPullParser.START_TAG:
                     String startTag = parser.getName();
                     if(startTag.equals("response")) {
                     	openData = new boardListDto();
                     }
                     if(startTag.equals("numOfRows")) {
                     	openData.setNumOfRows(parser.nextText());
                     }
                     if(startTag.equals("pageNo")) {
                     	openData.setPageNo(parser.nextText());
                     }
                     if(startTag.equals("totalCount")) {
                     	openData.setTotalCount(parser.nextText());
                     }
                     break;
                 	case XmlPullParser.END_TAG:
                     String endTag = parser.getName();
                     if(endTag.equals("response")) {
                         arrayList.add(openData);
                     }
                     break;
             }
             eventType = parser.next();
         }// while end
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return arrayList;
    }*/
	
	
}// class end
