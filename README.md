# Baigan configuration

Baigan configuration is a easy to use configuration framework.
Baigan out-of the box uses [etcd](https://github.com/coreos/etcd) to provide distributed, highly available and reliable configuration cluster.
What makes Baigan a rockstar configuration framework ?

* *Simple*: Baigan configuration is as simple as annotating a Java class.
* *Extensible*: Extend configurations, create rules, define types that suits you.
* *Distributed*: Baigan uses etcd cluster to persis the configurations, which means that in an auto-scalable cluster setting you dont have to worry about updating configuration on each of your server isntance anymore.
 
## Prerequisites
- Java 1.8
- [etcd](https://github.com/coreos/etcd)


## Getting started

### To build project execute on root folder:

    mvn clean install -Pintegration-test

