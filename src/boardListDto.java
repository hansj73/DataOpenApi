

public class boardListDto {

	/** 지역정보 리스트 
	 * 
	 * areaBasedList
	 * 
	 * **/
	
	String numOfRows="";	/*한 페이지 결과 수	한 페이지 결과 수*/
	String pageNo="";	/*페이지 번호	현재 페이지 번호*/
	String totalCount="";	/*전체 결과 수	전체 결과 수*/
	String contentid="";	/*콘텐츠ID	콘텐츠ID*/
	String contenttypeid="";	/*콘텐츠타입ID	관광타입(관광지, 숙박 등) ID*/
	
  /***
   * detailCommon
   *
   */
	String addr1="";	/*주소	주소(예, 서울 중구 다동)를 응답*/
	String addr2="";	/*상세주소	상세주소*/
	String firstimage="";	/*대표이미지(원본)	원본 대표이미지(약 500*333 size) URL 응답*/
	String firstimage2="";	/*대표이미지(썸네일)	썸네일 대표이미지(약 150*100 size) URL 응답*/
	String overview="";
	String homepage="";
	String zipcode=""; /*우편번호*/
	String title="";
	
	/**
	 * 
	 * detailIntro
	 * @return
	 */
	 
	String sponsor1tel="";
    String sponser1="";
    String eventplace="";
    String eventstartdate="";
    String eventenddate="";
	
    
	public String getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(String numOfRows) {
		this.numOfRows = numOfRows;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getContenttypeid() {
		return contenttypeid;
	}
	public void setContenttypeid(String contenttypeid) {
		this.contenttypeid = contenttypeid;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getFirstimage() {
		return firstimage;
	}
	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
	public String getFirstimage2() {
		return firstimage2;
	}
	public void setFirstimage2(String firstimage2) {
		this.firstimage2 = firstimage2;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSponsor1tel() {
		return sponsor1tel;
	}
	public void setSponsor1tel(String sponsor1tel) {
		this.sponsor1tel = sponsor1tel;
	}
	public String getSponser1() {
		return sponser1;
	}
	public void setSponser1(String sponser1) {
		this.sponser1 = sponser1;
	}
	public String getEventplace() {
		return eventplace;
	}
	public void setEventplace(String eventplace) {
		this.eventplace = eventplace;
	}
	public String getEventstartdate() {
		return eventstartdate;
	}
	public void setEventstartdate(String eventstartdate) {
		this.eventstartdate = eventstartdate;
	}
	public String getEventenddate() {
		return eventenddate;
	}
	public void setEventenddate(String eventenddate) {
		this.eventenddate = eventenddate;
	}


		
}
