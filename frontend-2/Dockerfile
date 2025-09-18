FROM nginx:1.27-alpine

# Nginx conf şablonu (envsubst ile BACKEND_URL gömeceğiz)
COPY nginx/default.conf.template /etc/nginx/templates/default.conf.template

# Statik dosyalar
COPY . /usr/share/nginx/html

# Varsayılan (Render envVars ile override edilir)
ENV BACKEND_URL=http://localhost:8080

EXPOSE 80
CMD ["/bin/sh", "-c", "envsubst '$$BACKEND_URL' \
  < /etc/nginx/templates/default.conf.template \
  > /etc/nginx/conf.d/default.conf && exec nginx -g 'daemon off;'"]
