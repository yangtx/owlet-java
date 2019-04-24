set -e
set -o pipefail

gradle build

mkdir -p docker-build/service
cp build/libs/*.jar docker-build/service/service.jar
chmod +x .cicd/docker/script/* && cp docker/script/* docker-build/service/

export PROJECT_VERSION=`gradle properties | grep "^version:" | awk '{print $2}'`
export BUILD_TIMESTAMP=`date '+%Y%m%d%H%M%S'`
export BUILD_VERSION=${PROJECT_VERSION}.${BUILD_TIMESTAMP:2:6}.${TRAVIS_COMMIT:0:6}

docker login -u $DOCKER_ACCOUNT -p $DOCKER_PASSWORD >/dev/null 2>&1

docker build -t xracoon/shikra:$BUILD_VERSION docker
docker push xracoon/owlet:$BUILD_VERSION

mkdir -p kube-build
envsubst < .cicd/kube/owlet.yaml > kube-build/owlet.yaml

scp -P $DEPLOY_PORT -i ~/.ssh/deploy_key -o StrictHostkeyChecking=no kube-build/*.yaml $DEPOTY_USER@$DEPLOY_TARGET:/opt/service/owlet/
ssh -p $DEPLOY_PORT -i ~/.ssh/deploy_key $DEPOTY_USER@$DEPLOY_TARGET "oc apply -f /opt/service/owlet/owlet.yaml"