package araobp.helloworld.web.resources;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import araobp.helloworld.service.HelloWorldService;

@Component(immediate = true)
@Service
public class GreetingServiceImpl implements GreetingService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
  HelloWorldService service;

  @Activate
  protected void activate() {
    log.info("Started");
  }

  @Deactivate
  protected void deactivate() {
    log.info("Stopped");
  }

  @Override
  public String fetchGreeting(String name) {
    String greeting = service.fetchGreeting(name);
    log.info("[GREETING] name: {}, greeting: {}", name, greeting);
    return greeting;
  }
}
