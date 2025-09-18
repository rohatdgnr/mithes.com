FROM node:18-alpine

WORKDIR /app

# Prod bağımlılıklar
COPY package*.json ./
RUN npm ci --omit=dev

# Kaynaklar
COPY . .

ENV NODE_ENV=production
ENV PORT=8080
EXPOSE 8080

CMD ["npm", "start"]
