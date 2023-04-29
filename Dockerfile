# Imaginea the baza de la care se va forma imaginea prezenta
# Versiunea de java este aceeasi precizata in pom.xml
FROM openjdk:17-jdk-slim

# Setam folderul default in care vom lucra
WORKDIR /app

# Copiem dependintele pentru mavem
COPY pom.xml mvnw ./
# In folderul .mvn pot exista dependinte aditionale
COPY .mvn ./.mvn

# Copiem intreg folderul sursa in imaginea de dokcer
COPY src ./src

# Schimbam permisiunilepe mvnx pentru a il putea executa
# De asemenea folosim comanda dos2unix intrucat original proiectul a fost creat pe windows
# Instalam maven si apoi cream fisierul .jar
# Atributul clean ne asigura ca pornim de la 0, in cazul in care deja exista  un folser taget
# Atributul package imi creata folderul target in
# care maven va instala toate dependintele si fisierul .jar
RUN chmod +x mvnw \
    && apt-get update && apt-get install -y maven \
    && apt install dos2unix \
    && dos2unix mvnwc \
RUN ./mvnw clean package

# Expunem portul 8080 pentru a putea accesa API-ul
EXPOSE 8080

# Comanda ce se va rula la pornirea container-ului
ENTRYPOINT ["java","-jar","/app/target/back-0.0.1-SNAPSHOT.jar"]