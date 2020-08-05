#Secure the endpoins with HTTP Basic authentication

============

Basic Security added in SpringSecurityConfig
 - 2 user created as user[role : USER ] and admin [Role USER & ADMIN]
 - Api Access Defined as below
    .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
    .antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
    .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
    .antMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
    .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

============

Start the application and run below curl commands

# get Book without credentials
curl -s localhost:8080/users

{"timestamp":"2020-08-05T03:24:22.665+00:00","status":401,"error":"Unauthorized","message":"","path":"/books"}

# post Book without credentials

curl -sX POST localhost:8080/users -H "Content-type:application/json" -d {\"firstName\":\"Sumit\",\"lastName\":\"Gupta\",\"age\":\"35\"}

{"timestamp":"2020-08-05T03:53:55.283+00:00","status":401,"error":"Unauthorized","message":"","path":"/users"}


# get Book with credentials
curl -s localhost:8080/users -u user:password


# post Book with credentials user as "user"
curl -sX POST localhost:8080/users -H "Content-type:application/json" -d {\"firstName\":\"Sumit\",\"lastName\":\"Gupta\",\"age\":\"35\"} -u user:password
{"timestamp":"2020-08-05T03:53:20.334+00:00","status":403,"error":"Forbidden","message":"","path":"/users"}

# post Book with credentials user as "admin"
curl -sX POST localhost:8080/users -H "Content-type:application/json" -d {\"firstName\":\"Sumit\",\"lastName\":\"Gupta\",\"age\":\"35\"} -u admin:password





