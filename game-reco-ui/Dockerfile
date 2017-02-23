# To build and run with Docker:
#
#  $ docker build -t game-reco-ui .
#  $ docker run -it --rm -p 3000:3000 -p 3001:3001 game-reco-ui
#
FROM node:latest

RUN mkdir -p /game-reco-ui-build /home/nodejs && \
    groupadd -r nodejs && \
    useradd -r -g nodejs -d /home/nodejs -s /sbin/nologin nodejs && \
    chown -R nodejs:nodejs /home/nodejs

WORKDIR /game-reco-ui-build
COPY package.json /game-reco-ui-build/
RUN npm install --unsafe-perm=true

COPY . /game-reco-ui-build
RUN chown -R nodejs:nodejs /game-reco-ui-build
USER nodejs

CMD npm start
