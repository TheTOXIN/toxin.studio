# Stage 1: Build Angular 18 App

FROM node:21-alpine AS builder
# Install Angular CLI globally
RUN npm install -g @angular/cli@19.2.0
WORKDIR /app
# Copy package files to install dependencies
COPY package*.json ./
# Install dependencies
RUN npm install
# Copy the rest of the application files
COPY . .
# Build the Angular app for production
RUN ng build --configuration=production

# Stage 2: Combine Angular with NGINX

FROM nginx:alpine
# Copy the Angular build from the builder stage to NGINX
COPY --from=builder /app/dist/toxin-frontend/browser /usr/share/nginx/html
# Expose the necessary ports
EXPOSE 80
# Start both NGINX
CMD ["nginx", "-g", "daemon off;"]
