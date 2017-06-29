import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;



public class DataApiExplorer207 {

	private static final Logger logger = Logger.getLogger(DataApiExplorer207.class);
	
	public static void main(String[] args)  {
		
		 DOMConfigurator.configure(DataUtil.getLogProperty());
      
		 DataApiDB.DbDriverLoad();
		 DataApiDB.getConnection();
		
//		 AreaBasedListExplorer.AreadBasedList();
		 String cat2="A0207";
		 logger.debug("::::[start]getTotalApiExplorer:::");
		String total=getTotalApiExplorer.getSearchTotal(cat2);
		logger.debug("::::total:::"+total);
		
		logger.debug("::::[start]searchFestivalList:::");
		searchFestivalExplorer.searchFestivalList(total,cat2);
		
		
	}	
		
}// class end
