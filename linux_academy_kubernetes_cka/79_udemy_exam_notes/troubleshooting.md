# fix a worker node service
`ps -ef | grep -i kubelet`
- get process id. This also works to see if a process is running at all
`systemctl status kubelet`
- see status
`systemctl status kubelet -l`
- see status/logs
`logout`
`journalctl -u kubelet`
- shift + g go to bottom of logs
`/etc/systemd/system/kubelet.service.d`
- kubelet configuration
`kubectl cluster-info`

run these everytime:
`systemctl daemon-reload`
`systemctl restart kubelet`

`6443` - apiserver default port

`kubectl expose deployment/webapp --port=80 --target-port=80 --type=NodePort -n web --dry-run -o yaml > web-service.yaml` 


??? how to run static pods on master/worker - especially master
`/var/lib/kubelet` - WHAT IS STORED IN `/var/lib`???
`/etc/kubernetes/kubelet.conf` - WHAT IS STORED IN `/etc/kubernetes`

