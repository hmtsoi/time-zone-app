# Runtime Environment:
1. Linux
2. Java 8
3. Gradle
4. A PostgreSQL 9.6 database filled with the time zones data from 

http://www.naturalearthdata.com/downloads/10m-cultural-vectors/timezones/

in database "timezones_db" and a user "timezones_user" having access to it with password "pass".

# To run:
1. gradle wrapper
2. ./gradlew bootRun

# To view:
Open a browser and type in
"http://127.0.0.1:8080/timeForLatLon/{latutude},{longitude}"
e.g.
"http://127.0.0.1:8080/timeForLatLon/-22,-43.17" (Rio de Janeiro),
"http://127.0.0.1:8080/timeForLatLon/22.39,114.11" (Hong Kong),
"http://127.0.0.1:8080/timeForLatLon/53.55,9.99" (Hamburg)
for the time zone information.
