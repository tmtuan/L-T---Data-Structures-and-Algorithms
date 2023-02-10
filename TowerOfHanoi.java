/* This program generates solutions to N-disks in Tower of Hanoi  */
/* References: https://www.youtube.com/watch?v=8lhxIOAfDss */

public class TowerOfHanoi {

    // Move a disk from src to des
    public static void moveDisk(String src, String des) {
        System.out.println("Move a disk from " + src + " to " + des + ".");
    }

    // Move numDisks from src, via mid, to des
    public static void towerOfHanoi(int numDisks, String src, String mid, String des) {
        if(numDisks == 0) return;
        else {
            towerOfHanoi(numDisks-1, src, des, mid);
            moveDisk(src, des);
            towerOfHanoi(numDisks-1, mid, des, src);
        }
    }
    public static void main(String[] args) {

        towerOfHanoi(4, "A", "B", "C");
    }
}
