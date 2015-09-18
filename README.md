#How to make an ONOS application

[The ONOS project](http://onosproject.org/) provides a maven archetype ["onos-bundle-archetype"](
https://wiki.onosproject.org/display/ONOS/Template+Application+Tutorial) that is very cool.

##Testing onos-bundle-archetype

```
$ mvn archetype:generate -DarchetypeGroupId=org.onosproject -DarchetypeArtifactId=onos-bundle-archetype

$ cd (artifact-name)
$ mvn clean package
```

I found an OSGi bundle (jar) in the "target" folder. I just copied the bundle to Karaf's "deploy" folder and started Karaf.

```
karaf@root()> feature:install scr
karaf@root()> scr:list
ID | State  | Component Name
---------------------------------
0  | ACTIVE | araobp.AppComponent
karaf@root()> scr:deactivate araobp.AppComponent 
karaf@root()> log:tail
        :
015-09-16 18:37:26,101 | INFO  |  Component Actor | AppComponent                     | 9 - araobp.onos-app - 0.0.1.SNAPSHOT | Stopped
```
It worked on Karaf OSGi container with "scr" feature.

##OSGi Declarative Services (DS)

ONOS adopts [DS](http://wiki.osgi.org/wiki/Declarative_Services) for every OSGi bundle, and the archetype "onos-bundle-archetype" seems to support DS. Unlike OpenDaylight, you can use "raw" (or standard) APIs such as @Activate annotaion.

The archetype generated the following code:

```
package araobp;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Skeletal ONOS application component.
 */
@Component(immediate = true)
public class AppComponent {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Activate
    protected void activate() {
        log.info("Started");
    }

    @Deactivate
    protected void deactivate() {
        log.info("Stopped");
    }

}
```

##Comparison with OpenDaylight

OpenDaylight project provides ["opendaylight-startup-archetype"](https://wiki.opendaylight.org/view/OpenDaylight_Controller:MD-SAL:Startup_Project_Archetype). Recently I used the archetype and felt that it is heavily dependent on YANG and MD-SAL: APIs for datastore transactions (ACID) and RPCs. OpenDaylight is good for network management or end-to-end nework service orchestration in an multi-vendor environment: that is the reason why YANG is necessary for modeling network services.

On the other hand, ONOS developers can enjoy very standard Java APIs such as "Map.put(K key, V value)" or @Activate annotaion.

ONOS also provides two kinds of datastore:
- ECMAP (Eventually Consistent KVS supporting Java's Map interface)
- RAFT/copycat (Stronglly Consistent KVS supporting Java's Map interface)

These datastores are embedded in ONOS, so no sockets(UNIX/INET) are required to get acccess to them from an application.

ONOS is good for networking services requiring BASE for high performance and linear scalability.

##Building ONOS

###Prerequisites

- Java8
- Maven3.3.X
- $JAVA_HOME
- $KARAF_HOME

See this page: https://wiki.onosproject.org/display/ONOS/Installing+and+Running+ONOS

###Build

```
$ git clone https://gerrit.onosproject.org/onos
$ mvn clean install
```

##Note
I could not build ONOS somehow due to some error, so I refered to the following:
https://ask.opendaylight.org/question/3816/odl-conntroller-build-error-execution-default-jar-of-goal-orgapachemavenpluginsmaven-archetype-plugin22jar-failed/

