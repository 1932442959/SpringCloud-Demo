docker run \
 -e RABBITMQ_DEFAULT_USER=jasfair \
 -e RABBITMQ_DEFAULT_PASS=HYSBEST1234 \
 --name rabbitmq_for_mac \
 --hostname rabbitmq_for_mac_01 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d \
 rabbitmq:3-management