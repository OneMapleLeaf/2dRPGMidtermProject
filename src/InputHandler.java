import java.awt.event.*;

public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {
    private int x, y;
    private boolean W, A, S, D, E, SPACE, One, Two, Three, Four, Zero;
    private boolean IsPressed, IsClicked;
    private boolean ClikedP;

    // Key Listener

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> W = true;
            case KeyEvent.VK_A -> A = true;
            case KeyEvent.VK_S -> S = true;
            case KeyEvent.VK_D -> D = true;
            case KeyEvent.VK_E -> E = true;
            case KeyEvent.VK_SPACE -> SPACE = true;
            case KeyEvent.VK_0 -> Zero = true;
            case KeyEvent.VK_1 -> One = true;
            case KeyEvent.VK_2 -> Two = true;
            case KeyEvent.VK_3 -> Three = true;
            case KeyEvent.VK_4 -> Four = true;
            case KeyEvent.VK_P -> ClikedP = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> W = false;
            case KeyEvent.VK_A -> A = false;
            case KeyEvent.VK_S -> S = false;
            case KeyEvent.VK_D -> D = false;
            case KeyEvent.VK_E -> E = false;
            case KeyEvent.VK_SPACE -> SPACE = false;
            case KeyEvent.VK_0 -> Zero = false;
            case KeyEvent.VK_1 -> One = false;
            case KeyEvent.VK_2 -> Two = false;
            case KeyEvent.VK_3 -> Three = false;
            case KeyEvent.VK_4 -> Four = false;
        }
    }

    // Mouse Listener

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        IsPressed = true;
        IsClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        IsPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Mouse Motion Listener

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
    public Boolean One(){
        return One;
    }
    public Boolean Two(){
        return Two;
    }
    public Boolean Three(){
        return Three;
    }
    public Boolean Four(){
        return Four;
    }
    public Boolean Zero(){
        return Zero;
    }
    public Boolean W(){
        return W;
    }
    public Boolean A(){
        return A;
    }
    public Boolean S(){
        return S;
    }
    public Boolean D(){
        return D;
    }
    public Boolean E() {return E;}
    public Boolean SPACE(){
        return SPACE;
    }
    public Boolean Pressed(){
        return IsPressed;
    }
    public Boolean CLicked(){
        if(IsClicked){
            IsClicked = false;
            return true;
        }
         return false;
    }
    public Boolean ClickedP(){
        if(ClikedP){
            ClikedP = false;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
