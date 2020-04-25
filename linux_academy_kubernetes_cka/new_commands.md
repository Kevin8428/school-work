kubectl top - ????
# commands not memorized
- `kubectl auth can-i update pods --as=john -n development`
    - search "auth" in kubernetes documentation
- `docker ps -a` - list all containers
- `kubectl get ep <service>` - get ips (endpoints) of a service
- `kubectl exec busybox nslookup kubernetes.default`
- `kubectl exec busybox nslookup <service_name>` - 
- `kubectl expose deploy foo --port=<new_port> --target-port=<pods/containers_port>` - expose existing deployment
- `kubectl get events | grep -i error` - 
- `kubectl get endpoints` - show ips of replica nodes
- `kubectl describe <object> --all-namespaces | grep -i <query>` - search for any word in a given object type; object eg pods, nodes
- `kubectl run nginx --image=nginx --restart=Never --port=80 --expose`
    - `--expose` 
        - this creates a `service`
        - this exposes a deployment thru the `clusterIP`
    - set port and targetPort to 80
    - `--expose` - this sets the port used by the `service`
    - `kubectl run nginx --image=nginx --restart=Never --port=80 `
        - set containerPort to 80
        - adding --expose creates service
    - `run:nginx` - selector for pods built automatically
- `kubectl edit svc nginx` - edit a service
- `kubectl get ep` - get endpoints
- `kubectl describe pods nginx | grep -i <anything>` - quick search of a resource
- `kubectl get po -L app` - Get the label 'app' for the pods
- `kubectl get pods -l app=v2` - get pods by label
- `kubectl get pods --show-labels` - show labels
- `kubectl label pods nginx1 app-` - remove label
- `kubectl label pods nginx2 app=v2 --overwrite` - Change the label
- `kubectl describe pods nginx1 | grep -i 'annotations'` - get annotation
- `kubectl annotate pods nginx1 description='my description'` - set annotation
- `kubectl annotate pods nginx1 description-` - remove annotation
- `kubectl rollout status deploy nginx` - view deployment status
- `kubectl set image pod/nginx nginx=nginx:1.7.1` - change pod image
- `kubectl set image deploy nginx nginx=nginx:1.7.9` - change deployment image
- `kubectl set image deployment/nginx-deploy <image_name>=nginx:1.17`
- `kubectl rollout history deploy nginx` - get rollout history for `nginx` deployment
- `kubectl rollout undo deploy nginx` - undo latest rollout
- `kubectl rollout undo deploy nginx --to-revision=2` - undo to revision 2
- `kubectl rollout history deploy nginx --revision=4` - details of fourth revision
- `kubectl scale deploy nginx --replicas=5` - scale deployment replicaset to 5
- `kubectl autoscale deploy nginx --min=5 --max=10 --cpu-percent=80` - autoscale
- `kubectl rollout pause deployment <name>` - pause rollout
- `kubectl delete deploy/nginx hpa/nginx` - delete deployment and hpa
- `kubectl <pod_element> pods <pod_name> <metadata_field>-`
- `kubectl create job pi  --image=perl -- perl -Mbignum=bpi -wle 'print bpi(2000)'` - create job
- `kubectl run busybox --image=busybox --restart=OnFailure --schedule="*/1 * * * *" -- /bin/sh -c 'date; echo Hello from the Kubernetes cluster'` - cronjob
- `kubectl get cj` - get cronjob
- `kubectl create cm config --from-literal=foo=bar` - create configmap
- `kubectl get cm config` - get configmap
- `kubectl delete cm config` - delete configmap
- `kubectl create cm configmap2 --from-file=config.txt` - from file
- `kubectl exec -it <pod_name> -- <terminaal commands eg 'ls -la'>`
- `kubectl run nginx --image=nginx --restart=Never --requests='cpu=100m,memory=256Mi' --limits='cpu=200m,memory=512Mi'` - request/limit
- `kubectl create secret generic <name> --from-literal=password=mypass` - write secret
- `kubectl get secret mysecret2 -o yaml` - view secret
    - `echo <data.file_name> | base64 -d` - human readable
- `kubectl run nginx --image=nginx --serviceaccount=myuser` - specify service account for pod



