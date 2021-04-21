# MyMail Mail User Agent

MUA - Mail User Agent

SMTP - Simple Mail Transfer Protocol

IMAP - Internet Message Access Protocol

## :clipboard: Table of contents 
  - [General Info](#information_source-general-info)
  - [Task Description](#white_check_mark-task-description)
  - [Technologies](#computer-technologies)
  - [Setup](#gear-setup)
  - [Implementation Notes](#bangbang-implementation-notes)

## :information_source: General Info
The project implements a simple MUA named MyMail. Instead of using ready-to-use framework like [JavaMail](https://javaee.github.io/javamail/) that provides solutions to general mail protocols SMTP and IMAP, project focuses on manually implementing solutions to usage of these protocols. MyMail works with *localhost*  and is capable to send, receive RFC822 messages and make some manipulations with list of messages (flagging, sorting, searching). As an SMTP server Postfix and for IMAP server Dovecot were used.  
In addition MyMail has a modern looking GUI. For designing the layout of MyMail, the app *Mail and Calender by Microsoft* was taken as a reference. All GUI elements were manually designed and created in PhotoShop.

## :white_check_mark: Task Description
This individual project was an implementation part of university final year thesis for ***SCC300:Third Year Project*** module. Main idea was to develop an MUA that uses self developed SMTP and IMAP protocols. To understand how protocols work, RFC papers were used as a guidance as well as Dovecot and Postfix manuals for individual specifications to each software.

The whole process of researching background resources, design, implementation and testing decisions as well as evaluation are detailly documented in the thesis. It can be found  in supporting docs repository folder *file: [Thesis.pdf](https://github.com/agoidin/MyMail/blob/main/SupportingDocs/Thesis.pdf)*

## :computer: Technologies
Project is created with:
* Java version: 15.0
* JavaFX version 11.0.2
* JavaFX CSS
* SceneBuilder 11.0.0 (laying out the elements)
* Dovecot (testing IMAP server) 
* Postfix (testing SMTP server)
	
## :gear: Setup

**-- JAVA 15+ REQUIRED --**

**-- Dovecot REQUIRED --**

**-- Postfix REQUIRED --**

1. EDIT LOGIN INFO:

Open MyMail.jar 

Go to [/Resources/Account/](https://github.com/agoidin/MyMail/blob/master/src/Resources/Account/)

Open ***UserDetails.txt*** and edit fields ***username*** and ***password*** with your local system credentials separated with **/**

Example: If my Linux system username is ***agoidin*** and password is ***1234*** my file entry would look like this:

```
agoidin/1234
```


2. RUN THE CLIENT:

Go to root folder open command-line in that folder and run the following command:

```
$ java -jar MyMail.jar
```

In some cases it may require to run Jar with --enable-preview:

```
$ java -jar --enable-preview MyMail.jar
```

## :bangbang: Implementation Notes
As it was a university project with limited timeframes and certain learning objectives, therefore only main functionalities were implemented. Project and GUI have some code templates and elements ready for futher development.
