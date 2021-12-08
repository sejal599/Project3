/*Sejal Jain
 * ITSS3312.002
 * 11/16/2021
 * Project 2 & 3
 */

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/* This class creates a student management system and a course management system, by reading in which task is selected 
 * and performing various acts upon the Students in the program, using the Student class, Course class, and Student_Employee subclass.
 */
public class SejalJain_Section002_Project2 {

	public static void main(String[] args) throws IOException 
	{
		//VARIABLE DECLARATION: end variable keeps the main loop going, scanner for reading inputs
		
		boolean mainEnd = true;
		Scanner in = new Scanner (System.in);
		
		//ARRAY: creates student array based on reader input on size
		System.out.print("\t\t\tWelcome to Student Management System!\r\n"
				+ "\r\n"
				+ "This system will allow you to manage students. Let’s start with the number of students this system will have: ");
		int length = in.nextInt();
		Student [] students = new Student[length];
		Student [] studentEmployees = new Student_Employee[length];
		
		//FILE CREATION: All 3 files needed, created here.
		File StudReport = new File("StudentReport.txt");
		File CourseFile = new File("Course.txt");
		File CourseAssgn = new File("CourseAssignment.txt");
		StudReport.createNewFile();
		CourseFile.createNewFile();
		CourseAssgn.createNewFile();
		
		//LOOP: main loop for the entire system
		do
		{
			//REQUESTING INPUT: starts the system up each round
			System.out.print("\n***Welcome to Student and Course Management System***"
					+ "\nPress ‘1’ for Student Management System (SMS)"
					+ "\nPress ‘2’ for Course Management System (CMS)"
					+ "\nPress ‘0’ to exit\n\n");
			int ch = in.nextInt();
			if(ch==1)
			{
				boolean end = true;
				do
				{
					//REQUESTING INPUT: starts the system up each round for SMS
					System.out.print("\n***Welcome to SMS***"
							+ "\n\nPress ‘1’ to add a student"
							+ "\nPress ‘2’ to deactivate a student"
							+ "\nPress ‘3’ to display all students"
							+ "\nPress ‘4’ to search for a student by ID"
							+ "\nPress ‘5’ to assign on-campus job"
							+ "\nPress ‘6’ to display all students with on-campus jobs"
							+ "\nPress ‘0’ to exit SMS \n\n");
					int check = in.nextInt();
					boolean b = true;
					int placer =0;
					
					//MENU OPTIONS: checks for what has been inputed, and does the action
					if (check==0)
						end = false;
					else if (check==1)
					{
						//requests for student info	
						System.out.print("Enter first name: ");
						String first = in.next();
						System.out.print("Enter last name: ");
						String last = in.next();
						String level;
						boolean flag = true;
						do
						{
							flag = true;
							System.out.print("Enter level of the student: ");
							level = in.next();
							if (!(level.equals("Freshman")||level.equals("Sophomore") || level.equals("Junior") || level.equals("Senior")))
							{
								flag = false;
								System.out.println("Error: level invalid. Try again.");
							}
						}
						while (!flag);
						
						//creates student
						Student s = new Student(first,last,level);
						
						//adds student to the array at the next available spot
						while (b)
						{
						try
							{
								if (students[placer] == null)
								{
									students[placer] = s;
									b = false;
									System.out.println("\n" + first + " " + last + " has been added as a " + level + " with ID "+ s.getStudentId());
								}
								else
									placer++;
							}
							catch(ArrayIndexOutOfBoundsException e)
							{
								//EXCEPTION 1: to catch ArrayIndexOutOfBounds exception
								System.out.println("Error: system is full.");
								b= false;
							}
						}
					}
					else if (check==2)
					{
						int id = 0;
						//gets ID 
						try
						{
							System.out.print("Enter the ID for the student you want to deactivate: ");
							id = in.nextInt();
							while(b)
							{
								//finds the student, deactivates, and prints
								if (students[placer].getStudentId() == id)
								{
									students[placer].setActive(false);
									System.out.println(students[placer].getFirstName() + " " + students[placer].getLastName() + " has been deactivated");
									b = false;
								}
								else
									placer++;	
							}
						}
						catch(InputMismatchException e) {
							//EXCEPTION 2: error if input is not a number for student ID
							System.out.println("Error: input is not a number, please try again.");
							in.next();
						}
					}
					else if (check==3)
					{
						Student [] temp = new Student[students.length];		
							//establishes the order to be printed
							for (int i =0; i<students.length;i++)
							{
								if (students[i]!=null)
								{
									//scenario if at the beginning of the list versus the end
									if (i!=0)
									{
										//c and temp are temporary placers
										int c = i;
										temp[c] = students[c];
										boolean place = true;
										
										//while loop to move around the array and check all the way down to create ascending order
										while(place)
										{
											if(c!=0)
											{
												//checks if the student before should be moved depending on the first name of the next student in the list
												if(temp[c-1].getFirstName().compareTo(temp[c].getFirstName())>0)
												{
													Student tempor =temp[c-1];
													temp[c-1]=temp[c];
													temp[c]=tempor;
													c--;
												}
												else
													place = false;
											}
											else
												place=false;
										}
									}
									else
										temp[i] = students[i];
								}
							}
							//does the final printing
							for (int a=0; a<temp.length;a++)
							{
								if (temp[a]!=null)
								{
									System.out.println(temp[a].print());
									
									//WRITING FILE: writes into StudentReport.txt
									Files.write(Paths.get("StudentReport.txt"),temp[a].print().getBytes(),StandardOpenOption.APPEND);
								}
							}
					}
					else if (check==4)
					{
						int id = 0;
						//finds the student and prints it
						try
						{
							System.out.print("Enter the student ID: ");
							id = in.nextInt();
							while(b)
							{
								if (students[placer].getStudentId() == id)
								{
									System.out.println(students[placer].print());
									b = false;
								}
								else
									placer++;	
							}
						}
						catch (InputMismatchException e){
							//EXCEPTION 3: error if input is not a number for student ID
							System.out.println("Error: input is not a number, please try again.");
							in.next();
						}
					}
					else if (check==5)
					{
						
						System.out.print("Enter the student ID: ");
						int theID = in.nextInt();
						String job;
						String jobType;
						
						boolean flag = true;
						
						//loop to check that the job given is either TA or RA
						do
						{
							flag = true;
							System.out.print("Enter job: ");
							job = in.next();
							if (!(job.equals("TA")|| job.equals("RA")))
							{
								flag = false;
								System.out.println("Error: level invalid. Options are TA or RA.");
							}
						}
						while(!flag);
						
						//loop to check that the job type given is either part-time or full-time
						do
						{
							flag = true;
							System.out.print("Enter job type: ");
							jobType = in.next();
							if (!(jobType.equals("part-time")|| job.equals("full-time")))
							{
								flag = false;
								System.out.println("Error: level invalid. Options are part-time or full-time.");
							}
						}
						while(!flag);
						
						
						//for loop to assign on campus job to a student
						for (int i =0; i<students.length; i++)
						{
							if(students[i]!=null)
							{
								if(students[i].getStudentId() == theID)
								{
									boolean checker = true;
									int d = 0;
									
									//while loop to stop after finding the correct student
									while (checker)
									{
										if (d < studentEmployees.length)
										{
											if(studentEmployees[d] ==null)
											{
												studentEmployees[d] = new Student_Employee(students[i],job,jobType);
												System.out.println(studentEmployees[d].getFirstName() + " "+ studentEmployees[d].getLastName() + " has been assigned " + ((Student_Employee)studentEmployees[d]).getJobType() + " " + ((Student_Employee)studentEmployees[d]).getEmployment() + " job.");
												checker = false;
											}
											else
												d++;
										}
										else
											checker = false;
									}
								}
							}
						}
					}
					else if (check==6)
					{
						//print all the student employees
						for (int i=0; i<studentEmployees.length;i++)
						{
							if (studentEmployees[i]!=null)
								System.out.println(studentEmployees[i].print());
						}
					}
				}
				while(end);
			}
			else if (ch==2)
			{
					boolean end = true;
					
					do
					{	
						//REQUESTING INPUT: starts the system up each round for SMS
						System.out.print("***Welcome to CMS***"
								+ "\n\nPress ‘1’ to add a new course"
								+ "\nPress ‘2’ to assign student a new course"
								+ "\nPress ‘3’ to display student with assigned courses"
								+ "\nPress ‘0’ to exit CMS\n\n");
						int check = in.nextInt();
						boolean b = true;
						int placer =0;
						if(check==0)
							end = false; 
						else if (check==1)
						{
							//creating a course and adding it to a Course.txt file
								System.out.print("Enter course ID: ");
								int courseID = in.nextInt();
								in.nextLine();
								System.out.print("Enter course name: ");
								String courseName = in.nextLine();
								String fullCourseName = courseName + "-" + courseID + ";";		
					
								//READING FILE: reads the Course.txt file to see if course already exists
								String content = Files.readString(Path.of("Course.txt"), StandardCharsets.US_ASCII);
							
								if(content.indexOf(fullCourseName) >= 0)
									System.out.println("Error: course already exists.");
								else 
								{
									//WRITING FILE: writes into Course.txt
									Files.write(Paths.get("Course.txt"),fullCourseName.getBytes(), StandardOpenOption.APPEND);
									Files.write(Paths.get("Course.txt"),"\n".getBytes(), StandardOpenOption.APPEND);		//to move to a new line
									System.out.println("Confirmation: New course " + courseID + " has been added.\n");
								}
							}
						else if (check==2)
						{
							System.out.print("Enter student ID: ");
							int studID = in.nextInt();
							System.out.print("Enter course ID: ");
							int courseID = in.nextInt();
							String courseID2 = "" + courseID + ";";
							
							//Checks if the course even exists before continuing
							String content = Files.readString(Path.of("Course.txt"), StandardCharsets.US_ASCII);
							if (content.indexOf(courseID2)<0)
								System.out.println("Invalid course ID, course does not exist.");
							else
							{
								//Adds students to a CourseAssignment.txt file with the name, ID, and course they are taking
								for(int i=0; i< students.length; i++)
								{
									if(students[i]!=null)
									{
										if (students[i].getStudentId() == studID)
										{
											String studentAssign = students[i].getFirstName() + " "+ students[i].getLastName() + "\nID - " + studID +"\nCourses: " + courseID + "\n";
											
											//WRITING FILE: writes into CourseAssignment.txt
											Files.write(Paths.get("CourseAssignment.txt"),studentAssign.getBytes(),StandardOpenOption.APPEND);
											System.out.println("Confirmation: " + students[i].getFirstName() + " " + students[i].getLastName() + " has been assigned course " + courseID + "\n");
										}
									}
								}
							}
						}	
						else if (check==3)
						{
							//READING FILE: reads CourseAssignment.txt to print all content
							String content = Files.readString(Path.of("CourseAssignment.txt"), StandardCharsets.US_ASCII);
							System.out.println(content);	
						}
					}
					while (end);
				}
			else 
				mainEnd = false;
		}
		while(mainEnd);
		
		//Closing out all the txt file writes and closing the scanner
		in.close();
		PrintWriter writer = new PrintWriter("Course.txt");
		writer.print("");
		writer.close();
		PrintWriter writer2 = new PrintWriter("CourseAssignment.txt");
		writer2.print("");
		writer2.close();
		PrintWriter writer3 = new PrintWriter("StudentReport.txt");
		writer3.print("");
		writer3.close();
		
		System.out.println("\n\nGoodbye!!");
	}
}