- `ip addr add 192.168.50.5 dev eth0` - Assign a IP Address to Specific Interface
- `ip route add <destination_io>/24 via <router_ip_for_network> dev eth0` - add route to ip route table
- `ip route add 0.0.0.0 via <router_ip_for_network>` - if server doesn't have a ip IP address registered in ip routes table, send to outside world.


# pod, deployment, service
- `--expose` creates a service, `--restart=Never` prevents a deployment - just builds pod
    - pod only: `--restart=Never`
    - pod + deployment: `kubectl run <name> --image=<image>`
    - pod + deployment + service: `kubectl run <name> --image=<image> --expose`

**exposing a deployment creates a service**
`kubectl expose deploy foo --port=6262 --target-port=8080`
- a deployment just specifies container ports since it's just an abstraction of pods
- exposing the deployment creates a service, which specifies the source port, and the container/pod port (target-port)
- 

```
apiVersion: v1
kind: Pod
metadata:
  annotations:
    description: update
  creationTimestamp: "2020-03-20T12:06:09Z"
  labels:
    app: v1
  name: nginx1
  namespace: default
  resourceVersion: "6006"
  selfLink: /api/v1/namespaces/default/pods/nginx1
  uid: 2f8ed5f5-6aa3-11ea-a3ae-0285534f36cb
```


# for work
- `kubectl rollout pause deployment <name>`
    - canary deployments?
    - create single pod, but not all of them
    - all old pods still run
    - good for canary release
- `kubectl exec -it...` - faster way to access terminal?
    - `kubectl exec -it pod -n web --sh` - open shell
    - incorporate to devops-tools?
- `kubectl describe csr <csr_name>`
    - view details of certificate signing request
- `kubectl get csr <csr_name> -o yaml`
    - view certificate
- `kubectl get csr <csr_name> -o jsonpath='{.status.certificate}' | base64 --decode`
    - extract/decode cert using json path
- `readonlyrootfilesystem`
    - can set this to make sure only write to datastore, not local filesystem
        - local FS is ephemeral and will be removed
- can use List instead of `--` to separate multiple objects written to single file

# TODO
1. create list of all k8s components/objects. Write extensive features/functions and have notes on each. Be able to build/edit/describe each in full.
2. pvc vs pv vs storageClass
    - what is a volume mount?
3. process vs container?
4. kubelet and `swap`??
5. what is flannel? what is it used for? learn about flannel
6. coredns - learn more about
7. rollouts - learn more about
8. learn what objects use `kubectl create` vs `kubectl run`
9. why use a job? how is it different?
10. get better at configMaps and using `env` vs `envFrom`, `configMapKeyRef` vs `configMapRef`
11. learn more about volumes - practice setting/mounting many times
12. learn way more about HPA, ingress, nginx
13. practice liveness/readiness - set initialDelay, periodSeconds, 
14. when to use `-it`, `sh`, `-c`
    - do `-it` if you need to keep terminal open
    - do `-- sh -c` if you want to run a bash commanad once after build
15. learn all about metrics-server
    - get very familiar with `kubectl top nodes`
16. cover secrets / configmaps, rbac - this a big part of the exam
17. when to use `edit` - what objects can't you edit the yaml?
18. learn `clusterIP` vs `nodePort`
19. learn about dns and how you can hit a pod/service by curling `some-service:8080` inside the cluster
20. practice exposing pods thru services:
    - https://github.com/dgkanatsios/CKAD-exercises/blob/master/f.services.md#user-content-create-a-service-that-exposes-the-deployment-on-port-6262-verify-its-existence-check-the-endpoints
21. learn about `networkPolicy`



# volume
- separate object defined within the pod
- associated with pod, but mounted into container at specified path
- can be mounted into two pods at different paths, or not mount to a container
- `cloud volumes` - create yaml that allows pod to connect to outside storage like aws elastic block service

## create and mount volume:
```
  volumes:
  - name: myvolume
    emptyDir: {}
  - args:
    - /bin/sh
    - -c
    - sleep 3600
    image: busybox
    name: busybox2different from the first container's name!
    volumeMounts:
    - name: myvolume
      mountPath: /etc/foo
```


# secrets
- a collection of key:value pairs
    - value can be contents of a file - eg cert_1:<cert.pem>
- place `secret` into a `pod` using a `volume`
    - inside pod spec, state the secret, and where to mount inside container
