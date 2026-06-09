# 🪙 XAUUSD Live Analytics Pipeline

A real-time, event-driven data streaming pipeline built with **Spring Boot** and **Apache Kafka** to ingest, process, and broadcast live spot gold (XAUUSD) market metrics. The application features a fully reactive front-end dashboard that updates ticker prices instantly via a decoupled microservices architecture.

---

## 🏗️ Architecture Overview

The project is structured as a decoupled multi-module microservice ecosystem:

```text
[ Live Market Ticks ] 
         │
         ▼
 ┌───────────────────┐
 │ Ingestion Service │ (Produces ticks every 200ms)
 └─────────┬─────────┘
           │
           ▼ [Kafka Topic: xauusd-ticks on port 9094]
 ┌───────────────────┐
 │  Apache Kafka     │ (Event Streaming Broker)
 └─────────┬─────────┘
           │
           ▼
 ┌───────────────────┐
 │ Analytics Service │ (Consumes streams & exposes websocket/static UI)
 └─────────┬─────────┘
           │
           ▼ [Port 8082]
 ┌───────────────────┐
 │ Live UI Dashboard │ (Real-Time HTML5/JS Frontend)
 └───────────────────┘



 ingestion-service: Simulates/ingests hyper-frequent spot price updates and publishes structural telemetry to a local Kafka cluster.

analytics-service: Acts as a high-throughput stream consumer, performing sliding window transformations, real-time calculations, and driving the web interface.

🛠️ Tech Stack & Specs
Backend Framework: Spring Boot 3.2.5 (Java 17)

Message Broker: Apache Kafka 3.6.2

Build Automation: Maven 3.x

CI/CD Pipeline: GitHub Actions (Automated verification on Ubuntu-latest runners)

Containerization: Docker Compose

🚀 Quick Start Guide
1. Spin up the Infrastructure
Boot up the containerized Apache Kafka environment using the root docker configuration:

Bash
docker-compose up -d
2. Launch the Ingestion Service
Open a terminal, navigate to the ingestion layer, and run the producer:

Bash
cd ingestion-service
mvn spring-boot:run
The service will initialize Tomcat on port 8081 and immediately start pushing mock metric data to localhost:9094.

3. Launch the Analytics Engine
Open a separate terminal window, step into the analytics service, and activate the consumer:

Bash
cd analytics-service
mvn spring-boot:run
This service deploys on port 8082 and mounts the presentation engine.

📊 Viewing the Live Dashboard
Once both microservices are streaming data, open any web browser and navigate to:

Plaintext
http://localhost:8082/index.html
You will be greeted by a swanky, Japanese-minimalism inspired dashboard showing live-updating spot gold rates running end-to-end through your distributed architecture.

⚙️ Automated CI/CD Execution
This project includes a native GitHub Actions pipeline configured inside .github/workflows/ci-cd.yml. Every code push or pull request to the main branch automatically triggers an isolated build runner that verifies compilation integrity across all services using:

Automated checking setups with Eclipse Temurin JDK 17

Isolated Maven multi-module unit build cycles with implicit flag filters (-DskipTests)


---

### 💾 Step 2: Push it directly to GitHub

Once you save the file locally, run these quick Git commands in your terminal to sync your profile updates:

```powershell
git add README.md
git commit -m "docs: create professional root project readme layout"
git push origin main