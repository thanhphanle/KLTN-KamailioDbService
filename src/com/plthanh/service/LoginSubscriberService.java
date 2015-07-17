package com.plthanh.service;

import com.plthanh.dao.SubscriberDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/login_subscriber")
public class LoginSubscriberService {
	@Path("{username}/{password}")
	@GET
	@Produces("application/json")
	public Response changePassword(@PathParam("username") String username,
			@PathParam("password") String password) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		int code = SubscriberDao.loginSubscriber(username, password);
		jsonObject.put("result_code", String.valueOf(code));

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}
}
