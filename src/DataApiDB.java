import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DataApiDB {
	
	
	static Connection con;
	static Statement stmt;
    static ResultSet rs;
	
	public DataApiDB() {}
	
	
	  public static void  DbDriverLoad()
	    {
	    	
	    	try{
	            
	            Class.forName("oracle.jdbc.driver.OracleDriver");
//	            	System.out.println("드라이버 로딩 성공");
				} catch (ClassNotFoundException e) {
//					System.out.println("드라이버 로딩 실패");
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
//		            System.out.println("커넥션 성공");
		           
	        	
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
	  
	  public  static boolean regApiData(boardListDto boardDto,String cat2) throws IOException
	    {
	    	  StringBuffer sb = new StringBuffer();
	    	  StringBuffer sb1 = new StringBuffer();
	    	  
	    	  boolean inTrue=false;
	    	  
	    	  try{
	           	stmt = con.createStatement();
	              //데이터를 가져온다.
	           	/**
	           	 * type 50  축제/행사
	           	 * 	           	 
	           	 * */
	           	
	           	 sb1.append("select count(*) count from PCN_RDF_METADATA where ");
	           	 sb1.append("sub_title='"+boardDto.getContentid()+"' and type='50' and ALTERNATIVE_TITLE='"+boardDto.getContenttypeid()+"'");
	           	 //sb1.append("and genre='"+GENRE+"'");
	      
	           	//System.out.println(":::sql:::"+sb1.toString());
	           	 rs = stmt.executeQuery(sb1.toString());
	              
	              int cnt=(rs.next()==true)?rs.getInt("count"):-1;
	              
	              //System.out.println(":::cnt:::"+cnt);
	              
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
	              	/*System.out.println(":::::::firstImageUrlThum:::"+firstImageUrlThum);*/
	              	
	              	long uci_time = System.currentTimeMillis();
	              	/** 장르 코드변환***//*
	              	String genreCode=KopisApiExplorer.getGenreCode(Api_detail.get(0).getGenrenm()); // 장르 코드 분류추가 
*/	              			
	             	String GENRE =("A0207".equals(cat2))?"1":"2";
	              	
	              	sb.append("INSERT INTO PCN_RDF_METADATA");
	              	sb.append("(");
	              	sb.append("SEQ,TITLE,ALTERNATIVE_TITLE");
	              	sb.append(",SUB_TITLE,REG_DATE,TYPE");
	              	sb.append(",DESCRIPTION,REFERENCE,RIGHTS");
	              	sb.append(",INSERT_DATE,VENUE,PERIOD");
	              	sb.append(",REFERENCE_IDENTIFIER_ORG,APPROVAL,GENRE");
	              	sb.append(",UCI,ADDR1,ADDR2,ZIP_CODE)");
	              	sb.append("VALUES");
	              	sb.append("(");
	              	sb.append("EVENT_FASTIVAL_SEQ.NEXTVAL,'"+title+"','"+boardDto.getContenttypeid()+"'");
	              	sb.append(",'"+boardDto.getContentid()+"',sysdate,'50'");
	              	sb.append(",'"+description+"','"+boardDto.getSponsor1tel()+"','"+boardDto.getSponser1()+"'");
	              	sb.append(",sysdate,'"+boardDto.getEventplace()+"','"+period+"'");
	              	sb.append(",'"+firstImageUrlThum+"','W','"+GENRE+"'");
	              	sb.append(",'G706"+uci_time+"','"+boardDto.getAddr1()+"','"+boardDto.getAddr2()+"','"+boardDto.getZipcode()+"')");
	              	
	                          	  
	              	//System.out.println("::SQL:::"+sb.toString());
	              	stmt.executeUpdate(sb.toString());
	              	inTrue=true;
	              	
	              	try {
	              		if(!"".equals(boardDto.getFirstimage2())){
						DataUtil.ImageRead(boardDto.getFirstimage2());
	              		}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              	
//	              	System.out.println("파일 입력 및 이미지 저장 완료");
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
	    	  return inTrue;
	        
	    }
	  
	 	
}
