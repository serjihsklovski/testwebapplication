#!/usr/bin/env bash
rm -R ./src/main/webapp/css/node_modules
mkdir ./src/main/webapp/css
npm install --prefix ./src/main/webapp/css summarize.css --legacy-bundling
