FROM mysql:5.6
ENV MYSQL_ROOT_PASSWORD=12345
ENV MYSQL_DATABASE=reactive_programming

COPY ./scripts-mysql/ /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]

