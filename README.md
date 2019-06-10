
# react-native-chat

## Getting started

`$ npm install react-native-xchat --save`

### Mostly automatic installation

`$ react-native link react-native-xchat`

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


# Usage

The Library provides 4 differents components

```javascript
import {
	ConversationManager,
	ChatView,
	Message,
	User
} from 'react-native-chat';
``` 

# ConversationManager

The `ConversationManager` provides accessors to create, list and save the followings :

  - users
	- conversations
	- messages (save only)


```javascript
import {
	ConversationManager
} from 'react-native-chat';

//a manager ca
const conversationManager: ConversationManager = new ConversationManager();
```

### getUsers

_Returns_ :
__Promise<User[]>__ the list of users internally known by the library


### createUser

_Parameters_ :
- user **User** a valid User to save into the library (or update)

_Returns_ :
__Promise<User>__ the saved instance of the User


### getConversations

_Returns_ :
__Promise<Conversation[]>__ the list of Conversation known to the library


### createConversation

_Parameters_ :
- uuid **string** the unique identifier of the conversation to create (or update)
- name **string** the name of the conversation

_Returns_ :
__Promise<Conversation>__ the saved instance of the Conversation


### addUserToConversation

_Parameters_ :
- user **User** the instance of User descriptor to save in the conversation
- conversation **Conversation** the Conversation to bind the user in

_Returns_ :
__Promise<boolean>__ the success of the operation (normally true)


### saveMessage

_Parameters_ :
- user **User** the sender of the message
- conversation **Conversation** the conversation in which the message has been sent
- message **Message** the message to register for both User/Conversation

_Returns_ :
__Promise<Message>__ the saved Message


### requery

_Returns_ :
__Promise<Message>__ the query state

# ChatView

The ChatView is a simple view which will display automatically messages as soon as they are sent or managed by the library. Any props modification will lead to :
- reloading the conversation if the uuid is the same
- loading the new conversation if it changed

Props managed by the View :

- conversationUUID **number** the Conversation's uuid

_Note that in the future, it'll be possible to directly pass the Conversation object_

## Usage

```javascript
<ChatView conversationUUId="someUUID" />
```

# Conversation

Conversations are simple interfaces with the following properties :

- id **number** the internal identifier of the conversation
- uuid **string** the onversation's uuid
- name **string** the conversation's name
- users **User[]** The list of Users in the conversation


# User

Users are simple interfaces with the following properties :

- uuid **string** the unique identifier
- avatar **string** the user's avatar URL
- name **string** the user's name
- additionnal **string?** _optionnal_ information about the user
- sent_at **number?** _optionnal_ Date number representation
- additionnal **number?** _optionnal_ Date number representation


# Message

Messages are simple interfaces bound to various types :

## Message
- uuid **string** the unique identifier
- content **string** the actual message content
- additionnal **string?** _optionnal_ information about the message
- local **boolean** _optional_ is this message local? (for instance sent by the user)

## MessageImage

(inherits every properties of [Message](#message))
- image **string** the url of the image to load

(_additional_ is not used)

## MessageIoT

(inherits every properties of [Message](#message))
- image **string?** _optional_ image to load as the product (for instance)
- state_1 **string** information about the first specific state of the IoT
- state_2 **string** information about the second specific state of the IoT
- state_connectivity_1 **string** information about the connectivity for the first info
- state_connectivity_2 **string** information about the connectivity for the second info

## MessageSystem

(inherits every properties of [Message](#message))
- image **string?** _optional_ image to load
- system **boolean** indicates if the message is a proper system message
- error **boolean** is this message an error ?
