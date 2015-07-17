package com.plthanh.service;

import java.io.IOException;

import com.plthanh.dao.RecoverDao;
import com.plthanh.esms.SendSmsService;
import com.plthanh.util.Config;
import com.plthanh.util.RandomCode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/request_recover_password")
public class RequestRecoverPasswordService {
	@Path("{username}")
	@GET
	@Produces("application/json")
	public Response changePassword(@PathParam("username") String username) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		int smsCode = RandomCode.getRandomCode();
		String message = Config.ESMS_BODY_MESSAGE + String.valueOf(smsCode);
		
		// Call eSMS service
		SendSmsService smsService = new SendSmsService();
		try {
			smsService.send(message, username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int code = RecoverDao.addRecoverMapping(String.valueOf(smsCode), username);
		jsonObject.put("result_code", String.valueOf(code));

		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	}
}
