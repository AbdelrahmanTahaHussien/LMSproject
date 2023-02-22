import java.io.IOException;
import java.util.Scanner;

public class StudentCoursesManipulation {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        ProgramOptions.print_All_Courses();
        System.out.println("--------------------------------------------------------------------------------------------\r\n"
                + "Please make one of the following:\r\n"
                + "Enter the course id that you want to enroll the student to\r\n"
                + "Enter b to go back to the home page\r\n"
                + "Please select the required action:");
        String action=in.next();
        if (action.equalsIgnoreCase("b")) {
            MainPg.main(args);
        }else {
            ProgramOptions.enroll_course(MainPg.id, Integer.parseInt(action));

        }

    }
}
