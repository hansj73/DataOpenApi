import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class DataApiDB {
	
	
	static Connection con;
	static Statement stmt;
    static ResultSet rs;
	
	public DataApiDB() {}
	
	
	  public static void  DbDriverLoad()
	    {
	    	
	    	try{
	            
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            	System.out.println("드라이버 로딩 성공");
				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				}
	    }
	  
	  public static void getConnection()
	    {
	    	 
	        try{
	            //커넥션을 가져온다.
	        	 	String driverClass = DataUtil.getProperty("driverClassName") ;
		            String url = DataUtil.getProperty("url") ;
		            String username =DataUtil.getProperty("username") ;
		            String password= DataUtil.getProperty("password") ;
		            
		            // 콘솔 출력
//		            System.out.println(driverClass+":"+url+":"+username+":"+password) ;
		            con = DriverManager.getConnection(url,username, password);
		            System.out.println("커넥션 성공");
		           
	        	
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	    }
	  
	  public static void closeConnection()
	    {
	    	try{
	                //자원 반환
	                rs.close();
	                stmt.close();
	                con.close();
//	                System.out.println("::::dbClose():::");
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	    }
	  
	  public  static void regApiData(boardListDto boardDto) throws IOException
	    {
	    	  StringBuffer sb = new StringBuffer();
	    	  StringBuffer sb1 = new StringBuffer();
	    	  
	    	  try{
	           	stmt = con.createStatement();
	              //데이터를 가져온다.
	           	/**
	           	 * type 50  축제
	           	 * 	           	 
	           	 * */
	           	
	           	 sb1.append("select count(*) count from OPENAPI_METADATA where ");
	           	 sb1.append("sub_title='"+boardDto.getContentid()+"' and type='50' and ALTERNATIVE_TITLE='"+boardDto.getContenttypeid()+"'");
	      
	           	 rs = stmt.executeQuery(sb1.toString());
	              
	              int cnt=(rs.next()==true)?rs.getInt("count"):-1;
	              
//	              System.out.println(":::cnt:::"+cnt);
	              
	              if(cnt==0)
	              {
	              	
	              	//** prfnm 공연 제목 특수 문자 unicode로 치환**/
	              	String contentId=boardDto.getContentid();
	              	String contentTypeId=boardDto.getContenttypeid();
	              	
	            	String title = DataUtil.getUnicodeChage(boardDto.getTitle());
	              	
	            	String description="";
	              	/** DESCRIPTION  이미지 url 추가하기 ***/
	            	if(!"".equals(boardDto.getFirstimage())){
	            		description=DataUtil.TagImageSrc(DataUtil.getUnicodeChage(boardDto.getOverview()),boardDto.getFirstimage(),contentTypeId);
	            	}else{
	            		description=DataUtil.getUnicodeChage(boardDto.getOverview());
	            	}
	              	
	              	/** 공연 기간 ***/
	              	String period=boardDto.getEventstartdate()+"~"+boardDto.getEventenddate();
	              	
	    		    /** thum url 변경 에 입력***/
	              	String firstImageUrlThum="";
	              	if(!"".equals(boardDto.getFirstimage2())){
	              	 firstImageUrlThum=DataUtil.ImageRename(boardDto.getFirstimage2());
	              	}else{
	              		firstImageUrlThum="";
	              	}
	              	System.out.println(":::::::firstImageUrlThum:::"+firstImageUrlThum);
	              	/** 장르 코드변환***//*
	              	String genreCode=KopisApiExplorer.getGenreCode(Api_detail.get(0).getGenrenm()); // 장르 코드 분류추가 
*/	              			
	              	sb.append("INSERT INTO OPENAPI_METADATA");
	              	sb.append("(");
	              	sb.append("SEQ,TITLE,ALTERNATIVE_TITLE");
	              	sb.append(",SUB_TITLE,REG_DATE,TYPE");
	              	sb.append(",DESCRIPTION,REFERENCE,RIGHTS");
	              	sb.append(",INSERT_DATE,VENUE,PERIOD");
	              	sb.append(",REFERENCE_IDENTIFIER_ORG,APPROVAL,GENRE");
	              	sb.append(")");
	              	sb.append("VALUES");
	              	sb.append("(");
	              	sb.append("OPENAPI_METADATA_SEQUENCE.NEXTVAL,'"+title+"','"+boardDto.getContenttypeid()+"'");
	              	sb.append(",'"+boardDto.getContentid()+"',sysdate,'50'");
	              	sb.append(",'"+description+"','"+boardDto.getSponsor1tel()+"','"+boardDto.getSponser1()+"'");
	              	sb.append(",sysdate,'"+boardDto.getEventplace()+"','"+period+"'");
	              	sb.append(",'"+firstImageUrlThum+"','W','1'");
	              	sb.append(")");
	              	
	                          	  
	              	System.out.println("::SQL:::"+sb.toString());
	              	stmt.executeUpdate(sb.toString());
	              	
	              	try {
	              		if(!"".equals(boardDto.getFirstimage2())){
						DataUtil.ImageRead(boardDto.getFirstimage2());
	              		}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              	
	              	System.out.println("파일 입력 및 이미지 저장 완료");
	              }
	            	else if(cnt>0)
	            	{
	            		System.out.println("등록되어 있습니다.");
	            	}
	            	else
	            	{
	            		System.out.println(":::error_msg::"+cnt);
	            	}
	              
	            
	             }catch(SQLException e){
	              e.printStackTrace();
	              System.out.println("::e::"+e);
	          }
	        
	    }
	  
	 	
}
