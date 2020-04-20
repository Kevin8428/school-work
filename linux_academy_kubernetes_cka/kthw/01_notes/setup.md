# Certificate Authority
- CA confirms that a cert is valid
- can validate any cert issued using CA

1. build CA to create the following:
2. client certificates
3. server certificates
4. service account key/pair
- cert used to sign service account tokens
- service account tokens are used to allow users to authenticate using service account

# distribute CA files

# kube-config
- stores info needed to connect
- stores info about cluster/user/namespace/auth
- `kubectl config set-cluster` - set location of cluster
- `kubectl config set-credentials` - set auth info (username and client cert)
- `kubectl config set-context default` - set context default
- `kubectl config use-context default` - set current context to config we provide

## generate kube-config files for the following:
1. kubelet (one for each worker node)
2. kube-proxy 
3. kube-controller-manager
4. kube-scheduler
5. admin

# Generating data encryption config and key

## data encryption config
- k8s supports `encrypting secret data at rest`
    - this means secrets are never stored as plain text on disc
    - important for security
- to do this, requires providing an `encryption key`
1. `encryption key` - generate
2. place in config file
3. copy to master servers

### generate
- used to activate data encryption when cluster is stood up
1. `ENCRYPTION_KEY=$(head -c 32 /dev/urandom | base64) cat > encryption-config.yaml << EOF`
2. create `EncryptionConfig` object and store ^ variable

# Building etcd
- distributed data store
- store data across all machines
- store on each master node

- stores cluster state info
- create pods? uses data objects stored in etcd

## install/configure etcd on master nodes
1. download etcd
2. extract
3. move to bin
    - `sudo mv etcd-v3.3.5-linux-amd64/etcd* /usr/local/bin/`
4. create directories:
    - `sudo mkdir -p /etc/etcd`
    - `sudo mkdir -p /var/lib/etcd`
5. move certificate files
    - `sudo cp ca.pem kubernetes-key.pem kubernetes.pem /etc/etcd/`
6. create `systemd unit file`
    - for etcd
```
ETCD_NAME=<cloud server hostname>
INTERNAL_IP=$(curl http://<private_ip>/latest/meta-data/local-ipv4)
INITIAL_CLUSTER=<controller 1 hostname>=https://<controller 1 private ip>:2380,<controller 2 hostname>=https://<controller 2 private ip>:2380
```
```
ETCD_NAME=kevinmdeutscher1c.mylabserver.com \
INTERNAL_IP=172.31.30.148 \
INITIAL_CLUSTER=kevinmdeutscher1c.mylabserver.com=https://172.31.30.148:2380,kevinmdeutscher2c.mylabserver.com=https://172.31.25.158:2380
```
```
ETCD_NAME=kevinmdeutscher2c.mylabserver.com
INTERNAL_IP=172.31.25.158
INITIAL_CLUSTER=kevinmdeutscher1c.mylabserver.com=https://172.31.30.148:2380,kevinmdeutscher2c.mylabserver.com=https://172.31.25.158:2380
```


CREATE UNIT FILE:
```
cat << EOF | sudo tee /etc/systemd/system/etcd.service
[Unit]
Description=etcd
Documentation=https://github.com/coreos

[Service]
ExecStart=/usr/local/bin/etcd \\
  --name ${ETCD_NAME} \\
  --cert-file=/etc/etcd/kubernetes.pem \\
  --key-file=/etc/etcd/kubernetes-key.pem \\
  --peer-cert-file=/etc/etcd/kubernetes.pem \\
  --peer-key-file=/etc/etcd/kubernetes-key.pem \\
  --trusted-ca-file=/etc/etcd/ca.pem \\
  --peer-trusted-ca-file=/etc/etcd/ca.pem \\
  --peer-client-cert-auth \\
  --client-cert-auth \\
  --initial-advertise-peer-urls https://${INTERNAL_IP}:2380 \\
  --listen-peer-urls https://${INTERNAL_IP}:2380 \\
  --listen-client-urls https://${INTERNAL_IP}:2379,https://127.0.0.1:2379 \\
  --advertise-client-urls https://${INTERNAL_IP}:2379 \\
  --initial-cluster-token etcd-cluster-0 \\
  --initial-cluster ${INITIAL_CLUSTER} \\
  --initial-cluster-state new \\
  --data-dir=/var/lib/etcd
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
```
- create a `systemd unit file` named `/etc/systemd/system/etcd.service`
- file creates a linux service for etcd
7. `sudo systemctl daemon-reload` - start service
    - makes systemd see changes
    - do this anytime you modify/add unit file
