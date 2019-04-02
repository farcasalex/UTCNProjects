//Created by Farcas Alexandru
//UTCN 2019
//30/03/2019
package thread;

import javafx.scene.control.Button;

public class VisualThread extends Thread implements Runnable{

    private int numberOfQueues;
    private Button[] firstQueue;
    private int firstQueueSize;
    private Button[] secondQueue;
    private int secondQueueSize;
    private Button[] thirdQueue;
    private int thirdQueueSize;
    private Button[] fourthQueue;
    private int fourthQueueSize;
    private Button[] fifthQueue;
    private int fifthQueueSize;
    private boolean stop;

    public VisualThread(int numberOfQueues, Button[] firstQueue, int firstQueueSize, Button[] secondQueue,
                        int secondQueueSize, Button[] thirdQueue, int thirdQueueSize, Button[] fourthQueue,
                        int fourthQueueSize, Button[] fifthQueue, int fifthQueueSize) {
        this.numberOfQueues = numberOfQueues;
        this.firstQueue = firstQueue;
        this.firstQueueSize = firstQueueSize;
        this.secondQueue = secondQueue;
        this.secondQueueSize = secondQueueSize;
        this.thirdQueue = thirdQueue;
        this.thirdQueueSize = thirdQueueSize;
        this.fourthQueue = fourthQueue;
        this.fourthQueueSize = fourthQueueSize;
        this.fifthQueue = fifthQueue;
        this.fifthQueueSize = fifthQueueSize;
    }

    public void stopThread(){
        this.stop = true;
    }

    public void update( int firstQueueSize, int secondQueueSize, int thirdQueueSize, int fourthQueueSize, int fifthQueueSize){
        this.firstQueueSize= firstQueueSize;
        this.secondQueueSize = secondQueueSize;
        this.thirdQueueSize = thirdQueueSize;
        this.fourthQueueSize = fourthQueueSize;
        this.fifthQueueSize = fifthQueueSize;
    }

    @Override
    public void run() {
        while (stop != true){
            switch (numberOfQueues){
                case 1:{
                    for(int i = 0; i < thirdQueueSize; i++)
                        thirdQueue[i].setVisible(true);
                    for(int i = thirdQueueSize; i < thirdQueue.length; i++)
                        thirdQueue[i].setVisible(false);
                }
                case 2:{
                    for(int i = 0; i < secondQueueSize; i++)
                        secondQueue[i].setVisible(true);
                    for(int i = 0; i < fourthQueueSize; i++)
                        fourthQueue[i].setVisible(true);
                    for(int i = secondQueueSize; i < secondQueue.length; i++)
                        secondQueue[i].setVisible(false);
                    for(int i = fourthQueueSize; i < fourthQueue.length; i++)
                        fourthQueue[i].setVisible(false);
                }
                case 3:{
                    for(int i = 0; i < firstQueueSize; i++)
                        firstQueue[i].setVisible(true);
                    for(int i = 0; i < secondQueueSize; i++)
                        secondQueue[i].setVisible(true);
                    for(int i = 0; i < thirdQueueSize; i++)
                        thirdQueue[i].setVisible(true);
                    for(int i = firstQueueSize; i < firstQueue.length; i++)
                        firstQueue[i].setVisible(false);
                    for(int i = secondQueueSize; i < secondQueue.length; i++)
                        secondQueue[i].setVisible(false);
                    for(int i = thirdQueueSize; i < thirdQueue.length; i++)
                        thirdQueue[i].setVisible(false);
                }
                case 4:{
                    for(int i = 0; i < firstQueueSize; i++)
                        firstQueue[i].setVisible(true);
                    for(int i = 0; i < secondQueueSize; i++)
                        secondQueue[i].setVisible(true);
                    for(int i = 0; i < thirdQueueSize; i++)
                        thirdQueue[i].setVisible(true);
                    for(int i = 0; i < fourthQueueSize; i++)
                        fourthQueue[i].setVisible(true);
                    for(int i = firstQueueSize; i < firstQueue.length; i++)
                        firstQueue[i].setVisible(false);
                    for(int i = secondQueueSize; i < secondQueue.length; i++)
                        secondQueue[i].setVisible(false);
                    for(int i = thirdQueueSize; i < thirdQueue.length; i++)
                        thirdQueue[i].setVisible(false);
                    for(int i = fourthQueueSize; i < fourthQueue.length; i++)
                        fourthQueue[i].setVisible(false);
                }
                case 5:{
                    for(int i = 0; i < firstQueueSize; i++)
                        firstQueue[i].setVisible(true);
                    for(int i = 0; i < secondQueueSize; i++)
                        secondQueue[i].setVisible(true);
                    for(int i = 0; i < thirdQueueSize; i++)
                        thirdQueue[i].setVisible(true);
                    for(int i = 0; i < fourthQueueSize; i++)
                        fourthQueue[i].setVisible(true);
                    for(int i = 0; i < fifthQueueSize; i++)
                        fifthQueue[i].setVisible(true);
                    for(int i = firstQueueSize; i < firstQueue.length; i++)
                        firstQueue[i].setVisible(false);
                    for(int i = secondQueueSize; i < secondQueue.length; i++)
                        secondQueue[i].setVisible(false);
                    for(int i = thirdQueueSize; i < thirdQueue.length; i++)
                        thirdQueue[i].setVisible(false);
                    for(int i = fourthQueueSize; i < fourthQueue.length; i++)
                        fourthQueue[i].setVisible(false);
                    for(int i = fifthQueueSize; i < fifthQueue.length; i++)
                        fifthQueue[i].setVisible(false);
                }
            }
        }
        this.stop = false;
    }
}
