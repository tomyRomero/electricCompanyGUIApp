## 📋 Table of Contents

1. 🖥️[Introduction](#introduction)
2. 🔍[Problem](#problem)
3. 💡[Solution](#solution)
4. 💻[Tech](#tech)
5. 📝[Final Comments](#final-comments)
6. 🖼️[Graphics](#graphics)
7. 📫[Contact](#contact)

## <a name="introduction">🖥️ Introduction</a>

The following application is an adaptation implementation of a desktop app for my local power supplier company and was created for educational purposes.

The application uses Java framework Spring to provide a Graphical User Interface experience. This was the first app I ever created during college and was used for a project.

I had limited knowledge back in the day and no access to database technologies, I had limited knowledge of database tools.

## <a name="problem">🔍 Problem</a>

I wanted to save data and use that data to create User accounts and Login, while the changes are made in real-time, without knowledge of a database.

## <a name="solution">💡 Solution</a>

I created my own personal database with the use of Binary and Txt Files. When creating an account I would write to a User txt file then I would implement a scanner to create the account based on data on each line, each line represents an account. Then I would create a binary file off that data and store inside a folder called database, the database folder would contain numerous accounts, starting from 0 to however many accounts there are with the .dat file extension. This allowed me to serialize objects into binary files and read but also update the objects into the binary files I am using, this resulted in real-time updates of accounts and data being saved whereas even if the application is closed when I return to it all the changed data remained. Please feel free to test it yourself, one feature that shines with this method was the change password page, which would update the password in the text file as well as in the binary file.

## <a name="tech">💻 Tech</a>

- **Java Framework:** ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- **Data Storage:** ![Binary Files](https://img.shields.io/badge/Binary%20Files-333333?style=for-the-badge&logo=File&logoColor=white), ![Txt Files](https://img.shields.io/badge/Txt%20Files-333333?style=for-the-badge&logo=File&logoColor=white)
- **Serialization:** ![Java Object Serialization](https://img.shields.io/badge/Java%20Object%20Serialization-007396?style=for-the-badge&logo=Java&logoColor=white)
- **GUI:** ![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=Java&logoColor=white)

## <a name="final-comments">📝 Final Comments</a>

The program uses an object-oriented programming approach and is a skeleton app in which more features can be implemented, it also comes with admin side functionalities that pave the path to more updates in the future, allowing open-source contributions.

## <a name="graphics">🖼️ Graphics</a>

### <a name="login">Login</a>
![Login](Login.png)

### <a name="create-account">Create Account</a>
![Create Account](CreateAccount.png)

### <a name="customer-home">Customer Home</a>
![Customer Home](CustomerHome.png)

### <a name="admin-home">Admin Home</a>
![Admin Home](AdminHome.png)

### <a name="customer-account-info">Customer Account Info</a>
![Customer Account Info](CustomerAccountInfo.png)

### <a name="customer-change-password">Customer Change Password</a>
![Customer Change Password](ChangePassword.png)

### <a name="customer-account-payment">Customer Account Payment</a>
![Customer Account Payment](CustomerAccountPayment.png)

### <a name="customer-consumption">Customer Consumption</a>
![Customer Consumption](CustomerConsumption.png)

## <a name="contact" > 📫 Contact </a>
tomyfletcher99@hotmail.com
[![LinkedIn](https://img.shields.io/badge/-LinkedIn-0A66C2?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/tomy-romero-902476145/)
