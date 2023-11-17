package circuitelements;

import java.util.ArrayList;

import utils.Util;

public class Decoder3X8 {

    int s2, s1, s0;
    Signal D0, D1, D2, D3, D4, D5, D6, D7;
    Util util;

    public Decoder3X8() {
        this.util = Util.instance();
    }

    public Signal decode(ArrayList<Integer> selectors) {

        s0 = selectors.get(2);
        s1 = selectors.get(1);
        s2 = selectors.get(0);

        D0 = new Signal("D0", util.complement(s2) & util.complement(s1) & util.complement(s0));
        // D0 = util.complement(s2) & util.complement(s1) & util.complement(s0);
        D1 = new Signal("D1", util.complement(s2) & util.complement(s1) & s0);
        D2 = new Signal("D2", util.complement(s2) & s1 & util.complement(s0));
        D3 = new Signal("D3", util.complement(s2) & s1 & s0);
        D4 = new Signal("D4", s2 & util.complement(s1) & util.complement(s0));
        D5 = new Signal("D5", s2 & util.complement(s1) & s0);
        D6 = new Signal("D6", s2 & s1 & util.complement(s0));
        D7 = new Signal("D7", s2 & s1 & s0);

        ArrayList<Signal> signals = new ArrayList<Signal>() {
            {
                add(D0);
                add(D1);
                add(D2);
                add(D3);
                add(D4);
                add(D5);
                add(D6);
                add(D7);

            }
        };
        for (Signal signal : signals) {
            if (signal.getCurrentValue() == 1)
                return signal;
        }

        return null;

    }

}
