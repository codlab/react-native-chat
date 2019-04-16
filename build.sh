#!/bin/bash

# remove files
mkdir -p build/dist
rm -rf build/dist

tsc

cp ./package.json ./build/dist/

# export the android files
mkdir -p build/dist/android
cp -r android/src build/dist/android/src
cp android/build.gradle build/dist/android/build.gradle

# export mandatory ios files
mkdir -p build/dist/ios
cp ios/RNChat.h build/dist/ios/RNChat.h
cp ios/RNChat.m build/dist/ios/RNChat.m
cp ios/RNChat.podspec build/dist/ios/RNChat.podspec
cp -r ios/RNChat build/dist/ios/RNChat
cp -r ios/RNChat.xcodeproj build/dist/ios/RNChat.xcodeproj
cp -r ios/RNChat.xcworkspace build/dist/ios/RNChat.xcworkspace

# copy back README
cp README.md build/dist/README.md
