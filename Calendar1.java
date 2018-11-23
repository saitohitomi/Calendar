import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calendar1 extends Application {
    
    LocalDate today;
    String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    Label lbl1;
    Button btn1, btn2;
    int year, month, start, day, i=0;
    GridPane grid = new GridPane();
    
    @Override
    public void start(Stage primaryStage) {
        //現在の日にちの取得
        today = LocalDate.now();
        //ヘッダー部
        btn1 = new Button("Previous");
        btn2 = new Button("   Next   ");        
        year = today.getYear();
        month = today.getMonthValue();
        lbl1 = new Label(String.valueOf(year)+"年"+String.valueOf(month)+"月");
        Pane sp1 = new Pane();
        Pane sp2 = new Pane();
        HBox h1 = new HBox(30,btn1,sp1,lbl1,sp2,btn2);
        HBox.setHgrow(sp1,Priority.ALWAYS);
        HBox.setHgrow(sp2,Priority.ALWAYS);
        //カレンダー部
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        //曜日の配置
        for(int j=0;j<7;j++){
            Label lbday = new Label(week[j]);
            GridPane.setConstraints(lbday,j,i);
            grid.getChildren().add(lbday);
        }
        //カレンダーの作成
        MakeCalendar(today);
        //配置
        VBox v1 = new VBox(30,h1,grid);
        VBox.setMargin(h1,new Insets(20));
        Scene scene = new Scene(v1, 400, 400);
        //ステージ
        primaryStage.setTitle("Calendar1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void MakeCalendar(LocalDate d){
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
        int length = d.lengthOfMonth();
        //日にちを入れる
        i=1;
        for(int j=start;j<7;j++){
            Label lbday = new Label(String.valueOf(day+1));
            GridPane.setConstraints(lbday,j,i);
            grid.getChildren().add(lbday);
            day++;
        }
        i++;
        while(day<length){
            for(int j=0;j<7;j++){
                Label lbday = new Label(String.valueOf(day+1));
                GridPane.setConstraints(lbday,j,i);
                grid.getChildren().add(lbday);
                day++;
                if(day>=length) break;
            }
            i++;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
