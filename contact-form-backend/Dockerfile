# backend: Node/Express varsayımı
FROM node:18-alpine

WORKDIR /app

# Prod güvenli ve hızlı kurulum
COPY package*.json ./
RUN npm ci --omit=dev

# Kaynaklar
COPY . .

ENV NODE_ENV=production
ENV PORT=8080
EXPOSE 8080

# Uygulaman (örn. "start": "node server.js")
CMD ["npm", "start"]
