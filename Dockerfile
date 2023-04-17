FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/jira-1.0.jar jira-1.0.jar
ENTRYPOINT ["java","-jar","/jira-1.0.jar"]


#docker run -p 8080:8080 -v C:/Users/uzer/IdeaProjects/jru-projects/spring-2/resources:/resources -e JIRARUSH_DB_PASSWORD=JiraRush -e JIRARUSH_DB_USER=jira -e JIRARUSH_GITHUB_SECRET=a5e41277f801e18a1944353f4c94f08fbd0920a3 -e JIRARUSH_GITLAB_SECRET=8a91cbcd5e9e51da3a13c42f677f65c35eb005299a75f650e371b147bd9de797 -e JIRARUSH_GOOGLE_SECRET=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 -e JIRARUSH_MAIL_PASSWORD=zdfzsrqvgimldzyj jira-1.0.jar