8. `sudo systemctl enable etcd`
    - start when server starts
9. `sudo systemctl start etcd`
    - start etcd service
10. `sudo systemctl status etcd`
    - get status
11. get list of etcd nodes
    - want both master nodes showing up
```
sudo ETCDCTL_API=3 etcdctl member list \
  --endpoints=https://127.0.0.1:2379 \
  --cacert=/etc/etcd/ca.pem \
  --cert=/etc/etcd/kubernetes.pem \
  --key=/etc/etcd/kubernetes-key.pem
```

# install control plane
## install control-plane binaries
- `sudo mkdir -p /etc/kubernetes/config` - directory for configs
```
wget -q --show-progress --https-only --timestamping \
  "https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kube-apiserver" \
  "https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kube-controller-manager" \
  "https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kube-scheduler" \
  "https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kubectl"
```
- install
    - `api-server`
    - `kube-controller-manager`
    - `kube-scheduler`
    - `kubectl` - don't really need, but good to interact w/ apiserver when on master node
- permissions and mode
```
chmod +x kube-apiserver kube-controller-manager kube-scheduler kubectl
sudo mv kube-apiserver kube-controller-manager kube-scheduler kubectl /usr/local/bin/
```

## api-server
- configure systemd services
### create `systemd` service for api-server
1. `sudo mkdir -p /var/lib/kubernetes/`
    - store files k8s needs

2. move files into directory
```
sudo cp ca.pem ca-key.pem kubernetes-key.pem kubernetes.pem \
  service-account-key.pem service-account.pem \
  encryption-config.yaml /var/lib/kubernetes/
```
- now api-server can find certs/configs

3. setup temp env vars
```
INTERNAL_IP=$(curl http://169.254.169.254/latest/meta-data/local-ipv4)
CONTROLLER0_IP=<private ip of controller 0>
CONTROLLER1_IP=<private ip of controller 1>
```
```
INTERNAL_IP=$(curl http://169.254.169.254/latest/meta-data/local-ipv4)
CONTROLLER0_IP=172.31.25.158
CONTROLLER1_IP=34.237.136.30
```
```
INTERNAL_IP=$(curl http://169.254.169.254/latest/meta-data/local-ipv4)
CONTROLLER0_IP=172.31.25.158
CONTROLLER1_IP=34.237.136.30
```

4. generate `systemd unit file`
```
cat << EOF | sudo tee /etc/systemd/system/kube-apiserver.service
[Unit]
Description=Kubernetes API Server
Documentation=https://github.com/kubernetes/kubernetes

[Service]
ExecStart=/usr/local/bin/kube-apiserver \\
  --advertise-address=${INTERNAL_IP} \\
  --allow-privileged=true \\
  --apiserver-count=3 \\
  --audit-log-maxage=30 \\
  --audit-log-maxbackup=3 \\
  --audit-log-maxsize=100 \\
  --audit-log-path=/var/log/audit.log \\
  --authorization-mode=Node,RBAC \\
  --bind-address=0.0.0.0 \\
  --client-ca-file=/var/lib/kubernetes/ca.pem \\
  --enable-admission-plugins=Initializers,NamespaceLifecycle,NodeRestriction,LimitRanger,ServiceAccount,DefaultStorageClass,ResourceQuota \\
  --enable-swagger-ui=true \\
  --etcd-cafile=/var/lib/kubernetes/ca.pem \\
  --etcd-certfile=/var/lib/kubernetes/kubernetes.pem \\
  --etcd-keyfile=/var/lib/kubernetes/kubernetes-key.pem \\
  --etcd-servers=https://$CONTROLLER0_IP:2379,https://$CONTROLLER1_IP:2379 \\
  --event-ttl=1h \\
  --experimental-encryption-provider-config=/var/lib/kubernetes/encryption-config.yaml \\
  --kubelet-certificate-authority=/var/lib/kubernetes/ca.pem \\
  --kubelet-client-certificate=/var/lib/kubernetes/kubernetes.pem \\
  --kubelet-client-key=/var/lib/kubernetes/kubernetes-key.pem \\
  --kubelet-https=true \\
  --runtime-config=api/all \\
  --service-account-key-file=/var/lib/kubernetes/service-account.pem \\
  --service-cluster-ip-range=10.32.0.0/24 \\
  --service-node-port-range=30000-32767 \\
  --tls-cert-file=/var/lib/kubernetes/kubernetes.pem \\
  --tls-private-key-file=/var/lib/kubernetes/kubernetes-key.pem \\
  --v=2 \\
  --kubelet-preferred-address-types=InternalIP,InternalDNS,Hostname,ExternalIP,ExternalDNS
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
```

