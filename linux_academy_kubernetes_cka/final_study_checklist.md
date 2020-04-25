questions I couldn't solve:

# lightning labs - passed

# mock exam 1 - passed
`kubectl logs <pod_name> --previous`

# mock exam 2
FAILED questions 7 and 8 

# mock exam 3

# practice test - pv & pvc - passed

# practice test - troubleshoot application
FAILED question 3

# practice test - troubleshoot worker node - passed

# practice test - troubleshoot control plane
FAILED multiple

# practice test - view certificates
FAILED - whats wrong with api-server? - only failed questions that shouldn't be on exam
1. `systemctl list-units | grep .service`
2. `journalctl -u etcd.service -l` - if native - not kubeadm/pod
3. `kubectl logs etcd` - not native - use kubeadm

1. `docker ps -a` - if kubectl not responding
2. `docker logs <container_id>`

# practice test - certificates API

# kubelet/etcd/proxy - checking/debugging
- how do I test this? build outline


practice
1. configmaps
2. `kubectl top`
3. TLS boostrapping
4. multi-cluster? 
    - https://www.reddit.com/r/devops/comments/b3q3hg/my_experience_on_passing_the_kubernetes_cert/

troubleshoot notes:
`kubectl get all --all-namespaces`
`systemctl status kubelet` - `systemctl start kubelet`
`kubectl cluster-info`
`/etc/kubernetes/manifests` - where you find scheduler, controller, etcd, etc. static pod yaml. 
- edit these to update them, can't deploy own yaml file w/ kubectl because they're not pods
1. get all resources all namespaces
    - is something crashing? 
    - if it has `-master` go to /etc/kubernetes/manifests - this is default spot for static pods
2. `kubectl logs <failing_pod> -n <pod_namespace>`