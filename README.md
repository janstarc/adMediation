# Ad Mediation API

## 1. Build
### Option 1: Local build
1. Clone this repository
1. Create an empty table in your MySQL database
1. Open the **AdMediation/src/main/resources/application.yml** file and correctly set **url**, **username** and **password** parameters
1. Run `mvn package` in the project root folder
1. Move to **AdMediation/target** folder
1. Run `java -jar ad_mediation_challenge-0.0.1-SNAPSHOT.jar`
----
### Option 2: Deploy to Google Cloud
#### 1.2.1 Build and test
1. Create a new project and activate console
1. Create a new instance `gcloud sql instances create my-instance --availability-type regional --region europe-west3 --enable-bin-log --backup-start-time=03:00`
1. Create a new DB `gcloud sql databases create <DB_NAME> --instance <INSTANCE_NAME>`
1. Clone this repository `git clone https://github.com/janstarc/adMediation`
1. Delete file **adMediation/src/main/resources/application.yml**
1. Move to the project root, delete the *"local" pom.xml* `rm pom.xml`
1. Activate the *"Google Cloud" pom.xml* `mv pom_gCloud.xml pom.xml`
1. *Uncomment* the content of **adMediation/src/main/resources/application.properties**
1. *Uncomment* the content of **adMediation/src/main/resources/application-mysql.properties**
1. Change `spring.cloud.gcp.sql.instance-connection-name` in **application-mysql.properties** to your <CONNECTION_NAME>
1. Run `./mvnw spring-boot:run` and open the **Web Preview** (if there are issues with permissions, run `chmod +x mvnw`)

#### 1.2.2 Deploy
1. Create app for this project `gcloud app create --region europe-west3`
1. Run `gcloud projects list` to display Google Cloud projects
1. Set current project to default project `gcloud config set project <THIS_PROJECT_ID>`
1. Enable the Cloud SQL Admin API `gcloud services enable sqladmin.googleapis.com`
1. Finally, deploy `./mvnw -DskipTests package appengine:deploy`
1. Run `gcloud app browse` to get the link

## 2. How to use?
### 2.1 Update list of ad networks
To update the list from batch processing send a PUT request with valid JSON file to `/data/deleteAndInsert`.

See a sample JSON file in **resources/json/** folder with 60 objects for testing purposes.

### 2.2 Retrieve list of ad networks
#### 2.2.1 Request
To retrieve a list of ad networks for a certain country, send a **GET** request to `/performanceData/subset`.

The GET request must have 5 parameters: 
- **platform** *(In the test data, there are currently Android and iOS added)*
- **osVersion**
- **appName**
- **appVersion**
- **countryCode** *(In the test data, there are currently 5 countries: SL, DE, US, CN)*

Example of a **GET** request: `/performanceData/subset?platform=Android&osVersion=10&appName=Talking Tom&appVersion=2.5&countryCode=SL`

#### 2.2.2 Response
A JSON object with an ordered list of ad networks for each ad type is returned.

Example of a response: --> Popravi, da ne vrne drzave!
`SE MANJKA`

**Assumptions:** 
- Performance scores are stored in the DB as numbers within interval [1, 100].
- Higher performance score means better performance.
- Smartphone apps only need **adProviderID** and **adTypeID** and **performanceScore** in order to identify and connect with the correct ad network 