## controller-manager
- configure systemd service
### create `systemd` service for controller-manager
1. move file needed by controller-manager
- `sudo cp kube-controller-manager.kubeconfig /var/lib/kubernetes/`
2. build `systemd unit file`
```
cat << EOF | sudo tee /etc/systemd/system/kube-controller-manager.service
[Unit]
Description=Kubernetes Controller Manager
Documentation=https://github.com/kubernetes/kubernetes

[Service]
ExecStart=/usr/local/bin/kube-controller-manager \\
  --address=0.0.0.0 \\
  --cluster-cidr=10.200.0.0/16 \\
  --cluster-name=kubernetes \\
  --cluster-signing-cert-file=/var/lib/kubernetes/ca.pem \\
  --cluster-signing-key-file=/var/lib/kubernetes/ca-key.pem \\
  --kubeconfig=/var/lib/kubernetes/kube-controller-manager.kubeconfig \\
  --leader-elect=true \\
  --root-ca-file=/var/lib/kubernetes/ca.pem \\
  --service-account-private-key-file=/var/lib/kubernetes/service-account-key.pem \\
  --service-cluster-ip-range=10.32.0.0/24 \\
  --use-service-account-credentials=true \\
  --v=2
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
```

## kube-scheduler
- configure systemd service
### create `systemd` service for kube-scheduler
1. move file needed
- `sudo cp kube-scheduler.kubeconfig /var/lib/kubernetes/`
2. create yaml config for scheduler
```
cat << EOF | sudo tee /etc/kubernetes/config/kube-scheduler.yaml
apiVersion: componentconfig/v1alpha1
kind: KubeSchedulerConfiguration
clientConnection:
  kubeconfig: "/var/lib/kubernetes/kube-scheduler.kubeconfig"
leaderElection:
  leaderElect: true
EOF
```
3. create `systemd unit file`
```
cat << EOF | sudo tee /etc/systemd/system/kube-scheduler.service
[Unit]
Description=Kubernetes Scheduler
Documentation=https://github.com/kubernetes/kubernetes

[Service]
ExecStart=/usr/local/bin/kube-scheduler \\
  --config=/etc/kubernetes/config/kube-scheduler.yaml \\
  --v=2
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
```
- using yaml file is just an alternative to passing in tons of flags

## start services
```
sudo systemctl daemon-reload
sudo systemctl enable kube-apiserver kube-controller-manager kube-scheduler
sudo systemctl start kube-apiserver kube-controller-manager kube-scheduler
```
- `sudo systemctl status kube-apiserver kube-controller-manager kube-scheduler` - verify works

## scheduler
1. copy config
- `sudo cp kube-scheduler.kubeconfig /var/lib/kubernetes/`
2. create configs file
```
cat << EOF | sudo tee /etc/kubernetes/config/kube-scheduler.yaml
apiVersion: componentconfig/v1alpha1
kind: KubeSchedulerConfiguration
clientConnection:
  kubeconfig: "/var/lib/kubernetes/kube-scheduler.kubeconfig"
leaderElection:
  leaderElect: true
EOF
```
3. create `systemd unit file`
```
cat << EOF | sudo tee /etc/systemd/system/kube-scheduler.service
[Unit]
Description=Kubernetes Scheduler
Documentation=https://github.com/kubernetes/kubernetes

[Service]
ExecStart=/usr/local/bin/kube-scheduler \\
  --config=/etc/kubernetes/config/kube-scheduler.yaml \\
  --v=2
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
```

# RBAC
- create roles/assign permissions
- kube-api needs permission to access kubelet
1. create cluster role w/ permissions and assign to `Kubernetes` user; assign to user with `ClusterRoleBinding`

