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

@Path("/delete_subscriber")
public class DeleteSubscriberService {
	@Path("{username}/{password}")
	@GET
	@Produces("application/json")
	public Response addSubcriber(@PathParam("username") String username,
			@PathParam("password") String password) throws JSONException {
		JSONObject jsonObject = new JSONObject();

		Subscriber sub = new Subscriber();
		sub.setUsername(username);
		sub.setPassword(password);

		int code = SubscriberDao.deleteSubscriber(sub);
		jsonObject.put("result_code", String.valueOf(code));

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}
}
