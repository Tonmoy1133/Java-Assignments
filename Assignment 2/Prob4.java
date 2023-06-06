/*
 * 4. Consider the packages designed in previous question.  Design an interface to ensure that the library management must have the option i) add book ii) search book iii) view all book iv) add member v) search a member  vii) view all members viii) issue book ix) return book.
Design the system by implementing the interface

 */

 import java.util.Scanner;
 import java.util.ArrayList;
 import LIBRARY.BOOKLIST.*;
 import LIBRARY.MEMBERLIST.*;
 import LIBRARY.TRANSACTIONLIST.*;

 interface LibraryInterface{
    public void addBook();
    public void addCopies();
    public void showBookList();
    public void showBook();
    public void addMember();
    public void showMemberList();
    public void showMember();
    public void issueBook();
    public void returnBook();
    public void displayTransactionList();
 }
 
 class Library implements LibraryInterface{
     private BookList arrBook;
     private MemberList arrMember;
     private TransactionList arrTransaction;
     public Library(){
         arrBook=new BookList();
         arrMember=new MemberList();
         arrTransaction=new TransactionList();
     }
     public void addBook(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the title of the book ");
         String title=sc.nextLine();
         System.out.println("Enter the no of copies");
         int copies=sc.nextInt();
         System.out.println("Book is added");
         arrBook.addBook(title, copies);
     }
     public void addCopies(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the book id");
         int id=sc.nextInt();
         if(!arrBook.isPresent(id)){
             System.out.println("The book with the given id is not present");
             return ;
         }
         Book temp=arrBook.getBook(id);
         System.out.println("Enter the number of copies to add");
         int copies=sc.nextInt();
         temp.addCopies(copies);
         System.out.println("Copy added");
     }
     public void showBookList(){
         arrBook.display();
     }
     public void showBook(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the book id");
         int id=sc.nextInt();
         if(!arrBook.isPresent(id)){
             System.out.println("The book with the given id is not present");
             return ;
         }
         Book temp=arrBook.getBook(id);
         temp.display();
     }
     public void addMember(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the name ");
         String name=sc.nextLine();
         System.out.println("Enter the date of birth");
         String dateOfBirth=sc.nextLine();
         System.out.println("Member is added");
         arrMember.addMember(name, dateOfBirth);
     }
     public void showMemberList(){
         arrMember.display();
     }
     public void showMember(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the member id");
         int id=sc.nextInt();
         if(!arrMember.isPresent(id)){
             System.out.println("The member with the given id is not present");
             return ;
         }
         Member temp=arrMember.getMember(id);
         temp.display();
         ArrayList<Integer> arrId=arrTransaction.getBookIds(Integer.toString(id));
         int n=arrId.size();
         System.out.println("Books: ");
         for(int i=0;i<n;i++){
             String bookName=arrBook.getBook(arrId.get(i)).getTitle();
             System.out.print(bookName+",");
         }
         System.out.println("");
     }
     public void issueBook(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the member id");
         int memberId=sc.nextInt();
         if(!arrMember.isPresent(memberId)){
             System.out.println("The member with given id is not exist");
             return ;
         }
         Member member=arrMember.getMember(memberId);
         if(!member.isEligible()){
             System.out.println("The member has exhausted his limit");
             return ;
         }
         System.out.println("Enter the book id");
         int bookId=sc.nextInt();
         if(!arrBook.isPresent(bookId)){
             System.out.println("The book with given id is not present");
             return ;
         }
         Book book=arrBook.getBook(bookId);
         if(book.getAvailabe()<=0){
             System.out.println("The book is not available");
             return ;
         }
         member.incrementBookIssued();
         book.incrementIssued();
         Transaction temp=arrTransaction.getEmptyTransaction();
         temp.setBookId(bookId);
         temp.setMemberId(Integer.toString(memberId));
         System.out.println("The book is issued");
     }
     public void returnBook(){
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter the member id");
         int memberId=sc.nextInt();
         if(!arrMember.isPresent(memberId)){
             System.out.println("The member with given id is not exist");
             return ;
         }
         Member member=arrMember.getMember(memberId);
         // if(!member.isEligible()){
         //     System.out.println("The member has exhausted his limit");
         //     return ;
         // }
         System.out.println("Enter the book id");
         int bookId=sc.nextInt();
         if(!arrBook.isPresent(bookId)){
             System.out.println("The book with given id is not present");
             return ;
         }
         Book book=arrBook.getBook(bookId);
         // if(book.getAvailabe()<=0){
         //     System.out.println("The book is not available");
         //     return ;
         // }
         if(!arrTransaction.isPresent(Integer.toString(memberId), bookId)){
             System.out.println("The book is not issued to that member");
             return ;
         }
         Transaction temp=arrTransaction.getTransaction(Integer.toString(memberId), bookId);
         member.decrementBookIssued();
         book.decrementIssued();
         temp.setMemberId("xxxx");
         System.out.println("The book is returned");
     }
     public void displayTransactionList(){
         arrTransaction.display();
     }
 }
 
 public class Prob4{
     public static void main(String[] args) {
         Library library=new Library();
         Scanner sc=new Scanner(System.in);
         while(true){
             System.out.println("Choose:");
             System.out.println("1.Add book");
             System.out.println("2.Add more copies");
             System.out.println("3.Show all books");
             System.out.println("4.Show a book with given id");
             System.out.println("5.Add member");
             System.out.println("6.Show all member");
             System.out.println("7.Show detail of a member");
             System.out.println("8.Issue a book");
             System.out.println("9.Return a book");
             System.out.println("10.Set book limit of users");
             System.out.println("11.Display Transaction list");
             System.out.println("12.Exit");
             int t=sc.nextInt();
             if(t==1){
                 library.addBook();
             }
             else if(t==2){
                 library.addCopies();
             }
             else if(t==3){
                 library.showBookList();
             }
             else if(t==4){
                 library.showBook();
             }
             else if(t==5){
                 library.addMember();
             }
             else if(t==6){
                 library.showMemberList();
             }
             else if(t==7){
                 library.showMember();
             }
             else if(t==8){
                 library.issueBook();
             }
             else if(t==9){
                 library.returnBook();
             }
             else if (t==10){
                 System.out.println("Enter the book limit");
                 int l=sc.nextInt();
                 Member.setLimit(l);
             }
             else if(t==11){
                 library.displayTransactionList();
             }
             else {
                 break;
             }
         }
     }
 }

// public class Prob4 {
    
// }
