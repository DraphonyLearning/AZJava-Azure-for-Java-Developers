# Function App

In this lab, we will create an image processing pipeline, which consists of the following steps:
1. A user sends a image via HTTP POST (Use Azure Function HTTP Trigger)
2. This will generate a new message in the Azure Storage Queue. The image itself is saved as Blob in the Azure Storage Blob.
3. A another Azure Function (time-triggered) runs every 15 seconds to process the image:
    * Send image to Cognitive Service to create tags
    * Save the result in Azure Cosmos DB
    * Crop and Resize the image to 1080p, 720p and 480i.
    * Save the result in Azure Storage Account, you can choose to use the same or another one.
    * Don't forget the remove the message from the 

## Project setup
Use Maven to setup project
```bash
mvn archetype:generate ^
    -DarchetypeGroupId=com.microsoft.azure ^
    -DarchetypeArtifactId=azure-functions-archetype
```

In addition, you ưill need to add additional packages>
```xml
<dependency>
  <groupId>com.azure</groupId>
  <artifactId>azure-core</artifactId>
  <version>1.0.0</version>
</dependency>
<dependency>
  <groupId>com.azure</groupId>
  <artifactId>azure-storage-queue</artifactId>
  <version>12.0.0</version>
</dependency>
<dependency>
  <groupId>com.azure</groupId>
  <artifactId>azure-storage-blob</artifactId>
  <version>12.0.0</version>
</dependency>

<dependency>
  <groupId>com.microsoft.azure.cognitiveservices</groupId>
  <artifactId>azure-cognitiveservices-computervision</artifactId>
  <version>1.0.2-beta</version>
</dependency>
<dependency>
  <groupId>org.mongodb</groupId>
  <artifactId>mongodb-driver-sync</artifactId>
  <version>3.11.2</version>
</dependency>
```

### Azure DevOps 
Also set up CD for your Azure functions.
* Keep in mind, that the auto config does not work for Java.
* There is a `Maven` task
* You only need to deploy the folder, that contains the `host.json`.

## Hints
1. Use `mvn azure-functions:run` to run your azure function locally. You need to build/updating the package before running with `mvn clean package`.
2. [Java SDK for Storage Account Queue](https://azuresdkdocs.blob.core.windows.net/$web/java/azure-storage-queue/12.0.0/index.html)
