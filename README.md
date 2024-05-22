# Service Booking System

Service Booking System is a full stack application that allows companies to post their services and clients to check and book the services they need. Companies can also manage bookings by accepting or rejecting them.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Contact](#contact)

## Features

### Company Features

- Create account
- Login
- Post ad
- Update & delete ad
- View bookings
- Approve or reject booking

### Client Features

- Create account
- Login
- Search services
- View ad details and reviews
- Book services
- View bookings
- Review services

## Technology Stack

- **Frontend:** Angular 16
  - **UI Library:** Ng Zorro
- **Backend:** Spring Boot 3.0.6
- **Database:** PostgreSQL
- **Authentication:** JWT (JSON Web Tokens)

## Installation

### Prerequisites

Make sure you have the following installed:

- Node.js
- Angular CLI
- Java JDK 17 or higher
- PostgreSQL

### Backend Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/sabrisahlaoui/Service-Booking-System.git
   cd Service-Booking-System/backend
   ```

2. Configure the PostgreSQL database:

   Update the `application.properties` file with your PostgreSQL database credentials.

3. Build and run the backend:

   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup

1. Navigate to the frontend directory:

   ```bash
   cd ../Angular app
   ```

2. Install the dependencies:

   ```bash
   npm install
   ```

3. Run the frontend application:

   ```bash
   ng serve
   ```

The frontend will be available at `http://localhost:4200` and the backend will run on `http://localhost:8080`.

## Usage

1. Register as a company or a client.
2. As a company:
   - Create an account and log in.
   - Post ads for your services.
   - Update or delete your ads as needed.
   - View bookings made by clients.
   - Approve or reject bookings.

3. As a client:
   - Create an account and log in.
   - Search for services.
   - View ad details and read reviews.
   - Book services you are interested in.
   - View your bookings.
   - Review the services you have used.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/YourFeature`).
6. Open a pull request.


## Contact
<p>
<a href="https://www.linkedin.com/in/sabrisahlaoui/">
<img alt="LinkedIn" src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"/>
</a> 
<br>
</p>
---
