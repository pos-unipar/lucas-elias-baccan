FROM php:7.2-alpine

RUN apk add --no-cache git

RUN docker-php-ext-install pdo pdo_mysql

WORKDIR /app

RUN git clone https://github.com/Bunkermaster/crud-webp2020-g1.git /app

COPY ./connexion.php ./connexion.php

CMD ["php", "-S", "0.0.0.0:80", "-t", "."]
# CMD [ "php", "./index.php" ]