- can mount from secret to env var as well
- secret contents are stored in `etcd` unencrypted
    - anyone who has access to `etcd` has access to secrets
        - recent solutions are using third party to encrypt
            - still, anyone w/ access to api-server have access to secrets, need to protect this too

# storage class and persistent volume
- both act as abstraction to underlying physical storage
    - fileStorage, ebs, efs, nfs, etc
- **inside PVC - if you provide a storageClassName you will connect to a storaage class, which uses dynamic storage**
    - if you try to connecto to storage class (sc) but one does not exist, pvc will connect to pv
    - `static` - pv's use static storage
    - `dynamic` - sc's use dynamic storage
    - pvc connects to one of these ^
    - pvc connection to `persistentVolume` or `storageClaass` are both done thru a `BIND`

# storage class
- `kubectl get sc`

# persistent volume
- volume plugin used to connect to one of physical storarge - piece of storage provisioned by admin 
- can map to something like azure disk, nfs, etc - persistent volume types
- live longer than pod - if pod/machine dies, volume stays alive
eg
- 2 machines. Pod A is on machine 1. If 1 dies, pod goes to machine 2
    - and volume moves too

- mount pv to /etc/foo:
```
kind: PersistentVolume
apiVersion: v1
metadata:
  name: myvolume
spec:
  storageClassName: normal
  capacity:
    storage: 10Gi
  accessModes:
    - ReadWriteOnce
    - ReadWriteMany
  hostPath:
    path: /etc/foo
```

# persistent volume claim
- responsible for saying 'I want 10GB of disk'
    - if replicaSet calls for 3 pods, 3 10GB persistent volumes are created
        - this allows for replication
- `storageClassName` - type of provisioning - static or dynamic
    - **if you provide a storageClassName you will connect to a storaage class, which uses dynamic storage**
    - `static` - persistent volume
    - `dynamic` - storage classes use dynamic storage
    - pvc connects to one of these ^
    - pvc connection to `persistentVolume` or `storageClaass` are both done thru a `BIND`
- `abstraction` of volumes
    - define size of the volume
    - uses `selector` to find/locate/use underlying pv
    - links between applications and storage system
    - different clusters can use different disk products
        - **can write template that will operate across clouds or even to on prem**
- `BINDING`
    - once bound, pvc claims are exclusive - cannot bind two pvcs to a pv
    - one pod can't have two pvc's talk to a pv
    - you can have two pods connecting to same pvc tho

# volumeMount
```
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: nginx
  name: nginx
spec:
  volumes: # add a volumes list
  - name: myvolume # just a name, you'll reference this in the pods
    configMap:
      name: cmvolume # name of your configmap
  containers:
  - image: nginx
    imagePullPolicy: IfNotPresent
    name: nginx
    resources: {}
    volumeMounts: # your volume mounts are listed here
    - name: myvolume # the name that you specified in pod.spec.volumes.name
      mountPath: /etc/lala # the path inside your container
  dnsPolicy: ClusterFirst
  restartPolicy: Never
status: {}
```

# configMap
- decouple configuration artifacts from image content to keep containerized applications portable
- can generate from yaml or directory or cli literal value

```
spec:
  containers:
  - image: nginx
    imagePullPolicy: IfNotPresent
    name: nginx
    resources: {}
    env:
    - name: option # name of the env variable
      valueFrom:
        configMapKeyRef:
          name: options # name of config map
          key: var5 # name of the entity in config map
```

or 
```
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: nginx
  name: nginx
spec:
  containers:
  - image: nginx
    imagePullPolicy: IfNotPresent
    name: nginx
    resources: {}
    envFrom: # different than previous one, that was 'env'
    - configMapRef: # different from the previous one, was 'configMapKeyRef'
        name: anotherone # the name of the config map
  dnsPolicy: ClusterFirst
  restartPolicy: Never
status: {}
```

# pod and svc DNS
## service
- `my-svc.my-namespace.svc.cluster-domain.example` - fully qualified domain name
## pod
- `hostname.subdomain.my-namespace.svc.cluster-domain.example` - pod

# service accounts
- provides an identity for processes that run in a Pod
- humans authenticate with `api-server` as a given `User Account` (typically `admin`)
- processes inside containers can access `api-server` as a given `Service Account` (typically `default`)

