FROM amazoncorretto:17 AS build
WORKDIR /app

# Copia os arquivos do projeto e compila
COPY . /app
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM amazoncorretto:17 AS release
WORKDIR /app

# Copia o .war gerado do build
COPY --from=build /app/build/libs/financa-0.0.1-SNAPSHOT.war ./app.war

EXPOSE 8080

# Inicia a aplicação passando o caminho do Firebase por variável de ambiente
ENTRYPOINT ["java", "-Denv.firebase=${env_firebase}", "-jar", "app.war"]
