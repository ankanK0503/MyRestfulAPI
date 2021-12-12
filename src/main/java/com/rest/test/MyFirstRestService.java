package com.rest.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;



@Path("/RestService")
public class MyFirstRestService {

  @POST
  @Path("/execute")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_FORM_URLENCODED)
  public Response execute(String body) {

    System.out.println("Enter execute() >>");

    Gson gson = new Gson();

    // Map<String, String> returnMap = new HashMap<>();
    // returnMap.put("Test", "Ping Successful");

    // String responseString = gson.toJson(returnMap);
    // System.out.println("Response JSON: " + responseString);
    System.out.println("Request: " + body);
    Map<String, Object> requestBody = gson.fromJson(body, Map.class);

    List<Map<String, String>> idobjects = (List) ((Map<String, Object>) requestBody.get("id")).get("List");

    List<Map<String, String>> responseIdObjects = new ArrayList<>();

    for (Map<String, String> id : idobjects) {

      String firstname = id.get("firstname");
      String lastname = id.get("lastname");

      // String samAccountName = getSamAccountName(ADConntext);
      String samAccountName = "dummySamAccount";

      Map<String, String> map = new HashMap<>();
      map.put("firstname", firstname);
      map.put("lastname", lastname);
      map.put("samAccountName", samAccountName);

      responseIdObjects.add(map);


    }

    String responseBody = "{\r\n" + "    \"response\": %s \r\n" + "}";

    responseBody = String.format(responseBody, gson.toJson(responseIdObjects));

    // Converting to Map
    System.out.println("Request Body: " + responseBody);



    return Response.ok(responseBody).build();

    // JSONObject response = request;

    // return response;


  }

}
