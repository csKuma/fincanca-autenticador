# Usa Amazon Corretto 17 como base para o build
FROM amazoncorretto:17 AS build
WORKDIR /app

# Copia os arquivos do projeto e compila
COPY . /app
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# Usa uma imagem separada para a execução
FROM amazoncorretto:17 AS release
WORKDIR /app

# Copia o .war gerado no build
COPY --from=build /app/build/libs/financa-0.0.1-SNAPSHOT.war ./app.war

# Expõe a porta 8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -DFIREBASE_CONFIG=$FIREBASE_CONFIG -jar app.war"]

