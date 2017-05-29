import java.io.BufferedReader;
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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class DetailCommonExplorer {

		
		public static Map<Integer,ArrayList<boardListDto>> DetailCommon(ArrayList<boardListDto> dataList)  {
		ArrayList<boardListDto> datailCommonlist=null;
		Map<Integer,ArrayList<boardListDto>> map = new HashMap<Integer,ArrayList<boardListDto>>();
		  try {
			  
			  for(int i=0; i< dataList.size(); i++ ){
				  String apiXml= detailCommonApi(dataList.get(i).getContenttypeid(),dataList.get(i).getContentid());
//				  ArrayList<boardListDto> datailIntrolist=DetailIntroXML(apiXml);
				  datailCommonlist=DetailCommonXML(apiXml);
				 
				 map.put(i, datailCommonlist);
				/* for(int j=0; j<datailIntrolist.size(); j++){
					 System.out.println("::::"+datailIntrolist.get(j).getEventplace());
				 }*/
			  }
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return map;
    }
	
	public static String detailCommonApi(String contentTypeId,String contentId) throws IOException{
		
		    
			String serviceKey=DataUtil.getProperty("serviceKey");// serviceKey 인증키
		
	        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon");
	        urlBuilder.append("?" +URLEncoder.encode("ServiceKey","UTF-8")+serviceKey); /*Service Key*/
	        urlBuilder.append("&MobileOS=ETC&MobileApp=web");
	        urlBuilder.append("&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y");
	        urlBuilder.append("&contentTypeId="+contentTypeId+"&contentId="+contentId+""); 
	        
//	        System.out.println("::url::"+urlBuilder.toString());
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
	
	private static ArrayList<boardListDto>  DetailCommonXML(String apiXml)  {
	    
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
                        if(startTag.equals("contentid")) {
                        	openData.setContentid(parser.nextText());
                        }
                        if(startTag.equals("contenttypeid")) {
                        	openData.setContenttypeid(parser.nextText());
                        }
                        if(startTag.equals("addr1")) {
                        	openData.setAddr1(parser.nextText());
                        }
                        if(startTag.equals("addr2")) {
                        	openData.setAddr2(parser.nextText());
                        }
                        if(startTag.equals("firstimage")) {
                        	openData.setFirstimage(parser.nextText());
                        }
                        if(startTag.equals("firstimage2")) {
                        	openData.setFirstimage2(parser.nextText());
                        }
                        if(startTag.equals("overview")) {
                        	openData.setOverview(parser.nextText());
                        }
                        if(startTag.equals("homepage")) {
                        	openData.setHomepage(parser.nextText());
                        }
                        if(startTag.equals("title")) {
                        	openData.setTitle(parser.nextText());
                        }
                        if(startTag.equals("zipcode")) {
                        	openData.setZipcode(parser.nextText());
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