/* Interface CreateStudent includes all the methods required for a student
 */
interface CreateStudent{
	//required methods for a student
	int getStudentId();
	String getFirstName();
	String getLastName();
	String getLevel();
	void setActive(boolean activeLevel);
	String print();}

/* Interface CreateCourse includes all the methods required for a course
 */
interface CreateCourse{
	//required course methods
	int getId();
	String getCourseName();}

/*
 * This class creates Students, with studentId, firstName, lastName, level, and active levels and 
 * implements the CreateStudent interface
 */
class Student implements CreateStudent{
	
	//all the required inputs for creating a Student
	int studentId;
	String firstName;
	String lastName;
	String level;
	boolean active;
	
/*constructor for creating a student, with active set as true
 * firstName2 first name
 * lastName2 last name
 * level2 level
 */
	public Student (String firstName2, String lastName2, String level2)
	{
		studentId = (int)(Math.random()*100);
		firstName = firstName2;
		lastName = lastName2;
		level = level2;
		active = true;
	}
	
/*returns studentId
 */
	public int getStudentId() {return studentId;}
/*returns firstName
 */
	public String getFirstName() {return firstName;}
/*returns lastName
 */
	public String getLastName(){return lastName;}
/*returns level
 */
	public String getLevel(){return level;}
/* sets active level
 * activeLevel new active level
 */
	public void setActive(boolean activeLevel){active = activeLevel;}
	
/*prints the student with all information
 */
	public String print()
	{
		String printActive;
		if (active)
			printActive = "Active";
		else
			printActive = "Inactive";
		
		return firstName + " " + lastName + "\nID: " + studentId + "\nLevel: " + level + "\nStatus: " + printActive + "\n";
	}
}

