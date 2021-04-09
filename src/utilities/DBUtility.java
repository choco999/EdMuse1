package utilities;

import models.Professor;
import models.Student;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
//    private static String user = "student";
//    private static String password = "student";
    private static String user = "root";
    private static String password = "";
    private static String connString = "jdbc:mysql://localhost:3306/edmuse";

    public static boolean validCRN(String crn){
        return crn.matches("21[0-9]{3}");
    }

    public static ArrayList<Student> getStudentsFromDB() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        //connect to the DB
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //connect to the Database
            conn = DriverManager.getConnection(connString, user, password);

            statement = conn.createStatement();

            //run the query on the DB
            resultSet = statement.executeQuery("SELECT * FROM students");

            // loop over the resultSet and create student object
            while (resultSet.next()){
                Student newStudent = new Student(resultSet.getString("firstName"),
                                                 resultSet.getString("lastName"),
                                                 resultSet.getString("address"),
                                                 resultSet.getDate("birthday").toLocalDate(),
                                                 resultSet.getInt("studentNum"));
                students.add(newStudent);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }

//        //query the DB for students
//        //loop over the result and create Student objects...add them to our students ArrayList
//        students.add(new Student("Rose","Ruffner","3846 St. Paul StreetSt Catharines ON L2S 3A1", LocalDate.of(1975,8,27),100000001));
//        students.add(new Student("Jack","Bradbury","867 rue des Églises Est Ste Cecile De Masham QC J0X 2W0", LocalDate.of(1979,10,14),100000002));
//        students.add(new Student("Elanore","Sanders","1145 47th Avenue Grassland AB T0A 1V0", LocalDate.of(1940,9,25),100000003));
//        students.add(new Student("Nancy","Walsh","1459 Harvest Moon Dr Unionville ON L3R 0L7", LocalDate.of(1999,1,12),100000004));
//        students.add(new Student("Greta","Tolbert","642 Front Street Toronto ON M5J 2N1", LocalDate.of(1957,12,18),100000005));
//        students.add(new Student("Barbara","Gable","3671 Scotchmere Dr Sarnia ON N7T 7T9", LocalDate.of(2002,11,13),100000006));

        return students;
    }

    public static ArrayList<Professor> getProfessorsFromDB() throws SQLException {
        ArrayList<Professor> professors = new ArrayList<>();

        //connect to the DB
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //connect to the Database
            conn = DriverManager.getConnection(connString, user, password);

            statement = conn.createStatement();

            //run the query on the DB
            resultSet = statement.executeQuery("SELECT * FROM professors");

            // loop over the resultSet and create student object
            while (resultSet.next()){
                Professor newProfessor = new Professor(resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("address"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getInt("professorID"));
                professors.add(newProfessor);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }


//        professors.add(new Professor("Lois","Parker","674 Goyeau Ave Windsor ON N9A 1H9", LocalDate.of(1987,3,21),10001));
//        professors.add(new Professor("Ginger","Harris","3514 Yonge Street Toronto ON M4W 1J7",LocalDate.of(1967,11,12),10002));
//        professors.add(new Professor("Winchester","Solomon","3099 Balmy Beach Road Owen Sound ON N4K 2N7",LocalDate.of(1977,12,18),10003));
//        professors.add(new Professor("John","Pressley","1101 Eglinton Avenue Toronto ON M4P 1A6",LocalDate.of(1973,11,29),10004));
        return professors;
    }


    public static int insertStudentIntoDB(Student newStudent) throws SQLException {
        int studentNum = -1;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // connect to the DB
            conn = DriverManager.getConnection(connString, user, password);

            // create our sql statement
            statement = conn.prepareStatement("INSERT INTO students (firstName, lastName, address, birthday) VALUES " +
                    "(?,?,?,?)", new String[]{"studentNum"});

            // bind the values to the datatypes
            statement.setString(1,newStudent.getFirstName());
            statement.setString(2,newStudent.getLastName());
            statement.setString(3,newStudent.getAddress());
            statement.setDate(4, Date.valueOf(newStudent.getBirthday()));

            // execute the insert
            statement.executeUpdate();

            // get the student number returned
            resultSet = statement.getGeneratedKeys();

            // update the student number variable
            while (resultSet.next())
                studentNum = resultSet.getInt(1);

        } catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }


        return studentNum;
    }

    public static ArrayList<String> getCourseCodesAndNames(){
        ArrayList<String> courses = new ArrayList<>();

        // try with resources .. doing this will auto-close the objects that can be closed
        try(
                Connection conn = DriverManager.getConnection(connString, user, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from approvedCourses");
                )
        {
            while(resultSet.next()){
                courses.add(resultSet.getString("courseCode"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

}
