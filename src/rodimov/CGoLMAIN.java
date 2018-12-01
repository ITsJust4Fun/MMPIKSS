package rodimov;

public class CGoLMAIN{
    public static void main(String[] args) {
        StartGame st = new StartGame();
        st.StartGUI();
        while (st.GUIisVisible()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        st.StartGame();
    }
}