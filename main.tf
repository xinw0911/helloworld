terraform {
    backend "s3" {
        bucket = "dummy"
        key    = "dummy"
        region = "us-west-2"
    }
}
provider "aws" {

}
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 6.14.1"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.27.0"
    }
    helm = {
      source  = "hashicorp/helm"
      version = "~> 2.7.1"
    }
  }
  # backend "s3" {
  #   bucket = "yagr-tfstate-log-us"
  #   key    = "tfc/observability/blog/python-apm-demo"
  #   region = "us-east-1"
  # }
}
resource "aws_kinesis_stream" "apm_test_stream" {
  #checkov:skip=CKV_AWS_43:demo only, not encryption is needed
  #checkov:skip=CKV_AWS_185:demo only, not encryption is needed
  name             = "apm_test2"
  shard_count      = 2
}

resource "aws_sqs_queue" "apm_test_queue_" {
  #checkov:skip=CKV_AWS_27:demo only, not encryption is needed
  name                      = "apm_test2_"
  delay_seconds             = 100
  max_message_size          = 2048
  message_retention_seconds = 86400
  receive_wait_time_seconds = 19
}

resource "aws_dynamodb_table" "test_2_table" {
  #checkov:skip=CKV2_AWS_16:demo only, autoscaling is not needed
  #checkov:skip=CKV_AWS_119:demo only, no encryption is needed

  name           = "test5newtable"
  billing_mode   = "PROVISIONED"
  read_capacity  = 3
  write_capacity = 4
  hash_key       = "id"

  point_in_time_recovery {
   enabled = true
  }

  # server_side_encryption {
  #   enabled     = true
  # }

  attribute {
    name = "id"
    type = "S"
  }

}

module "iam_role_inline_policy" {
  source = "terraform-aws-modules/iam/aws//modules/iam-role"

  name = "test-role-module-inline-policy"

  create_instance_profile = true

  trust_policy_permissions = {
    ec2 = {
      effect = "Allow"
      actions = [
        "sts:AssumeRole"
      ]
      principals = [{
        type        = "Service"
        identifiers = ["ec2.amazonaws.com"]
      }]
    }
  }

  create_inline_policy = true
  inline_policy_permissions = {
    S3ReadAccess = {
      effect = "Allow"
      actions = [
        "s3:GetObject",
        "s3:ListBucket"
      ]
      resources = [
        "arn:aws:s3:::example-bucket",
        "arn:aws:s3:::example-bucket/*"
      ]
    }
  }
}
