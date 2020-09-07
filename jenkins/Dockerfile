FROM jenkins/jenkins

ENV JENKINS_USER admin
ENV JENKINS_PASS admin

# Skip initial setup
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt
USER root

RUN apt-get update && \
    apt-get install -y software-properties-common && \
    add-apt-repository -y ppa:linuxuprising/java && \
    apt-get update && \
    apt-get -y install sudo && \
    echo oracle-java14-installer shared/accepted-oracle-license-v1-2 select true | sudo debconf-set-selections && \
    apt-get install -y --allow-unauthenticated oracle-java14-installer && \
    apt install oracle-java14-set-default

ENV JAVA_HOME /usr/lib/jvm/java-14-oracle/
RUN export JAVA_HOME

USER jenkins