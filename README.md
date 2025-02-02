

# 🏢 **Apartment Building Control System**

A **Java-based** application to manage heating and cooling systems for rooms in an apartment building. It allows dynamic temperature adjustments and room additions.

---

## 📂 **Project Structure**

```
📦 ApartmentBuilding
 ┣ 📂 src/main/java/com/apartmentbuilding
 ┃ ┣ 📂 entity         🏠 Room, Apartment, CommonRoom classes  
 ┃ ┣ 📂 service        ⚙️  BuildingService (core logic)  
 ┃ ┣ 📂 exception      🚨 Custom exceptions (e.g., DuplicateRoomException)  
 ┃ ┗ 📜 Main.java      🎯 Entry point of the application  
 ┣ 📂 src/test/java    ✅ Unit tests  
 ┣ 📜 pom.xml          🛠️ Maven dependencies  
 ┣ 📜 Dockerfile       🐳 Docker containerization  
 ┗ 📜 README.md        📖 Project documentation  
```

---

## 🛠 **Prerequisites**

Ensure you have the following installed:

- ☕ **Java JDK 11+**
- 📦 **Maven 3.8+**
- 🐳 **Docker** *(optional for containerized deployment)*

---

## 🚀 **Running the Application**

### ▶️ **Using Maven**

1️⃣ **Build the JAR file:**
```bash
mvn clean package
```  

2️⃣ **Run the application:**
```bash
java -jar target/building-control-1.0-SNAPSHOT.jar
```  

### 🐳 **Using Docker** *(Optional)*

1️⃣ **Build the Docker image:**
```bash
docker build -t building-control .
```  

2️⃣ **Run the application in a container:**
```bash
docker run -it --rm building-control
```  

---

## 🌟 **Features**

✅ **Initialize a building** with 2 apartments, a gym, and a library  
✅ **Dynamically add** apartments and common rooms  
✅ **Adjust the building temperature** to trigger heating/cooling logic:
- 🔥 **Heating ON** if room temp **<** building temp
- ❄️ **Cooling ON** if room temp **>** building temp  
  ✅ **Unique room ID validation** to prevent duplicates

---

## 🏛 **Design Patterns Used**

🟡 **Singleton Pattern** → Ensures only one instance of `BuildingService` exists.  
🔵 **Factory Pattern** → Used for creating different types of rooms (apartments, gym, library).  
🟢 **Observer Pattern** → Notifies rooms when temperature changes.

---

## 🧪 **Testing**

Run unit tests using:
```bash
mvn test
```  

### 🔍 **Key Test Cases:**
✔️ Room creation (valid & invalid IDs)  
✔️ Heating/cooling logic validation  
✔️ Preventing duplicate room IDs

---

## 🏗️ **Key Classes & Responsibilities**

### 🏠 **Entity Classes (`/entity`)**
📌 **Room** → Base class for all rooms  
📌 **Apartment** → Extends `Room` with owner name  
📌 **CommonRoom** → Extends `Room` with type *(Gym, Library, Laundry)*

### 🚨 **Exception Handling (`/exception`)**
⚠️ **DuplicateRoomException** → Thrown when a room ID is duplicated

### ⚙️ **Service Layer (`/service`)**
🛠 **BuildingService** → Manages rooms and temperature adjustments

---

## 📋 **Logging Mechanism**

Uses **`java.util.logging`** with different levels:

🟢 **INFO** → Room creation, temperature updates  
🟡 **WARNING** → Invalid user input  
🔴 **SEVERE** → Critical errors (e.g., duplicate room IDs)

---

## 🐳 **Docker Configuration**

- 🏗️ **Multi-stage build** for optimized image size
- 📌 **Uses OpenJDK 11** as the base image

---

## 📜 **License**

📄 This project is licensed under the **MIT License**. See [LICENSE](LICENSE) for details.

---

### 🎯 **Why This README Stands Out?**
✅ **Clean formatting** with emojis for better readability  
✅ **Structured sections** with bullet points  
✅ **Visual representation** of project structure  
✅ **Concise yet informative** descriptions

Now your README is **well-structured, easy to read, and visually appealing**! 🎉 Let me know if you need more refinements. 🚀💡