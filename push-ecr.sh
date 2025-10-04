#!/usr/bin/env bash

# Enhanced Java Calculator ECR Push Script
export REPOSITORY_PREFIX=${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com
export REPOSITORY_NAME=gh/repo

echo "🔐 Logging into ECR..."
aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${REPOSITORY_PREFIX}

echo "📦 Creating ECR repository if it doesn't exist..."
aws ecr create-repository --repository-name ${REPOSITORY_NAME} --region ${REGION} --no-cli-pager || true

echo "🏗️  Building Docker image..."
docker build -t enhanced-calculator . --no-cache

echo "🏷️  Tagging Docker images..."
docker tag enhanced-calculator:latest ${REPOSITORY_PREFIX}/${REPOSITORY_NAME}:latest
docker tag enhanced-calculator:latest ${REPOSITORY_PREFIX}/${REPOSITORY_NAME}:${COMMIT_SHA}

echo "🚀 Pushing Docker images to ECR..."
docker push ${REPOSITORY_PREFIX}/${REPOSITORY_NAME}:latest
docker push ${REPOSITORY_PREFIX}/${REPOSITORY_NAME}:${COMMIT_SHA}

echo "✅ Docker images pushed successfully!"
echo "📍 Images available at:"
echo "   - ${REPOSITORY_PREFIX}/${REPOSITORY_NAME}:latest"
echo "   - ${REPOSITORY_PREFIX}/${REPOSITORY_NAME}:${COMMIT_SHA}"
