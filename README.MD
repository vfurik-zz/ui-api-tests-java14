java 14 should be installed

docker pull selenoid/vnc_chrome:84.0
docker pull selenoid/vnc_firefox:79.0
docker pull selenoid/video-recorder:latest-release

Build custom_jenkins image (from jenkins folder):
docker build -t custom_jenkins . 
