# Citizen Information System

## Project Description

The Citizen Information System is a comprehensive application designed to manage and retrieve detailed information about citizens residing in Turkey. This system encapsulates a citizen's personal information, family relationships, contact details, and address records. It integrates seamlessly with multiple databases to gather and present a unified view of a citizen's identity and familial connections.

The application is built using Java and is designed to provide a user-friendly graphical user interface (GUI) that allows users to easily search for citizens, view their family members, and access their contact information. The system ensures secure and efficient handling of sensitive data while maintaining the integrity and consistency of the information retrieved from various databases.

## Author

- Arda Baran

## Features

- **Comprehensive Citizen Management:**
  - Manage detailed information about citizens, including personal details (name, surname, TCKN), family relationships, contact numbers, and living addresses.
  - Track immediate and extended family members such as parents, siblings,children, uncles, aunts, cousins, and grandparents.

- **Family Relationship Mapping:**
  - Visualize family relationships within the citizen data.
  - Retrieve and display detailed family connections , including parental TCKN numbers and relationships to the citizen.
   
- **Database Integration:**
  - Seamlessly integrate with multiple databases, including the citizienDatabase citizen database, the GSM database for mobile numbers, and the address database for tax and address records.
  - Perform SQL queries to retrieve, update, and manage citizen data.

- **Interactive Graphical User Interface (GUI):**
  - User-friendly interface for searching citizens by name, TCKN, or GSM number.
  - Display detailed citizen information, including personal, family, and contact details in a well-organized format.
  - Allow users to navigate through family members and related information with ease.

- **Secure Data Management:**
  - Ensure the secure handling of sensitive citizen data.
  - Implement error handling and resource management to prevent data leaks and maintain system integrity.

- **Customizable Queries:**
  - Provide utility methods for custom SQL queries, allowing flexible data retrieval based on different criteria such as name, TCKN, or GSM number.

## Technologies and Data Structures Used

- **Programming Language:** Java
- **IDE:** Eclipse and MySQL
- **Data Structures:**
  - **HashMap:** Efficiently store and retrieve citizen details and related information.
  - **ArrayList:** Maintain ordered lists of citizen records, search results, and family members.
  - **Set:** Track unique TCKN and GSM numbers associated with citizens, ensuring no duplicates.
  - **DefaultTableModel:** Manage and display tabular data within the GUI, especially for family members.
  - **SQL:** Perform structured queries to interact with MySQL databases, retrieving and updating citizen data.
  
## Classes

### 1. Citizen Class
- **Description:** 
  - The `Citizen` class models a citizen residing in Turkey, encapsulating all relevant personal, familial, and contact details. It includes attributes such as the citizen's name, surname, TCKN, city, county, and the TCKN numbers of their parents. 
  - The class also maintains details of the citizen's family members, including parents, siblings, uncles, aunts, cousins, and grandparents. Additionally, it stores the citizen's phone numbers and living addresses.
  - This class is crucial for integrating and representing data across the system, ensuring that each citizen’s information is complete and easily accessible.

### 2. QueryHelper Class
- **Description:** 
  - The `QueryHelper` class provides utility methods for querying citizen information from connected databases. It allows for searching citizens by name and surname, finding citizens associated with specific GSM numbers, and retrieving tax and address records based on TCKN.
  - The class also manages internal collections for storing query results, which can be further processed or displayed within the application. This class is designed to work seamlessly with the `DatabaseConnection` class to handle SQL queries and manage results effectively.

### 3. TaxAndAddressQueryHelper Class
- **Description:** 
  - The `TaxAndAddressQueryHelper` class is a specialized helper class focused on obtaining and managing a citizen's tax and address records from the address database. It supports specific queries related to the citizen's financial and residential information, ensuring that all data is accurately retrieved and presented.

### 4. DatabaseConnection Class
- **Description:** 
  - The `DatabaseConnection` class is responsible for establishing and managing connections to MySQL databases. It encapsulates the details required to connect to three specific databases: the 'citizienDatabase' database (for citizen data), the 'gsm' database (for mobile numbers), and the 'data' database (for tax and address records).
  - The class provides methods to securely connect to each database, handle connection errors, and ensure that all resources are properly managed and closed after use. This setup is crucial for maintaining data integrity and consistency across the application.

### 5. CitizenInfoForm Class
- **Description:** 
  - The `CitizenInfoForm` class provides the graphical user interface (GUI) for displaying all the information related to a citizen. It integrates data from multiple sources and presents it in an organized manner, allowing users to view comprehensive details about a citizen, including personal details, family members, and contact information.
  - The class also includes functionality to populate a table with the citizen's family members. It handles user interactions, such as clicking on a TCKN to view more detailed information, and ensures that the data is presented in a user-friendly and accessible way.

### 6. Main Class
- **Description:** 
  - The `Main` class serves as the entry point for the application. It initializes the system and launches the main window, allowing users to start interacting with the citizen information system. This class is responsible for setting up the initial environment and ensuring that all necessary components are ready for use.



