terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.27"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_key_pair" "deployer" {
  key_name   = "atividade-key"
  public_key = file("~/.ssh/id_rsa.pub")
}

resource "aws_default_vpc" "default" {
}

resource "aws_security_group" "web" {
  vpc_id      = aws_default_vpc.default.id

  ingress {
    description      = "HTTP Access"
    from_port        = 5001
    to_port          = 5001
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  ingress {
    description      = "HTTPS Access"
    from_port        = 5432
    to_port          = 5432
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  ingress {
    description      = "SSH Access"
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "web" {
  ami           = "ami-052efd3df9dad4825"
  instance_type = "t2.micro"
  key_name = aws_key_pair.deployer.key_name
  vpc_security_group_ids = [ aws_security_group.web.id ]

  # copy "/docker" folder to "/home/ubuntu/docker" folder
  provisioner "file" {
    source      = "docker"
    destination = "/home/ubuntu/docker"

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/id_rsa")
      host = self.public_ip
    }
  }

  # Install Docker
  provisioner "remote-exec" {
    inline = [
      "echo 'Installing Docker'",
      "sudo apt-get update",
      "sudo apt-get install -y ca-certificates curl gnupg lsb-release",
      "sudo mkdir -p /etc/apt/keyrings",
      "curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg",
      # ubuntu docker list
      "echo \"deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable\" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null",
      "sudo apt-get update",
      "sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-compose-plugin",
      "sudo systemctl enable docker",
      "sudo systemctl start docker",
    ]

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/id_rsa")
      host = self.public_ip
    }
  }

  # install docker-compose from plugin
  provisioner "remote-exec" {
    inline = [
      "echo 'Installing Docker Compose'",
      "sudo apt-get update",
      "sudo apt-get install -y docker-compose-plugin",
    ]

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/id_rsa")
      host = self.public_ip
    }
  }

  provisioner "remote-exec" {
    inline = [
      "echo 'Starting Docker Compose'",
      "cd ~/docker/",
      "sudo docker compose up",
    ]

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/id_rsa")
      host = self.public_ip
    }
  }

  tags = {
    Name = "Aplicação CRUD-Reactjs-Nodejs-Mysql"
    Alunos = "João e Lucas"
  }
}
