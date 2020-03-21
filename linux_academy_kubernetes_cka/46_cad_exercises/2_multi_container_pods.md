# Create a Pod with two containers, both with image busybox and command "echo hello; sleep 3600". Connect to the second container and run 'ls'
- Easiest way to do it is create a pod with a single container and save its definition in a YAML file:

`kubectl run busybox --image=busybox --restart=Never -o yaml --dry-run -- /bin/sh -c 'echo hello;sleep 3600' > pod.yaml`

`kubectl create -f pod.yaml`

`kubectl exec busybox -it -- sh`