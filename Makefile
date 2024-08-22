.PHONY: default
default: | help

.PHONY: build-mvn
build-mvn: ## Build project and install to you local maven repo
	./mvnw clean install

.PHONY: run-docker
run-docker: ## Run Docker image
	docker run -p 8080:8080 inseefrlab/onyxia-api

.PHONY: help
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
