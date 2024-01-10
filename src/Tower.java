import java.util.ArrayList;
import java.util.Scanner;

public class Tower {

    // 0 represents empty
    // other numbers represents order (bigger the number bigger the disk)

    final int height; // maximum number of disks or A.size()
    final ArrayList<Integer> A = new ArrayList<>();
    final ArrayList<Integer> B = new ArrayList<>();
    final ArrayList<Integer> C = new ArrayList<>();

    public Tower(int height) {
        this.height = height;
        for (int i = 0; i < height; i++) {
            A.add(height-i);
            B.add(0);
            C.add(0);
        }
    }


    /**
     * @param tower A, B, C
     * @return index based floor position of the smallest disk at the tower
     */
    private int getSmallestDiskIndex(ArrayList<Integer> tower) {
        int index = height - 1;
        while(tower.get(index) == 0 && index > 0)
            index--;

        return index;
    }

    /**
     * @param tower A, B, C towers
     * @return Get first empty floor's index based position
     */
    private int getFirstEmptyFloorIndex(ArrayList<Integer> tower) {
        int index = 0;

        while(tower.get(index) != 0 && index < height)
            index++;

        return index;
    }


    /**
     * Move 1 disk from one tower to another
     * @param from_tower move disk from this tower
     * @param to_tower to this tower
     */
    private void moveDisk(ArrayList<Integer> from_tower, ArrayList<Integer> to_tower) {
        // find which disk do we need to move and where do we need to move
        int from_index = getSmallestDiskIndex(from_tower);
        int to_index = getFirstEmptyFloorIndex(to_tower);

        // put disk from "from_tower" to "to_tower" && set "from_tower"s floor to empty
        to_tower.set(to_index, from_tower.get(from_index));
        from_tower.set(from_index, 0);
    }


    /**
     * Move n disks from one tower to another
     * @param n number of disks to move
     * @param from_tower move disk from this tower
     * @param to_tower to this tower
     * @param aux_tower this is other tower
     */
    private void move(int n, ArrayList<Integer> from_tower, ArrayList<Integer> to_tower, ArrayList<Integer> aux_tower) {
        if (n == 0)
            return;

        char fromChar = from_tower.equals(A) ? 'A' : (from_tower.equals(B) ? 'B' : 'C');
        char toChar = to_tower.equals(A) ? 'A' : (to_tower.equals(B) ? 'B' : 'C');


        move(n-1, from_tower, aux_tower, to_tower);

        moveDisk(from_tower, to_tower);

        System.out.println("Move disk " + " from rod "  + fromChar + " to rod " + toChar);
        System.out.println(this);
        System.out.println();

        move(n-1, aux_tower, to_tower, from_tower);
    }

    /**
     * Solve Tower of Hanoi step by step
     */
    public void solve() {
        move(height, A, C, B);
    }

    /**
     * @param floorNumber index number for tower (ArrayList)
     * @param tower the towers A, B or C
     * @return the string representation of a tower's floor
     */
    private String getFloor(int floorNumber, ArrayList<Integer> tower) {
        int numDisk = tower.get(floorNumber);
        int numSpaces = height - numDisk;

        String spaces = new String(new char[numSpaces]).replace("\0", " ");
        String disk = new String(new char[numDisk]).replace("\0", "=");
        String floor = spaces + disk + "|" + disk + spaces;

        return floor;
    }

    /**
     * @param floorNumber index number for tower (ArrayList)
     * @return combination of 3 towers specific floor's string representation
     */
    private String getFloor(int floorNumber) {
        String floorA = getFloor(floorNumber, A);
        String floorB = getFloor(floorNumber, B);
        String floorC = getFloor(floorNumber, C);

        return floorA + " " + floorB + " " + floorC;
    }

    @Override
    public String toString() {
        String spaces = new String(new char[height]).replace("\0", " ");
        StringBuilder tower = new StringBuilder(spaces + "A" + spaces + " " + spaces + "B" + spaces + " " + spaces + "C" + spaces + "\n");

        for (int i = height-1; i >= 0; i--) {
            String floor = getFloor(i);
            tower.append(floor);
            tower.append('\n');
        }

        return tower.toString();
    }
}
