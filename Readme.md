# Api-Leo Compilado de Conhecimentos

## Comandos úteis:

* DEV-MODE:

Criação da rede Docker
```bash
docker network create -d bridge minha-rede-docker
```
Levantamento do container do Mysql
```bash
docker run -d --name mysql-docker --network minha-rede-docker -v "/home/leonardo/Music/workspace-leo/DockerVolumes/MySQL:/var/lib/mysql" -e MYSQL_ROOT_PASSWORD=senhaSegura -e MYSQL_DATABASE=dbDev -p 3306:3306 mysql:latest
```
Levantamento do container do Redis
```bash
docker run -d --name redis-docker --network minha-rede-docker -v "/home/leonardo/Music/workspace-leo/DockerVolumes/Redis:/usr/local/etc/Redis" -p 6379:6379 redis:latest
```

Start da aplicação
```bash
mvn spring-boot:run
```
## Endpoints úteis:

* OpenAPI-UI <http://localhost:8090/swagger-ui/index.html>
* OpenAPI-DOCS <http://localhost:8090/v2/api-docs>
* Actuator <http://localhost:8090/actuator/>