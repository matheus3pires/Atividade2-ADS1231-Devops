FROM openjdk:11

WORKDIR /app

COPY ./*.java /app/

RUN javac -d . *.java

ENV CLASSPATH=/app

CMD ["java", "AtividadeJava.Main"]