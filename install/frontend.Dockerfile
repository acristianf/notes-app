FROM node:20
WORKDIR /app 
COPY ./frontend/package.json .
RUN ["npm", "i"]
COPY ./frontend/ .
EXPOSE 5173
CMD ["npm", "run", "dev"]