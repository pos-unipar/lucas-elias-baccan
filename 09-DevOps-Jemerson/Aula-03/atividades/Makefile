all: start

start:
	docker-compose -f projeto/docker-compose.yml up --build

infra-apply:
	cd terraform && terraform init
	cd terraform && terraform apply

infra-destroy:
	cd terraform && terraform destroy

infra-install:
	cd ansible && ansible-playbook -i inventory.yaml playbook.yaml