2. create `ClusterRole`
```
cat << EOF | kubectl apply --kubeconfig admin.kubeconfig -f -
apiVersion: rbac.authorization.k8s.io/v1beta1
kind: ClusterRole
metadata:
  annotations:
    rbac.authorization.kubernetes.io/autoupdate: "true"
  labels:
    kubernetes.io/bootstrapping: rbac-defaults
  name: system:kube-apiserver-to-kubelet
rules:
  - apiGroups:
      - ""
    resources:
      - nodes/proxy
      - nodes/stats
      - nodes/log
      - nodes/spec
      - nodes/metrics
    verbs:
      - "*"
EOF
```
3. create `ClusterRoleBinding` to bind to user
```
cat << EOF | kubectl apply --kubeconfig admin.kubeconfig -f -
apiVersion: rbac.authorization.k8s.io/v1beta1
kind: ClusterRoleBinding
metadata:
  name: system:kube-apiserver
  namespace: ""
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: system:kube-apiserver-to-kubelet
subjects:
  - apiGroup: rbac.authorization.k8s.io
    kind: User
    name: kubernetes
EOF
```

# kube-api front end load balancer
- nginx load balancer hit api-server
1. install nginx `sudo apt-get install -y nginx`
2. `sudo systemctl enable nginx`
3. `sudo mkdir -p /etc/nginx/tcpconf.d`
4. `sudo vi /etc/nginx/nginx.conf`
5. create nginx config file
```
cat << EOF | sudo tee /etc/nginx/tcpconf.d/kubernetes.conf
stream {
    upstream kubernetes {
        server $CONTROLLER0_IP:6443;
        server $CONTROLLER1_IP:6443;
    }

    server {
        listen 6443;
        listen 443;
        proxy_pass kubernetes;
    }
}
EOF
```
6. `sudo nginx -s reload` - reload
7. `curl -k https://localhost:6443/version` - test
    - should return kubernetes version eg 1.10.2

# worker nodes
3 things to setup:
- containerd (runtime)
- kubelet
- kube-proxy

## worker binaries
- run on each worker node
1. install needed packages
- `sudo apt-get -y install socat conntrack ipset`
```
wget -q --show-progress --https-only --timestamping \
  https://github.com/kubernetes-incubator/cri-tools/releases/download/v1.0.0-beta.0/crictl-v1.0.0-beta.0-linux-amd64.tar.gz \
  https://storage.googleapis.com/kubernetes-the-hard-way/runsc \
  https://github.com/opencontainers/runc/releases/download/v1.0.0-rc5/runc.amd64 \
  https://github.com/containernetworking/plugins/releases/download/v0.6.0/cni-plugins-amd64-v0.6.0.tgz \
  https://github.com/containerd/containerd/releases/download/v1.1.0/containerd-1.1.0.linux-amd64.tar.gz \
  https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kubectl \
  https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kube-proxy \
  https://storage.googleapis.com/kubernetes-release/release/v1.10.2/bin/linux/amd64/kubelet
```
2. extract binaries and move
```
sudo mkdir -p \
  /etc/cni/net.d \
  /opt/cni/bin \
  /var/lib/kubelet \
  /var/lib/kube-proxy \
  /var/lib/kubernetes \
  /var/run/kubernetes

chmod +x kubectl kube-proxy kubelet runc.amd64 runsc

sudo mv runc.amd64 runc

sudo mv kubectl kube-proxy kubelet runc runsc /usr/local/bin/

sudo tar -xvf crictl-v1.0.0-beta.0-linux-amd64.tar.gz -C /usr/local/bin/

sudo tar -xvf cni-plugins-amd64-v0.6.0.tgz -C /opt/cni/bin/

sudo tar -xvf containerd-1.1.0.linux-amd64.tar.gz -C /
```

## configure containerd
- run on each worker

`containerd` 
- core container runtime
- runs containers in docker
- does container execution and provision

1. `sudo mkdir -p /etc/containerd/`