# services
- have `ip`, `dns`, `port`
- persist ip address since node/pod ip addresses can change
- send service to api-server with kubectl

# network policiy
```
kind: NetworkPolicy
apiVersion: networking.k8s.io/v1
metadata:
  name: access-nginx # pick a name
spec:
  podSelector:
    matchLabels:
      run: nginx # selector for the pods
  ingress: # allow ingress traffic
  - from:
    - podSelector: # from pods
        matchLabels: # with this label
          access: granted
```
- only aallow pods with labels `access:granted` to talk to pods with labels `run:nginx`

# job
- A job in Kubernetes is a supervisor for pods carrying out batch processes
    - processes that runs for a certain time to completion
        - eg process: a calculation or a backup operation.
- A Job creates one or more Pods and ensures that a specified number of them successfully terminate. 
- As pods successfully complete, the Job tracks the successful completions. 
- When a specified number of successful completions is reached, the task (ie, Job) is complete. 
- Deleting a Job will clean up the Pods it created.

# daemonset
- ensures that all (or some) Nodes run a copy of a Pod. 
- you could have 3 nodes and all 3 pods get on one node (because scheduler asigns them this way). Can fix with daemonset.
- reason: every node needs 1 instance of pod - like if it's an antivirus software for nodes. it's bad if a node doesn't have one.
- As nodes are added to the cluster, Pods are added to them. As nodes are removed from the cluster, those Pods are garbage collected. Deleting a DaemonSet will clean up the Pods it created

# security context
## run as user
```
spec:
  securityContext: # insert this line
    runAsUser: 101 # UID for the user
```
## capabilities
```
spec:
  containers:
  - image: nginx
    imagePullPolicy: IfNotPresent
    name: nginx
    securityContext: # insert this line
      capabilities: # and this
        add: ["NET_ADMIN", "SYS_TIME"] # this as well
```

# nodeport
- Exposes the Service on each Node’s IP at a static port (the NodePort)
- reserve a port on all nodes in a cluster

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

# common commands
`kubectl edit deploy nginx -n web` - edit any resource!
`kubectl get all --all-namespaces` or `kubectl get all -o wide --all-namespaces`
`kubectl get pods --all-namespaces`
`kubectl get rs -n web` - get replicaset for namespace
`kubectl run busybox --image=busybox --rm -it --restart=Never -- sh` - start a busybox pod to be able to ping other pod directly
`wget -qO- <pod_ip_address>:<pod_port>` - access the pod directly via its container port

`kubectl label po nginx2 app=v2 --overwrite` - overwrite any existing configuration

# ALL COMMANDS
**build pod vs build deployment**
- `--restart=Never` - build pod, not deployment

- `kubectl explain pod.spec`

- `kubectl get all --all-namespaces`

- `systemctl status docker` - is docker service active/running?
    - `systemctl enable docker` - if not running
    - `systemctl start docker` - start

- `kubectl config view` - check credentials

- `kubectl exec -it group-context -c <container_name> sh`
    - connect to one container by name and open shell

- `kubectl get pv`
    - short for `kubectl get persistentvolumes`

- `kubectl get ep`
    - shorthand for `kubectl get endpoints`

- `kubectl get --raw /apis/metrics.k8s.io/`
    - raw???

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
        - operator: equals
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



# resource types
Resource Name
bindings           
componentstatuses
configmaps
endpoints
limitranges
namespaces
nodes
persistentvolumeclaims
persistentvolumes
pods
podtemplates
replicationcontrollers
resourcequotas
secrets
serviceaccounts
services
mutatingwebhookconfigurations
validatingwebhookconfigurations
customresourcedefinitions
apiservices
controllerrevisions
daemonsets
deployments
replicasets
statefulsets
tokenreviews
localsubjectaccessreviews
selfsubjectaccessreviews
selfsubjectrulesreviews
subjectaccessreviews
horizontalpodautoscalers
cronjobs
jobs
certificatesigningrequests
leases
events
ingresses
networkpolicies
poddisruptionbudgets
podsecuritypolicies
clusterrolebindings
clusterroles
rolebindings
roles
priorityclasses
csidrivers
csinodes
storageclasses
volumeattachments