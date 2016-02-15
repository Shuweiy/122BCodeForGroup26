// CS 122B
// Project 2
// Jasmine Mou, Shuwei Yang, Ziyu Yi


public class ParseResult {

	public ParseResult()
	{
		
	}
	
	public String GetIfEmailExists(String email)
	{
		String sql = "SELECT EXISTS(SELECT * FROM customers WHERE email='"+email+"';";
		return sql;
	}
	
	public String GetPasswordByEmail(String email)
	{
		String sql = "SELECT password FROM customers WHERE email='"+email+"';";
		return sql;
	}
	
	public String GetMovieIDByTitle(String title)
	{
		String sql="SELECT id FROM movies WHERE title LIKE '%"+title+"%';";
		return sql;
	}
	
	public String GetMovieIDByYear(int year)
	{
		String sql="SELECT id FROM movies WHERE year="+year+";";
		return sql;
	}
	
	public String GetMovieIDByDirector(String director)
	{
		String sql="SELECT id FROM movies WHERE director LIKE '%"+director+"%';";
		return sql;
	}
	
	public String GetMovieIDByStarID(int star_id)
	{
		String sql="SELECT movie_id FROM stars_in_movies where star_id='"+Integer.toString(star_id)+"';";
		return sql;
	}
	
	public String GetStarIDByStarsFirstName(String firstname)
	{
		String sql="SELECT id FROM stars WHERE first_name LIKE '%"+firstname+"%';";
		return sql;
	}
	
	public String GetStarIDByStarsLastName(String lastname)
	{
		String sql="SELECT id FROM stars WHERE last_name LIKE '%"+lastname+"%';";
		return sql;
	}
	
	public String GetStarIDByStarsFullName(String firstname, String lastname)
	{
		String sql="SELECT id FROM stars WHERE first_name LIKE '%"+firstname+"%' AND last_name LIKE '%"+lastname+"%';";
		return sql;
	}
	
	public String GetGenreIDByMovieID(int movie_id)
	{
		String sql="SELECT movie_id FROM genres_in_movies WHERE movie_id='"+Integer.toString(movie_id)+"';";
		return sql;
	}
	
	public String GetGenreIDByGenreName(String genre_name)
	{
		String sql="SELECT id FROM genres WHERE name='"+genre_name+"';";
		return sql;
	}
	
	public String GetMovieIDByGenreID(int genre_id)
	{
		String sql="SELECT movie_id FROM genres_in_movies WHERE genre_id='"+Integer.toString(genre_id)+"';";
		return sql;
	}
	
	public String GetMovieIDByTitleFirstChar(String title_first_char)
	{
		String sql="SELECT id FROM movies WHERE title LIKE'%"+title_first_char+"%';";
		return sql;
	}
	
	public String GetStarIDByMovieID(int movie_id)
	{
		String sql="SELECT star_id FROM stars_in_movies WHERE movie_id='"+Integer.toString(movie_id)+"';";
		return sql;
	}
	
	public String GetIfTransactionExists(String first_name, String last_name, int credit_card_no, String expiration)
	{
		String sql = "SELECT EXISTS(SELECT * FROM creditcards WHERE first_name='"+first_name+"' AND last_name='"+last_name+"' AND id='"+Integer.toString(credit_card_no)+"' AND expiration='"+expiration+"';";
		return sql;
	}

	public String InsertSale(int customer_id, int movie_id, String sale_date)
	{
		String sql = "INSERT INTO sales(customer_id, movie_id, sale_date) VALUES('"+Integer.toString(customer_id)+"', '"+Integer.toString(movie_id)+"', '"+sale_date+"');";
		return sql;
	}

	
	// for auxiliary classes
	public String GetMovieTitleByMovieID(int movie_id)
	{
		String sql = "SELECT title FROM movies WHERE id='"+Integer.toString(movie_id)+"';";
		return sql;
	}
	
	public String GetMovieYearByMovieID(int movie_id)
	{
		String sql = "SELECT year FROM movies WHERE id='"+Integer.toString(movie_id)+"';";
		return sql;
	}
	
	public String GetMovieDirectorByMovieID(int movie_id)
	{
		String sql = "SELECT director FROM movies WHERE id='"+Integer.toString(movie_id)+"';";
		return sql;
	}
	
	public String GetStarFirstNameByStarID(int star_id)
	{
		String sql = "SELECT first_name FROM stars WHERE id='"+Integer.toString(star_id)+"';";
		return sql;
	}
	
	public String GetStarLastNameByStarID(int star_id)
	{
		String sql = "SELECT last_name FROM stars WHERE id='"+Integer.toString(star_id)+"';";
		return sql;
	}
	
	public String GetStarDOBByStarID(int star_id)
	{
		String sql = "SELECT dob FROM stars WHERE id='"+Integer.toString(star_id)+"';";
		return sql;
	}
	
	public String GetStarPhotoURLByStarID(int star_id)
	{
		String sql = "SELECT photo_url FROM stars WHERE id='"+Integer.toString(star_id)+"';";
		return sql;
	}
	

}
