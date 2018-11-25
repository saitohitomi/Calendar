import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Calendar1 extends Application {
    
    Stage stage;
    Scene scene;
    LocalDate d;
    String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    Label lbl;
    Button btnpre, btnnext,btnok;
    int year, month, start, length, day, i=0,count;
    //色変え
    ComboBox<String> changeday;
    String[] colors ={"RED","BLUE","BLACK"};
    ComboBox<String> color;
    
    Pane sp1 = new Pane(),sp2 = new Pane();
    GridPane grid = new GridPane();
    HBox h1,h2;
    
    @Override
    public void start(Stage stage) {
        //現在の日にちの取得
        d = LocalDate.now();
        //カレンダーの作成
        MakeCalendar(d);
        //ボタンの作成
        btnpre = new Button("Previous");
        btnnext = new Button("   Next   ");
        Set();
        //ボタンアクションの設定
        btnpre.setOnAction(event ->{
            d = d.minusMonths(1);
            MakeCalendar(d);
            Set();
            stage.setScene(scene);
        });
        btnnext.setOnAction(event ->{
            d = d.plusMonths(1);
            MakeCalendar(d);
            Set();
            stage.setScene(scene);
        });
        //ステージ
        stage.setTitle("Calendar1");
        stage.setScene(scene);
        stage.show();
    }
    
    void MakeCalendar(LocalDate d){
        //年月
        year = d.getYear();
        month = d.getMonthValue();
        lbl = new Label(String.valueOf(year)+"年"+String.valueOf(month)+"月");
        //カレンダー部
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        //曜日の配置
        i=0;
        for(int j=0;j<7;j++){
            Label lbday = new Label(week[j]);
            grid.add(lbday,j,i);
        }
        //月初めの曜日を取得
        DayOfWeek firstday = d.withDayOfMonth(1).getDayOfWeek();
        switch(firstday){
            case SUNDAY:
                start = 0; break;
            case MONDAY:
                start = 1; break;
            case TUESDAY:
                start = 2; break;
            case WEDNESDAY:
                start = 3; break;
            case THURSDAY:
                start = 4; break;
            case FRIDAY:
                start = 5; break;
            case SATURDAY:
                start = 6; break;
        }
        //その月の日数を取得
        length = d.lengthOfMonth();
        //日にちを入れる
        i=1;
        day=0;
        for(int j=start;j<7;j++){
            Label lbday = new Label(String.valueOf(day+1));
            grid.add(lbday,j,i);
            day++;
        }
        i++;
        while(day<length){
            for(int j=0;j<7;j++){
                Label lbday = new Label(String.valueOf(day+1));
                grid.add(lbday,j,i);
                day++;
                if(day>=length) break;
            }
            i++;
        }
    }
    
    void Set(){
        //上部
        h1 = new HBox(30,btnpre,sp1,lbl,sp2,btnnext);
        HBox.setHgrow(sp1,Priority.ALWAYS);
        HBox.setHgrow(sp2,Priority.ALWAYS);
        //下部
        //日付・色選択
        changeday = new ComboBox<>();
        for(int n=1;n<length+1;n++){
            changeday.getItems().add(String.valueOf(n));
        }
        color = new ComboBox<>();
        color.getItems().addAll(colors);
        btnok = new Button("OK");
        btnok.setOnAction(event ->{
            int change = Integer.parseInt(changeday.getValue());
            ChangeColor(change,color.getValue());
        });
        h2 = new HBox(30,changeday,color,btnok);
        //配置
        VBox v1 = new VBox(30,h1,grid,h2);
        VBox.setMargin(h1,new Insets(20));
        VBox.setMargin(h2,new Insets(20));
        scene = new Scene(v1);
    }
    
    //文字色変更
    void ChangeColor(int cday, String color){
        int x,y;//指定の日にちの座標
        if(cday<=(7-start)){
            x = start+cday-1;
            y = 1;
        } else{
            x = (cday-start)%7;
            y = ((cday-start)/7)+2;
        }
        switch(color){
            case "RED":
                for(int k=7;k<length;k++){
                    if(grid.getChildren().get(k).toString()==String.valueOf(cday)){
                        grid.getChildren().remove(k);
                        break;
                    }
                }
                Label clbl1 = new Label(String.valueOf(cday));
                clbl1.setTextFill(Color.RED);
                grid.add(clbl1,x,y);
                break;
            case "BLUE":
                for(int k=7;k<length;k++){
                    if(grid.getChildren().get(k).toString()==String.valueOf(cday)){
                        grid.getChildren().remove(k);
                        break;
                    }
                }
                Label clbl2 = new Label(String.valueOf(cday));
                clbl2.setTextFill(Color.BLUE);
                grid.add(clbl2,x,y);
                break;
            case "BLACK":
                for(int k=7;k<length;k++){
                    if(grid.getChildren().get(k).toString()==String.valueOf(cday)){
                        grid.getChildren().remove(k);
                        break;
                    }
                }
                Label clbl3 = new Label(String.valueOf(cday));
                clbl3.setTextFill(Color.BLACK);
                grid.add(clbl3,x,y);
                break;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
