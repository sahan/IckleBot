![IckleBot](https://raw.github.com/sahan/IckleBot/master/logo.png)

> **IckleBot** /'ikəl'bôt/ <em>noun.</em> **1** A boilerplate code generator with a 
simplified threading and event model. **2** A runtime dependency injection framework. 
[![Build Status](https://travis-ci.org/sahan/IckleBot.png?branch=master)](https://travis-ci.org/sahan/IckleBot)

<br/>
##About

**IckleBot** facilitates quick Android development by generating boilerplate code in 
`Activity` instances and offers alternative simplified threading and event models. It encourages 
loose-coupling and maintainability by providing a runtime [dependency injection](http://en.wikipedia.org/wiki/Dependency_injection) 
framework.   
   
Annotation based Android development with...
   
* Dependency Injection for...
   
	1. Layouts
	2. Resources
	3. System Services
	4. Applications
	5. POJOs   
	
* An alternative threading model which simplifies the execution of worker threads and 
UI tasks.   

* An alternative event model which simplifies the linking of event listeners.   

* Activity state management and configuration of window features.   
   
* Utilities to discover and manage network state changes.   
<br/>

##Setup

### 1. For Maven Based Android Projects

Add the following dependency in your project's pom.xml.

```xml
<dependency>
   <groupId>com.lonepulse</groupId>
   <artifactId>icklebot</artifactId>
   <version>1.1.4</version>
   <type>apklib</type>
</dependency>
```

For information on building Android projects using Maven here's [Chapter 14](http://www.sonatype.com/books/mvnref-book/reference/android-dev.html) of `Maven: The Complete Reference`.   
<br/>   

### 2. For Standard Android Projects

[Download](https://github.com/sahan/IckleBot/archive/master.zip) the repository or clone it and import it as an **existing project** in Eclipse.

```bash
$ git clone git://github.com/sahan/IckleBot.git
```

Since the Eclipse metadata is maintained in the repository, the project should be immediately available as an Android library which you can reference in your own project. Refer the [developer guide](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject) for information on referencing library projects.   
<br/>

##Usage

Kickoff with the [quickstart](https://github.com/sahan/IckleBot/wiki/Quickstart) and follow the rest of the wiki pages.

1. [Quickstart](https://github.com/sahan/IckleBot/wiki/Quickstart)

2. [Manage Activity Configuration](https://github.com/sahan/IckleBot/wiki/Manage-Activity-Configuration)

3. [Inject Views and Resources](https://github.com/sahan/IckleBot/wiki/Inject-Views-and-Resources)

4. [Inject System Services, Application Instances and POJOs](https://github.com/sahan/IckleBot/wiki/Inject-System-Services,-Application-Instances-and-POJOs)

5. [Leverage Implicit Injection](https://github.com/sahan/IckleBot/wiki/Leverage-Implicit-Injection)

6. [Use IckleBot's Threading Model](https://github.com/sahan/IckleBot/wiki/Use-IckleBot's-Threading-Model)
  
7. [Use IckleBot's Event Model](https://github.com/sahan/IckleBot/wiki/Use-IckleBot's-Event-Model)

8. [Detect Network State](https://github.com/sahan/IckleBot/wiki/Detect-Network-State)
  
9. [Avoid Coupling with IckleActivity](https://github.com/sahan/IckleBot/wiki/Avoid-Coupling-with-IckleActivity)
<br/>

##License
This library is licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
