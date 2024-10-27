package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDatabaseOperations {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String databaseName = "StudentsDB";
        String username = "root";
        String password = "3832";

        try {
            // Task 1: Connect to MySQL DB, Create DB, and Table
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Create a new database if it doesn't exist
            String checkDbQuery = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + databaseName + "'";
            ResultSet resultSet = statement.executeQuery(checkDbQuery);

            if (!resultSet.next()) {
                String createDbQuery = "CREATE DATABASE " + databaseName;
                statement.executeUpdate(createDbQuery);
                System.out.println("Database '" + databaseName + "' created successfully.");
            } else {
                System.out.println("Database '" + databaseName + "' already exists.");
            }

            statement.execute("USE " + databaseName);
            statement.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), grade INT)");

            // Task 2: Insert Data
            insertSampleData(connection);

            // Task 3: Retrieve Data
            retrieveData(connection);

            // Task 4: Update Data
            updateStudentName(connection, 1, "UpdatedFirstName");

            // Task 5: Delete Data
            deleteStudent(connection, 2);

            // Task 6: Calculate Average Grade
            calculateAverageGrade(connection);

            // Close the resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void insertSampleData(Connection connection) {
        try {
            // Insert a single row
            PreparedStatement insertSingle = connection.prepareStatement("INSERT INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
            insertSingle.setInt(1, 1);
            insertSingle.setString(2, "Hamdi");
            insertSingle.setString(3, "Mohammed");
            insertSingle.setInt(4, 90);
            insertSingle.executeUpdate();

            // Inserts ten more rows to the DB
            Statement statement = connection.createStatement();
            for (int i = 2; i <= 11; i++) {
                String firstname = "FirstName " + i;
                String lastname = "LastName " + i;
                int grade = new java.util.Random().nextInt(61) + 40; // random integer b/n 39 and 99

                String template = String.format("INSERT INTO students (id, firstname, lastname, grade) VALUES (%d, '%s', '%s', %d)", i, firstname, lastname, grade);
                statement.executeUpdate(template);
            }
            System.out.println("Student data inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students LIMIT 5");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int grade = resultSet.getInt("grade");
                System.out.println("ID: "+ id + ", Name: "+ firstname + " " + lastname + ", Grade: " + grade);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateStudentName(Connection connection, int id, String newFirstName) {
        try {
            PreparedStatement updateStatement =
                    connection.prepareStatement("UPDATE students SET firstname = ? WHERE id = ?");
            updateStatement.setString(1, newFirstName);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection connection, int id) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void calculateAverageGrade(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT AVG(grade) AS average_grade FROM students");

            while (resultSet.next()) {
                double averageGrade = resultSet.getDouble("average_grade");
                System.out.println("Average Grade: " + averageGrade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
