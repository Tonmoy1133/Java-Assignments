/* Add constructors in the Student class of earlier problem so that objects can be created with i)roll only, ii)roll and name only, iii)roll, name and score, iv)no value. Also include a copy constructo. Check whether constructors are working or not. Verify, copy constructor results into deep copy or not. */

import java.util.Scanner;

class Student2{
    private int roll;
    private String name;
    private float score;

    public Student2(int roll,String name,float score){// with roll , name and score
        this.name=name;
        this.roll=roll;
        this.score=score;
        System.out.println("Constructor with roll , name and score called");
    }

    public Student2(int roll,String name){// with roll and name
        this.roll=roll;
        this.name=name;
        System.out.println("Constructor with roll and name called");
    }

    public Student2(int roll){// with roll only
        System.out.println("Constructor with only roll called");
        this.roll=roll;
    }

    

    public Student2(Student2 s2){// default constructor
        this.name=s2.name;
        this.roll=s2.roll;
        this.score=s2.score;
        //copy constructor called
    }

    public Student2(){//default constructor
        System.out.println("Default constructor called");
    }


    // public void setAttributes(String name,int roll,float score){
    //     this.name=name;
    //     this.roll=roll;
    //     this.score=score;
    // }

    public void display(){
        System.out.println("Name : "+name);
        System.out.println("Roll : "+roll);
        System.out.println("Score : "+score);
    }

    public void setAttributes(String name,int roll,float score){
        this.name=name;
        this.roll=roll;
        this.score=score;
    }

    // public void copy(Student s2){
    //     s2.name=name;
    //     s2.roll=roll;
    //     s2.score=score;
    // }
    
}


public class ProbNo8 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String name;
        int roll;
        float score;
        System.out.println("Enter the name");
        name=sc.nextLine();//taking input for name
        System.out.println("Enter the roll no");
        roll=sc.nextInt();//taking input for roll
        System.out.println("Enter the score");
        score=(float)(sc.nextFloat());//taking input for score
        Student2 s1=new Student2(roll,name,score);
        Student2 s2=new Student2(roll,name);
        Student2 s3=new Student2(roll);
        Student2 s4=new Student2(s1);
        s4.display();//for checking whether it is working or not
        if(s1==s4){//for checking whether copy constuctor results in deep copy or not
            System.out.println("Copy constructor results in shallow copy");
        }
        else{
            System.out.println("Copy constructor results in deep copy");
        }
        sc.close();
    }
}
