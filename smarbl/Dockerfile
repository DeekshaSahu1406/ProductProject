FROM openjdk
WORKDIR /usr/src/myapp
COPY ./target/product.jar /usr/src/myapp/product.jar
CMD ["java","-jar","product.jar"]
EXPOSE 8080
