package araobp.helloworld.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.onosproject.rest.AbstractWebResource;

@Path("helloworld")
public class HelloWorldResource  extends AbstractWebResource {
  
  @GET
  public Response getGreeting(@PathParam("name") String name) {
    return Response.status(200).entity("OK").build();
  }

  @GET
  @Path("{name}")
  public Response getGreetingWithName(@PathParam("name") String name) {
    return Response.status(200).entity(name).build();
  }
}