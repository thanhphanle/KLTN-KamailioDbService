package com.plthanh.service;

import com.plthanh.dao.SubscriberDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/change_password")
public class ChangePasswordService {

	@Path("{username}/{old_password}/{new_password}")
	@GET
	@Produces("application/json")
	public Response changePassword(@PathParam("username") String username,
			@PathParam("old_password") String oldPassword,
			@PathParam("new_password") String newPassword) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		int code = SubscriberDao.changePassword(username, oldPassword, newPassword);
		jsonObject.put("result_code", String.valueOf(code));

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}

}
