##Comparison with OpenDaylight

OpenDaylight project provides ["opendaylight-startup-archetype"](https://wiki.opendaylight.org/view/OpenDaylight_Controller:MD-SAL:Startup_Project_Archetype). Recently I used the archetype and felt that it is heavily dependent on YANG and MD-SAL: APIs for datastore transactions (ACID) and RPCs. OpenDaylight is good for network management or end-to-end nework service orchestration in an multi-vendor environment: that is the reason why YANG is necessary for modeling network services.

On the other hand, ONOS developers can enjoy very standard Java APIs such as "Map.put(K key, V value)" or @Activate annotaion.

ONOS also provides two kinds of datastore:
- ECMAP (Eventually Consistent KVS supporting Java's Map interface)
- RAFT/copycat (Stronglly Consistent KVS supporting Java's Map interface)

These datastores are embedded in ONOS, so no sockets(UNIX/INET) are required to get acccess to them from an application.

ONOS is good for networking services requiring BASE for high performance and linear scalability.

##Pros and Cons

###ODL
Pros
- N.B. APIs generated from YANG models automatically
- YANG models (incl. RPCs) being standardized by IETF
- Tree-structure data models for network configuration
- Datastore transaction support

Cons
- YANG models required also for inter-bundle communications and for dependency injection into bundles
- Imcomplete clustering capabilities
- Data persistency unsupported yet

###ONOS
Pros
- Java-centric APIs
- High-performance, high-scalability
- Two types of datastores: ECMAP and RAFT
- CLI support (based on Karaf CLI)

Cons
- N.B. APIs implemeted manually
- Data models not standardized by SDO 
- Key-Value data structure not sutable for network configuration 

