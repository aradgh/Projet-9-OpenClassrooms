FROM node:20 AS build
WORKDIR /app
COPY front-service/package*.json ./
RUN npm install
COPY front-service ./front-service
WORKDIR /app/front-service
RUN npm run build

FROM nginx:1.25
COPY --from=build /app/front-service/dist /usr/share/nginx/html
COPY front-service/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]