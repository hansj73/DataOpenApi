


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class searchFestivalExplorer {
	
	private static final Logger logger = Logger.getLogger(searchFestivalExplorer.class);
	
    public static void searchFestivalList(String total){  
		
    	   boolean inTrue=false;
    	   int inTrueCnt=0;
		
		  try {
			String apiXml= areaBasedListApi(total);
			
			ArrayList<boardListDto> list=areaBasedListXML(apiXml);
			
			    
			Map<Integer,ArrayList<boardListDto>>detailIntro = DetailIntroExplorer.DetailIntro(list);
			Map<Integer,ArrayList<boardListDto>>detailCommon = DetailCommonExplorer.DetailCommon(list);
		
			
			
//			  System.out.println("::::detailIntro::"+map3.size());
			  for(int key:detailCommon.keySet()){
//				  System.out.println("key"+key+"detail"+detailIntro.get(key));
			/*	  for(int j=0; j<detailIntro.get(key).size(); j++){
					  System.out.println(":::장소:::"+detailIntro.get(key).get(j).getEventplace());
					  System.out.println(":::주소:::"+detailCommon.get(key).get(j).getAddr1());
				  }*/
				  inTrue=ApiDataInput(detailIntro.get(key),detailCommon.get(key));
				  if(inTrue==true)inTrueCnt=inTrueCnt+1;
			  }
			  
			  logger.debug(":::insert 갯수:::"+inTrueCnt);
		       
				/**
				 * return 
				 * totalCount,pageNo,numOfRows
				 */
		        /** 리스트  총 갯수 , 페이지 1, 페이지 row**/
				/*ArrayList<boardListDto> listData=areaBasedListEtcElement(apiXml);
					
				System.out.println("::listData::"+listData.get(0).getTotalCount());
				System.out.println("::listData::"+listData.get(0).getPageNo());
				System.out.println("::listData::"+listData.get(0).getNumOfRows());*/
					
				
			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	public static String areaBasedListApi(String total) throws IOException{
		 
		    /**
		     * arrange  정렬구분
		     * (A=제목순, B=조회순, C=수정일순, D=생성일순)
		     * contentTypeId 15 (행사/공연/축제)
		     * cat2 : A0207 (축제) / A0208 (공연/행사)
		     * cat1 : A02 인문(문화/예술/역사)
		     */
		
		 	String serviceKey=DataUtil.getProperty("serviceKey");// serviceKey 인증키
		 	String contentTypeId=DataUtil.getProperty("contentTypeId");// 타입
		 	String cat1=DataUtil.getProperty("cat1");// 대분류
		 	String cat2=DataUtil.getProperty("cat2");// 중분류
		 	String arrange=DataUtil.getProperty("arrange");// 정렬구분
//		 	String numOfRows=DataUtil.getProperty("numOfRows");// 페이지갯수
		 	String numOfRows=total;// 페이지갯수
		
		 	String[] SEdate = DataUtil.SEdate().split(":");
		 	
			StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"); 
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") +serviceKey); /*Service Key*/
	        urlBuilder.append("&eventStartDate="+SEdate[0]+"&eventEndDate="+SEdate[1]+"");
	        urlBuilder.append("&areaCode=&sigunguCode=&listYN=Y&MobileOS=ETC&MobileApp=web");
	        urlBuilder.append("&arrange="+arrange+"&numOfRows="+numOfRows+"&pageNo=1");
	        urlBuilder.append("&cat1="+cat1+"&cat2="+cat2+""); // 축제 cat1=A02 축제 중분류 cat2=A0207 축제
	        /*System.out.println("::url::"+urlBuilder.toString());*/
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
	
	private static ArrayList<boardListDto>  areaBasedListXML(String apiXml)  {
	    
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
	
	public static  ArrayList<boardListDto> areaBasedListEtcElement (String apiXml)
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
    }
	
	public static boolean ApiDataInput(ArrayList<boardListDto>detailIntro ,ArrayList<boardListDto>detailCommon ) throws IOException{
		
		boardListDto bDto = new boardListDto();
		boolean inTrue=false;
		
		
		for(int i=0; i<detailIntro.size(); i++){
			/*System.out.println("::::size::"+detailIntro.get(i).getEventplace()+"::::"+detailCommon.get(i).getAddr1());*/
			bDto.setEventplace(detailIntro.get(i).getEventplace());
			bDto.setSponser1(detailIntro.get(i).getSponser1());
			bDto.setSponsor1tel(detailIntro.get(i).getSponsor1tel());
			bDto.setEventstartdate(detailIntro.get(i).getEventstartdate());
			bDto.setEventenddate(detailIntro.get(i).getEventenddate());
			
			bDto.setAddr1(detailCommon.get(i).getAddr1());
			bDto.setAddr2(detailCommon.get(i).getAddr2());
			bDto.setFirstimage(detailCommon.get(i).getFirstimage());
			bDto.setFirstimage2(detailCommon.get(i).getFirstimage2());
			bDto.setOverview(detailCommon.get(i).getOverview());
			bDto.setHomepage(detailCommon.get(i).getHomepage());
			bDto.setZipcode(detailCommon.get(i).getZipcode());
			bDto.setContentid(detailCommon.get(i).getContentid());
			bDto.setContenttypeid(detailCommon.get(i).getContenttypeid());
			bDto.setTitle(detailCommon.get(i).getTitle());
			
			inTrue=DataApiDB.regApiData(bDto);
			
		}
		return inTrue;
//		 logger.debug(":::insert 갯수:::"+inTrueCnt);
	}
	
		
}// class end
