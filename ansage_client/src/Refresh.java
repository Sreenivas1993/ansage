

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Refresh
 */
@WebServlet("/Refresh")
public class Refresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refresh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String src = request.getHeader("Referer");/*
		System.out.println(src);
		if(src.contains("index2.jsp")){
			response.getWriter().print("index2.jsp");
		}
		else if(src.contains("DisplayQuestion"))
			response.getWriter().print("DisplayQuestion");
		else if(src.contains("ViewQuestion")){
			int k = src.indexOf("ViewQuestion");
			System.out.println(src.substring(k+1,src.length()-1));
		}*/
		response.getWriter().print(src);
	}

}
