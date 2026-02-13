Stability and Cost Optimization Approach

This project was designed with stability, reliability, and cost awareness in mind. Since this is a DevOps-focused setup, the goal was not only to deploy the application but also to make sure it runs smoothly, can recover from failures, and does not consume unnecessary resources.

Stability Strategy

To keep the application stable and running properly, I followed these practices:

Separate environments

I created separate Kubernetes namespaces for:

dev

qa

This helps in testing features safely without affecting other environments and keeps configurations isolated.

Health checks

I used readiness and liveness probes in Kubernetes.

Liveness probe ensures the application restarts if it crashes.

Readiness probe ensures traffic is only sent when the app is fully ready.

This helps avoid downtime and improves reliability.

Resource limits

CPU and memory limits were defined in deployment files.

This prevents:

one container using all system resources

application crashes due to memory spikes

Rollback Strategy

In case something breaks after a deployment, Kubernetes allows easy rollback.

I can revert to the previous working version using:

kubectl rollout undo deployment springboot-app -n dev


Since Docker images are versioned, I can quickly move back to a stable image.

This ensures safe deployments and reduces risk.

Cost Optimization

While building this project, I tried to avoid unnecessary infrastructure usage.

Efficient resource usage

Limited CPU and memory for pods

Avoided over-provisioning

Minimal replicas

Only one replica used in dev and qa environments

Scaling planned only when needed

Local Kubernetes cluster

I used Minikube for development instead of cloud resources, which helps reduce infrastructure costs.

Monitoring Approach

Basic monitoring was done using Kubernetes and application logs.

Application monitoring

Checked logs using:

kubectl logs

Pod monitoring

Verified pod status using:

kubectl get pods
kubectl describe pod

Kafka monitoring

Verified messages using Kafka console consumer

Checked broker logs for errors

Database monitoring

Checked connectivity logs

Observed application DB interactions

In the future, monitoring tools like Prometheus and Grafana can be added for better visibility.

Future Improvements

If this system is extended further, I would implement:

Helm charts for easier deployments

Prometheus and Grafana for monitoring

Horizontal Pod Autoscaling

Terraform for infrastructure provisioning

Automated Kafka topic creation

These steps would make the system more production-ready.