/*
 * Copyright 2014 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package araobp.helloworld.app;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import araobp.helloworld.service.HelloWorldService;

/**
 * Skeletal ONOS application component.
 */
@Component(immediate = true)
@Service(HelloWorldApp.class)
public class HelloWorldApp {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String[] MESSAGES = {"Hello ONOS!", "How are you?"};
    
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    private HelloWorldService helloWorldService;

    @Activate
    protected void activate() {
        log.info("Started");
        Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              Thread.sleep(3000);
              helloWorldService.helloWorld("1st", MESSAGES[0]);
              helloWorldService.helloWorld("2nd", MESSAGES[1]);
              log.info("[APP] hello world service has been called");
              Thread.sleep(3000);
              log.info("[APP] 2nd message fetched: {}",
                  helloWorldService.getMessage("2nd"));
              log.info("[APP] 1st message fetched: {}",
                  helloWorldService.getMessage("1st"));
            } catch (InterruptedException e) {
              log.error("Unable to run a thread", e);
            }
          }
        });
        thread.start();
    }
    
    @Deactivate
    protected void deactivate() {
        log.info("Stopped");
    }

}
