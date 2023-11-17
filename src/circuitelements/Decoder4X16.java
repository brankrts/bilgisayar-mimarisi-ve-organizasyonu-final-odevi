package circuitelements;

import utils.Util;
import java.util.ArrayList;

public class Decoder4X16 {

    int s3, s2, s1, s0;
    Signal D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15;
    Util util;

    public Decoder4X16() {
        this.util = Util.instance();
    }

    public Signal decode(ArrayList<Integer> selectors) {

        s0 = selectors.get(3);
        s1 = selectors.get(2);
        s2 = selectors.get(1);
        s3 = selectors.get(0);

        D0 = new Signal("D0", util.complement(s3) & util.complement(s2) & util.complement(s1) & util.complement(s0));
        D1 = new Signal("D1", util.complement(s3) & util.complement(s2) & util.complement(s1) & s0);
        D2 = new Signal("D2", util.complement(s3) & util.complement(s2) & s1 & util.complement(s0));
        D3 = new Signal("D3", util.complement(s3) & util.complement(s2) & s1 & s0);
        D4 = new Signal("D4", util.complement(s3) & s2 & util.complement(s1) & util.complement(s0));
        D5 = new Signal("D5", util.complement(s3) & s2 & util.complement(s1) & s0);
        D6 = new Signal("D6", util.complement(s3) & s2 & s1 & util.complement(s0));
        D7 = new Signal("D7", util.complement(s3) & s2 & s1 & s0);
        D8 = new Signal("D8", s3 & util.complement(s2) & util.complement(s1) & util.complement(s0));
        D9 = new Signal("D9", s3 & util.complement(s2) & util.complement(s1) & s0);
        D10 = new Signal("D10", s3 & util.complement(s2) & s1 & util.complement(s0));
        D11 = new Signal("D11", s3 & util.complement(s2) & s1 & s0);
        D12 = new Signal("D12", s3 & s2 & util.complement(s1) & util.complement(s0));
        D13 = new Signal("D13", s3 & s2 & util.complement(s1) & s0);
        D14 = new Signal("D14", s3 & s2 & s1 & util.complement(s0));
        D15 = new Signal("D15", s3 & s2 & s1 & s0);

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
                add(D8);
                add(D9);
                add(D10);
                add(D11);
                add(D12);
                add(D13);
                add(D14);
                add(D15);

            }
        };
        for (Signal signal : signals) {
            if (signal.getCurrentValue() == 1)
                return signal;
        }

        return null;
    }

}
