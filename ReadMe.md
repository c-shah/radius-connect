### DIfferent Files
    
    pom.xml : obvious
    Procfile : tells heroku what to run  (both local and server)
    .env : local copy of env for heroku local
    system.properties : tells which version of JRE to use. 

### How to run:

    1) mvn clean install
    2) heroku local web -p 8991  (this set PORT env variable)
    

### How it works : local   
    
    1) .env files stores all variable which are avialble via System.getenv
    ** ONLY APPLIES TO LOCAL : heroku local web -p 9000 **
    
    2) system.propties determines which version of JRE to use
    *** ONLY APPLIES TO SERVER ***
    
    3) pom.xml has build is plugin is used by heroku
       -> build will generate the radius-connect.jar file
          -> this jar file has main class which is com.spring.heroku.spark.SparkMain
             -> procFile which is read by Heroku has entry to execute radius-connect.jar file
    
             so heroku local or server
             reads Procfile -> reads Jar -> Jar has Main file which runs spark jetty
    
### How it works : server
    
    Just Commit. It picks up from Procfile


### Oauth 2.0 Flow

1) create Connected App (in any org, preferred production)
https://chintanjshah12345-dev-ed.my.salesforce.com/app/mgmt/forceconnectedapps/forceAppDetail.apexp?applicationId=06Pf1000000QIXq&id=0Cif1000000CdlK

2) redirect to login.salesforce.com, test.salesforce.com with 
client_id       ( The Consumer Key from the connected app definition )
redirect_uri	( The Callback URL from the connected app definition )

3) It comes back with 
client_secret
code
grant_type
client_id
redirect_uri
