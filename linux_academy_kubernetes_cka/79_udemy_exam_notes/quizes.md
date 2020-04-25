`ip addr` - get ip range
`ps -aux | grep kube-api` - IP Range configured for the services
`kubectl logs kube-proxy-<hash> -n kube-system` - get type of proxy for kube-proxy
`kubectl describe configmap coredns -n kube-system` - get root domain/zone for cluster

`kubernetes cluster.local in-addr.arpa ip6.arpa`
- brush up on what each of these mean, especially `cluster.local`




# kubeadm
- setup multi-node cluster w/ master/workers

## upgrade master
1. `apt-mark unhold kubeadm`
2. `apt-get update && apt-get install -y kubeadm=1.17.0-00`
3. `apt-mark hold kubeadm`
4. `kubectl drain <cp-node-name> --ignore-daemonsets` - drain master
5. `sudo kubeadm upgrade plan` - upgrade kubeadm 
6. `kubeadm upgrade apply v1.17.0` - apply upgrade
7. `apt install kubelet=1.17.0-00` - upgrade kubelet
8. `apt-mark hold kubeadm` - prevent auto upgrades
9. `kubectl uncordon <cp-node-name>` - make schedulable

## upgrade worker
1. drain worker
2. 
```
apt-mark unhold kubelet kubectl && \
apt-get update && apt-get install -y kubelet=1.18.x-00 kubectl=1.18.x-00 && \
apt-mark hold kubelet kubectl
```
3. `sudo systemctl restart kubelet`
4. uncordon