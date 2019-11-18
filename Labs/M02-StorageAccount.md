# Azure Storage Account

Create a console "Blob Storage Explorer".

## Features
Your solution should support the following features.

### Containers and Directories
1. User can list all the BlobItems in the current container or directory.
2. User can switch into a container or directory. When switching the container, show for the new container:
    1. Show the public access level and stored access policies
    2. Lists all the meta data
    3. List all Blobs with AccessTier, BlobType, LastModified
3. User can create directories in the current context (current container and current directories).

### Files
1. Acquiring the Lease for the current BlobItem
    * Delete the BlobItem on the [Azure Portal](https://portal.azure.com) when you have acquired the Lease. Does it work?
2. Create a Shared Access Signature from a given Shared Access Policy
    * Delete the SAP after you have acquired the SAS. Use your SAS again. Does it still work?
3. Enable user to upload files as BlockBlob, AppendBlob and PageBlob 
4. Decrease the access tier of a blob. (**Do not increase the access tier, this will cause extra charges!**).
    * You can do this all blob types?

### Bonus Task
1. Use a [Blob Batch](https://docs.microsoft.com/en-us/rest/api/storageservices/blob-batch) to perform multiple operations.

## Hints
1. If you prefer, you can use the REST-API. [MSDN Docs]( https://docs.microsoft.com/en-us/rest/api/storageservices/blob-service-rest-api)
2. Or use the [Java Azure SDK for Blob Storage Accounts](https://azuresdkdocs.blob.core.windows.net/$web/java/azure-storage-blob/12.0.0/index.html)
    * [BlobLeaseClientBuilder](https://azuresdkdocs.blob.core.windows.net/$web/java/azure-storage-blob/12.0.0/index.html)
    * [BlobItemProperties](https://azuresdkdocs.blob.core.windows.net/$web/java/azure-storage-blob/12.0.0/index.html) 
3. [BlobServiceSasSignatureValues](https://azuresdkdocs.blob.core.windows.net/$web/java/azure-storage-blob/12.0.0/index.html)
4. On Windows machines, you can use the [storage emulator](https://docs.microsoft.com/en-us/azure/storage/common/storage-use-emulator). Not all the features might be supported though.
