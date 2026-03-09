package codelab.modul2;

public class codelab2 {

    static class Island {
        String name;
        Island next;

        public Island(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Island startIsland;
    private Island lastIsland;

    // Add Island
    public void addIsland(String name) {
        Island newIsland = new Island(name);

        if (startIsland == null) {
            startIsland = newIsland;
            lastIsland = newIsland;
        } else {
            lastIsland.next = newIsland;
            lastIsland = newIsland;
        }
    }


    public void busterCall(String keyName) {
        Island current = startIsland;
        Island prev = null;

        if (current != null && current.name.equals(keyName)) {
            startIsland = current.next;
            return;
        }

        while (current != null && !current.name.equals(keyName)) {
            prev = current;
            current = current.next;
        }

        if (current == null)
            return;

        prev.next = current.next;
    }


    public void printLogbook() {
        Island current = startIsland;
        System.out.print("Grand Line Route: ");
        while (current != null) {
            System.out.print(current.name + " -> ");
            current = current.next;
        }
        System.out.println("Laugh Tale (End)");
    }


    public boolean isIslandOnRoute(String keyName) {
        Island current = startIsland;
        while (current != null) {
            if (current.name.equals(keyName)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Count
    public int countIslands() {
        int count = 0;
        Island current = startIsland;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {

        codelab2 grandLine = new codelab2();

        grandLine.addIsland("Romance Dawn");
        grandLine.addIsland("Skypiea");
        grandLine.addIsland("Water 7");
        grandLine.addIsland("Wano Kuni");

        grandLine.printLogbook();

        System.out.println("Visited Fishman Island? "
                + grandLine.isIslandOnRoute("Fishman Island"));

        System.out.println("Buster Call initiated on Skypiea!");
        grandLine.busterCall("Skypiea");

        grandLine.printLogbook();

        System.out.println("Total Islands visited: "
                + grandLine.countIslands());
    }
}
