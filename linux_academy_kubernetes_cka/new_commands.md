# for work
- canary deployments?
- `kubectl exec -it...` - faster way to access terminal?
    - `kubectl exec -it pod -n web --sh` - open shell
    - incorporate to devops-tools?

# TODO
1. create list of all k8s components/objects. Write extensive features/functions and have notes on each. Be able to build/edit/describe each in full.
2. pvc vs pv vs storageClass
    - what is a volume mount?

# daemonset
- ensures that all (or some) Nodes run a copy of a Pod. 
- you could have 3 nodes and all 3 pods get on one node (because scheduler asigns them this way). Can fix with daemonset.
- reason: every node needs 1 instance of pod - like if it's an antivirus software for nodes. it's bad if a node doesn't have one.
- As nodes are added to the cluster, Pods are added to them. As nodes are removed from the cluster, those Pods are garbage collected. Deleting a DaemonSet will clean up the Pods it created

# nodeport
- Exposes the Service on each Node’s IP at a static port (the NodePort)

# ClusterIP 
- Exposes the Service on a cluster-internal IP. Choosing this value makes the Service only reachable from within the cluster. This is the default ServiceType.

# endpoints
- object in api-server
- created with service
- keeps cache of IP addresses of the pods in the service

# configMap
- allows you to decouple configuration artifacts from image content to keep containerized applications portable


`kubeadm` - the command to bootstrap the cluster.
`kubelet` - the component that runs on all of the machines in your cluster and does things like starting pods and containers.
`kubectl` - the command line util to talk to your cluster.

# COMMANDS

- `kubectl config view` - check credentials

- `kubectl get pv`
    - short for `kubectl get persistentvolumes`

- `kubectl exec configmap-volume-pod -- ls /etc/config`
    - view configs (eg environment variables for a pod)
- `kubectl exec configmap-volume-pod -- ls /etc/config/key1`
    - view configs value

# rollout
- `kubectl rollout pause deployment <name>`
    - create single pod, but not all of them
    - all old pods still run
    - good for canary release
- `kubectl rollout resume deployment <name>`
    - do full rollout
- `kubectl rollout status deployments <name>` - view deployment status

# taints and toleration
- `kubectl taint node <node_name> node-type=prod:NoSchedule`
    - `node-type=prod:NoSchedule` => key-operator-value-effect
        - key: node-type
        - operator: equuals
        - value: prod
        - effect: NoSchedule
- `kubectl get pods -o wide -w`
    - watch live changing events!!
- `kubectl get pods --all-namespaces -o wide`
    - get pods in master and worker nodes
        - by default typically only shows default namespace unless specify `all-namespaces`

- `kubectl get pods --all-namespaces --show-labels -o wide`
    - get labels for etcd pod on master node

- `kubectl version --short`
    - shows version of client and server `kubelet`/`kubectl`

- `kubectl describe nodes`
    - kubelet version
    - kube-proxy version 

- `kubectl get services`
    - `EXTERNAL-IP` - if <none>, service is only accessible internally

- `kubectl get pods -n kube-system`

- `kubectl get endpoints`
- `sudo iptables-save | grep KUBE | grep nginx`
    - view ip tables for Service "nginx"

- `kubectl get nodes`
    - shows `kubectl` version

# tokens and networking

- `sudo kubeadm token generate`
    - print out a randomly-generated bootstrap token that can be used with the “init” and “join” commands
    - tokens are used for establishing bidirectional trust between a node joining the cluster and a control-plane node

- `sudo kubeadm token create <token_generated> --ttl 2h --print-join-command`
    - This command will create a bootstrap token for you. You can specify the usages for this token, the “time to live” and an optional human friendly description

- `docker inspect --format '{{ .State.Pid }}' <container_id>` - get PID of container

- `nsenter -t <pid> -n ip addr` - run a command in the process' network namespace

- `kubectl exec busybox -- nslookup <name>` - 