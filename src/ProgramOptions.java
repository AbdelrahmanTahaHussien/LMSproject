import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramOptions {
   
    public static void print_All_Student_Data() {
        System.out.println("-------------------------------\r\n"+ "Student List:\r\n"+ "-------------------------------");
        File f=new File(".\\Files\\StdsData.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;

            for (int i = 0; i <= 100; i++) {
                line = reader.readLine();
                String[] part=line.split(",");
                if (i==0) {
                    System.out.format("%1s %1s %15s %15s %24s %20s %10s\n", part[0],part[1],part[2],part[3],part[4],part[5],part[6]);
                    continue;
                }
                System.out.format("%1s %4s %5s %15s %1s %5s %10s\n", part[0],part[1]+",",part[2]+",",part[3]+",",part[4]+",",part[5]+",",part[6]);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String print_Required_Student(String id) {
        File f=new File(".\\Files\\StdsData.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;

            while ((line=reader.readLine()) !=null) {

                String[] part=line.split(",");
                if (part[0].equalsIgnoreCase(id)) {
                    return"Name:"+part[1]+"      "+"Grade:"+part[2]+"       "+"Email:"+part[3];
                }
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return"The input you have provided is invalid, please enter a valid input";
    }
    public static void student_course(String id) throws FileNotFoundException, IOException {
        String resourceName = ".\\Files\\StdCoursedetail.json";
        InputStream inputStream = new FileInputStream(resourceName);

        JSONTokener jsonTokener = new JSONTokener(inputStream);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(id);
            List courseList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                courseList.add(jsonArray.getInt(i));


            }
            File f=new File(".\\Files\\Courses.csv");
            BufferedReader reader = null;

            reader = new BufferedReader(new FileReader(f));
            String line;

            while ((line=reader.readLine()) !=null) {

                String[] part=line.split(",");
                String x=part[0];
                for (int i = 0; i < courseList.size(); i++) {

                    if (part[0].equalsIgnoreCase(courseList.get(i).toString())) {

                        System.out.println(i+1+"-  "+part[0]+",     "+part[1]+",     "+part[2]+","+part[3]+",    "+part[4]+",    "+part[5]+",    "+part[6]+",");

                    }

                }
            }
            reader.close();

        }
        catch (Exception e) {

            System.out.println("Student has not enrolled in any course yet.");

        } finally {
        }

    }

    public static void enroll_course(String student_Id,int course_Id) throws FileNotFoundException {
        String resourceName = ".\\Files\\StdCoursedetail.json";
        InputStream inputStream = new FileInputStream(resourceName);

        JSONTokener jsonTokener = new JSONTokener(inputStream);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        try {
            org.json.JSONArray jsonArray = jsonObject.getJSONArray(student_Id);
            if (course_Id >17) {
                System.out.println("The input you have provided is invalid, please enter a valid input");
            }else {
                if (jsonArray.length()<6) {
                    jsonArray.put(jsonArray.length(), course_Id);
                    System.out.println("The student is Enrolled Successfully");
                }else {
                    System.out.println("Students can’t enroll in more than 6 programs at the same time.");
                }
            }
            List courseList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                courseList.add(jsonArray.getInt(i));


            }
            FileWriter writer = new FileWriter(".\\Files\\StdCoursedetail.json", false);
            writer.write(jsonObject.toString());
            writer.close();

        }
        catch (Exception e) {

            System.out.println(e);

        } finally {
        }



    }


    public static void unEnroll_course(String student_Id) throws FileNotFoundException {
        String resourceName = ".\\Files\\StdCoursedetail.json";
        InputStream inputStream = new FileInputStream(resourceName);

        JSONTokener jsonTokener = new JSONTokener(inputStream);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        try {
            org.json.JSONArray jsonArray = jsonObject.getJSONArray(student_Id);
            if (jsonArray.length() >1) {

                jsonArray.remove(jsonArray.length()-1);
                System.out.println("unenrolled successfully");


            }else {
                System.out.println("Option Not Available - Students should at least have 1 course");
            }
            List course = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                course.add(jsonArray.getInt(i));


            }
            FileWriter writer = new FileWriter(".\\Files\\StdCoursedetail.json", false);
            writer.write(jsonObject.toString());
            writer.close();
        }
        catch (Exception e) {

            System.out.println(e);

        } finally {
        }

    }

    //Function to print list of courses
    public static void print_All_Courses() {
        System.out.println("Available Courses\r\n"
                + "=====================================================================================================");
        File f=new File(".\\Files\\Courses.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            int i=0;
            while((line = reader.readLine())!= null) {

                String[] part=line.split(",");
                if (i==0) {
                    i++;
                    System.out.format("%1s %1s %15s %25s %20s %10s\n", "id,","Course Name,","Instructor,","Course duration,","Course time,","Location");
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.format("%1s %13s %22s %15s %20s %10s\n", part[0],part[1]+",",part[2]+part[3]+",",part[4]+",",part[5]+",",part[6]);
                    continue;
                }
                System.out.format("%1s %13s %20s %15s %20s %10s\n", part[0],part[1]+",",part[2]+part[3]+",",part[4]+",",part[5]+",",part[6]);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void get_course_By_Id(String id) {

        File f=new File(".\\Files\\Courses.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            while((line = reader.readLine())!= null) {

                String[] part=line.split(",");
                if (part[0].equalsIgnoreCase(id)) {
                    System.out.println("Available courses\r\n"
                            + "=========================================================================================");
                    System.out.format("%1s %1s %15s %25s %20s %10s\n", "id,","Course Name,","Instructor,","Course duration,","Course time,","Location");
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.format("%1s %13s %22s %15s %20s %10s\n", part[0],part[1]+",",part[2]+part[3]+",",part[4]+",",part[5]+",",part[6]);
                    System.out.println("--------------------------------------------------------------------------------");

                }

            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    public static void replace(String student_Id,String old_Course,String new_Course) throws FileNotFoundException {
        String resourceName = ".\\Files\\StdCoursedetail.json";
        InputStream inputStream = new FileInputStream(resourceName);

        JSONTokener jsonTokener = new JSONTokener(inputStream);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        try {
            org.json.JSONArray jsonArray = jsonObject.getJSONArray(student_Id);


            if (jsonArray.length()==0) {
                System.out.println("Failed\n"+"  Student hasn't enrolled in any course yet");
            }else {
                for (int i = 0; i < jsonArray.length(); i++) {


                    if (jsonArray.getInt(i)==Integer.parseInt(old_Course)) {

                        jsonArray.put(i, Integer.parseInt(new_Course));
                        System.out.println("Courses replaced successfully");
                    }

                }
                FileWriter writer = new FileWriter(".\\Files\\StdCoursedetail.json", false);
                writer.write(jsonObject.toString());
                writer.close();

            }
        }
        catch (Exception e) {

            System.out.println(e);

        } finally {
        }

    }


}
