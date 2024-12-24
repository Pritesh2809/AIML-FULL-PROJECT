public class Notes {
    private int count2000;
    private int count500;
    private int count200;
    private int count100;

    public Notes() {
        this.count2000 = 0;
        this.count500 = 0;
        this.count200 = 0;
        this.count100 = 0;
    }

    public int getCount2000() {
        return count2000;
    }

    public int getCount500() {
        return count500;
    }

    public int getCount200() {
        return count200;
    }

    public int getCount100() {
        return count100;
    }

    public void addNotes(int notes2000, int notes500, int notes200, int notes100) {
        this.count2000 += notes2000;
        this.count500 += notes500;
        this.count200 += notes200;
        this.count100 += notes100;
    }

    public boolean withdrawNotes(double amount) {
        int required2000 = 0, required500 = 0, required200 = 0, required100 = 0;

        while (amount >= 2000 && count2000 > 0) {
            amount -= 2000;
            count2000--;
            required2000++;
        }
        while (amount >= 500 && count500 > 0) {
            amount -= 500;
            count500--;
            required500++;
        }
        while (amount >= 200 && count200 > 0) {
            amount -= 200;
            count200--;
            required200++;
        }
        while (amount >= 100 && count100 > 0) {
            amount -= 100;
            count100--;
            required100++;
        }

        if (amount == 0) {
            System.out.println("Withdraw successful. Notes dispensed: " + required2000 + " x 2000 Rs, " + required500 + " x 500 Rs, " + required200 + " x 200 Rs, " + required100 + " x 100 Rs");
            return true;
        } else {
            addNotes(required2000, required500, required200, required100);
            return false;
        }
    }
}
