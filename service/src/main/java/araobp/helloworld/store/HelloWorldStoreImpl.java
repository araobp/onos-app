package araobp.helloworld.store;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onlab.util.KryoNamespace;
import org.onosproject.store.service.EventuallyConsistentMap;
import org.onosproject.store.service.LogicalClockService;
import org.onosproject.store.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@Service
public class HelloWorldStoreImpl implements HelloWorldStore {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private EventuallyConsistentMap<String, String> messages;

  @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
  protected StorageService storageService;
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
  protected LogicalClockService clockService; 

  @Activate
  protected void activate() {

    KryoNamespace.Builder serializer = KryoNamespace
        .newBuilder()
        .register(String.class);

    messages = storageService.<String, String> eventuallyConsistentMapBuilder()
        .withName("apps")
        .withSerializer(serializer)
        .withTimestampProvider((k, v) -> clockService.getTimestamp())
        .build();
    
    log.info("Started");
  }

  @Deactivate
  protected void deactivate() {
  }
  
  @Override
  public void put(String key, String value) {
    messages.put(key, value);
    log.info("[STORE] key: {}, value: {}", key, value);
  }

  @Override
  public String get(String key) {
    String value = messages.get(key);
    log.info("[STORE] key: {}, value: {}", key, value);
    return value;
  }
}