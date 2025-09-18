FROM nginx:1.27-alpine

# Nginx conf şablonu (PORT ve BACKEND_URL envsubst ile gömülecek)
COPY nginx/default.conf.template /etc/nginx/templates/default.conf.template

# Statik dosyalar
COPY . /usr/share/nginx/html

# Varsayılanlar (Render'da Environment Vars ile override edeceğiz)
ENV BACKEND_URL=mithes-api.onrender.com

# Not: EXPOSE şart değil, Render zaten PORT env’i ile yönlendiriyor
CMD ["/bin/sh", "-c", "envsubst '$$BACKEND_URL' < /etc/nginx/templates/default.conf.template > /etc/nginx/conf.d/default.conf && exec nginx -g 'daemon off;'"]
