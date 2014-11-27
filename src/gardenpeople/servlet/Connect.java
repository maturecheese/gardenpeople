package gardenpeople.servlet;

import java.io.IOException;



import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.sql.DataSource;


@WebServlet("/Connect")
public class Connect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/testDB")
    DataSource ds;
    
    public Connect()
    {
        super();
    }

  

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException{
    	
    	Connection c = null;
        PrintWriter out = response.getWriter();
        out.print( "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0" );
        out.print( "Transitional//EN\">\n" );
        out.print( "<html><head><title>Hello JDBC</title></head>\n<body>" );

        try
        {
        	c = ds.getConnection();
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from items" );

            while( rs.next() )
            {
                out.println( rs.getString( "name" ) );
                out.println( rs.getString( "price" ) );
                out.println( rs.getFloat( "quantity" ) );
                out.println( "<br />" );
            }
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }

        out.print( "</body></html>" );
    }

}