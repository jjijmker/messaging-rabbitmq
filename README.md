# messaging-rabbitmq

https://spring.io/guides/gs/messaging-rabbitmq/

## Prerequisites

1. Install RabbitMQ with `brew install rabbitmq`
2. Start RabbitMQ with `rabbitmq-server` (might have to add `/usr/local/sbin` to path)

Or:

1. Install Docker with `brew cask install docker`
2. Create `docker-compose.yml` with:

```yml
rabbitmq:
  image: rabbitmq:management
  ports:
    - "5672:5672"
    - "15672:15672"
```

3. Start RabbitMQ with `docker-compose up`

## Project Creation

1. 

