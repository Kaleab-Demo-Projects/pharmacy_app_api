#Setup Notes:

Deploy to Cloud Run:


Create Artifact Registry(Once)

gcloud artifacts repositories create pharmacy-api-repo \
  --repository-format=docker \
  --location=europe-west3 \
  --project=personal-projects-467919 \
  --description="Artifact repo for pharmacy-api backend"


Build & push image (Cloud Build)

gcloud builds submit \
--tag europe-west3-docker.pkg.dev/personal-projects-467919/pharmacy-api-repo/pharmacy-api

Deploy to Cloud Run (Frankfurt)

gcloud run deploy pharmacy-api \
  --image=europe-west3-docker.pkg.dev/personal-projects-467919/pharmacy-api-repo/pharmacy-api \
  --platform=managed \
  --region=europe-west3 \
  --allow-unauthenticated \
  --project=personal-projects-467919



> Firestore Integration

This service uses Google Cloud Firestore (Native mode) as the primary data store.
The application is configured to run both locally and in Cloud Run using a dedicated service account with Firestore access.

Setup steps:
	1.	Enable Firestore (Native mode) in your GCP project.
	2.	Assign the Cloud Run service a service account with the roles/datastore.user permission.
	3.	Store and load the service account JSON key locally when running outside GCP.
	4.	The application seeds initial data when running with the seed profile.
	5.	All CRUD operations for entities (e.g., Medicine, Orders) are persisted to Firestore.

Local run tip:
If running locally, ensure you have your service account key file and set:

Refer commit history: https://github.com/Kaleab-Demo-Projects/pharmacy_app_api/commit/d5182fedab087c9b4ddd91881c5539a26f3fda8b

Command to run code with seed profile to add first values to firestore: SPRING_PROFILES_ACTIVE=seed ./gradlew bootRun  
Command to enable accessing firestore from firestore from local api code: gcloud auth application-default login 

