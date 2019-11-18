# Azure Monitors

In this lab, we will use the HTTP Data Collector API from Azure to add log items into a Log Analytics Workspace.

## Setup Development Environment

### Connecting to the Labs
* Download the [client](https://remotelabs.io/rl3/download) to connect to the labs.
* Please note, a free Fast Lane account is needed for each user. After you have started the Remote Labs 3 Client you can create an account instantly.

### Installing Tools
* Download & Install [Azul](https://www.azul.com/downloads/azure-only/zulu/?&version=java-8-lts&architecture=x86-64-bit&package=jdk)
* Download & Install [Maven](https://maven.apache.org/download.cgi)
* Download & Install [NodeJS](https://nodejs.org/en/download/)
* Download & Install IDE [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)

### Configuring Tools
After the installations,
1. Make sure that the Maven's binary folder "bin" is added to the Environment variables.
   On Windows machine, `PATH`.
2. Make sure that the environment variable `JAVA_HOME` is defined and point to your JDK folder. e.g. `C:\Program Files\Zulu\zulu-8`
3. Run `mvn -version`. It should return somewhat like this:
```bash
Apache Maven 3.6.2 (40f52333136460af0dc0d7232c0dc0bcf0d9e117; 2019-08-27T17:06:16+02:00)
Maven home: C:\Program Files\Java\apache-maven-3.6.2\bin\..
Java version: 1.8.0_232, vendor: Azul Systems, Inc., runtime: C:\Program Files\Zulu\zulu-8\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

## Setup your Azure Resources
* Activate the [Azure Passes] (https://www.microsoftazurepass.com/)
* Go to Azure Portal
* Create a new Log Analytics Workspace
* Use the Code Try functionality to get the [Shared Key](https://docs.microsoft.com/en-us/rest/api/loganalytics/workspaces/getsharedkeys#code-try-0)

## Develop your Java solutions
* Create a new `Maven` Project in IntelliJ
  * Use Azul as JDK
* Use the [Netty HTTP client](
https://azuresdkdocs.blob.core.windows.net/$web/java/azure-core-http-netty/1.0.0/index.html) to send POST request to your Workspace
* Check in Azure Monitor, that your requests has been added.

### Hints
* https://docs.microsoft.com/en-us/rest/api/loganalytics/create-request
* com.azure.core.implementation.DateTimeRfc1123
* java.time.OffsetDateTime
* javax.crypto.Mac
* javax.crypto.spec.SecretKeySpec
* javax.xml.bind.DatatypeConverter
* **It may takes a few minutes until your data are shown in the Azure Monitor blade**