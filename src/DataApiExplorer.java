import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;



public class DataApiExplorer {

	private static final Logger logger = Logger.getLogger(DataApiExplorer.class);
	
	public static void main(String[] args)  {
		
		 DOMConfigurator.configure(DataUtil.getLogProperty());
      
		 DataApiDB.DbDriverLoad();
		 DataApiDB.getConnection();
		
//		 AreaBasedListExplorer.AreadBasedList();
		 logger.debug("::::[start]getTotalApiExplorer:::");
		String total=getTotalApiExplorer.getSearchTotal();
		logger.debug("::::total:::"+total);
		
		logger.debug("::::[start]searchFestivalList:::");
		searchFestivalExplorer.searchFestivalList(total);
		
		
	}	
		
}// class end
