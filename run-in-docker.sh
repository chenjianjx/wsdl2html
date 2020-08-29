#!/bin/sh

docker build -t wsdl2html .
docker run --rm \
  -v $2:/usr/local/wsdl2html/output \
  wsdl2html $1

echo "The html files are inside $2"