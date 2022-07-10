package Controller;

import Model.DiscS;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import static java.lang.Math.abs;


public class MainPageController extends Application implements Initializable {
    @FXML
    private TableView<DiscS> allDiscsTable;
    @FXML
    private Button showBtn;


    ArrayList<DiscS> discS =new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../View/main_page.fxml"));
        loader.load();
        primaryStage.setScene(new Scene((Parent) loader.getRoot()));
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<DiscS,String> diskNameCol = new TableColumn<>("Name");
        TableColumn<DiscS,Integer> diskXCol = new TableColumn<>("X");
        TableColumn<DiscS,Integer> diskHeadCol = new TableColumn<>("Head");
        TableColumn<DiscS,String> diskDirectionCol = new TableColumn<>("Direction");
        TableColumn<DiscS,String> diskAlgCol  = new TableColumn<>("Algorithm");
        TableColumn<DiscS,Integer> diskZCol  = new TableColumn<>("Z");
        TableColumn<DiscS,Integer> diskTimeCol  = new TableColumn<>("Time");
        TableColumn<DiscS,Integer> diskCylindersCol  = new TableColumn<>("Cylinders");

        diskNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        diskXCol.setCellValueFactory(new PropertyValueFactory<>("x"));
        diskHeadCol.setCellValueFactory(new PropertyValueFactory<>("y"));
        diskDirectionCol.setCellValueFactory(new PropertyValueFactory<>("direction"));
        diskAlgCol.setCellValueFactory(new PropertyValueFactory<>("method"));
        diskZCol.setCellValueFactory(new PropertyValueFactory<>("z"));
        diskTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        diskCylindersCol.setCellValueFactory(new PropertyValueFactory<>("cylinders"));

        allDiscsTable.getColumns().addAll(diskNameCol,diskXCol,diskHeadCol,diskZCol,diskDirectionCol,diskAlgCol,diskCylindersCol,diskTimeCol);

        readFile();

        showBtn.setOnAction(e->{
            for(DiscS ds :discS){
                allDiscsTable.getItems().add(ds);
            }
        });


    }
    private void readFile() {
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

    private void CreateDs(String line) {

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
        DiscS ds =new DiscS(name,x,y,direction,z,method, cylinders);

        //set method
        setMethod(method,ds);

    }

    private int Scan(DiscS ds) {
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
        int time = sum*ds.z;
        System.out.println("Time = "+time);
        System.out.println("=================================****===================================");
        return time;
    }
    private int CScan(DiscS ds){
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
        int time = sum*ds.z;
        System.out.println("Time = "+time);
        System.out.println("=================================****===================================");
        return time;
    }
    private int CLook(DiscS ds){
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
        int time = sum*ds.z;
        System.out.println("Time = "+time);
        System.out.println("=================================****===================================");
        return time;
    }
    private int Look(DiscS ds){
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
        int time = sum*ds.z;
        System.out.println("Time = "+time);
        System.out.println("=================================****===================================");
        return time;
    }
    private  void setMethod(String method, DiscS ds) {
        switch (method){
            case "Scan":{

                ds.setTime(Scan(ds));
                discS.add(ds);
                break;
            }
            case "C-scan":{
                ds.setTime(CScan(ds));
                discS.add(ds);
                break;
            }
            case "C-look":{
                ds.setTime(CLook(ds));
                discS.add(ds);
                break;
            }
            case "Look":{
                ds.setTime(Look(ds));
                discS.add(ds);
                break;
            }
        }
    }
}
