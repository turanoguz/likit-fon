FROM node:lts-alpine3.18

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json yarn.lock* package-lock.json* pnpm-lock.yaml* ./
RUN \
  if [ -f yarn.lock ]; then yarn --frozen-lockfile; \
  elif [ -f package-lock.json ]; then npm ci; \
  elif [ -f pnpm-lock.yaml ]; then yarn global add pnpm && pnpm i --frozen-lockfile; \
  else echo "Lockfile not found." && exit 1; \
  fi


COPY public public
COPY src src
COPY tailwind.config.js tailwind.config.js


EXPOSE 3000
CMD [ "npm", "start" ]