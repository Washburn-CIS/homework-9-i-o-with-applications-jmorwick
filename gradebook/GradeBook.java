import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GradeBook {
    private static Student[] students;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // initialize students from contents of grades.txt file
        try {
            Scanner fin = new Scanner(new File("grades.txt"));
            int numStudents = Integer.parseInt(fin.nextLine());
            students = new Student[numStudents];
            for(int i=0; i<numStudents; i++) {
                students[i] = new Student();
                String[] tokens = fin.nextLine().split(",");
                students[i].setFirstName(tokens[0]);
                students[i].setLastName(tokens[1]);
                students[i].setGrade(Double.parseDouble(tokens[2]));
            }
            
        } catch(FileNotFoundException e) {
            System.out.println("Could not open grades.txt");
            System.exit(10);
        }

        System.out.println("Welcome to the CM111 Grade Book App!");

        while(true) {
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1) List Class Grades");
            System.out.println("2) Update Grade");
            System.out.println("3) Exit");
            System.out.print("\nPlease choose an option: ");
            String choice = input.nextLine();
            System.out.println();
            switch(choice) {
                case "1": 
                    for(Student student: students) {
                        System.out.printf("%s, %s: %f%n", student.getFirstName(), 
                                                        student.getLastName(), 
                                                        student.getGrade());
                    }
                    break;
                case "2":
                    System.out.println("Enter First Name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lname = input.nextLine();
                    
                    for(Student student: students) {
                        if(student.getLastName().equals(fname) &&
                           student.getFirstName().equals(lname)) {
                           System.out.println("Enter Grade: ");
                           student.setGrade(Double.parseDouble(input.nextLine()));
                           System.out.println("Grade updated");
                           continue;
                        }
                    }
                    System.out.println("Student not found");
                    break;
                case "3":
                    // write code to save the grades to grades.txt
                    try {
                        PrintWriter fout = new PrintWriter(new File("grades.txt"));
                        fout.println(students.length);
                        for(Student s : students) {
                            fout.printf("%s,%s,%f%n",
                                s.getFirstName(),
                                s.getLastName(),
                                s.getGrade());
                        }
                        fout.close();
                    } catch(FileNotFoundException e) {
                        System.out.println("ERROR!!!! could not save grades to grades.txt!");
                    }
                    System.out.println("Goodbye!");
                    return;

            }
        }
    }
}
