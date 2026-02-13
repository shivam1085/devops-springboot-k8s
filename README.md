# devops-springboot-k8s

Minimal repo for running a sample Spring Boot app with Kafka, MySQL and Zookeeper on Kubernetes (Minikube).

## Quick overview
- Kubernetes manifests: `k8s/` (environments: `dev`, `qa`, `ci`)
- App: `app/sample-spring-boot-app` (Maven + Dockerfile)
- Helper scripts: `scripts/` (includes `kafka-consumer.ps1`)

## Prerequisites
- Docker (or Minikube's Docker)
- kubectl configured to your cluster (example uses `minikube`)
- Minikube (optional, for local cluster)

## Run locally on Minikube
1. Start minikube: `minikube start`
2. Load or build the app image into Minikube (example tag used in manifests: `springboot-devops:8`):

```powershell
docker build -t springboot-devops:8 app/sample-spring-boot-app
minikube image load springboot-devops:8
```

3. Apply manifests to the `dev` namespace:

```powershell
kubectl apply -f k8s/dev -n dev
```

4. Verify pods:

```powershell
kubectl get pods -n dev
```

## Kafka topic consumer helper
To consume messages from `demo-topic` (Windows PowerShell):

```powershell
powershell -File scripts\kafka-consumer.ps1 -FromBeginning -MaxMessages 10 -Topic demo-topic -Namespace dev
```

This script finds a running Kafka pod and execs the console consumer inside it.

## Build & push image (CI / registry)
Tag and push the image to your registry, then update `k8s/dev/deployment.yaml` image to the registry URL and `kubectl apply`.

## Suggestions
- Add a POSIX consumer script `scripts/kafka-consumer.sh` for Linux/macOS users.
- Add readiness/liveness probes to `k8s/dev/deployment.yaml`.
- Add GitHub Actions to build, test, and push images (CI/CD).

## Contact
Open an issue or create a PR in this repo for changes.
