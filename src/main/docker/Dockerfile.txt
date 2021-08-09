FROM dockerfile/java:oracle-java8

VOLUME /tmp
ADD spring-boot-cron-job.jar /opt/spring-boot-cron-job/
EXPOSE 8080
WORKDIR /opt/spring-boot-cron-job/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "spring-boot-cron-job.jar"]
