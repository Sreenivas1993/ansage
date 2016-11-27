package webSvcs;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/bidservices")
public class BidServices {
	@Path("/add")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response saveBid(String jon)
		{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try 
			{
			json = (JSONObject) parser.parse(jon);
			} 
		catch (ParseException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		BidClass bid = new BidClass();
		String check = bid.savebid(json); 
		if(check=="false")
			{
			   return Response.status(202).entity("false").build();
			}
			else
			{
			return Response.status(200).entity(check).build();
			}
		}
	@SuppressWarnings("unchecked")
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
    public Response retbid(@PathParam("bid") String bd)
    	{
		  
		  BidClass bid=new BidClass();
		  List<Bidpojo> bidlist=bid.retbids(bd);
		  GenericEntity<List<Bidpojo>> entity=new GenericEntity<List<Bidpojo>>(bidlist) {};
		  
		  if(bidlist==null)
			  return Response.status(202).entity("false").build();
		  else
		  {
		      return  Response.ok(entity).build();
		  }
		}
			
}
	
