# Ad Mediation API

## 1. Build
### Option 1: Local build
1. Clone this repository
1. Create an empty table in your MySQL database
1. Open the **AdMediation/src/main/resources/application.yml** file and correctly set **url**, **username** and **password** parameters
1. Run `mvn package` in the project root folder
1. Move to **AdMediation/target** folder
1. Run `java -jar ad_mediation_challenge-0.0.1-SNAPSHOT.jar`
---
### Option 2: Deploy to Google Cloud
#### 1.2.1 Build and test
1. Create a new project and activate console
1. Create a new instance `gcloud sql instances create <INSTANCE_NAME> --availability-type regional --region europe-west3 --enable-bin-log --backup-start-time=03:00`
1. Create a new DB `gcloud sql databases create <DB_NAME> --instance <INSTANCE_NAME>`
1. Clone this repository `git clone https://github.com/janstarc/adMediation`
1. Move to the project root, delete file **application.yml** `rm src/main/resources/application.yml`
1. Delete the *"local" pom.xml* `rm pom.xml`
1. Activate the *"Google Cloud" pom.xml* `mv pom_gCloud.xml pom.xml`
1. Rename **application.properties** `mv /src/main/resources/application.properties.gCloud  /src/main/resources/application.properties`
1. Rename **application-mysql.properties** `mv src/main/resources/application-mysql.properties.gCloud src/main/resources/application-mysql.properties`
1. Change the `spring.cloud.gcp.sql.instance-connection-name=` parameter in **src/main/resources/application-mysql.properties** to your **<CONNECTION_NAME>**
1. Run `./mvnw spring-boot:run` and open the **Web Preview** (if there are issues with permissions, run `chmod +x mvnw`)

#### 1.2.2 Deploy
1. Create app for this project `gcloud app create --region europe-west3`
1. Run `gcloud projects list` to display Google Cloud projects
1. Set current project to default project `gcloud config set project <THIS_PROJECT_ID>`
1. Enable the Cloud SQL Admin API `gcloud services enable sqladmin.googleapis.com`
1. Finally, deploy `./mvnw -DskipTests package appengine:deploy`
1. Run `gcloud app browse` to get the link

## 2. How to use?
### TASK 1: Update list of ad networks (part of the API accessible only internally)
To update the DB with the new data from batch processing send a PUT request with valid JSON file to `/data`.

See a sample JSON file in **resources/json/batchResults1.json** folder with 60 objects for testing purposes.

**Example of a valid JSON file** (which is assumed to be the result of a batch job):
```json
[
  {
    "idPerformanceData": 1,
    "performanceScore": 15,
    "country": {
      "countryCode": "SL",
      "countryName": "Slovenia"
    },
    "adType": {
      "idAdType": 1,
      "descriptionType": "Banner"
    },
    "adProvider": {
      "idAdProvider": 1,
      "descriptionProvider": "Facebook"
    }
  },
  {
    "idPerformanceData": 2,
    "performanceScore": 78,
    "country": {
      "countryCode": "SL",
      "countryName": "Slovenia"
    },
    "adType": {
      "idAdType": 2,
      "descriptionType": "interstitial"
    },
    "adProvider": {
      "idAdProvider": 1,
      "descriptionProvider": "Facebook"
    }
  },
...
]

```
#### Other supported methods (for testing purposes)
`GET /data` returns JSON with all records in the DB.

`DELETE /data` deletes **all** records from the performance_data table.


---
### TASK 2: Retrieve list of ad networks (part of the API open to mobile apps)
#### Request
To retrieve a list of ad networks for a certain country, send a **GET** request to `/performanceData`.

The GET request must have 5 parameters: 
- **platform** *(In the test data, there are currently Android and iOS added)*
- **osVersion**
- **appName**
- **appVersion**
- **countryCode** *(In the test data, there are currently 4 countries: SL, DE, US, CN)*

Example of a **GET** request: `/performanceData?platform=Android&osVersion=10&appName=Talking Tom&appVersion=2.5&countryCode=SL`

*Note: GET requests return an error in some programs API development programs (e.g. Postman) if authentication is not configured properly. In such cases configure authentication OR simply use your web browser*

#### Response 
A JSON object with an ordered list of ad networks for each ad type is returned as a result.

JSON result contains 3 arrays, each of them with data for a specific ad type. It is firstly sorted by **adType**, additionally **adType** arrays are sorted by **performanceScore** in descending order (it is assumed that higer performanceScore means better performance).

The response is **simple** and **easy to read**.

**Example:**
```json
{
    "adType1": [
        {
            "performanceScore": 83,
            "adProviderId": 3
        },
        {
            "performanceScore": 58,
            "adProviderId": 2
        },
        ...
    ],
    "adType2": [
        {
            "performanceScore": 86,
            "adProviderId": 2
        },
        {
            "performanceScore": 58,
            "adProviderId": 4
        },
        ...
    ],
    "adType3": [
        {
            "performanceScore": 69,
            "adProviderId": 1
        },
        {
            "performanceScore": 41,
            "adProviderId": 2
        },
        ...
    ]
}

```
## 3. Other
**Assumptions:** 
- Performance scores are stored in the DB as numbers within interval [1, 100].
- Higher performance score means better performance.
- Smartphone apps only needs **adProviderID** and **adTypeID** and **performanceScore** in order to identify and connect with the correct ad network 

**To Do - For Production**
- Unit tests need to be added
- Access to `/data` needs to be granted to authorized users only

