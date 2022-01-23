docker run -d \
  --name kibana \
  -e ELASTICSEARCH_HOSTS=http://es:9200 \
  --network es-net \
  -p 5601:5601 \
  kibana:7.16.3
