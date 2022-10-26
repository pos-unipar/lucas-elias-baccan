FROM node:18.11.0-alpine3.16

WORKDIR /app

COPY package.json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 8080

CMD ["npx", "next", "start", "-p", "8080"]