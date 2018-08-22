public class ArrayChar {
    private char[] data;

    public ArrayChar (String line) {
        this.data  = line.toCharArray();
    }

    public boolean startWith (String prefix) {
        boolean result = true;
        char[] pref = prefix.toCharArray();
        if (pref.length<=this.data.length) {
            for (int i=0; i<pref.length;i++) {
                if (this.data[i] != pref[i]) {
                    result = false;
                    break;
                }
            }
        }
        else {
            result = false;
        }
        return result;
    }
}
