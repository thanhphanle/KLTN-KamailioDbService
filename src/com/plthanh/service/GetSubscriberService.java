package com.plthanh.service;

import com.plthanh.dao.SubscriberDao;
import com.plthanh.model.Subscriber;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/get_subscriber")
public class GetSubscriberService {

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getSubcriberFromId(@PathParam("id") int userId)
			throws JSONException {

		JSONObject jsonObject = new JSONObject();
		
		Subscriber sub = SubscriberDao.getSubscriber(userId);
		
		jsonObject.put("id", sub.getUserId());
		jsonObject.put("username", sub.getUsername());
		jsonObject.put("password", sub.getPassword());
		jsonObject.put("domain", sub.getDomain());
		jsonObject.put("email", sub.getEmail());
		
		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}
}
