import java.io.IOException;
import java.util.Scanner;

public class MainPg {
    public static String id;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        System.out.println("Welcome to Simple Learning Management System"+"\n"+
                "Created By   : Abdelrahman Taha Hussein  22/02/2023\n"+
                "======================================================================================================="
                +"\n"+"Home page"+"\n"+"================================================================================");
        ProgramOptions.print_All_Student_Data();
        System.out.println("============================================================================================");
        Scanner in=new Scanner(System.in);
        System.out.print("please select the required student:");
        id =in.next();
        System.out.println("============================================================================================"
                +"\n"+"Student Details page\n"+"========================================================================"+"\n");
        System.out.println(ProgramOptions.print_Required_Student(id));
        System.out.println("--------------------------------------------------------------------------------------------\r\n"
                + "Enrolled Courses:");
        ProgramOptions.student_course(id);
        System.out.print("----------------------------------------------------------------------------------------------\n" +
                "Please choose from the following:\r\n"
                + "a - Enroll in a course\r\n"
                + "d - Unenrollfrom an existing course\r\n"
                + "r - Replacing an existing course\r\n"
                + "b - Back to the main page\r\n\n"
                + "please select the required action:");
        String action=in.next();
        switch (action) {
            case "a": {
                StudentCoursesManipulation.main(args);
                break;
            }case "d":{
                ProgramOptions.unEnroll_course(id);
                break;
            }case "r": {
                System.out.print("Please enter the course id to be replaced:");
                String old_Course=in.next();
                ProgramOptions.get_course_By_Id(old_Course);
                System.out.print("Please enter the required course id to replace:");
                String new_Course=in.next();
                ProgramOptions.replace(id, old_Course, new_Course);
                System.out.println("====================================================================================");
                System.out.println(ProgramOptions.print_Required_Student(id));
                System.out.println("------------------------------------------------------------------------------------\r\n"
                        + "Enrolled Courses:");
                ProgramOptions.student_course(id);
                System.out.print("--------------------------------------------------------------------------------------\n" +
                        "Please choose from the following:\r\n"

                        + "a - Enroll in a course\r\n"
                        + "d - Unenroll from an existing course\r\n"
                        + "r - Replacing an existing course\r\n"
                        + "b - Back to the main page\r\n\n"
                        + "please select the required action:");

                break;
            }case "b":{

                MainPg.main(args);
                break;
            }
            default:

                MainPg.main(args);
        }


    }

}
