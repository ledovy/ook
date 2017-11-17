package ch.ledovy.projects.ook.service;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.sewer.log.HasLogger;

@Service
public class IsbnService implements HasLogger {
	private static final String URL = "http://isbndb.com/api/v2/json/27DUHMH8/books?q=";
	
	public List<Book> search(final String isbn) {
		try {
			InputStream is = new URL(URL + isbn).openStream();
			String jsonText = IOUtils.toString(is);
			is.close();
			// BufferedReader rd = new BufferedReader(new InputStreamReader(is,
			// Charset.forName("UTF-8")));
			// String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return toBook(json);
		} catch (Exception e) {
			getLogger().error("unable to read from webservice for " + isbn, e);
			return null;
		}
	}
	
	private List<Book> toBook(final JSONObject json) {
		List<Book> books = new ArrayList<>();
		try {
			Book book = new Book();
			JSONObject data = json.getJSONArray("data").getJSONObject(0);
			// book.setAuthor(author);
			// book.setBookDate(bookDate);
			book.setDescription(data.getString("summary"));
			book.setEdition(data.getString("edition_info"));
			// book.setEditionDate(editionDate);
			book.setIsbn(data.getString("isbn13"));
			// book.setLanguage(language);
			// book.setPlace(place);
			book.setPublisher(data.getString("publisher_text"));
			book.setTitle(data.getString("title"));
			books.add(book);
		} catch (JSONException e) {
			getLogger().warn("unable to read from json", e);
		}
		return books;
	}
	
	/*
	 * 
	 * { "data" : [ { "lcc_number" : "", "dewey_normal" : "0", "book_id" :
	 * "guards_guards_a17", "title" : "Guards! Guards!", "dewey_decimal" : "",
	 * "marc_enc_level" : "~", "publisher_name" : "Corgi", "isbn13" :
	 * "9780552166669", "title_long" : "Guards! Guards! (Discworld)",
	 * "awards_text" : "", "edition_info" : "Paperback; 2012-11-05",
	 * "publisher_id" : "corgi", "subject_ids" : [ "literature_fiction",
	 * "science_fiction_fantasy_fantasy" ], "notes" : "", "language" : "",
	 * "urls_text" : "", "title_latin" : "Guards! Guards!", "isbn10" :
	 * "0552166669", "author_data" : [ { "name" : "Pratchett, Terry", "id" :
	 * "pratchett_terry" } ], "summary" :
	 * "The eighth Discworld novel An aura of mean-minded resentfulness is thick in the streets of Ankh-Morpork. Insurrection is in the air. The Haves and Have-Nots are about to fall out all over again. The Have-Nots ant some of their own magic. But magic in the hands of amateurs is a dangerous thing. The City Watch is the last line of defence against such unnatural goings-on. But when even the Watch have trouble telling Right from Wrong, you know that Law and Order ain't what it used to be. But that's all about to change."
	 * , "publisher_text" : "Corgi", "physical_description_text" :
	 * "5.0\"x7.7\"x1.2\"; 0.7 lb; 432 pages" } ], "page_count" : 1,
	 * "result_count" : 1, "index_searched" : "isbn", "current_page" : 1 }
	 * 
	 */
}
