import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	Block ball = new Block(237, 435, 25, 25, "ball.png");
	Block paddle=new Block(175, 480, 150, 25, "paddle.png");
	
	Animate animate;
	
	BlockBreakerPanel(){
		
		for(int i=0; i<8; i++) 
			blocks.add(new Block((i*60),0,60,60,"blue.png"));
		//for(int i=0; i<8; i++) 
			//blocks.add(new Block(i*60));
		////for(int i=0; i<8; i++) 
			//blocks.add(new Block(i*60));
		//for(int i=0; i<8; i++) 
		//	blocks.add(new Block(i*60));
		
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		blocks.forEach(block -> {
			block.draw(g, this);
		});
		paddle.draw(g, this);
	}
	
	public void update() {
		repaint();
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			animate = new Animate(this);
			new Thread(( ) -> {
				while(true) {
					update();
					try {
						Thread.sleep(10);
					} catch(InterruptedException err) {
						err.printStackTrace();
					}
				}
			}).start();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x < (getWidth()-paddle.width)) {
			paddle.x += 15;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x > (getWidth()-paddle.width)) {
			paddle.x -= 15;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
