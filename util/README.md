#Running a cluser of three ONOS nodes

Refer to the following wiki page: https://wiki.onosproject.org/display/ONOS/Running+the+published+Docker+ONOS+images

##Cluster config

```
$ onos-form-cluster <ip1> <ip2> <ip3> 
```

For example,
```
$ onos-form-cluster 172.17.0.1 172.17.0.2 172.17.0.3
```
###Mininet

Run this script to start mininet:
```
$ mn_onos.py <ip1> <ip2> <ip3>
```

For example,
```
$ mn_onos.py 172.17.0.1 172.17.0.2 172.17.0.3
```

