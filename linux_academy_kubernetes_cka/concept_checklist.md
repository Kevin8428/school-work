# Checklist
1. be able to explain each
2. be able to build each

- selectors filter labels
    - you select a label
- annotations
    - record other info for informing

`kubectl exec -it webapp -- sh`
- open ternimal of conttainer
`kubectl describe ingress --namespace app-space`

`kubectl get role -n ingress-space`
`kubectl get clusterrole -n ingress-space`
`kubectl get rolebinding -n ingress-space`
`kubectl get clusterrolebinding -n ingress-space`

`watch “kubectl top node && kubectl top pod”`???????
`kubectl top pod <pod_name> --containers`

# QUESTIONS BEFORE TAKING EXAM
- what do I need to know from `systemd` and other linux commands. Why do I need them? in what cases will I use them?
    - need `systemctl` for restaring systemd unit files
- PVC - what if no label selection, matching just happens to first unbound PV that has space? how does pv bind to pod

# SHORTLIST TO LEARN
0. `kubectl expose deployment/webapp --port=80 --target-port=80 --type=NodePort -n web --dry-run -o yaml > web-service.yaml` 
    - practice setting nodeport. Need to specify type, port, targetport
0. debugging:
    - `kubectl run busybox --image=busybox --restart=Never -it -- sh` - MEMORIZE
    - learn about NETWORK POLICIES
    - `netcat` command?
    - `nc -z -v my-service 80` - open ports, don't send data
        - from inside busybox container
    - `kubectl get netpol` - network policy
0. file system - `/etc/systemd/system/some_service.service`, `/var/lib/kubelet`, `/etc/kubernetes/pki`, `/etc/kubernetes/manifests`, etc
    - learn common yaml/config file params like `--kubeconfig`, `--config`
1. RBAC
2. TLS bootstrap - certificates
    - create user with a provided csr - create a `CertificateSigningRequest`, run `cat server.csr | base64 | tr -d '\n'` and place cert in `request`
    - test cert files work, break them, and generate new ones - do several times
    - draw out CA, nodes, end users and how they all have certs and how CA has ca.key/ca.crt and it's used as the CA Authority
    - how can you tell in server logs that api-server isn't authenticating to etcd?
        - can see handshake fail, but not who fails when attempting 
    - `/etc/kubernetes/manifests/`
        - default static pod path
        - `--cert-file` learn about all of these container commands
    - `/etc/kubernetes/pki/`
    - learn what both of these paths are used for and why
    - `kubectl get logs etcd-master` - how does this work, what is etcd-master
3. learn about ELB, nginx ingress-controller
4. learn about network-overlay
5. CoreDNS - explain, diagram, etc
6. HPA - how autoscaling works in depth
7. Prometheus
8. learn about metrics-server, metrics API
9. look at udemy - anything not covered?
10. look at service files like kube-apiserver.service - what is a service file? when are they used, how to edit etc
11. kubernetes w/ aws - EKS etc
13. containerd
14. look at commands checklist eg `nslookup`, `systemctl`, `docker ps -a`
15. SECRETS - practice setting/getting


# Lightning lab questions
- what is draining a node? why use it and what does it do?
- `kubectl uncordon` - what does this do?
- there is kubelet on control plane? I thought just worker

0. learn about service networking - kubelet, kube-proxy and ip tables - DONE
0. learn about controllers like nginx contollers and deploying them - DONE
    - learn about using a service to expose an ingress
0. draw diagrams of all major concepts/objects - DONE
1. diagram and explain clusterIp, nodePort, services - DONE
- explain/diagram what the default kubernets ClusterIP is
- explain how servics are created in api-server, api-server notifies all kube-proxy agents, and what happens further
- explain ip tables and how they're updated
- https://linuxacademy.com/cp/courses/lesson/course/4018/lesson/3/module/327
2. be able to setup nodeport and clusterIP and ping both from correct places
3. the kubelet - what is it and what does it do?
4. explain CoreDNS, sketch diagram of all parts involved including: 
- ip tables 
- services/apps
- `kubelet` - FOCUS ON THIS
- `resolve.conf` - FOCUS ON THIS
- what happens when a pod is created
- explain nameserver: https://kubernetes.io/docs/tasks/administer-cluster/dns-custom-nameservers/#example
5. explain RBAC in kubernets
6. explain TLS in kubernetes
7. how to find all kubernetes daemonsets
    - `kubectl get all --all-namespaces`
8. get better w/ `kubectl exec` - when to use and why
9. pv vs volume - DONE
    - practice: mount a volume and access it inside the container, not just pod
        - practice loading data, writing data, deleting pod and accessing
    - how does a continer access pod data
    - REDO pv/pvc test on udemy
    - `postPath` - practice setting this and mounting etc
    - what needs to match for claim to work? accessMode etc
10. static (`pv`) vs dynamic storage (`storage class`)
    - practice PV and PVC
    - dynamic - happens if you specify StorageClassName
    - PVC binds to PV or storage class
11. ingress
    - 
12. SERVICE ACCOUNTS


# PV
- abstraction for underlying physical storage
- storage incluster, provisioned by admin
- it's a cluster resource just like a node
- volume dies with pod, pv does not
- PV has specs for connecting to cloud storage like EBS
- devs build PVC's, OPS builds PV

# PVC
- used by devs
- abstracts away PV
- specify resources
- connects to PV

# Controller Manager
- manages controllers

# nslookup
- get domain info/search DNS
- ``

# DNS
- `kubectl exec -it busybox  -- cat /etc/resolve.conf` - view FQDN
- `kubectl exec -it busybox  -- nslookup kubernetes`
    - `kubernetes.default.svc.cluster.local` - kubernetes service FQDN

# clusterIP vs nodeport
## clusterIP
- services build single interface/ip for others to hit - this is `clusterIP`
    - clusterIP is default service
- ip accessible inside cluster, across nodes. 2 pods on 2 nodes can talk via clusterIP
- Exposes the Service on a cluster-internal IP. Choosing this value makes the Service only reachable from within the cluster. This is the default ServiceType
- clusterIP service built by default when cluster is created. This allows pods to find each other no matter what node they are on.
- `targetPort` - where underlying app is exposted
- `port` - where service is exposed
- `selector` - how we tie service to pods

## nodePort
- Exposes the Service on each Node’s IP at a static port
- make pod accessible from outside the cluster via `nodeIP:nodePort`
- Exposes the Service on each Node’s IP at a static port (the NodePort). A ClusterIP Service, to which the NodePort Service routes, is automatically created. You’ll be able to contact the NodePort Service, from outside the cluster, by requesting <NodeIP>:<NodePort>.
- `PORT VALID RANGE IS 30000 - 32767`
- `kubectl expose pod nginx --dry-run --port=90 --target-port=91 --type=NodePort -o yaml > nginx-nodeport.yaml`
    - create nodeport from pod
    - spec.selector - apply service to pod
- `kubectl describe svc nginx`
    - can see `endpoints` of registered pods


- `service` - listens to port on node and forward to port on pod running app
    - NODEPORT SERVICE


# deployment vs svc
`--restart=Never` - prevent deployment
`--expose` - create service

## steps:
1. create pod nginx
2. create svc `kubectl expose pod nginx --port=8080 --target-port=8081 --name=nginx-svc`
3. curl pod IP and see response
4. update pod port from 80 to 8081
5. curl clusterIP for 8080 and see nginxk




