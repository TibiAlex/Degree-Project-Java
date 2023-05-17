namespace=$1

kubectl config set-context --current --namespace="$namespace";
pod_name=$(kubectl get pods --sort-by=.metadata.creationTimestamp -o jsonpath="{.items[0].metadata.name}")
status=$(kubectl get pods "$pod_name" -o jsonpath='{.status.containerStatuses[0].ready}')

SECONDS=0
until $status
do
  echo "Pod not ready yet. Waiting for $SECONDS seconds"
  sleep 30
  status=$(kubectl get pods "$pod_name" -o jsonpath='{.status.containerStatuses[0].ready}')
done
echo "Pod is running"