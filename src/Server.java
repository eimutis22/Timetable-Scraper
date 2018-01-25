import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Server
 */
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();


		private Scraper sr = new Scraper();
		sr.main();


		out.println("<HTML>");
		out.println("Hello world\n");
		String test = Encrypt.chuckNorris("Ana are mere!");
		out.println(test + "\n");
		//test = Decrypt.text(test);
		if (test.length() > 0)
			out.println(test);
		else
			out.println("\nNU A MERS");
		out.println("</HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		
		/*out.println("Hello from server");
		
		BufferedReader br = request.getReader();
		String line = null;
		
		while ((line=br.readLine()) != null) {
			out.println("Server read this: " + line);
		}*/
		
		/*String connectionUrl = "jdbc:sqlserver://itsimmigrants.database.windows.net:1433;database=ITSligoCompanion;user=immigrants@itsimmigrants;password=@Sligocompanion;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null) {
		      jb.append(line);
		    }
		} catch (Exception e) {
			  
		}

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			JSONObject json = new JSONObject(jb.toString());
			
			Connection con = DriverManager.getConnection(connectionUrl);
			
			String SQL = "INSERT INTO Users (FirstName, Surname, Email, Password) VALUES ('" + json.getString("firstname") + "','" +
					json.getString("surname") + "','" + json.getString("email") + "','" + json.getString("password") + "')";
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(SQL);
			stmt.close();
			con.close();
			out.println("Data added successfully");
		} catch (JSONException e) {
		    //throw new IOException("Error parsing JSON request string");
			out.println("Error parsing JSON string" + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			out.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.println("Error class: " + e.getMessage());
		}*/
		/*Enumeration<String> headers = request.getHeaderNames();
		String next;
		while(headers.hasMoreElements()) {
			next = headers.nextElement();
			out.println(next + ": " + request.getHeader(next));
		}*/
		String contentType = request.getHeader("content-type");
		
			
		if(contentType.equals("User-registration")) {
			try {
				JSONObject json = JSONParser.parse(request);
				out.println(Database.registerUser(json.getString("firstname"), json.getString("surname"), json.getString("email"), json.getString("password")));
			} catch (JSONException e) {
				out.println(e.getMessage());
			}
		}
		else if (contentType.equals("User-login")) {
			try {
				JSONObject json = JSONParser.parse(request);
				out.println(Database.checkUserLogin(json.getString("email"), json.getString("password")));
			} catch (JSONException e) {
				out.println(e.getMessage());
			}
		}
		else if (contentType.equals("User-details"))
		{
			JSONObject json = JSONParser.parse(request);
			try {
				out.println(Database.getUserDetails(json.getString("email")));
			} catch (JSONException e) {
				out.print(e.getMessage());
			}
		}
		else
			out.println(contentType);
		
	}

}