/*
 * This class creates Student employees, with studentId, firstName, lastName, level, and active levels and extends the student class
 * by including employment and job type for a student as well. 
 */
class Student_Employee extends Student{
	
	//the required inputs to create a student employee.
	String employment;
	String job;
	
	/*
	 * Creates a student employee from a current student and adds their employment history to it.
	 * s Student
	 * employment2 employment place
	 * job2 job type - part time or full time
	 */
	public Student_Employee(Student s, String employment2, String job2)
	{
		super(s.getFirstName(),s.getLastName(),s.getLevel());
		employment = employment2;
		job = job2;
	}
	
	/*returns the job type
	 */
	public String getJobType(){return job;}
	/*returns the employment 
	 */
	public String getEmployment(){return employment;}
	/*getFirstName from student class
	 */
	public String getFirstName(){return super.getFirstName();}
	/*getLastName from student class
	 */
	public String getLastName(){return super.getLastName();}
	/*getStudentId from student class
	 */
	public int getStudentId(){return super.getStudentId();}
	
	/*prints the student employee with all necessary information
	 */
	public String print()
	{
		return getFirstName() + " " + getLastName() + "\nID - " + getStudentId() + "\n" + job + " " + employment + "\n";
	}
}

/*
 * This class creates a Course with a course ID and course name and implements the CreateCourse interface.
 */
class Course implements CreateCourse
{
	//required variables to create a course
	int iD;
	String courseName;
	
	/*
	 * creates a course with an ID and course name
	 * iD2 course ID
	 * courseName2 course name
	 */
	public Course(int iD2, String courseName2)
	{
		iD = iD2;
		courseName = courseName2;
	}
	
	/*returns the course ID
	 */
	public int getId(){return iD;}
	/*returns the course name
	 */
	public String getCourseName(){return courseName;}
}
