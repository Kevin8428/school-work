TO LEARN
- resource quotas?


# Create a namespace called 'mynamespace' and a pod with image nginx called nginx on this namespace
`kubectl create ns web` - create namespace

# Create the pod that was just described using YAML
`kubectl run` - container
`kubectl run nginx --image=nginx restart=Never -n mynamespace` creates:
- a pod named `nginx-<some_hash>`
- a deployment named `nginx`
- a replicaset named `nginx-<some_hash>`

OR 

`kubectl run nginx --image=nginx --restart=Never --dry-run -o yaml > pod.yaml`
- build pod yaml
`kubectl create -f pod.yaml -n mynamespace`
- run pod yaml

# Create a busybox pod (using kubectl command) that runs the command "env". Run it and see the output
`kubectl run busybox --image=busybox --command --restart=Never -it -- env`

# Create a busybox pod (using YAML) that runs the command "env". Run it and see the output
`kubectl run busybox --image=busybox --restart=Never --dry-run -o yaml --command -- env > envpod.yaml`

# create pod and allow traffic on port 80 
`kubectl run nginx --image=nginx --restart=Never --port=80`

# set pod image:
`kubectl set image pod/nginx nginx=nginx:1.7.1`

# get pods yaml:
`kubectl get po nginx -o yaml`

# get logs about previous instance:
`kubectl logs nginx -p`

# creaate pod and set env var
`kubectl run nginx --image=nginx --restart=Never --env=var1=val1`

# Get the YAML for a new ResourceQuota called 'myrq' with hard limits of 1 CPU, 1G memory and 2 pods without creating it
`kubectl create quota myrq --hard=cpu=1,memory=1G,pods=2 --dry-run -o yaml`

# Execute a simple shell on the nginx pod
`kubectl exec -it nginx -- sh`