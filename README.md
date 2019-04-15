
# react-native-chat

## Getting started

`$ npm install react-native-chat --save`

### Mostly automatic installation

`$ react-native link react-native-chat`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-chat` and add `RNChat.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNChat.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import eu.codlab.chat.RNChatPackage;` to the imports at the top of the file
  - Add `new RNChatPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-chat'
  	project(':react-native-chat').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-chat/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-chat')
  	```


## Usage
```javascript
import RNChat from 'react-native-chat';

// TODO: What to do with the module?
RNChat;
```
  