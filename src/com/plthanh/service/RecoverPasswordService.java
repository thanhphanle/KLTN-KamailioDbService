package com.plthanh.service;

import com.plthanh.dao.RecoverDao;
import com.plthanh.dao.SubscriberDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/recover_password")
public class RecoverPasswordService {
	@Path("{username}/{new_password}/{sms_code}")
	@GET
	@Produces("application/json")
	public Response changePassword(@PathParam("username") String username,
			@PathParam("new_password") String newPassword,
			@PathParam("sms_code") String smsCode) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		int code = RecoverDao.checkRecoverMapping(smsCode, username);

		if (code == 1) { // correct info
			code = SubscriberDao.recoverPassword(username, newPassword);

			if (code == 1) {
				// delete recover mapping
				RecoverDao.deleteRecoverMapping(smsCode, username);
			}
		}
		jsonObject.put("result_code", String.valueOf(code));

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}
}
