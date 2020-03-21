/////////////////////////////////////////////////////////////////////////////////
///////////////////////////////// exam part 1 ///////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

////////////////////////////// create deployment ////////////////////////////////
1. create namespaace
2. create deployment
3. create service from deployment

`kubectl create ns web` - create namespace
`kubectl run` - container
`kubectl run nginx --image=nginx restart=Never -n mynamespace` creates:
- a pod named `nginx-<some_hash>`
- a deployment named `nginx`
- a replicaset named `nginx-<some_hash>`
`kubectl run nginx --image=nginx --restart=Never --dry-run -o yaml > pod.yaml`
- build pod yaml
`kubectl create -f pod.yaml -n mynamespace`
- run pod yaml

`kubectl run webapp --image=linuxacademycontent/podofminerva:latest --port=80 --replicas=3 -n web` - create deployment

`kubectl expose deployment/webapp --port=80 --target-port=80 --type=NodePort -n web --dry-run -o yaml > web-service.yaml` 
- create service from deployment
    - `--dry-run` - preview objet without sending

////////////////////////////// notes ////////////////////////////////
`kubectl get all --all-namespaces` - get everything
`kubectl edit <object> <object_name> -n <namespace>` - edit object

`kubectl get <object> <object_name> -o yaml -n <namespace>` - output to yaml
    eg `kubectl get deployment webapp -o yaml -n web`


`kubectl run` - start running 1 or more instances of a container image on your cluster

`kubectl run busybox --image=busybox --rm -it --restart=Never -- sh` - create temporary pod w/ shell to container




## Create a deployment named `webapp` in the `web` namespace and verify connectivity:
`kubectl run webapp --image=linuxacademycontent/podofminerva:latest --port=80 --replicas=3 -n web` 
^^ builds deployment: 
- 
```
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  annotations:
    deployment.kubernetes.io/revision: "1"
  creationTimestamp: "2020-03-18T23:53:30Z"
  generation: 1
  labels:
    run: webapp
  name: webapp
  namespace: web
  resourceVersion: "11103"
  selfLink: /apis/extensions/v1beta1/namespaces/web/deployments/webapp
  uid: ab993f74-6973-11ea-ac6a-0e6661e947bd
spec:
  progressDeadlineSeconds: 600
  replicas: 3
  revisionHistoryLimit: 10
  selector:
    matchLabels:
      run: webapp
  strategy:
    rollingUpdate:
      maxSurge: 25%
      maxUnavailable: 25%
    type: RollingUpdate
  template:
    metadata:
      creationTimestamp: null
      labels:
        run: webapp
    spec:
      containers:
      - image: linuxacademycontent/podofminerva:latest
        imagePullPolicy: Always
        name: webapp
        ports:
        - containerPort: 80
          protocol: TCP
        resources: {}
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
      dnsPolicy: ClusterFirst
      restartPolicy: Always
      schedulerName: default-scheduler
      securityContext: {}
      terminationGracePeriodSeconds: 30
status:
  availableReplicas: 3
  conditions:
  - lastTransitionTime: "2020-03-18T23:53:32Z"
    lastUpdateTime: "2020-03-18T23:53:32Z"
    message: Deployment has minimum availability.
    reason: MinimumReplicasAvailable
    status: "True"
    type: Available
  - lastTransitionTime: "2020-03-18T23:53:30Z"
    lastUpdateTime: "2020-03-18T23:53:32Z"
    message: ReplicaSet "webapp-6b75f6fbf7" has successfully progressed.
    reason: NewReplicaSetAvailable
    status: "True"
    type: Progressing
  observedGeneration: 1
  readyReplicas: 3
  replicas: 3
  updatedReplicas: 3
```
- set nodeport:
```
apiVersion: v1
kind: Service
metadata:
  creationTimestamp: null
  labels:
    run: webapp
  name: web-service
  namespace: web
spec:
  ports:
  - nodePort: 30080
    port: 80
    protocol: TCP
    targetPort: 80
  selector:
    run: webapp
  type: NodePort
status:
  loadBalancer: {}
```