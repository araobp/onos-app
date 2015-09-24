package araobp.helloworld.store;

public interface HelloWorldStore {
  
  public void put(String key, String value);

  public String get(String key);

}
