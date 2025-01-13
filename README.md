# RentSplitter
RentSplitter is a utility application designed to help roommates manage and split their shared bills and expenses. The application allows users to create groups, add bills, track payment due dates, and manage notifications for both bill payers and roommates. Each user can create an account, join a group using a unique code, and keep track of their payment obligations and schedules.

## Setup

### Clone the Repository

```bash
git clone https://github.com/maurasweeney1/RentSplitter.git
cd rent-splitter
```

### Install Dependencies 

Ensure Java, Maven, and MySQL are installed on your system.

#### Java Installation

Verify Java installation by running:
```bash
java -version
```
If not installed, download and install Java from Oracle or use a package manager like Homebrew or APT.

#### Maven Installation

Verify Maven installation by running:
```bash
mvn -v
```
If not installed, install Maven using your system's package manager or download it from Maven's official website.

#### MySQL Installation

Ensure MySQL is installed and running on your system. You'll need to set up a database and configure the connection settings.

### Build the Project

Compile and package the application using Maven:
```bash
mvn clean install
```

### Run the Application

Run the project using the Maven exec plugin:
```bash
mvn exec:java -Dexec.mainClass="personal.rentSplitter.RentSplitter"
```

### Usage

- Launch the application
- Sign up for a new account or sign in with existing credentials
- Create or join a group using a unique group code
- Add bills with details including:
  - Bill name
  - Total amount
  - Due dates
  - Company information
  - Payer assignment
  - Roommate allocation
- Manage and track payments
- Set up notifications for payment reminders

### Features

- User account management
- Group creation and management
- Bill tracking and management
- Payment scheduling
- Due date notifications
- Multiple roommate support
- Bill editing and deletion
- Roommate management

### Contributing

To contribute to the project:

1. Fork the repository
2. Create a new branch for your feature or bug fix
3. Make changes and commit them with clear messages
4. Push to your branch and open a pull request

### Testing

Run the test suite using:
```bash
mvn test
```

### License

This project is licensed under the MIT License. Feel free to use, modify, and share as needed.
