#!/bin/sh

sonarToken=$1

analysis_status=$(curl -u "$sonarToken:" "http://20.123.28.251:9000/api/qualitygates/project_status?projectKey=TibiAlex_Degree-Project-Java_AYfiN4EAqOakdOebiAL0")

value=$(echo "$analysis_status" | jq -r '.projectStatus.status')

if [ "$value" = "ERROR" ]; then
      echo "SonarQube check failed!"
      exit 1
fi



