FROM java:7

ENV RELEASE_DATE 2017-04-23
ENV H2DATA /h2-data

RUN curl http://www.h2database.com/h2-$RELEASE_DATE.zip -o h2.zip \
    && unzip h2.zip -d . \
    && rm h2.zip

RUN ln -s $(ls /h2/bin/*jar) /h2/bin/h2.jar

RUN mkdir /docker-entrypoint-initdb.d
COPY h2_init.sql /docker-entrypoint-initdb.d/h2database.sql

VOLUME /h2-data

EXPOSE 8082 9092

COPY db-docker-entrypoint.sh /usr/local/bin/
ENTRYPOINT ["db-docker-entrypoint.sh"]

CMD java -cp /h2/bin/h2.jar \
   org.h2.tools.Server \
   -web -webDaemon -webAllowOthers -webPort 8082 \
   -tcp -tcpAllowOthers -tcpPort 9082 \
   -baseDir $H2DATA