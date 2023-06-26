package animations;

import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(TextField node){
        tt = new TranslateTransition(Duration.millis(150), (javafx.scene.Node) node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(5);
        tt.setAutoReverse(true);
    }

    public void playAnim(){
        tt.playFromStart();
    }
}
