# app-jwt-spring
sample code for JWT auth spring boot

'''
curl -X POST http://localhost:8081/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"test","password":"test"}' \
-v
'''

'''
curl -X GET http://localhost:8081/api/test \
-H "Authorization: Bearer YOUR_TOKEN_HERE" \
-v
'''
