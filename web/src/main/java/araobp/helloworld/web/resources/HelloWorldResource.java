package araobp.helloworld.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.onosproject.rest.AbstractWebResource;

import araobp.helloworld.service.HelloWorldService;

@Path("greeting")
public class HelloWorldResource  extends AbstractWebResource {
  
  @GET
  public Response getGreeting() {
    return Response.status(200).entity("OK").build();
  }

  @GET
  @Path("{name}")
  public Response getGreetingWithName(@PathParam("name") String name) {
    HelloWorldService service = get(HelloWorldService.class);
    String greeting = service.fetchHelloWorld(name);
    return Response.status(200).entity(greeting).build();
  }
}
