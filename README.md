⸻

🚀 EMAIL VALIDATION API — README.md

📌 Overview

The Email Validation API is a high-accuracy REST API built using Spring Boot 3 + Java 17.
It validates an email across multiple layers:

✔ Syntax
✔ MX Records
✔ SMTP Connectivity
✔ Disposable Domain Check
✔ Score (0–100)
✔ Suggestions (Advanced)
✔ Domain Reputation (Advanced)

This API is designed as a sellable SaaS API with API-key security, versioning, and future monetization support.

⸻

🏗 Architecture Overview

Tech Stack
•	Java 17
•	Spring Boot 3.x
•	Maven
•	dnsjava (MX lookup)
•	Apache Commons Validator (syntax)
•	Jakarta Mail (SMTP handshake)
•	Docker
•	Swagger / OpenAPI
•	Railway / Render Deployment

⸻

🔥 VERSIONING

The API comes with two versions:
•	/api/v1 → Intermediate Version (Production-ready + Sellable)
•	/api/v2 → Advanced Version (AI-powered + Enterprise-level)

⸻

⸻

🟦 INTERMEDIATE VERSION (v1)

This version is designed to launch fast, validate emails accurately, and be monetized.

✨ Features

✅ 1. Syntax Validation

Checks if the email format matches RFC standards.

✅ 2. MX Record Lookup

Confirms whether the domain has valid mail servers.

✅ 3. SMTP Connectivity Test

Attempts a lightweight handshake to verify server reachability.

❗ Note:

Most big providers (Gmail, Yahoo, Outlook, corporate domains) block SMTP checks, so smtpConnectivity = false is expected.

✅ 4. Disposable Domain Detection

Uses a 10k+ domain list to block temp emails.

✅ 5. Email Quality Score (0–100)

Calculated based on:
•	Syntax
•	MX
•	SMTP
•	Disposable check

✅ 6. API Key Security

Uses x-api-key header for authentication.

✅ 7. Swagger UI Documentation

Available at:

/swagger-ui/index.html

✅ 8. Docker Support

Builds and deploys using a lightweight Java 17 runtime.

⸻

📌 API Endpoint (Intermediate)

GET /api/v1/validate?email=someone@example.com

Example Response

{
"email": "test@gmail.com",
"validSyntax": true,
"validMx": true,
"smtpConnectivity": false,
"disposable": false,
"suggestion": null,
"reason": "SMTP not reachable",
"score": 80
}


⸻

🚀 Docker Commands

Build:

mvn clean package -DskipTests
docker build -t email-api .

Run:

docker run -p 8081:8081 email-api


⸻

🌍 Deployment

Recommended free hosting:
•	Railway.app
•	Render.com
•	Fly.io

⸻

⸻

🟥 ADVANCED VERSION (v2)

This version introduces AI, ML scoring, advanced detection, and enterprise-grade features.

✨ Additional Features in Advanced Version

⭐ 1. AI-Powered Typo Correction

Detects common mistakes:
•	gmial → gmail
•	yaho → yahoo
•	hotmial → hotmail

Response example:

"suggestion": "Did you mean test@gmail.com?"


⸻

⭐ 2. Domain Reputation Score

Factors include:
•	Domain age
•	DNS stability
•	Spam history
•	Provider reputation (Google Workspace, MS365, Zoho, AWS SES)

⸻

⭐ 3. Catch-all Domain Detection

Detects domains where ANY email appears valid.

"catchAll": true

Examples: Google Workspace, OpenAI, Companies etc.

⸻

⭐ 4. Enhanced Deliverability Score (0–100)

Smarter algorithm using:
•	SMTP behavior
•	Provider reputation
•	Catch-all patterns
•	Historical validation trends

⸻

⭐ 5. Auto-Updating Disposable Email List

Daily auto-sync using a remote raw file or GitHub source.

⸻

⭐ 6. API Key Management System
•	Generate keys
•	Store in DB
•	Track usage
•	Rate limit
•	Assign free & paid plans

⸻

⭐ 7. Rate Limiting
•	FREE users → 50/day
•	PRO users → unlimited

⸻

⭐ 8. Analytics & Logs
•	Requests per key
•	Success/failure counts
•	Geo breakdown
•	Dashboard UI (optional)

⸻

📌 Advanced Endpoint

GET /api/v2/validate?email=someone@example.com

Example Response (Advanced)

{
"email": "suraj@gmial.com",
"validSyntax": true,
"validMx": true,
"smtpConnectivity": false,
"disposable": false,
"catchAll": false,
"domainReputation": 92,
"score": 88,
"suggestion": "Did you mean suraj@gmail.com?",
"reason": "Domain valid but SMTP blocked"
}


⸻

📦 Project Structure

src/main/java/com/emailvalidator/
├── controller/
│     ├── EmailValidationControllerV1.java
│     └── EmailValidationControllerV2.java
├── service/
│     ├── IntermediateService.java
│     └── AdvancedService.java
├── util/
│     ├── DnsUtil.java
│     ├── SmtpUtil.java
│     ├── DisposableEmailUtil.java
├── config/
│     ├── SwaggerConfig.java
│     └── ApiKeyFilter.java
├── model/
│     └── ValidationResponse.java


⸻

🎯 Roadmap

Intermediate Version (DONE)

✔ Syntax
✔ MX
✔ SMTP
✔ Disposable
✔ Score
✔ API Key
✔ Swagger
✔ Docker

Advanced Version (Upcoming)

⬜ AI Typo Correction
⬜ Domain Reputation Engine
⬜ Catch-All Detection
⬜ ML Deliverability Score
⬜ Automated Disposable List Sync
⬜ DB-based API Keys
⬜ Rate Limiting
⬜ User Dashboard

⸻

🤝 Contributing

Pull requests are welcome.
Please follow the project structure and coding standards.

⸻

📜 License

MIT License (or choose any you prefer).

⸻
