package araobp.helloworld.service;

public interface HelloWorldService {
  
  public void helloWorld(final String name, final String greeting);

  public String fetchGreeting(final String name);
}
