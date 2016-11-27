package webSvcs;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
 
@Path("/qservices")
public class QServices {
	@Path("/add")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
    public Response addQuestion(String jon) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		
		try {
			json = (JSONObject) parser.parse(jon);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QuestionClass qc = new QuestionClass();
		String check = qc.quesdetails(json);
	
	   if(check==null)
		{
		   return Response.status(202).entity("false").build();
		
		}
		else
		{
			
			return Response.status(200).entity(check).build();
		}
	}
	
	@Path("/getq/{qid}")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
    public Response getQuestion(@PathParam("qid")int qid) {
		//some hibernate class calls getQDetails(qid)
		QuestionClass qc = new QuestionClass();
		JSONObject jo = qc.retrievequs(qid); 
		return Response.status(200).entity(jo.toString()).build();
	}
	
	@Path("/displayq")
	public Response displayQuestion()
	{
		QuestionClass qc = new QuestionClass();
		List<JSONObject> jsonlist=null;
		List<Questionpojo> quslist=qc.displayqus();
		if(quslist==null)
		{
			return Response.status(202).entity("false").build();
		}
		else
		{
			for(Questionpojo q:quslist)
			{
				JSONObject json=new JSONObject();
				json.put("qid",q.getQid());
				json.put("mainQ",q.getQus());
				json.put("descrQ",q.getDescr());
				json.put("ownerid",q.getOwnerid());
				jsonlist.add(json);
			}
			
			 
			 return Response.status(200).entity(jsonlist).build();
			
		}
		
		
		
		
	}
	
}

