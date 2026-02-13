param(
    [string]$Topic = "demo-topic",
    [int]$MaxMessages = 10,
    [switch]$FromBeginning,
    [string]$Namespace = "dev",
    [string]$Label = "app=kafka"
)

# Find a running kafka pod
$pod = kubectl get pods -n $Namespace -l $Label -o jsonpath="{.items[0].metadata.name}"
if (-not $pod) {
    Write-Error "No pod found with label '$Label' in namespace '$Namespace'"
    exit 2
}

$fromBeginningArg = ''
if ($FromBeginning) { $fromBeginningArg = '--from-beginning' }

Write-Host "Using pod: $pod`nRunning consumer for topic '$Topic' (max $MaxMessages)"

# Build the command
$cmd = "kafka-console-consumer --bootstrap-server kafka-service:9092 --topic $Topic $fromBeginningArg --max-messages $MaxMessages"

kubectl exec -it $pod -n $Namespace -- bash -c $cmd
