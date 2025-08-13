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

