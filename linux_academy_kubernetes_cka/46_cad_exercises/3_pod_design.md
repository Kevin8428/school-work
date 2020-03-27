- `kubectl get po -L app` - Get the label 'app' for the pods
- `kubectl get pods -l app=v2` - get pods by label
- `kubectl get pods --show-labels` - show labels
- `kubectl label po nginx1 app-` - remove label
- `kubectl label po nginx2 app=v2 --overwrite` - Change the label

- `kubectl run nginx --image=nginx:1.7.8 --replicas=2 --port=80` - create deployment, 2 replicas, port 80