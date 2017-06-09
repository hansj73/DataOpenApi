

public class DataApiExplorer {

	public static void main(String[] args)  {
      
		 DataApiDB.DbDriverLoad();
		 DataApiDB.getConnection();
		
//		 AreaBasedListExplorer.AreadBasedList();
		String total=getTotalApiExplorer.getSearcTotal();
		
		
		searchFestivalExplorer.searchFestivalList(total);
		
		
	}	
		
}// class end
