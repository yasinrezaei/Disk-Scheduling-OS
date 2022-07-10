import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;

public class main {

    public static void main(String[] args){

        readFile();

    }

    private static void readFile() {
        try
        {
            FileReader fr = null;
            BufferedReader br = null;
            fr = new FileReader("C:\\Users\\Yasin\\Desktop\\os_project\\untitled\\src\\data.csv");
            br = new BufferedReader(fr);
            String line = null;
            ArrayList<Object> myList = new ArrayList<Object>();
            line = br.readLine();
            while(line!=null)
            {
                myList.add(line);
                line = br.readLine();
            }
            for(int i=0; i<myList.size(); i++)
            {
                CreateDs(String.valueOf(myList.get(i)));
            }
        }
        catch(IOException ioex)
        {
            System.out.print(ioex);
        }
    }

    private static void CreateDs(String line) {

        //create DS object
        String[] tokens = line.split("\t");
        String name = tokens[0];
        int x = Integer.valueOf(tokens[1]);
        int y=Integer.valueOf(tokens[2]);
        String direction =tokens[3];
        int z =Integer.valueOf(tokens[4]);
        String method = tokens[5];
        ArrayList<Integer> cylinders = new ArrayList<>();
        for(int i=6;i<tokens.length;i++){
            cylinders.add(Integer.valueOf(tokens[i]));
        }
        Collections.sort(cylinders);
        DS ds =new DS(name,x,y,direction,z,method, cylinders);

        //set method
        setMethod(method,ds);

    }

    private static void Scan(DS ds) {
        System.out.println(ds.toString());
        int start_index = 0;
        ds.cylinders.add(0,0);
        ds.cylinders.add(ds.x-1);
        for(int i=0;i<ds.cylinders.size();i++){
            if(ds.cylinders.get(i)>=ds.y){
                start_index = i;
                break;
            }
        }
        int sum = 0;
        int head = ds.y;
        if(ds.direction.equals("UP")){
            
            //go to end
            for(int i=start_index;i<ds.cylinders.size();i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            //from end to start
            head = ds.cylinders.get(start_index-1);//head move to startindex-1
            for(int i = start_index-1; i >= 0; i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
        }
        else{
            //go to start
            for(int i = start_index-1; i >= 0; i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            //from end to start
            for(int i=start_index;i<ds.cylinders.size();i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
        }
        System.out.println("Time = "+sum*ds.z);
        System.out.println("=================================****===================================");
    }
    private static void CScan(DS ds){
        System.out.println(ds.toString());
        ds.cylinders.add(0,0);
        ds.cylinders.add(ds.x-1);
        int start_index = 0;
        int sum = 0;
        int head = ds.y;

        if(ds.direction.equals("UP")){
            //start index
            for(int i=0;i<ds.cylinders.size();i++){
                if(ds.cylinders.get(i)>=ds.y){
                    start_index = i;
                    break;
                }
            }
            //go to end
            for(int i=start_index;i<ds.cylinders.size();i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            head = ds.cylinders.get(0);
            //go to first then go to index-1
            for(int i=0;i<start_index;i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }

        }
        else{
            //start index
            for(int i=0;i<ds.cylinders.size();i++){
                if(ds.cylinders.get(i)>=ds.y){
                    start_index = i-1;
                    break;
                }
            }
            //go to first
            for(int i=start_index;i>=0;i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            head = ds.cylinders.get(ds.cylinders.size()-1);
            //go to end then go to first
            for(int i=ds.cylinders.size()-1;i>start_index;i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
        }
        System.out.println("Time = "+sum*ds.z);
        System.out.println("=================================****===================================");
    }
    private static void CLook(DS ds){
        //System.out.println(ds.toString());
        System.out.println(ds.toString());
        int start_index = 0;
        int sum = 0;
        int head = ds.y;

        if(ds.direction.equals("UP")){
            //start index
            for(int i=0;i<ds.cylinders.size();i++){
                if(ds.cylinders.get(i)>=ds.y){
                    start_index = i;
                    break;
                }
            }
            //go to end
            for(int i=start_index;i<ds.cylinders.size();i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            head = ds.cylinders.get(0);
            //go to first then go to index-1
            for(int i=0;i<start_index;i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }

        }
        else{
            //start index
            for(int i=0;i<ds.cylinders.size();i++){
                if(ds.cylinders.get(i)>=ds.y){
                    start_index = i-1;
                    break;
                }
            }
            //go to first
            for(int i=start_index;i>=0;i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            head = ds.cylinders.get(ds.cylinders.size()-1);
            //go to end then go to first
            for(int i=ds.cylinders.size()-1;i>start_index;i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
        }
        System.out.println("Time = "+sum*ds.z);
        System.out.println("=================================****===================================");
    }
    private static void Look(DS ds){
        System.out.println(ds.toString());
        int start_index = 0;
        for(int i=0;i<ds.cylinders.size();i++){
            if(ds.cylinders.get(i)>=ds.y){
                start_index = i;
                break;
            }
        }
        int sum = 0;
        int head = ds.y;
        if(ds.direction.equals("UP")){


            //go to end

            for(int i=start_index;i<ds.cylinders.size();i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            //from end to start
            for(int i = start_index-1; i >= 0; i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
        }
        else{
            //go to start
            for(int i = start_index-1; i >= 0; i--){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
            //from end to start
            for(int i=start_index;i<ds.cylinders.size();i++){
                sum=sum+abs(ds.cylinders.get(i)-head);
                head =ds.cylinders.get(i);
                System.out.print(ds.cylinders.get(i)+"->");
            }
        }
        System.out.println("Time = "+sum*ds.z);
        System.out.println("=================================****===================================");
    }
    private static void setMethod(String method,DS ds) {
        switch (method){
            case "Scan":{
                Scan(ds);
                break;
            }
            case "C-scan":{
                CScan(ds);
                break;
            }
            case "C-look":{
                CLook(ds);
                break;
            }
            case "Look":{
                Look(ds);
                System.out.println("look");
                break;
            }
        }
    }
}
