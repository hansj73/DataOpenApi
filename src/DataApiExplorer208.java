import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;



public class DataApiExplorer208 {

	private static final Logger logger = Logger.getLogger(DataApiExplorer208.class);
	
	public static void main(String[] args)  {
		
		 DOMConfigurator.configure(DataUtil.getLogProperty());
      
		 DataApiDB.DbDriverLoad();
		 DataApiDB.getConnection();
		
		 String cat2="A0208";
//		 AreaBasedListExplorer.AreadBasedList();
		 logger.debug("::::[start]getTotalApiExplorer:::");
		String total=getTotalApiExplorer.getSearchTotal(cat2);
		logger.debug("::::total:::"+total);
		
		logger.debug("::::[start]searchFestivalList:::");
		searchFestivalExplorer.searchFestivalList(total,cat2);
		
		
	}	
		
}// class end
