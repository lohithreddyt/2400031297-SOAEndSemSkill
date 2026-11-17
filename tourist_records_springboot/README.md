# Tourist Records API — Spring Boot

REST API built with Java 17, Spring Boot 3, Spring Data JPA, H2 database, JWT token validation and Swagger UI.

## Requirements
- Java 17+
- Maven 3.8+

## Run
```bash
mvn spring-boot:run
```
Swagger UI: http://localhost:8080/swagger-ui/index.html

## Login and token
Use `POST /token` with JSON:
```json
{"username":"admin","password":"admin123"}
```
Copy `access_token`. In Swagger UI click **Authorize** and enter `Bearer <token>`. The tourist endpoints and token inspection endpoint require this token.

Demo credentials are for local testing only. Set a strong `JWT_SECRET` environment variable and replace the demo login with a real user store before deployment.

## API endpoints
| Method | URL | Purpose |
|---|---|---|
| POST | `/token` | Generate JWT token |
| GET | `/auth/inspect` | Validate token and show subject/expiry |
| POST | `/tourists` | Create tourist |
| GET | `/tourists` | Retrieve all tourists |
| GET | `/tourists/{id}` | Retrieve tourist by ID |
| PUT | `/tourists/{id}` | Update tourist |
| DELETE | `/tourists/{id}` | Delete tourist |

## Sample tourist request
```json
{
  "name": "Asha Rao",
  "email": "asha@example.com",
  "destination": "Taj Mahal",
  "country": "India",
  "visitDate": "2026-12-10",
  "notes": "Family trip"
}
```
H2 database is stored locally in `./data/touristsdb`.
