//import ParseResult.*;
// CS 122B
// Project 2
// Jasmine Mou, Shuwei Yang, Ziyu Yi

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class JDBC 
{
	private static ParseResult parResult;
	private static Connection connection;
	private static Statement action1;
	private static Statement action2;
	private static Statement action3;	
	
	public JDBC(Statement action1, Statement action2, Statement action3)
	{
		ParseResult parResult = new ParseResult();
		this.action1 = action1;
		this.action2 = action2;
		this.action3 = action3;
	}
	
	// "Login" Page
	public static boolean CheckIfEmailExsits(String email) throws Exception
	{
		ResultSet result = action1.executeQuery(parResult.GetIfEmailExists(email));
		
		if(result.next())
		{
			if(result.getInt(1)==0)
			{
				result.close();
				return false;
			}
			else
			{
				result.close();
				return true;
			}
		}
		
		return false;
	}

	public static boolean CheckIfEmailPasswordMatch(String email, String password) throws Exception
	{



		String sqlEmailExist = "SELECT EXISTS(SELECT * FROM customers WHERE email='"+email+"';";

		ResultSet result = action1.executeQuery(sqlEmailExist);

		if(result.next())
		{

			if(result.getInt(1)==0)
			{
				result.close();
				return false;
			}
			else
			{
				result.close();
				String sqlPasswordTrue = "SELECT password FROM customers WHERE email='"+email+"';";
				ResultSet result2 = action2.executeQuery(sqlPasswordTrue);
				if(result2.next())
				{
					if(result2.getString(1)==password)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				result2.close();
			}
		}
		
		return false;
	}
	
	// "Searching" Page
	
	public static ArrayList<Integer> GetMovieIDsByTitle(String title) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList<>();
		
		ResultSet result = action1.executeQuery(parResult.GetMovieIDByTitle(title));
		while(result.next())
		{
			movie_ids.add(result.getInt(1));
		}
		result.close();
		
		return movie_ids;
	}

	public static ArrayList<Integer> GetMovieIDsByYear(int year) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList<>();
		
		ResultSet result = action1.executeQuery(parResult.GetMovieIDByYear(year));
		while(result.next())
		{
			movie_ids.add(result.getInt(1));
		}
		result.close();
		
		return movie_ids;
	}
	
	public static ArrayList<Integer> GetMovieIDsByDirector(String director) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result = action1.executeQuery(parResult.GetMovieIDByDirector(director));
		while(result.next())
		{
			movie_ids.add(result.getInt(1));
		}
		result.close();
		
		return movie_ids;
	}
	
	public static ArrayList<Integer> GetMovieIDsByStarsFirstName(String firstname) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result1 = action1.executeQuery(parResult.GetStarIDByStarsFirstName(firstname));
		ResultSet result2 = null;
		while(result1.next())
		{
			result2 = action2.executeQuery(parResult.GetMovieIDByStarID(result1.getInt(1)));
			while(result2.next())
			{
				movie_ids.add(result2.getInt(1));
			}
			result2.close();
		}
		result1.close();
		
		return movie_ids;
	}
	
	public static ArrayList<Integer> GetMovieIDsByStarsLastName(String lastname) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result1 = action1.executeQuery(parResult.GetStarIDByStarsLastName(lastname));
		ResultSet result2 = null;
		while(result1.next())
		{
			result2 = action2.executeQuery(parResult.GetMovieIDByStarID(result1.getInt(1)));
			while(result2.next())
			{
				movie_ids.add(result2.getInt(1));
			}
			result2.close();
		}
		result1.close();
		
		return movie_ids;		
		
	}
	
	public static ArrayList<Integer> GetMovieIDsByStarsFullName(String firstname, String lastname) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result1 = action1.executeQuery(parResult.GetStarIDByStarsFullName(firstname, lastname));
		ResultSet result2 = null;
		while(result1.next())
		{
			result2 = action2.executeQuery(parResult.GetMovieIDByStarID(result1.getInt(1)));
			while(result2.next())
			{
				movie_ids.add(result2.getInt(1));
			}
			result2.close();
		}
		result1.close();		
		
		return movie_ids;
	}

	// "Browsing" Page
	
	public static ArrayList<Integer> GetGenreIDsByMovieID(int movie_id) throws Exception
	{
		ArrayList<Integer> genre_ids = new ArrayList();
		
		ResultSet result = action1.executeQuery(parResult.GetGenreIDByMovieID(movie_id));

		while(result.next())
		{
			genre_ids.add(result.getInt(1));
		}
		result.close();
		
		return genre_ids;
	}
	
	public static ArrayList<Integer> GetMovieIDsByGenreName(String genre_name) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result1 = action1.executeQuery(parResult.GetGenreIDByGenreName(genre_name));
		ResultSet result2 = null;
		while(result1.next())
		{
			result2 = action2.executeQuery(parResult.GetMovieIDByGenreID(result1.getInt(1)));
			while(result2.next())
			{
				movie_ids.add(result2.getInt(1));
			}
			result2.close();
		}
		result1.close();
		
		return movie_ids;
	}

	public static ArrayList<Integer> GetMovieIDsByTitleFirstChar(String title_first_char) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result = action1.executeQuery("SELECT id FROM movies WHERE title LIKE'"+title_first_char+"%';");
		while(result.next())
		{
			movie_ids.add(result.getInt(1));
		}
		result.close();
		
		return movie_ids;
	}
	
	public static ArrayList<Integer> GetMovieIDsByStarID(int star_id) throws Exception
	{
		ArrayList<Integer> movie_ids = new ArrayList();
		
		ResultSet result = action1.executeQuery(parResult.GetMovieIDByStarID(star_id));
		while(result.next())
		{
			movie_ids.add(result.getInt(1));
		}
		result.close();
		
		return movie_ids;
	}
	
	public static ArrayList<Integer> GetStarIDsByMovieID(int movie_id) throws Exception
	{
		ArrayList<Integer> star_ids = new ArrayList();
		
		ResultSet result = action1.executeQuery(parResult.GetStarIDByMovieID(movie_id));
		while(result.next())
		{
			star_ids.add(result.getInt(1));
		}
		result.close();
		return star_ids;
	}

	// "Checkout" Page
	public static boolean CheckIfTransactionCanSucceed(String first_name, String last_name, int credit_card_no, String expiration) throws Exception
	// NOTE: make sure expiration is in "2004/05/10" format before passed into the input parameter "expiration"
	{
		boolean bool = false;
		
		ResultSet result = action1.executeQuery(parResult.GetIfTransactionExists(first_name, last_name, credit_card_no, expiration));
		if(result.next())
		{
			if(result.getInt(1)==1)
			{
				bool=true;
			}
		}
		result.close();
		
		return bool;
	}
	
	public static void InsertSales(int customer_id, int movie_id, String sale_date) throws Exception
	{
		action1.executeUpdate(parResult.InsertSale(customer_id, movie_id, sale_date));
	}

	// exit
	public static void exit() throws Exception
	{
		connection.close();
	}
	
	// auxiliary classes
	public static class MovieInfo implements Comparable<MovieInfo>
	{
		private int id;
		private String title;
		private Integer year;
		private String director;
		private ArrayList<Integer> genre_ids;
		private ArrayList<Integer> star_ids;
		// private String hyperlink; ??? 
		
		public MovieInfo(int movie_id) throws Exception
		{
			this.id = movie_id;
			this.title = SetTitle(movie_id);
			this.year = SetYear(movie_id);
			this.director = SetDirector(movie_id);
			this.genre_ids = GetGenreIDsByMovieID(movie_id);
			this.star_ids = GetStarIDsByMovieID(movie_id);
			// this.hyperlink = SetHyperLink(movie_id); ????
		}
		
		// Set methods
		private String SetTitle(int movie_id) throws Exception
		{
			String TITLE = null;
			
			ResultSet result = action1.executeQuery(parResult.GetMovieTitleByMovieID(movie_id));
			if(result.next())
			{
				TITLE = result.getString(1);
			}
			result.close();
			
			return TITLE;
		}
		
		private Integer SetYear(int movie_id) throws Exception
		{
			Integer YEAR = null;
			
			ResultSet result = action1.executeQuery(parResult.GetMovieYearByMovieID(movie_id));
			if(result.next())
			{
				YEAR = result.getInt(1);
			}
			result.close();
			
			return YEAR;
		}

		private String SetDirector(int movie_id) throws Exception
		{
			String DIRECTOR = null;
			
			ResultSet result = action1.executeQuery(parResult.GetMovieDirectorByMovieID(movie_id));
			while(result.next())
			{
				DIRECTOR = result.getString(1);
			}
			result.close();
			
			return DIRECTOR;
		}
		
		// Get methods
		public int GetID() throws Exception
		{
			return id;
		}
		
		public String GetTitle() throws Exception
		{
			return title;
		}
		
		public int GetYear() throws Exception
		{
			return year;
		}
		
		public String GetDirector() throws Exception
		{
			return director; 
		}
	
		public ArrayList<Integer> GetGenreIDs() throws Exception
		{
			return genre_ids;
		}
		
		public ArrayList<Integer> GetStarIDs() throws Exception
		{
			return star_ids;
		}
		
		// public String GetHyperLink() throws Exception
		// {
		//	return hyperlink;
		// }
		
		// Sort methods
		@Override
		public int compareTo(MovieInfo o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public static Comparator<MovieInfo> TitleComparator = new Comparator<MovieInfo>()
		{
			@Override
			public int compare(MovieInfo mi1, MovieInfo mi2)
			{
				try {
					return mi1.GetTitle().compareTo(mi2.GetTitle());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Error happens during comparison of titles." );
				return 0;
			}
		};
		
		public static Comparator<MovieInfo> YearComparator = new Comparator<MovieInfo>()
		{
			@Override
			public int compare(MovieInfo mi1, MovieInfo mi2)
			{
				try {
					return (int) (mi1.GetYear() - mi2.GetYear());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Error happens during comparison of years.");
				return 0;
				
			}
		};


	}		

	
	public class StarInfo
	{
		private int id;
		private String first_name;
		private String last_name;
		private String dob;
		private String photo_url;
		private ArrayList<Integer> movie_ids;
		// private String hyperlink; ???? how to represent star's own hyperlink? 
		
		public StarInfo(int star_id) throws Exception
		{
			this.id = star_id;
			this.first_name = SetFirstName(star_id);
			this.last_name = SetLastName(star_id);
			this.dob = SetDOB(star_id);
			this.photo_url = SetPhotoURL(star_id);
			this.movie_ids = GetMovieIDsByStarID(star_id);
			// this.hyperlink = SetHyperlink(star_id); ????
		}
		
		// Set methods
		private String SetFirstName(int star_id) throws Exception
		{
			String FN=null;
		
			ResultSet result = action1.executeQuery(parResult.GetStarFirstNameByStarID(star_id));
			if(result.next())
			{
				FN = result.getString(1);
			}
			result.close();
			
			return FN;
		}
		
		private String SetLastName(int star_id) throws Exception
		{
			String LN=null;
			
			ResultSet result = action1.executeQuery(parResult.GetStarLastNameByStarID(star_id));
			if(result.next())
			{
				LN = result.getString(1);
			}
			result.close();
			
			return LN;			
		}
		
		private String SetDOB(int star_id) throws Exception
		{
			String DOB =null;
			
			ResultSet result = action1.executeQuery(parResult.GetStarDOBByStarID(star_id));
			if(result.next())
			{
				DOB = result.getString(1);
			}
			result.close();
			
			return DOB;
		}
		
		private String SetPhotoURL(int star_id) throws Exception
		{
			String URL =null;
			
			ResultSet result = action1.executeQuery(parResult.GetStarPhotoURLByStarID(star_id));
			if(result.next())
			{
				URL = result.getString(1);
			}
			result.close();
			
			return URL;
		}
	
		// private String SetHyperLink(int star_id) throws Exception ???? 
		// {}
				
		// Get methods
		public int GetID()
		{
			return id;
		}
	
		public String GetFirstName()
		{
			return first_name;
		}
	
		public String GetLastName()
		{
			return last_name;
		}
		
		public String GetDOB()
		{
			return dob;
		}
		
		public String GetPhotoURL()
		{
			return photo_url;
		}
	
		public ArrayList<Integer> GetMovieIDs()
		{
			return movie_ids;
		}
	
		// public String GetHyperLink() ????
		// {
		//	return hyperlink;
		//}
		
		
	}
/*	
	public void main(String s[]) throws Exception
	{
		MovieInfo[] MovieInfoArray = new MovieInfo[3];
		MovieInfoArray[0] = new MovieInfo(490003);
		MovieInfoArray[1] = new MovieInfo(490008);
		MovieInfoArray[2] = new MovieInfo(490010);
		Arrays.sort(MovieInfoArray, MovieInfo.TitleComparator);
		Arrays.sort(MovieInfoArray, MovieInfo.YearComparator);
	}
*/
}


/*MovieInfo class(movie_id):
 * attributes:
 *		id, title, year, director, [genre_id], [star_id]	
 * methods:
 * 		String GetID()
 * 		String GetTitle()
 * 		String GetYear()
 * 		String GetDirector()
 * 		[genre_name] GetGenres()
 * 		[star_id] GetStarID()  
 * 
 * 		// examples of comparison 
		MovieInfo[] MovieInfoArray = new MovieInfo[3];
		MovieInfoArray[0] = new MovieInfo(490003);
		MovieInfoArray[1] = new MovieInfo(490008);
		MovieInfoArray[2] = new MovieInfo(490010);
		Arrays.sort(MovieInfoArray, MovieInfo.TitleComparator);
		Arrays.sort(MovieInfoArray, MovieInfo.YearComparator);
*/

/*StarInfo class(star_id)
 * attributes:
 * 		id, first_name, last_name, dob, photo_url, [movie_id]
 */
