#sıfır bir makinede(freeDOS) bu projeyi nasıl kurarım?
#İşletim sistemi -> java? -> maven? -> proje -> run


#İşletim sistemi -> java? -> maven
#single stage image boyutu büyük önerilmez
FROM maven:3.9-eclipse-temurin-21 as builder

# -> proje -> run
WORKDIR /build

COPY pom.xml ./

RUN mvn -DskipTests dependency:purge-local-repository

COPY . .

RUN mvn -U clean package -DskipTests

FROM eclipse-temurin:21-jre AS runtime

COPY --from=builder /build/target/*-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]