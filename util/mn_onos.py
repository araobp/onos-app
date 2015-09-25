#!/usr/bin/python

from mininet.net import Mininet
from mininet.node import Controller, OVSKernelSwitch, RemoteController
from mininet.cli import CLI
from mininet.log import setLogLevel, info
import sys

def network(ip1, ip2, ip3):

   net = Mininet(controller=RemoteController, switch=OVSKernelSwitch)

   # Secure Channels to ONOS nodes
   c1 = net.addController('c1', controller=RemoteController, ip=ip1, port=6633)
   c2 = net.addController('c2', controller=RemoteController, ip=ip2, port=6633)
   c3 = net.addController('c3', controller=RemoteController, ip=ip3, port=6633)

   # Hosts
   h1 = net.addHost( 'h1', ip='10.0.0.1' )
   h2 = net.addHost( 'h2', ip='10.0.0.2' )
   h3 = net.addHost( 'h3', ip='10.0.0.3' )
   h4 = net.addHost( 'h4', ip='10.0.0.4' )
   h5 = net.addHost( 'h5', ip='10.0.0.5' )
   h6 = net.addHost( 'h6', ip='10.0.0.6' )
   h7 = net.addHost( 'h7', ip='10.0.0.7' )
   h8 = net.addHost( 'h8', ip='10.0.0.8' )

   # Switches
   s1 = net.addSwitch( 's1' )
   s2 = net.addSwitch( 's2' )
   s3 = net.addSwitch( 's3' )
   s4 = net.addSwitch( 's4' )
   s5 = net.addSwitch( 's5' )
   s6 = net.addSwitch( 's6' )

   # Links among switches
   s1.linkTo( s2 )
   s1.linkTo( s3 )
   s4.linkTo( s2 )
   s4.linkTo( s3 )
   s4.linkTo( s6 )
   s5.linkTo( s6 )
   s5.linkTo( s3 )

   # Links between switchs and hosts
   s1.linkTo( h1 )
   s1.linkTo( h2 )
   s2.linkTo( h3 )
   s2.linkTo( h4 )
   s5.linkTo( h5 )
   s5.linkTo( h6 )
   s6.linkTo( h7 )
   s6.linkTo( h8 )

   # Mininet start
   net.build()
   c1.start()
   c2.start()
   c3.start()
   s1.start([c1,c2,c3])
   s2.start([c1,c2,c3])
   s3.start([c1,c2,c3])
   s4.start([c1,c2,c3])
   s5.start([c1,c2,c3])
   s6.start([c1,c2,c3])
   net.start()
   net.staticArp()
   CLI( net )
   net.stop()

if __name__ == '__main__':

    print('Mininet for ONOS cluster with three nodes')

    ip1 = sys.argv[1]
    ip2 = sys.argv[2]
    ip3 = sys.argv[3]

    setLogLevel( 'info' )
    network(ip1, ip2, ip3)

