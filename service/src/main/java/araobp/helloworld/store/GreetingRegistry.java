package araobp.helloworld.store;

public interface GreetingRegistry {
  
  public void put(String key, String value);

  public String get(String key);

}
