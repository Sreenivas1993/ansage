

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@WebServlet("/DisplayQuestion")

public class DisplayQuestion extends HttpServlet {
	
	public DisplayQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	 public static void dCV() {
		    // Create a trust manager that does not validate certificate chains
		    TrustManager[] trustAllCerts = new TrustManager[] { 
		      new X509TrustManager() {
		        public X509Certificate[] getAcceptedIssuers() { 
		          return new X509Certificate[0]; 
		        }
		        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
		        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
		    }};

		    try {
		      SSLContext sc = SSLContext.getInstance("SSL");
		      sc.init(null, trustAllCerts, new SecureRandom());
		      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		      //HttpsURLConnection.setDefaultHostnameVerifier(hv);
		    } catch (Exception e) {}
		  }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	DisplayQuestion.dCV();
	ServletContext context = getServletContext();
	String apikey = context.getInitParameter("apipass");
		List<QuestionPojo> qulist=new ArrayList<QuestionPojo>();
		
		String proto = "https://";
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		WebResource wsvc = cl.resource(proto+"localhost:9443/webSvcs");
		
		ClientResponse c = wsvc.path("qservices").path("displayq").path(apikey).type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		
		if (c.getStatus() != 200) {
			response.getOutputStream().print(c.getStatus());
 
			}
		else
		{  String jsonstring = c.getEntity(String.class);
			Object object=null;
			JSONParser jsonParser=new JSONParser();
			try {
				object=jsonParser.parse(jsonstring);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray arrayObj=null;
			arrayObj=(JSONArray)object;
			Iterator i = arrayObj.iterator();
			
			
			while(i.hasNext())
			{
				QuestionPojo qu=new QuestionPojo();
				JSONObject jon=(JSONObject)i.next();
				String qd=jon.get("qid").toString();
				int qid=Integer.parseInt(qd);
				String ques=jon.get("mainQ").toString();
				String descr=jon.get("descrQ").toString();
				String oid=jon.get("ownerid").toString();
				int ownerid=Integer.parseInt(oid);
				qu.setQid(qid);
				qu.setOid(ownerid);
				qu.setQuestion(ques);
				qu.setDescr(descr);
				qulist.add(qu);
			}
			
			request.setAttribute("aci","liqs");
			request.setAttribute("rows",qulist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayQuestion.jsp");
			if(dispatcher != null){
				dispatcher.forward(request,response);
			}
			
		}
		
		
	}
}

	
	

