rootProject.name = "webcron"
include("applications:webcron-controller")
include("applications:webcron-frontend")
include("core:spring-yaml-importer")
include("clients:webcron-controller-client")
include("domains:db-webcron")
include("domains:db-quartz")