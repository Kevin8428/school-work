`daemonset` 
- ensures that all (or some) Nodes run a copy of a Pod. 
- you could have 3 nodes and all 3 pods get on one node (because scheduler asigns them this way). Can fix with daemonset.
- reason: every node needs 1 instance of pod - like if it's an antivirus software for nodes. it's bad if a node doesn't have one.
- As nodes are added to the cluster, Pods are added to them. As nodes are removed from the cluster, those Pods are garbage collected. Deleting a DaemonSet will clean up the Pods it created

`kubeadm` - the command to bootstrap the cluster.
`kubelet` - the component that runs on all of the machines in your cluster and does things like starting pods and containers.
`kubectl` - the command line util to talk to your cluster.


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

- `kubectl get pods -n kube-system`

- `kubectl get nodes`
    - shows `kubectl` version

- `sudo kubeadm token generate`
    - print out a randomly-generated bootstrap token that can be used with the “init” and “join” commands
    - tokens are used for establishing bidirectional trust between a node joining the cluster and a control-plane node

- `sudo kubeadm token create <token_generated> --ttl 2h --print-join-command`
    - This command will create a bootstrap token for you. You can specify the usages for this token, the “time to live” and an optional human friendly description

- `docker inspect --format '{{ .State.Pid }}' <container_id>` - get PID of container

- `nsenter -t <pid> -n ip addr` - run a command in the process' network namespace