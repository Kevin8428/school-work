`master - CASE`
- never contains pods or run application components
- can be replicatetd for `high availability` - recommended
- executs and follows instructions according to spec
1. controller-manager
    - maintains cluster. Handles failures, replicating components, keep correct # pods etc.
2. api-server
    - exposes api
    - what all other components reach out to
3. scheduler
    - sounds like same as controller-manager
4. etcd
    - storage of cluster configs

`worker - CCC`
- runs app
1. cubelet
    - 1 per node
    - run containers on node
    - talks to api-server
    - point to `container runtime`
    - tells docker to pull images from registry
2. cube-proxy
    - 1 per node
    - load-balances traffic between app components
3. container-runtime
    - 1 per node
    - program that runs, like docker, rocket etc.
0. pod
    - can have one or more images inside the pod on a worker
