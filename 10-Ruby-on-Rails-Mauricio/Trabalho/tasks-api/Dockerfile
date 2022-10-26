FROM ruby:3.1.2-alpine3.16

LABEL maintainer="Lucas Elias Baccan"

RUN apk add --update --no-cache \
    build-base \
    nodejs \
    tzdata \
    yarn \
    git \
    dos2unix

WORKDIR /app

COPY Gemfile Gemfile.lock ./

RUN bundle install

COPY . .

COPY docker-entrypoint.sh ./docker-entrypoint.sh

RUN dos2unix docker-entrypoint.sh

ENTRYPOINT ["sh", "docker-entrypoint.sh"]

EXPOSE 3000

CMD ["rails", "server", "-p", "3000", "-b", "0.0.0.0"]