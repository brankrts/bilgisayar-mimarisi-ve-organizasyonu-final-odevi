package registers;

import java.util.ArrayList;

import circuitelements.Decoder4X16;
import circuitelements.Signal;
import utils.Util;

public class SC {
    private ArrayList<Integer> currentBits;
    private Util util;
    private Decoder4X16 decoder4x16;
    private Signal currentSignal;
    static SC _instance;

    public static SC instance() {

        if (SC._instance == null) {
            return new SC();
        }
        return _instance;
    }

    public SC() {
        this.util = Util.instance();
        this.decoder4x16 = new Decoder4X16();
        this.currentBits = new ArrayList<Integer>() {
            {
                add(0);
                add(0);
                add(0);
                add(0);
            }
        };
        this.currentSignal = this.decoder4x16.decode(currentBits);
    }

    public void incrementSC() {
        util.incrementBinary(this.currentBits);
        this.currentSignal = this.decoder4x16.decode(currentBits);
    }

    public ArrayList<Integer> getCurrentBits() {
        return this.currentBits;
    }

    public Signal getSignal() {
        return this.currentSignal;
    }

}
