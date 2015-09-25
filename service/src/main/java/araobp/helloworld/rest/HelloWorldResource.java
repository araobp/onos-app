package araobp.helloworld.rest;

import com.esotericsoftware.minlog.Log;
import com.fasterxml.jackson.databind.node.ObjectNode;

import araobp.helloworld.service.HelloWorldService;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.onosproject.rest.AbstractWebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("helloworld")
public class HelloWorldResource extends AbstractWebResource {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    HelloWorldService service;

    @GET
    public Response fetchGreering() {
     ObjectNode result = codec(String.class).encode("GreetingRegistry", this);
     return ok(result).build(); 
      
    }

    @GET
    @Path("{name}")
    public Response fetchGreeting(@PathParam("name") String name) {
     String greeting = service.fetchHelloWorld(name);
     log.info("[REST] name: {}, greeting: {}", name, greeting);
     ObjectNode result = codec(String.class).encode(name, this);
     return ok(result).build(); 
    }
}
