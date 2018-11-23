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
    
    Stage stage;
    LocalDate d;
    String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    Label lbl;
    Button btnpre, btnnext;
    int year, month, start, day, i=0;
    Pane sp1 = new Pane(),sp2 = new Pane();
    HBox h1;
    GridPane grid = new GridPane();
    Scene scene;
    
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
            GridPane.setConstraints(lbday,j,i);
            grid.getChildren().add(lbday);
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
        int length = d.lengthOfMonth();
        //日にちを入れる
        i=1;
        day=0;
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
    
    void Set(){
        //ヘッダー部
        h1 = new HBox(30,btnpre,sp1,lbl,sp2,btnnext);
        HBox.setHgrow(sp1,Priority.ALWAYS);
        HBox.setHgrow(sp2,Priority.ALWAYS);
        //配置
        VBox v1 = new VBox(30,h1,grid);
        VBox.setMargin(h1,new Insets(20));
        scene = new Scene(v1, 400, 400);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
