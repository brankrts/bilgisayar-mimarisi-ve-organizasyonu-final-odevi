package enums;

public enum Commands {

    AND("0"),
    ANDI("8"),
    ADD("1"),
    ADDI("9"),
    LDA("2"),
    LDAI("A"),
    STA("3"),
    STAI("B"),
    BUN("4"),
    BUNI("C"),
    BSA("5"),
    BSAI("D"),
    ISZ("6"),
    ISZI("E"),
    /*
     * 1000 1111 1100 0000
     * F
     */
    CLA("7800"),
    CLE("7400"),
    CMA("7200"),
    CME("7100"),
    CIR("7080"),
    CIL("7040"),
    INC("7020"),
    SPA("7010"),
    SNA("7008"),
    SZA("7004"),
    SZE("7002"),
    HLT("7001"),
    INP("F800"),
    OUT("F400"),
    SKI("F200"),
    SKO("F100"),
    ION("F080"),
    IOF("F040"),
    NOTFOUND("NOT_FOUND");

    private String commandHex;

    Commands(String commandHex) {
        this.commandHex = commandHex;
    }

    public String getCommandHex() {
        return this.commandHex;
    }

}