## configure kubelet
- run on each worker
- this is the `worker node agent`
1. move certificates and config files to correct spot
```
HOSTNAME=$(hostname)
sudo mv ${HOSTNAME}-key.pem ${HOSTNAME}.pem /var/lib/kubelet/
sudo mv ${HOSTNAME}.kubeconfig /var/lib/kubelet/kubeconfig // this is renaming hostname.kubeconfig to `kubeconfig`
sudo mv ca.pem /var/lib/kubernetes/
```
2. create config 
```
cat << EOF | sudo tee /var/lib/kubelet/kubelet-config.yaml
kind: KubeletConfiguration
apiVersion: kubelet.config.k8s.io/v1beta1
authentication:
  anonymous:
    enabled: false
  webhook:
    enabled: true
  x509:
    clientCAFile: "/var/lib/kubernetes/ca.pem"
authorization:
  mode: Webhook
clusterDomain: "cluster.local"
clusterDNS: 
  - "10.32.0.10"
runtimeRequestTimeout: "15m"
tlsCertFile: "/var/lib/kubelet/${HOSTNAME}.pem"
tlsPrivateKeyFile: "/var/lib/kubelet/${HOSTNAME}-key.pem"
EOF
```
3. create `unit file`
```
cat << EOF | sudo tee /etc/systemd/system/kubelet.service // -- create a new systemd service
[Unit]
Description=Kubernetes Kubelet
Documentation=https://github.com/kubernetes/kubernetes
After=containerd.service
Requires=containerd.service

[Service]
ExecStart=/usr/local/bin/kubelet \\ ----- run this binary to start systemd service
  --config=/var/lib/kubelet/kubelet-config.yaml \\
  --container-runtime=remote \\
  --container-runtime-endpoint=unix:///var/run/containerd/containerd.sock \\
  --image-pull-progress-deadline=2m \\
  --kubeconfig=/var/lib/kubelet/kubeconfig \\
  --network-plugin=cni \\
  --register-node=true \\
  --v=2 \\
  --hostname-override=${HOSTNAME} \\ -- 
  --allow-privileged=true
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
```

## configure kube-proxy server
- run on each worker
1. move certs
2. create config file
3. create `service unit file`

# remote cluster access and kubectl
- cloud servers change IPs
- no access to port 6443
    - therefore, setup ssl tunnel

## ssl tunnel
`ssh -L 6443:localhost:6443 user@server_public_ip`
- works as long as terminal is open
- `localhost:6443` - listen on here
- `server_public_ip:6443` - forward to here

## setup
- setup new cluster in local config for kubectl
```
kubectl config set-cluster kubernetes-the-hard-way \
  --certificate-authority=ca.pem \
  --embed-certs=true \ -- embed certs into kubeconfig
  --server=https://localhost:6443 -- traffic will forward to load balancer
```

- set credentials
```
kubectl config set-credentials admin \
  --client-certificate=admin.pem \
  --client-key=admin-key.pem
```

- set context
- context is set of data used to connect to server
- can have multiple contexts for multiple clusters
```
kubectl config set-context kubernetes-the-hard-way \
  --cluster=kubernetes-the-hard-way \
  --user=admin
```

# networking

## kubernetes networking model
- 1 virtual networking model for entire cluster
- each pod has unique ip
- each service has unique ip
    - service ips are in different range than pods

## cluster network architecture
- `cluster CIDR`
    - ip range used to assign ips to pods in the cluster
    - in this course, use CIDR `10.200.0.0/16`
    - need to set cluster CIDR flag for things like kube-controller
- `pod CIDR`
    - subset of `cluster CIDR`
    - ip range for pods on a specific worker node
    - should fall within `cluster CIDR` 
    - should NOT overlap `pod CIDR` of any other worker node
- `service cluster IP range`
    - ip range for services in the cluster
    - should not overlap with cluster CIDR range
    - in this course, use CIDR `10.32.0.0/24`

## installing weave


# DNS service
- provide DNS service for pods
- DNS server inside cluster
- configures containers to use DNS service
    - injects configuration into each container, to instruct to use DNS service

## usage
- can access services using DNS names
- can access pods using DNS
    - each pod/service has a DNS name

# deploy
1. install kube-dns
    - `kubectl create -f https://storage.googleapis.com/kubernetes-the-hard-way/kube-dns.yaml`
2. verify pod is running
    - `kubectl get pods -l k8s-app=kube-dns -n kube-system`
3. create busybox pod to test
    - `kubectl run busybox --image=busybox:1.28 --command -- sleep 3600 POD_NAME=$(kubectl get pods -l run=busybox -o jsonpath="{.items[0].metadata.name}")`
4. verify with nslookup
    - `kubectl exec -ti $POD_NAME -- nslookup kubernetes`
    - `kubernetes` is a domain name
