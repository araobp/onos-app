package araobp.helloworld.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.onosproject.rest.AbstractWebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("greeting")
public class HelloWorldResource extends AbstractWebResource {
  
  private final Logger log = LoggerFactory.getLogger(getClass());
  
  @GET
  public Response getGreeting() {
    log.info("[REST] getGreeting() called");
    return Response.status(200).entity("OK").build();
  }

  @GET
  @Path("{name}")
  public Response getGreetingWithName(@PathParam("name") String name) {
    log.info("[REST] getGreetingWithName() called");
    GreetingService service = get(GreetingService.class);
    String greeting = service.fetchGreeting(name);
    log.info("[REST] getGreetingWithName() -- name: {}, greeting: {}", name, greeting);
    return Response.status(200).entity(greeting).build();
  }
}
