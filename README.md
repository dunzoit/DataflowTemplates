# Google Cloud Dataflow Template Pipelines

These Dataflow templates are an effort to solve simple, but large, in-Cloud data
tasks, including data import/export/backup/restore and bulk API operations,
without a development environment. The technology under the hood which makes
these operations possible is the
[Google Cloud Dataflow](https://cloud.google.com/dataflow/) service combined
with a set of [Apache Beam](https://beam.apache.org/) SDK templated pipelines.

Google is providing this collection of pre-implemented Dataflow templates as a
reference and to provide easy customization for developers wanting to extend
their functionality.

[![Open in Cloud Shell](http://gstatic.com/cloudssh/images/open-btn.svg)](https://console.cloud.google.com/cloudshell/editor?cloudshell_git_repo=https%3A%2F%2Fgithub.com%2FGoogleCloudPlatform%2FDataflowTemplates.git)

## Running the PubSubDLQToBigquery

#### Steps

- Create separate subscriptions for topics you want to ingest into BQ
- Create a table in Bigquery. Schema should be same as `prod-data-platform:error_reporting.fact_pubsubdlq_errors`. This job also ingests the pubsub attributes and actual bytes in addition to raw payload.
 - --inputSubscriptions expects a comma separated list of subscriptions
 - --jobName param can be different.
 - Run the job for 0.5-1 hour and then stop otherwise high costs in dataflow and BQ may be incurred.
 - Ideally backend events dlq job should be run separately due to higher volume there.
- The command below includes every non backend events DLQ. Add more subscriptions if needed.
`mvn clean compile exec:java \
 -Dmaven.clean.failOnError=false -Dexec.mainClass=com.google.cloud.teleport.templates.PubSubToBigQuery \
 -Dexec.cleanupDaemonThreads=false \
 -Dexec.args=" --region=asia-south1 --project=prod-data-platform --inputSubscriptions=projects/prod-data-platform/subscriptions/dead-letter-postgres-othersources-test-subscip,projects/prod-data-platform/subscriptions/dead-letter-postgres-load-othersources-test-subscr,projects/prod-data-platform/subscriptions/dead-letter-espresso-postgres-transform-test-subscip,projects/prod-data-platform/subscriptions/dead-letter-store-realtime-test-subscr,projects/prod-data-platform/subscriptions/dead-letter-user-frauddetection-realtime-sub,projects/prod-data-platform/subscriptions/dead-letter-userappui-sessiondata-test-subscr,projects/prod-data-platform/subscriptions/dead-letter-usertasks-runnertask-test-subscr,projects/prod-data-platform/subscriptions/dead-letter-usertasktask-realtime-test-subscip,projects/prod-data-platform/subscriptions/dead-letter-pubsub-transform-mongo-stream-other-test-sbscr,projects/prod-data-platform/subscriptions/dead-letter-pubsub-bq-load-mongo-stream-others-testsbscr,projects/prod-data-platform/subscriptions/dead-letter-bq-load-espresso-postgres-test-sbscr,projects/prod-data-platform/subscriptions/dead-letter-freshchat-user-csat-test-sub,projects/prod-data-platform/subscriptions/dead-letter-dbz-extract-mongo-stream-others-test-sbscr --jobName=pubsub-dlq-to-bq-errors --outputTableSpec=prod-data-platform:error_reporting.fact_pubsubdlq_errors --stagingLocation=gs://dunzo-de-dataflow-temp-jobs/staging --runner=DataflowRunner"
`

Run this command 
