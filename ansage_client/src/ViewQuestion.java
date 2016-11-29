

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class ViewQuestion
 */
@WebServlet("/ViewQuestion")
public class ViewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qid = request.getParameter("question");
		
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		ClientResponse c = wsvc.path("qservices").path("getq").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, qid);
		
		if (c.getStatus() != 200) {
		response.getOutputStream().print(c.getStatus());
		}
		else{
		
			String jon = c.getEntity(String.class);
			JSONParser parser = new JSONParser();
			JSONObject json = new JSONObject();
			
			try {
				json = (JSONObject) parser.parse(jon);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			QuestionPojo qp = new QuestionPojo();
			//qp.setQuestion(json.get("question"));
			qp.setQuestion(json.get("question").toString());
			qp.setOname(json.get("oname").toString());
			qp.setDescr(json.get("descr").toString());
			qp.setQid(Integer.parseInt(qid));
			qp.setOid(Integer.parseInt(json.get("oid").toString()));
			request.setAttribute("qdets", qp);
		}
		
		List<BidPojo> bidlist=new ArrayList<BidPojo>();
		//WebResource bidwsvc = cl.resource(proto+"localhost:9080/webSvcs");
		ClientResponse bidc = wsvc.path("bidservices").path("retrieve").path(qid).type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		if (bidc.getStatus() == 200 || bidc.getStatus() == 202){
			if(bidc.getStatus() == 202){
				
			}
			else
			{
				String jsonstring = bidc.getEntity(String.class);
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
					BidPojo qu=new BidPojo();
					JSONObject jon=(JSONObject)i.next();
					String qud=jon.get("qid").toString();
					int quid=Integer.parseInt(qud);
					String reqd=jon.get("reqid").toString();
					int reqid=Integer.parseInt(reqd);
					String offr=jon.get("offer").toString();
					int offer=Integer.parseInt(offr);
					String bd=jon.get("bidid").toString();
					int bidid=Integer.parseInt(bd);
					qu.setBidid(bidid);
					qu.setOffer(offer);
					qu.setQid(quid);
					qu.setReqid(reqid);
					bidlist.add(qu);
					
				}
				
				request.setAttribute("bidrows",bidlist);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewquestion.jsp");
		if(dispatcher != null){
			dispatcher.forward(request,response);
		}
	}
}
